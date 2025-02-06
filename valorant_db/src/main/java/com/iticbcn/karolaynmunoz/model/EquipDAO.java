package com.iticbcn.karolaynmunoz.model;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EquipDAO {
    private SessionFactory sessionFactory;
    
    public EquipDAO (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearEquip (int taula, SessionFactory sesion, Equip equip) throws IOException {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                session.persist(equip);
                session.getTransaction().commit();

            } catch (HibernateException e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.err.println("Error en Hibernate: " + e.getMessage()); 
                }
            } catch (Exception e) {
                if (session.getTransaction()  != null) {
                    session.getTransaction().rollback();
                    System.err.println("Error inesperado: " + e.getMessage());
                }
            }
        }
    }
    
}

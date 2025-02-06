package com.iticbcn.karolaynmunoz.model;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PersonatgeDAO {
    private SessionFactory sessionFactory;

    public PersonatgeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;    
    }

    public void crearPersonatge (SessionFactory sesion, Personatge personatge) throws IOException {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                session.persist(personatge);
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

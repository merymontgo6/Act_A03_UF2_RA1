package com.iticbcn.karolaynmunoz.model;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PartidaDAO {
    private SessionFactory sessionFactory;

    public PartidaDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;    
    }
    
    public void crearPartida (int taula, SessionFactory sesion, Partida partida) throws IOException {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                session.persist(partida);
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

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
    
    public void crearPartida (SessionFactory sesion, Partida partida) throws IOException {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                for (Equip equip : partida.getEquips()) {
                    Equip persistentEquip = session.get(Equip.class, equip.getId_equip());
                    if (persistentEquip == null) {
                        System.out.println("No s'ha trobat cap equip amb l'ID proporcionat.");
                        return;
                    }
                    equip.setPartidas(persistentEquip.getPartides());
                    equip.getPartides().add(partida);
                }

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

    public void readPartida(SessionFactory sesion, int id) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                Partida partida = session.find(Partida.class, id);
                if (partida != null) {
                    String message = "==================";
                    System.out.println(message);
                    System.out.println(partida);
                    System.out.println(message);
                } else {
                    System.out.println("Partida amb ID " + id + " no trobada.");
                }
            } catch (HibernateException e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.err.println("Error en Hibernate: " + e.getMessage());
                }
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.err.println("Error inesperado: " + e.getMessage());
                }
            }
        }
    }
}

package com.iticbcn.karolaynmunoz.DAO;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.iticbcn.karolaynmunoz.model.Equip;
import com.iticbcn.karolaynmunoz.model.Personatge;

public class EquipDAO {
    private SessionFactory sessionFactory;
    
    public EquipDAO (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearEquip (SessionFactory sesion, Equip equip) throws IOException {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                // Assegurar que el personatge amb l'ID existeix a la base de dades
                Personatge personatge = session.get(Personatge.class, equip.getId_Personatge().getId());

                if (personatge == null) {
                    System.out.println("No s'ha trobat cap personatge amb l'ID proporcionat.");
                    return;
                }

                equip.setIdPersonatge(personatge);

                // Persistir l'equip
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

    public void readEquip(SessionFactory sesion, int id) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                // Llegir equip per FIND
                Equip equip = session.find(Equip.class, id);
                if (equip != null) {
                    String message = "==================";
                    System.out.println(message);
                    System.out.println(equip);
                    System.out.println(message);
                } else {
                    System.out.println("Equip amb ID " + id + " no trobat.");
                }
                session.getTransaction().commit();
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

package com.iticbcn.karolaynmunoz;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        Session session = null;
        try {
            SessionFactory sesion = HibernateUtil.getSessionFactory(); //crea instancia de la sessio

            session = sesion.openSession();

            session.beginTransaction();

            //Professor p1 = new Professor("Antonio Talens"); 
            //Professor p2 = new Professor("Roger Sobrino");
        
            //Moduls m1 = new Moduls("Acces a Dades"); 
            //Moduls m2 = new Moduls("Programacio"); 
            //Moduls m3 = new Moduls("Sistemes");

            //p1.addModul(m2); p1.addModul(m1); // afegim mòduls a p1
            //p2.addModul(m3); p2.addModul(m1); // afegim mòduls a p2

            //desem (professors i mòduls en cascada)
            //session.persist(p1);
            //session.persist(p2);

            //commit y cierre de sesion
            session.getTransaction().commit();
            //session.close(); esto no se hace ya que se cierra en el finally

        } catch (HibernateException e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            System.err.println("Error en Hibernate: " + e.getMessage());
        } catch (Exception e) {
            if (session.getTransaction()  != null) session.getTransaction().rollback();
            System.err.println("Error inesperado: " + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }
}
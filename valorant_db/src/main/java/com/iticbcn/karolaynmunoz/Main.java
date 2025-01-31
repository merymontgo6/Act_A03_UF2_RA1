package com.iticbcn.karolaynmunoz;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.iticbcn.karolaynmunoz.model.Equip;
import com.iticbcn.karolaynmunoz.model.Partida;
import com.iticbcn.karolaynmunoz.model.Personatge;
import com.iticbcn.karolaynmunoz.model.Rol;

public class Main {
    public static void main(String[] args) {
        Session session = null;
        try {
            SessionFactory sesion = HibernateUtil.getSessionFactory(); //crea instancia de la sessio

            session = sesion.openSession();
            session.beginTransaction();

            
            Rol r1 = new Rol("Duelista");
            Personatge p1 = new Personatge("Reyna", r1);
            Equip e1 = new Equip(1,  p1);
            Partida pa1 = new Partida();

            session.persist(p1);
            
            
            //Professor p1 = new Professor("Antonio Talens");
            //Moduls m1 = new Moduls("Acces a Dades");
            //p1.addModul(m2); p1.addModul(m1); // afegim mòduls a p1

            //desem (professors i mòduls en cascada)
            //session.persist(p1);

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
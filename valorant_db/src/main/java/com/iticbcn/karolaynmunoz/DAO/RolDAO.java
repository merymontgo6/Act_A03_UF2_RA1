package com.iticbcn.karolaynmunoz.DAO;
import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.iticbcn.karolaynmunoz.model.Personatge;
import com.iticbcn.karolaynmunoz.model.Rol;


public class RolDAO {
    private SessionFactory sessionFactory;
    String message = "==================";

    public RolDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;    
    }

    public void crearRol(SessionFactory sesion, Rol rol) throws IOException {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                // Buscar si ja existeix un rol amb el mateix nom
                Rol existentRol = (Rol) session.createQuery("FROM Rol r WHERE r.nom_rol = :nom", Rol.class)
                    .setParameter("nom", rol.getNom_rol())
                    .uniqueResult(); // Obtenir el rol amb el nom especificat, si existeix
    
                if (existentRol != null) { // Si el rol ja existeix, assignar-lo als personatges
                    rol = existentRol;
                } else { // Si no existeix, persistir el nou rol
                    session.persist(rol);
                }
    
                // Assignar el rol als personatges i persistir-los
                for (Personatge personatge : rol.getPersonatges()) {
                    personatge.setRol(rol); // Assegurar que el rol està assignat al personatge
                    session.persist(personatge);
                }
                    session.getTransaction().commit();
                    
            //Es gestionen les excepcions
            } catch (HibernateException e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.err.println("Error en Hibernate: " + e.getMessage());
                }
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.err.println("Error inesperat: " + e.getMessage());
                }
            }
        }
    }        

    public void readRol(SessionFactory sesion, int id) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try { // llegir rol per FIND
                Rol rol = session.find(Rol.class, id);
                if (rol != null) {
                    System.out.println(message);
                    System.out.println(rol);
                    System.out.println(message);

                } else {
                    System.out.println("Rol amb ID " + id + " no trobat.");
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

    public boolean existeixRolId(SessionFactory sesion, int id) {
        try (Session session = sesion.openSession()) {
            Rol rol = session.find(Rol.class, id);
            return rol != null;
        }
    }
    
    public void updateRol(SessionFactory sesion, int id, String nouNom) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                Rol rol = session.find(Rol.class, id);
                if (rol != null) {
                    rol.setNom_rol(nouNom);
                    session.merge(rol); // Actualitzar el rol
                    session.getTransaction().commit();
                    System.out.println(message);
                    System.out.println("Rol actualitzat correctament.");
                    System.out.println(message);
                } else {
                    System.out.println("No s'ha trobat cap rol amb l'ID " + id);
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

    public void deleteRol(SessionFactory sesion, int id) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                Rol rol = session.find(Rol.class, id);
                if (rol != null) {
                    session.delete(rol); // Eliminar el rol
                    session.getTransaction().commit();
                    System.out.println(message);
                    System.out.println("Rol eliminat correctament.");
                    System.out.println(message);
                } else {
                    System.out.println("No s'ha trobat cap rol amb l'ID 1");
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

    public void findAllRol(SessionFactory sesion) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                // Crear la consulta per obtenir tots els rols
                Query<Rol> query = session.createQuery("from Rol", Rol.class);
            
                // Obtenir la llista de rols
                List<Rol> rols = query.getResultList();
                
                // Iterar sobre la llista i imprimir cada rol
                for (Rol rol : rols) {
                    System.out.println(message);
                    System.out.println(rol);
                    System.out.println(message);
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

    public void agregacionsRol(SessionFactory sesion, int opcio) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                Query<Object[]> query;
                if (opcio == 1) {
                    // Aggregation by id_rol
                    query = session.createQuery(
                        "SELECT r.id_rol, COUNT(p.id_personatge) " +
                        "FROM Personatge p JOIN p.rol r " +
                        "GROUP BY r.id_rol", Object[].class);
                } else {
                    // Aggregation by nom_rol
                    query = session.createQuery(
                        "SELECT r.nom_rol, COUNT(p.id_personatge) " +
                        "FROM Personatge p JOIN p.rol r " +
                        "GROUP BY r.nom_rol", Object[].class);
                }
    
                // Get the result of the query
                List<Object[]> resultats = query.getResultList();
    
                // Print the results 
                //metode fer per chat per poder imprimir tots els resultats
                System.out.println(message);
                for (Object[] resultat : resultats) {
                    if (opcio == 1) {
                        Integer idRol = (Integer) resultat[0];
                        Long countPersonatges = (Long) resultat[1];
                        System.out.println("ID Rol: " + idRol + ", Nombre de personatges: " + countPersonatges);
                    } else {
                        String nomRol = (String) resultat[0];
                        Long countPersonatges = (Long) resultat[1];
                        System.out.println("Rol: " + nomRol + ", Nombre de personatges: " + countPersonatges);
                    }
                }
                System.out.println(message);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                System.err.println("Error en Hibernate: " + e.getMessage());
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                System.err.println("Error inesperado: " + e.getMessage());
            }
        }
    }
}
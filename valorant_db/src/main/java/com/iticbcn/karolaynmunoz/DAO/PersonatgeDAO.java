package com.iticbcn.karolaynmunoz.DAO;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.iticbcn.karolaynmunoz.model.Equip;
import com.iticbcn.karolaynmunoz.model.Personatge;
import com.iticbcn.karolaynmunoz.model.Rol;

public class PersonatgeDAO {
    private SessionFactory sessionFactory;
    String message = "==================";

    public PersonatgeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;    
    }

    public void crearPersonatge(SessionFactory sesion, Personatge personatge) throws IOException {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                // Persistir el rol abans de persistir el personatge
                if (personatge.getRol() != null) {
                    session.persist(personatge.getRol());
                }

                // Persistir el personatge
                session.persist(personatge);

                // Persistir els equips
                for (Equip equip : personatge.getEquips()) {
                    session.persist(equip);
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

    public void readPersonatge(SessionFactory sesion, int id) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                // Llegir personatge per FIND
                Personatge personatge = session.find(Personatge.class, id);
                if (personatge != null) {
                    System.out.println(message);
                    System.out.println(personatge);
                    System.out.println(message);
                } else {
                    System.out.println("Personatge amb ID " + id + " no trobat.");
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

    public boolean existeixPersonatgeId(SessionFactory sesion, int id) {
        try (Session session = sesion.openSession()) {
            Personatge p = session.find(Personatge.class, id);
            return p != null;
        }
    }

    public void updatePersonatge(int id, String nouValor, int opcio, int idRol) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                Personatge personatge = session.find(Personatge.class, id);
                if (personatge != null) {
                    switch (opcio) {
                        case 1 -> personatge.setNom(nouValor);
                        case 2 -> {
                            Rol rol = session.find(Rol.class, idRol);
                            if (rol != null) {
                                personatge.setRol(rol);
                            } else {
                                System.out.println("No s'ha trobat cap rol amb l'ID " + idRol);
                                return;
                            }
                        }
                        default -> {
                            System.out.println("Opció no vàlida.");
                            return;
                        }
                    }
                    session.merge(personatge);
                    session.getTransaction().commit();
                    String message = "==================";
                    System.out.println(message);
                    System.out.println("Personatge actualitzat correctament.");
                    System.out.println(message);
                } else {
                    System.out.println("No s'ha trobat cap personatge amb l'ID " + id);
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

    public void agregacionsPersonatge(SessionFactory sesion, int opcio) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                Query<Object[]> query;
                switch (opcio) {
                    case 1:
                        // Aggregation by id_personatge
                        query = session.createQuery(
                            "SELECT p.id_personatge, COUNT(p.id_personatge) " +
                            "FROM Personatge p " +
                            "GROUP BY p.id_personatge", Object[].class);
                        break;
                    case 2:
                        // Aggregation by id_rol
                        query = session.createQuery(
                            "SELECT r.id_rol, COUNT(p.id_personatge) " +
                            "FROM Personatge p JOIN p.rol r " +
                            "GROUP BY r.id_rol", Object[].class);
                        break;
                    case 3:
                        // Aggregation by nom_personatge
                        query = session.createQuery(
                            "SELECT p.nom_personatge, COUNT(p.id_personatge) " +
                            "FROM Personatge p " +
                            "GROUP BY p.nom_personatge", Object[].class);
                        break;
                    default:
                        throw new IllegalArgumentException("Opció invàlida: " + opcio);
                }
    
                // Get the result of the query
                List<Object[]> resultats = query.getResultList();
    
                // Print the results
                //metode fet per chat per imprimir els resultats
                System.out.println(message);
                for (Object[] resultat : resultats) {
                    if (opcio == 1) {
                        Integer idPersonatge = (Integer) resultat[0];
                        Long countPersonatges = (Long) resultat[1];
                        System.out.println("ID Personatge: " + idPersonatge + ", Nombre de personatges: " + countPersonatges);
                    } else if (opcio == 2) {
                        Integer idRol = (Integer) resultat[0];
                        Long countPersonatges = (Long) resultat[1];
                        System.out.println("ID Rol: " + idRol + ", Nombre de personatges: " + countPersonatges);
                    } else {
                        String nomPersonatge = (String) resultat[0];
                        Long countPersonatges = (Long) resultat[1];
                        System.out.println("Nom Personatge: " + nomPersonatge + ", Nombre de personatges: " + countPersonatges);
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

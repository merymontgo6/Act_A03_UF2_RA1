package com.iticbcn.karolaynmunoz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.iticbcn.karolaynmunoz.model.Equip;
import com.iticbcn.karolaynmunoz.model.Partida;
import com.iticbcn.karolaynmunoz.model.Personatge;
import com.iticbcn.karolaynmunoz.model.Rol;

public class Main {
    static boolean sortirapp = false;
    public static void main(String[] args) throws IOException, SQLException, InterruptedException {

        Session session = null;
        try {
            SessionFactory sesion = HibernateUtil.getSessionFactory(); //crea instancia de la sessio
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            MenuOptions(br, sesion);

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

    public static void MenuOptions(BufferedReader br, SessionFactory session) throws NumberFormatException, IOException, SQLException, InterruptedException {
        String message = "";

        message = "==================";
        System.out.println(message);

        message = "OPCIONS";
        System.out.println(message);

        message = "1. CREAR TAULA";
        System.out.println(message);

        message = "2. CONSULTAR TOTES LES DADES";
        System.out.println(message);

        message = "3. CONSULTAR PER ID";
        System.out.println(message);

        message = "4. INSERIR VALORS";
        System.out.println(message);

        message = "7. ESBORRAR ROL";
        System.out.println(message);

        message = "8. MODIFICAR ROL";
        System.out.println(message);

        message = "0. SORTIR";
        System.out.println(message);

        message = "Opció: ";
        for (char c : message.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            Thread.sleep(10);
        }

        int opcio = Integer.parseInt(br.readLine());

        switch(opcio) {
            case 1 -> { //Demana la taula amb la que es vol treballar
                int taula = demanarTaula(br);
                crearDades(taula, br, session);
            }
            case 0 -> {
                sortirapp = true;
                break;
            }
            default -> {
                System.out.println("Opcio no vàlida");
                MenuOptions(br, session);
            }
        }
    }

    public static int demanarTaula (BufferedReader br) throws IOException {
        System.out.println("Quina taula vols tractar?:\n1. Rol\n2. Personatge\n3. Equipo\n4. Partida ");
        int taula = Integer.parseInt(br.readLine());

        return taula;
    }

    public static int demanarAccio (BufferedReader br) throws IOException {
        System.out.println("Quina acció vols fer?:\n1. Inserir\n2. Esborrar\n3. Modificar");
        int accio = Integer.parseInt(br.readLine());
        return accio;
    }
    public void crearDades(int taula, SessionFactory sesion, Rol rol, Personatge personatge, Equip equip, Partida partida) throws IOException {

        try (Session session = sesion.openSession()) {
           session.beginTransaction();
            switch (taula) {
                case 1 -> {
                    try {
                        session.persist(rol);
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
                case 2 -> {
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
                case 3 -> {
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
                
                case 4 -> {
                    try {
                        session.persist(partida);
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
}
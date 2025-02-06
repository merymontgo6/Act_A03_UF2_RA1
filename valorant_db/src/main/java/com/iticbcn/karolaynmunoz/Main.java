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
            int opcio = MenuOptions(br);

            switch(opcio) {
                case 1 -> { //Demana la taula amb la que es vol treballar
                    int taula = demanarTaula(br);
                    switch (taula) {
                        case 1 -> crearRol(taula, session, rol);
                        case 2 -> crearPersonatge(taula, session, personatge);
                        case 3 -> crearEquip(taula, session, equip);
                        case 4 -> crearPartida(taula, session, partida);
                    }
                }
                case 2 -> {
                    int taula = demanarTaula(br);
                    switch (taula) {
                        case 1 -> readRol(session);
                        case 2 -> readPersonatge(session);
                        case 3 -> readEquip(session);
                        case 4 -> readPartida(session);
                    }

                }
                case 0 -> {
                    sortirapp = true;
                    break;
                }
                default -> {
                    System.out.println("Opcio no vàlida");
                    opcio = MenuOptions(br);
                }
            }

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

    public static int MenuOptions(BufferedReader br) throws NumberFormatException, IOException, SQLException, InterruptedException {
        String message = "";

        message = "==================";
        System.out.println(message);

        message = "OPCIONS";
        System.out.println(message);

        message = "1. CREAR TAULA";
        System.out.println(message);

        message = "2. READ LES DADES";
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
        return opcio;
        
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

    public void crearRol(int taula, SessionFactory sesion, Rol rol) throws IOException {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
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
    }

    public void crearPersonatge (int taula, SessionFactory sesion, Personatge personatge) throws IOException {
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

    public void readRol (int taula, SessionFactory sesion, Rol rol) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                

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
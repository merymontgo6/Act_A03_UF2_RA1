package com.iticbcn.karolaynmunoz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.iticbcn.karolaynmunoz.model.Equip;
import com.iticbcn.karolaynmunoz.model.Partida;
import com.iticbcn.karolaynmunoz.model.Personatge;
import com.iticbcn.karolaynmunoz.model.PersonatgeDAO;
import com.iticbcn.karolaynmunoz.model.Rol;
import com.iticbcn.karolaynmunoz.model.RolDAO;

public class Main {
    static boolean sortirapp = false;
    public static void main(String[] args) throws IOException, SQLException, InterruptedException {

        Session session = null;
        try {
            SessionFactory sesion = HibernateUtil.getSessionFactory(); //crea instancia de la sessio
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int opcio = MenuOptions(br);

            switch(opcio) {
                case 1 -> {
                    int taula = demanarTaula(br);
                    switch (taula) {
                        case 1 -> dadesRol(br, sesion);
                        case 2 -> dadesPersonatge(br, sesion);
                        case 3 -> dadesEquip(br, sesion);
                        case 4 -> dadesPartida(br, sesion);
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

    public static void dadesRol(BufferedReader br, SessionFactory sesion) throws IOException {
        RolDAO rDAO = new RolDAO(sesion);
        System.out.println("Introdueix el nom del rol: ");
        String nomRol = br.readLine();

        System.out.println("Introdueix el nom dels personatges: ");
        String[] nomsPersonatges = br.readLine().split(",");
        Set<Personatge> nomPersonatge = new HashSet<>();
        for (String nom : nomsPersonatges) {
            nomPersonatge.add(new Personatge(nom.trim()));
        }

        Rol rol1 = new Rol(nomRol, nomPersonatge);
        rDAO.crearRol(sesion, rol1);
    }

    public static void dadesPersonatge(BufferedReader br, SessionFactory sesion) throws  IOException {
        PersonatgeDAO pDAO= new PersonatgeDAO(sesion);
        System.out.println("Introdueix el nom del personatge: ");
        String nomPersonatge = br.readLine();

        System.out.println("Introdueix el id de rol: ");
        int idRol = Integer.parseInt(br.readLine());

        System.out.println("Introdueix l'equip");


        Personatge p1 = new Personatge(nomPersonatge, idRol, equip);
        pDAO.crearPersonatge(sesion, p1);

    }
}
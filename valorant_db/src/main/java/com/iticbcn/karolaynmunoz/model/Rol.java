package com.iticbcn.karolaynmunoz.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "Rol")

public class Rol implements Serializable {
    private int id_rol;
    private String nom_rol;

    public Rol() {}

    public Rol(int id, String nom) {
        this.id_rol = id;
        this.nom_rol = nom;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id) {
        this.id_rol = id;
    }

    public String getNom_rol() {
        return nom_rol;
    }

    public void setNom_rol(String nom) {
        this.nom_rol = nom;
    }

    @Override
    public String toString() {
        return "Rol [id=" + id_rol + ", nom=" + nom_rol +"]";
    }
}

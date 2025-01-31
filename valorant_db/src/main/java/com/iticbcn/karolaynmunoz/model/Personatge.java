package com.iticbcn.karolaynmunoz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Personatge")
public class Personatge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_personatge;
    @Column
    private String nom_personatges;
   
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol id_rol; 

    @OneToMany(mappedBy = "personatge", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Equip> equips = new ArrayList<>();
    
    public Personatge() {}

    public Personatge(int id, String nom, Rol id_rol) {
        this.id_personatge = id;
        this.nom_personatges = nom;
        this.id_rol = id_rol;
    }

    public int getId() {
        return id_personatge;
    }

    public void setId(int id) {
        this.id_personatge = id;
    }

    public String getNom() {
        return nom_personatges;
    }

    public void setNom(String nom) {
        this.nom_personatges = nom;
    }

    public Rol getId_rol() {
        return id_rol;
    }

    public void setId_rol(Rol id_rol) {
        this.id_rol = id_rol;
    }

    public List<Equip> getEquips() {
        return equips;
    }

    public void setEquips(List<Equip> equips) {
        this.equips = equips;
    }
}

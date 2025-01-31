package com.iticbcn.karolaynmunoz.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Equip")
public class Equip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_equip;

    @ManyToOne
    @JoinColumn(name = "id_personatge")
    private Personatge personatge;

    @ManyToMany
    @JoinTable( name = "equip_partida",
        joinColumns = @JoinColumn(name = "id_equip", foreignKey = @ForeignKey(name = "FK_EQUIP_PARTIDA_EQUIP"),nullable = false),
        inverseJoinColumns = @JoinColumn(name = "id_partida", foreignKey = @ForeignKey(name = "FK_EQUIP_PARTIDA_PARTIDA"), nullable = false))
    private Set<Partida> partides;

    public Equip() {}

    public Equip(int id_equip, Personatge id_personatge) {
        this.id_equip = id_equip;
        this.personatge = id_personatge;
    }

    public int getId_equip() {
        return id_equip;
    }

    public void setId_equip(int id_equip) {
        this.id_equip = id_equip;
    }

    public Personatge getId_Personatge() {
        return personatge;
    }

    public void setIdPersonatge(Personatge id_personatge) {
        this.personatge = id_personatge;
    }

    public Set<Partida> getPartides() {
        return partides;
    }

    public void setPartidas(Set<Partida> partides) {
        this.partides = partides;
    }
}
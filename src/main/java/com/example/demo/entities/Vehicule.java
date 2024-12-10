package com.example.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "marque")
    private String marque;
    @Column(name = "modele")
    private String modele;
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assurance> assurances;


    public String getNumeroImmatriculation() {
        return numeroImmatriculation;
    }

    public void setNumeroImmatriculation(String numeroImmatriculation) {
        this.numeroImmatriculation = numeroImmatriculation;
    }

    @Column(name = "numero_immatriculation", nullable = true)
    private String numeroImmatriculation; // Renommé pour correspondre aux conventions camelCase


    public Propriétaire getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Propriétaire proprietaire) {
        this.proprietaire = proprietaire;
    }

    // Relationships and other fields
    @ManyToOne
    @JoinColumn(name = "proprietaire_id") // This column will store the relationship
    private Propriétaire proprietaire;
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }
    @OneToMany(mappedBy = "vehiculeA", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Constat> constatsAsVehiculeA = new ArrayList<>();

    @OneToMany(mappedBy = "vehiculeB", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Constat> constatsAsVehiculeB = new ArrayList<>();

}

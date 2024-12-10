package com.example.demo.entities;

import com.example.demo.entities.Assurance;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class TypeAssurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; // Nom du type d'assurance

    // Constructeur par défaut requis par Hibernate
    public TypeAssurance() {
    }

    // Constructeur supplémentaire
    public TypeAssurance(String typeAssurance) {
        this.nom = typeAssurance;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

package com.example.demo.entities;

import java.util.Date;

public class VehiculeDTO {

    private Long id;
    private String marque;
    private String modele;
    private String numeroImmatriculation;

    // Informations du propriétaire
    private String proprietaireNom;
    private String proprietairePrenom;
    private String proprietaireEmail;

    // Informations sur l'assurance
    private String typeAssurance;
    private Date assuranceDateDebut;
    private Date assuranceDateFin;
    private double assuranceMontant;

    // Getters et Setters
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

    public String getNumeroImmatriculation() {
        return numeroImmatriculation;
    }

    public void setNumeroImmatriculation(String numeroImmatriculation) {
        this.numeroImmatriculation = numeroImmatriculation;
    }

    public String getProprietaireNom() {
        return proprietaireNom;
    }

    public void setProprietaireNom(String proprietaireNom) {
        this.proprietaireNom = proprietaireNom;
    }

    public String getProprietairePrenom() {
        return proprietairePrenom;
    }

    public void setProprietairePrenom(String proprietairePrenom) {
        this.proprietairePrenom = proprietairePrenom;
    }

    public String getProprietaireEmail() {
        return proprietaireEmail;
    }

    public void setProprietaireEmail(String proprietaireEmail) {
        this.proprietaireEmail = proprietaireEmail;
    }

    public String getTypeAssurance() {
        return typeAssurance;
    }

    public void setTypeAssurance(String typeAssurance) {
        this.typeAssurance = typeAssurance;
    }

    public Date getAssuranceDateDebut() {
        return assuranceDateDebut;
    }

    public void setAssuranceDateDebut(Date assuranceDateDebut) {
        this.assuranceDateDebut = assuranceDateDebut;
    }

    public Date getAssuranceDateFin() {
        return assuranceDateFin;
    }

    public void setAssuranceDateFin(Date assuranceDateFin) {
        this.assuranceDateFin = assuranceDateFin;
    }

    public double getAssuranceMontant() {
        return assuranceMontant;
    }

    public void setAssuranceMontant(double assuranceMontant) {
        this.assuranceMontant = assuranceMontant;
    }
}

package com.example.demo.entities;

import java.util.Date;

public class AssuranceDTO {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getTypeAssuranceNom() {
        return typeAssuranceNom;
    }

    public void setTypeAssuranceNom(String typeAssuranceNom) {
        this.typeAssuranceNom = typeAssuranceNom;
    }

    public String getProprietaireNom() {
        return proprietaireNom;
    }

    public void setProprietaireNom(String proprietaireNom) {
        this.proprietaireNom = proprietaireNom;
    }

    public String getVoitureMarque() {
        return voitureMarque;
    }

    public void setVoitureMarque(String voitureMarque) {
        this.voitureMarque = voitureMarque;
    }

    public String getVoitureModele() {
        return voitureModele;
    }

    public void setVoitureModele(String voitureModele) {
        this.voitureModele = voitureModele;
    }

    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private double montant;
    private String typeAssuranceNom;
    private String proprietaireNom;

    public String getNumeroIdentite() {
        return numeroIdentite;
    }

    public void setNumeroIdentite(String numeroIdentite) {
        this.numeroIdentite = numeroIdentite;
    }

    private String numeroIdentite;
    public String getProprietairePrenom() {
        return proprietairePrenom;
    }

    public void setProprietairePrenom(String proprietairePrenom) {
        this.proprietairePrenom = proprietairePrenom;
    }

    private String proprietairePrenom;
    private String voitureMarque;

    public String getImmatriculamation() {
        return immatriculamation;
    }

    public void setImmatriculamation(String immatriculamation) {
        this.immatriculamation = immatriculamation;
    }

    private String immatriculamation;
    private String voitureModele;


    public String getVoitureimmatriculation() {
        return voitureimmatriculation;
    }

    public void setVoitureimmatriculation(String voitureimmatriculation) {
        this.voitureimmatriculation = voitureimmatriculation;
    }

    private String voitureimmatriculation;

    // Constructeur et Getters/Setters
}

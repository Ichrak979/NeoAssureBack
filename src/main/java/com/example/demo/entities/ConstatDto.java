package com.example.demo.entities;

import java.util.Date;

public class ConstatDto {
    private Vehicule vehiculeA;
    private Vehicule vehiculeB;
    private Date dateAccident;
    private String lieu;
    private String statut;
    private String details;
    private String circonstancesA;
    private String circonstancesB;
    private String conducteurA;
    private String societeAssuranceB;

    public String getSocieteAssuranceB() {
        return societeAssuranceB;
    }

    public void setSocieteAssuranceB(String societeAssuranceB) {
        this.societeAssuranceB = societeAssuranceB;
    }

    public String getSocieteAssuranceA() {
        return societeAssuranceA;
    }

    public void setSocieteAssuranceA(String societeAssuranceA) {
        this.societeAssuranceA = societeAssuranceA;
    }

    private String societeAssuranceA;
    public String getConducteurA() {
        return conducteurA;
    }

    public void setConducteurA(String conducteurA) {
        this.conducteurA = conducteurA;
    }

    public String getConducteurB() {
        return conducteurB;
    }

    public void setConducteurB(String conducteurB) {
        this.conducteurB = conducteurB;
    }

    private String conducteurB;
    private String observationsA;
    private String observationsB;

    public String getNumeroImmatriculation() {
        return numeroImmatriculation;
    }

    public void setNumeroImmatriculation(String numeroImmatriculation) {
        this.numeroImmatriculation = numeroImmatriculation;
    }

    private String numeroImmatriculation;
    // Getters et Setters
    private String croquisAccidentA; // Ajout de ce champ
    private String croquisAccidentB; // Ajout de ce champ

    public String getNomAssure() {
        return NomAssure;
    }

    public void setNomAssure(String nomAssure) {
        NomAssure = nomAssure;
    }

    private String NomAssure;

    public String getCroquisAccidentA() {
        return croquisAccidentA;
    }

    public void setCroquisAccidentA(String croquisAccidentA) {
        this.croquisAccidentA = croquisAccidentA;
    }

    public String getCroquisAccidentB() {
        return croquisAccidentB;
    }

    public void setCroquisAccidentB(String croquisAccidentB) {
        this.croquisAccidentB = croquisAccidentB;
    }

    public String getDommagesApparentsA() {
        return dommagesApparentsA;
    }

    public void setDommagesApparentsA(String dommagesApparentsA) {
        this.dommagesApparentsA = dommagesApparentsA;
    }

    public String getDommagesApparentsB() {
        return dommagesApparentsB;
    }

    public void setDommagesApparentsB(String dommagesApparentsB) {
        this.dommagesApparentsB = dommagesApparentsB;
    }

    private String dommagesApparentsA; // Ajout de ce champ
    private String dommagesApparentsB; // Ajout de ce champ
    public Vehicule getVehiculeA() {
        return vehiculeA;
    }

    public void setVehiculeA(Vehicule vehiculeA) {
        this.vehiculeA = vehiculeA;
    }

    public Vehicule getVehiculeB() {
        return vehiculeB;
    }

    public void setVehiculeB(Vehicule vehiculeB) {
        this.vehiculeB = vehiculeB;
    }

    public Date getDateAccident() {
        return dateAccident;
    }

    public void setDateAccident(Date dateAccident) {
        this.dateAccident = dateAccident;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCirconstancesA() {
        return circonstancesA;
    }

    public void setCirconstancesA(String circonstancesA) {
        this.circonstancesA = circonstancesA;
    }

    public String getCirconstancesB() {
        return circonstancesB;
    }

    public void setCirconstancesB(String circonstancesB) {
        this.circonstancesB = circonstancesB;
    }

    public String getObservationsA() {
        return observationsA;
    }

    public void setObservationsA(String observationsA) {
        this.observationsA = observationsA;
    }

    public String getObservationsB() {
        return observationsB;
    }

    public void setObservationsB(String observationsB) {
        this.observationsB = observationsB;
    }
}

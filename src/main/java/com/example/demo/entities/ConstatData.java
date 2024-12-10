package com.example.demo.entities;

public class ConstatData {
    private String constat_id;
    private VehiculeData vehicule_a;
    private VehiculeData vehicule_b;
    private String circonstances;
    private String croquis_accident;
    private String dommages_apparents;
    private String observations;
    private String lieu;  // Nouveau champ "lieu"

    // Getters et Setters

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    // Getters et Setters
    public String getConstat_id() {
        return constat_id;
    }

    public void setConstat_id(String constat_id) {
        this.constat_id = constat_id;
    }

    public VehiculeData getVehicule_a() {
        return vehicule_a;
    }

    public void setVehicule_a(VehiculeData vehicule_a) {
        this.vehicule_a = vehicule_a;
    }

    public VehiculeData getVehicule_b() {
        return vehicule_b;
    }

    public void setVehicule_b(VehiculeData vehicule_b) {
        this.vehicule_b = vehicule_b;
    }

    public String getCirconstances() {
        return circonstances;
    }

    public void setCirconstances(String circonstances) {
        this.circonstances = circonstances;
    }

    public String getCroquis_accident() {
        return croquis_accident;
    }

    public void setCroquis_accident(String croquis_accident) {
        this.croquis_accident = croquis_accident;
    }

    public String getDommages_apparents() {
        return dommages_apparents;
    }

    public void setDommages_apparents(String dommages_apparents) {
        this.dommages_apparents = dommages_apparents;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public VehiculeData getVehiculeA() {
        return vehicule_a;
    }

    // Classe interne pour représenter les données d'un véhicule
    public static class VehiculeData {
        private String societe_assurances;
        private String numero_police;
        private String agence;
        private Conducteur identite_conducteur;
        private Assure assure;
        private Vehicule vehicule;

        // Getters et Setters
        public String getSociete_assurances() {
            return societe_assurances;
        }

        public void setSociete_assurances(String societe_assurances) {
            this.societe_assurances = societe_assurances;
        }

        public String getNumero_police() {
            return numero_police;
        }

        public void setNumero_police(String numero_police) {
            this.numero_police = numero_police;
        }

        public String getAgence() {
            return agence;
        }

        public void setAgence(String agence) {
            this.agence = agence;
        }

        public Conducteur getIdentite_conducteur() {
            return identite_conducteur;
        }

        public void setIdentite_conducteur(Conducteur identite_conducteur) {
            this.identite_conducteur = identite_conducteur;
        }

        public Assure getAssure() {
            return assure;
        }

        public void setAssure(Assure assure) {
            this.assure = assure;
        }

        public Vehicule getVehicule() {
            return vehicule;
        }

        public void setVehicule(Vehicule vehicule) {
            this.vehicule = vehicule;
        }

        // Classe interne pour les informations sur le conducteur
        public static class Conducteur {
            private String nom;
            private String prenom;
            private String adresse;
            private String telephone;

            public String getNom() {
                return nom;
            }

            public void setNom(String nom) {
                this.nom = nom;
            }

            public String getPrenom() {
                return prenom;
            }

            public void setPrenom(String prenom) {
                this.prenom = prenom;
            }

            public String getAdresse() {
                return adresse;
            }

            public void setAdresse(String adresse) {
                this.adresse = adresse;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }
        }

        // Classe interne pour les informations d'assurance
        public static class Assure {
            private String nom;
            private String prenom;
            private String adresse;

            public String getNom() {
                return nom;
            }

            public void setNom(String nom) {
                this.nom = nom;
            }

            public String getPrenom() {
                return prenom;
            }

            public void setPrenom(String prenom) {
                this.prenom = prenom;
            }

            public String getAdresse() {
                return adresse;
            }

            public void setAdresse(String adresse) {
                this.adresse = adresse;
            }
        }

        // Classe interne pour les informations sur le véhicule
        public static class Vehicule {
            private String marque;
            private String numero_immatriculation;
            private String sens;
            private String allant_a;

            public String getMarque() {
                return marque;
            }

            public void setMarque(String marque) {
                this.marque = marque;
            }

            public String getNumero_immatriculation() {
                return numero_immatriculation;
            }

            public void setNumero_immatriculation(String numero_immatriculation) {
                this.numero_immatriculation = numero_immatriculation;
            }

            public String getSens() {
                return sens;
            }

            public void setSens(String sens) {
                this.sens = sens;
            }

            public String getAllant_a() {
                return allant_a;
            }

            public void setAllant_a(String allant_a) {
                this.allant_a = allant_a;
            }
        }
    }
}

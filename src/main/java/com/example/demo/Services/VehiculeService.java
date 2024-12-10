package com.example.demo.Services;

import com.example.demo.entities.Assurance;
import com.example.demo.entities.Propriétaire;
import com.example.demo.entities.Vehicule;
import com.example.demo.entities.VehiculeDTO;

import java.util.List;
import java.util.Optional;

public interface VehiculeService {
    Vehicule createVoiture(Vehicule vehicule);
    List<VehiculeDTO> getAllVehicules();
    Optional<Vehicule> getVoitureById(Long id);
    Vehicule updateVoiture(Long id, Vehicule vehicule);
    void deleteVoiture(Long id);
    Optional<Vehicule> getVehiculeById(Long id);

    void testVehiculeConstatRelationship(Long vehiculeId);


    boolean isVehiculeInsured(String numeroImmatriculation);
    void saveVehiculeWithDetails(Vehicule vehicule, Propriétaire proprietaire, Assurance assurance);

    void updateVehiculeWithDetails(Vehicule vehicule, Propriétaire proprietaire, Assurance assurance);
    void supprimerVehiculeAvecDetails(Long id); // Pour supprimer un véhicule avec ses détails
    void supprimerVehicule(Long id); // Si nécessaire pour une suppression simple

}

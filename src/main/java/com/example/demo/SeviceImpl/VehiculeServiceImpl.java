package com.example.demo.SeviceImpl;

import com.example.demo.Repository.AssuranceRepository;
import com.example.demo.Repository.ConstatRepository;
import com.example.demo.Repository.PropriétaireRepository;
import com.example.demo.Repository.VehiculeRepository;
import com.example.demo.Services.VehiculeService;
import com.example.demo.entities.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiculeServiceImpl implements VehiculeService {
    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private PropriétaireRepository proprietaireRepository;

    @Autowired
    private AssuranceRepository assuranceRepository;



    @Autowired
    private ConstatRepository constatRepository;

    @Override
    public Vehicule createVoiture(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }


    @Override
    public List<VehiculeDTO> getAllVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();

        // Map chaque entité Vehicule vers VehiculeDTO
        return vehicules.stream().map(vehicule -> {
            VehiculeDTO dto = new VehiculeDTO();
            dto.setId(vehicule.getId());
            dto.setMarque(vehicule.getMarque());
            dto.setModele(vehicule.getModele());
            dto.setNumeroImmatriculation(vehicule.getNumeroImmatriculation());

            // Informations du propriétaire
            if (vehicule.getProprietaire() != null) {
                dto.setProprietaireNom(vehicule.getProprietaire().getNom());
                dto.setProprietairePrenom(vehicule.getProprietaire().getPrenom());
                dto.setProprietaireEmail(vehicule.getProprietaire().getEmail());
            }

            // Informations sur l'assurance
            if (vehicule.getProprietaire() != null && !vehicule.getProprietaire().getAssurances().isEmpty()) {
                Assurance assurance = vehicule.getProprietaire().getAssurances().get(0); // Exemple : première assurance
                dto.setTypeAssurance(assurance.getTypeAssurance());
                dto.setAssuranceDateDebut(assurance.getDateDebut());
                dto.setAssuranceDateFin(assurance.getDateFin());
                dto.setAssuranceMontant(assurance.getMontant());
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Vehicule> getVoitureById(Long id) {
        return vehiculeRepository.findById(id);
    }
    @Override
    public boolean isVehiculeInsured(String numeroImmatriculation) {
        return vehiculeRepository.findByNumeroImmatriculation(numeroImmatriculation).isPresent();
    }
    @Override
    public Vehicule updateVoiture(Long id, Vehicule vehicule) {
        return vehiculeRepository.findById(id).map(existingVehicule -> {
            existingVehicule.setMarque(vehicule.getMarque());
            existingVehicule.setModele(vehicule.getModele());
            existingVehicule.setNumeroImmatriculation(vehicule.getNumeroImmatriculation());
            return vehiculeRepository.save(existingVehicule);
        }).orElse(null);
    }

    @Override
    public void deleteVoiture(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    public void testVehiculeConstatRelationship(Long vehiculeId) {

    }
    @Override
    public Optional<Vehicule> getVehiculeById(Long id) {
        return vehiculeRepository.findById(id);
    }



    @Override
    public void saveVehiculeWithDetails(Vehicule vehicule, Propriétaire proprietaire, Assurance assurance) {
        // Sauvegarder le propriétaire
        Propriétaire savedProprietaire = proprietaireRepository.save(proprietaire);

        // Associer le propriétaire au véhicule
        vehicule.setProprietaire(savedProprietaire);
        Vehicule savedVehicule = vehiculeRepository.save(vehicule);

        // Associer le véhicule et le propriétaire à l'assurance
        assurance.setProprietaire(savedProprietaire);
        assurance.setVehicule(savedVehicule);
        assuranceRepository.save(assurance);
    }

    @Override
    public void updateVehiculeWithDetails(Vehicule vehicule, Propriétaire proprietaire, Assurance assurance) {
        try {
            // Vérifiez si le véhicule existe
            Vehicule existingVehicule = vehiculeRepository.findById(vehicule.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Véhicule introuvable avec l'ID : " + vehicule.getId()));

            // Mettez à jour les détails du véhicule
            existingVehicule.setMarque(vehicule.getMarque());
            existingVehicule.setModele(vehicule.getModele());
            existingVehicule.setNumeroImmatriculation(vehicule.getNumeroImmatriculation());

            // Gérez le propriétaire
            if (proprietaire != null) {
                Propriétaire existingProprietaire = existingVehicule.getProprietaire();
                if (existingProprietaire == null) {
                    // Nouveau propriétaire
                    existingVehicule.setProprietaire(proprietaire);
                } else {
                    // Mettez à jour le propriétaire existant
                    existingProprietaire.setNom(proprietaire.getNom());
                    existingProprietaire.setPrenom(proprietaire.getPrenom());
                    existingProprietaire.setEmail(proprietaire.getEmail());
                    existingProprietaire.setNumeroIdentite(proprietaire.getNumeroIdentite());
                    proprietaireRepository.save(existingProprietaire);
                }
            }

            // Gérez l'assurance
            if (assurance != null) {
                Assurance existingAssurance = assuranceRepository.findByVehiculeId(existingVehicule.getId())
                        .orElse(null);

                if (existingAssurance == null) {
                    // Nouvelle assurance
                    assurance.setVehicule(existingVehicule);
                    assurance.setProprietaire(existingVehicule.getProprietaire());
                    assuranceRepository.save(assurance);
                } else {
                    // Mettez à jour l'assurance existante
                    existingAssurance.setDateDebut(assurance.getDateDebut());
                    existingAssurance.setDateFin(assurance.getDateFin());
                    existingAssurance.setMontant(assurance.getMontant());
                    existingAssurance.setTypeAssurance(assurance.getTypeAssurance());
                    assuranceRepository.save(existingAssurance);
                }
            }

            // Sauvegardez le véhicule avec les modifications
            vehiculeRepository.save(existingVehicule);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la mise à jour des détails du véhicule : " + e.getMessage(), e);
        }
    }

    public Vehicule updateVehiculeWithDetails(Long id, Vehicule vehicule, Propriétaire proprietaire, Assurance assurance) {
        Optional<Vehicule> existingVehiculeOptional = vehiculeRepository.findById(id);
        if (!existingVehiculeOptional.isPresent()) {
            throw new RuntimeException("Véhicule avec l'ID " + id + " non trouvé.");
        }

        Vehicule existingVehicule = existingVehiculeOptional.get();

        // Mise à jour des informations du véhicule
        existingVehicule.setMarque(vehicule.getMarque());
        existingVehicule.setModele(vehicule.getModele());
        existingVehicule.setNumeroImmatriculation(vehicule.getNumeroImmatriculation());

        // Mise à jour ou création du propriétaire
        Propriétaire existingProprietaire = existingVehicule.getProprietaire();
        if (existingProprietaire == null) {
            existingProprietaire = new Propriétaire();
        }
        existingProprietaire.setNom(proprietaire.getNom());
        existingProprietaire.setPrenom(proprietaire.getPrenom());
        existingProprietaire.setEmail(proprietaire.getEmail());
        existingProprietaire.setNumeroIdentite(proprietaire.getNumeroIdentite());
        proprietaireRepository.save(existingProprietaire);
        existingVehicule.setProprietaire(existingProprietaire);

        // Mise à jour ou création de l'assurance
        Assurance existingAssurance = assuranceRepository.findByVehiculeId(id).orElse(new Assurance());
        existingAssurance.setDateDebut(assurance.getDateDebut());
        existingAssurance.setDateFin(assurance.getDateFin());
        existingAssurance.setMontant(assurance.getMontant());
        existingAssurance.setTypeAssurance(assurance.getTypeAssurance());
        existingAssurance.setProprietaire(existingProprietaire);
        existingAssurance.setVehicule(existingVehicule);
        assuranceRepository.save(existingAssurance);

        // Sauvegarder les modifications
        return vehiculeRepository.save(existingVehicule);
    }

    @Override
    public void supprimerVehiculeAvecDetails(Long id) {
        // Récupérez le véhicule et ses relations
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Véhicule introuvable avec l'ID : " + id));

        // Supprimez les assurances associées
        List<Assurance> assurances = assuranceRepository.findByVehiculesId(id);
        if (assurances != null && !assurances.isEmpty()) {
            assuranceRepository.deleteAll(assurances);
        }

        // Supprimez le propriétaire associé uniquement si ce propriétaire n'a pas d'autres véhicules
        Propriétaire proprietaire = vehicule.getProprietaire();
        if (proprietaire != null && proprietaire.getVehicules().size() <= 1) {
            proprietaireRepository.delete(proprietaire);
        }

        // Supprimez le véhicule
        vehiculeRepository.delete(vehicule);
    }

    @Override
    public void supprimerVehicule(Long id) {

    }


}

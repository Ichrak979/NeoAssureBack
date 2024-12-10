package com.example.demo.Resources;

import com.example.demo.Services.AssuranceService;
import com.example.demo.entities.Assurance;
import com.example.demo.Repository.AssuranceRepository;
import com.example.demo.entities.AssuranceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200") // Permet les requêtes cross-origin
@RestController
@RequestMapping("/api/assurances") // Définit le chemin racine pour tous les endpoints
public class AssuranceController {

    @Autowired
    private AssuranceService assuranceService;

    @Autowired
    private AssuranceRepository assuranceRepository;

    // Créer une nouvelle assurance
    @PostMapping
    public Assurance createAssurance(@RequestBody Assurance assurance) {
        return assuranceService.createAssurance(assurance);
    }

    // Récupérer toutes les assurances
@GetMapping
    public List<AssuranceDTO> getAllAssurancesDTO() {
        List<Assurance> assurances = assuranceRepository.findAll();

        // Transformation des entités en DTO
        return assurances.stream().map(assurance -> {
            AssuranceDTO dto = new AssuranceDTO();

            dto.setId(assurance.getId());
            dto.setDateDebut(assurance.getDateDebut());
            dto.setDateFin(assurance.getDateFin());
            dto.setMontant(assurance.getMontant());
            dto.setNumeroIdentite(
                    assurance.getProprietaire() != null ? assurance.getProprietaire().getNumeroIdentite() : "Non spécifié"
            );
            dto.setTypeAssuranceNom(
                    assurance.getTypeAssurance() != null ? assurance.getTypeAssurance(): "Non spécifié"
            );
            dto.setProprietaireNom(
                    assurance.getProprietaire() != null ? assurance.getProprietaire().getNom() : "Non spécifié"
            );
            dto.setProprietairePrenom(
                    assurance.getProprietaire() != null ? assurance.getProprietaire().getPrenom() : "Non spécifié"
            );
            dto.setVoitureMarque(
                    assurance.getVehicule() != null ? assurance.getVehicule().getMarque() : "Non spécifié"
            );
            dto.setVoitureModele(
                    assurance.getVehicule() != null ? assurance.getVehicule().getModele() : "Non spécifié"
            );
            dto.setVoitureimmatriculation(
                    assurance.getVehicule() != null ? assurance.getVehicule().getNumeroImmatriculation() : "Non spécifié"
            );

            return dto;
        }).collect(Collectors.toList());
    }


    // Récupérer une assurance par ID
    @GetMapping("/{id}")
    public ResponseEntity<Assurance> getAssuranceById(@PathVariable Long id) {
        return assuranceService.getAssuranceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Mettre à jour une assurance
    @PutMapping("/{id}")
    public ResponseEntity<Assurance> updateAssurance(@PathVariable Long id, @RequestBody Assurance assurance) {
        Assurance updatedAssurance = assuranceService.updateAssurance(id, assurance);
        return updatedAssurance != null ? ResponseEntity.ok(updatedAssurance) : ResponseEntity.notFound().build();
    }

    // Supprimer une assurance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssurance(@PathVariable Long id) {
        assuranceService.deleteAssurance(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/verifier-assurance")
    public ResponseEntity<String> verifierAssurance(
            @RequestParam String immatriculation,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateAccident) {
        String result = assuranceService.verifierAssurance(immatriculation, dateAccident);
        return ResponseEntity.ok(result);
    }

}

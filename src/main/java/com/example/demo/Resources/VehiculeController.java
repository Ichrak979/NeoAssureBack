package com.example.demo.Resources;

import com.example.demo.Repository.VehiculeRepository;
import com.example.demo.Services.VehiculeService;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

import java.util.Optional;

@RestController
@RequestMapping("/vehicules")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

    @GetMapping("/all")
    public ResponseEntity<List<VehiculeDTO>> getAllVehicules() {
        List<VehiculeDTO> vehicules = vehiculeService.getAllVehicules();
        return ResponseEntity.ok(vehicules);
    }

    @PostMapping
    public Vehicule createVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.createVoiture(vehicule);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicule(@PathVariable Long id) {
        Optional<Vehicule> vehicule = vehiculeService.getVehiculeById(id);
        if (vehicule.isPresent()) {
            return ResponseEntity.ok(vehicule.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Véhicule non trouvé");
        }
    }






    @GetMapping("/assurance/{immatriculation}")
    public ResponseEntity<?> verifierAssurance(@PathVariable String immatriculation) {
        VehiculeRepository vehiculeRepository = null;
        Optional<Vehicule> vehicule = vehiculeRepository.findByNumeroImmatriculation(immatriculation);

        if (vehicule.isPresent()) {
            return ResponseEntity.ok(Map.of("message", "Le véhicule est assuré par NeoAssur."));
        } else {
            return ResponseEntity.ok(Map.of("message", "Le véhicule n’est pas assuré par NeoAssur."));
        }
    }
    @PostMapping("/analyse-image")
    public ResponseEntity<?> analyserImage(@RequestParam("file") MultipartFile file) {
        try {
            // Appel au service pour analyser l'image et extraire l'immatriculation
            String immatriculation = envoyerImageAColab(file);

            // Retourne l'immatriculation dans une réponse JSON
            return ResponseEntity.ok(Map.of("immatriculation", immatriculation));
        } catch (Exception e) {
            // Capture et log des erreurs
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    private String envoyerImageAColab(MultipartFile file) throws IOException {
        String colabUrl = "https://a016-34-42-170-128.ngrok-free.app/analyser-image";

        if (file.isEmpty() || file.getSize() > 10 * 1024 * 1024) {
            throw new IllegalArgumentException("Fichier invalide ou trop volumineux.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename() != null ? file.getOriginalFilename() : "uploaded_file";
            }

            @Override
            public long contentLength() {
                return file.getSize();
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(colabUrl, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode responseBody = objectMapper.readTree(response.getBody());

                if (responseBody.has("immatriculation")) {
                    return responseBody.get("immatriculation").asText();
                } else {
                    throw new RuntimeException("La réponse ne contient pas de clé 'immatriculation'. Réponse : " + response.getBody());
                }
            } else {
                throw new RuntimeException("Erreur Colab : " + response.getStatusCode() + ", message : " + response.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'envoi de l'image à Colab : " + e.getMessage());
        }
    }


    @PostMapping("/add")
    public ResponseEntity<?> ajouterVehiculeAvecDetails(@RequestBody Map<String, Object> data) {
        try {
            // Vérifier la validité des données reçues
            if (data == null || data.isEmpty()) {
                return ResponseEntity.badRequest().body("Les données sont invalides.");
            }

            Map<String, Object> vehiculeData = (Map<String, Object>) data.get("vehicule");
            Map<String, Object> proprietaireData = (Map<String, Object>) data.get("proprietaire");
            Map<String, Object> assuranceData = (Map<String, Object>) data.get("assurance");

            Propriétaire proprietaire = new Propriétaire();
            proprietaire.setNom((String) proprietaireData.get("nom"));
            proprietaire.setPrenom((String) proprietaireData.get("prenom"));
            proprietaire.setEmail((String) proprietaireData.get("email"));
            proprietaire.setNumeroIdentite((String) proprietaireData.get("numeroIdentite"));

            Vehicule vehicule = new Vehicule();
            vehicule.setMarque((String) vehiculeData.get("marque"));
            vehicule.setModele((String) vehiculeData.get("modele"));
            vehicule.setNumeroImmatriculation((String) vehiculeData.get("numeroImmatriculation"));

            Assurance assurance = new Assurance();
            assurance.setDateDebut(Date.valueOf((String) assuranceData.get("dateDebut")));
            assurance.setDateFin(Date.valueOf((String) assuranceData.get("dateFin")));
            assurance.setMontant(Double.parseDouble(assuranceData.get("montant").toString()));
            assurance.setTypeAssurance((String) assuranceData.get("typeAssurance"));

            vehiculeService.saveVehiculeWithDetails(vehicule, proprietaire, assurance);

            return ResponseEntity.ok("Véhicule ajouté avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l’ajout : " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVehiculeAvecDetails(@PathVariable Long id, @RequestBody Map<String, Object> data) {
        try {
            // Vérifiez si les données sont valides
            if (data == null || data.isEmpty()) {
                return ResponseEntity.badRequest().body("Les données fournies sont invalides ou manquantes.");
            }
            System.out.println("Données reçues : " + data);

            // Extraire les données envoyées
            Map<String, Object> vehiculeData = (Map<String, Object>) data.get("vehicule");
            System.out.println("vehiculeData"+vehiculeData);
            Map<String, Object> proprietaireData = (Map<String, Object>) data.get("proprietaire");
            System.out.println("proprietaireData"+proprietaireData);
            Map<String, Object> assuranceData = (Map<String, Object>) data.get("assurance");
            System.out.println("assuranceData"+assuranceData);

            // Rechercher le véhicule existant
            Optional<Vehicule> optionalVehicule = vehiculeService.getVehiculeById(id);
            if (!optionalVehicule.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Véhicule avec l'ID " + id + " non trouvé.");
            }

            // Mettre à jour les informations du véhicule
            Vehicule vehicule = optionalVehicule.get();
            vehicule.setMarque((String) vehiculeData.get("marque"));
            vehicule.setModele((String) vehiculeData.get("modele"));
            vehicule.setNumeroImmatriculation((String) vehiculeData.get("numeroImmatriculation"));

            // Mettre à jour ou créer les informations du propriétaire
            Propriétaire proprietaire = vehicule.getProprietaire();
            if (proprietaire == null) {
                proprietaire = new Propriétaire();
            }
            proprietaire.setNom((String) proprietaireData.get("nom"));
            proprietaire.setPrenom((String) proprietaireData.get("prenom"));
            proprietaire.setEmail((String) proprietaireData.get("email"));
            proprietaire.setNumeroIdentite((String) proprietaireData.get("numeroIdentite"));
            vehicule.setProprietaire(proprietaire);

            // Mettre à jour ou créer les informations d'assurance
            Assurance assurance = new Assurance();
            assurance.setDateDebut(Date.valueOf((String) assuranceData.get("dateDebut")));
            assurance.setDateFin(Date.valueOf((String) assuranceData.get("dateFin")));
            assurance.setTypeAssurance((String) assuranceData.get("typeAssurance"));
            assurance.setVehicule(vehicule);
            assurance.setProprietaire(proprietaire);

            // Appeler le service pour sauvegarder les modifications
            System.out.println("Données reçues pour mise à jour : " + data);
            vehiculeService.updateVehiculeWithDetails(vehicule, proprietaire, assurance);
            System.out.println("Véhicule après mise à jour : " + vehicule);
            System.out.println("Propriétaire après mise à jour : " + proprietaire);
            System.out.println("Assurance après mise à jour : " + assurance);

            return ResponseEntity.ok("Véhicule mis à jour avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour : " + e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> supprimerVehiculeAvecDetails(@PathVariable Long id) {
        try {
            vehiculeService.supprimerVehiculeAvecDetails(id);
            return ResponseEntity.ok("Véhicule et ses détails supprimés avec succès !");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Véhicule introuvable avec l'ID : " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression.");
        }
    }




}
package com.example.demo.Resources;

import com.example.demo.Services.ConstatService;
import com.example.demo.entities.Constat;
import com.example.demo.entities.ConstatDto;
import com.example.demo.entities.Vehicule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("constats")

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class ConstatController {

    @Autowired
    private ConstatService constatService;


    @GetMapping
    public ResponseEntity<List<Constat>> getAllConstats() {
        List<Constat> constats = constatService.getAllConstats();

        // Afficher les données récupérées dans la console
        System.out.println("Données renvoyées par le backend : " + constats);

        return ResponseEntity.ok(constats);
    }

    @GetMapping("/verify/{numeroImmatriculation}")
    public ResponseEntity<Boolean> verifyVehicule(@PathVariable String numeroImmatriculation) {
        boolean exists = constatService.vehiculeExistsByNumeroImmatriculation(numeroImmatriculation);
        return ResponseEntity.ok(exists);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Constat> getConstatById(@PathVariable Long id) {
        Optional<Constat> constat = constatService.getConstatById(id);
        return constat.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/enregistrer")
    public ResponseEntity<Constat> saveConstat(@RequestBody ConstatDto constatDto) {
        if (constatDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données du constat invalides.");
        }

        System.out.println("Données reçues pour enregistrement : " + constatDto);

        // Convertir le DTO en entité
        Constat constat = convertToEntity(constatDto);

        // Enregistrer dans la base de données
        Constat savedConstat = constatService.saveConstat(constat);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedConstat);
    }

    private Constat convertToEntity(ConstatDto constatDto) {
        Constat constat = new Constat();
        if (constatDto.getDateAccident() != null) {
            constat.setDateAccident(constatDto.getDateAccident().toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime());
        }        constat.setLieu(constatDto.getLieu());
        constat.setStatut(constatDto.getStatut());
        constat.setDetails(constatDto.getDetails());
        constat.setConducteurA(constatDto.getConducteurA());
        constat.setConducteurB(constatDto.getConducteurB());
        constat.setSocieteAssuranceA(constatDto.getSocieteAssuranceA());
        constat.setSocieteAssuranceB(constatDto.getSocieteAssuranceB());

        constat.setCirconstancesA(constatDto.getCirconstancesA());
        constat.setCirconstancesB(constatDto.getCirconstancesB());
        constat.setCroquisAccidentA(constatDto.getCroquisAccidentA());
        constat.setCroquisAccidentB(constatDto.getCroquisAccidentB());
        constat.setDommagesApparentsA(constatDto.getDommagesApparentsA());
        constat.setDommagesApparentsB(constatDto.getDommagesApparentsB());
        constat.setObservationsA(constatDto.getObservationsA());
        constat.setObservationsB(constatDto.getObservationsB());
        constat.setNomAssure(constatDto.getNomAssure());
        if (constatDto.getVehiculeA() != null) {
            Vehicule vehiculeA = new Vehicule();
            vehiculeA.setMarque(constatDto.getVehiculeA().getMarque());
            vehiculeA.setModele(constatDto.getVehiculeA().getModele());
            vehiculeA.setNumeroImmatriculation(constatDto.getVehiculeA().getNumeroImmatriculation());
            constat.setVehiculeA(vehiculeA);
        }

        if (constatDto.getVehiculeB() != null) {
            Vehicule vehiculeB = new Vehicule();
            vehiculeB.setMarque(constatDto.getVehiculeB().getMarque());
            vehiculeB.setModele(constatDto.getVehiculeB().getModele());
            vehiculeB.setNumeroImmatriculation(constatDto.getVehiculeB().getNumeroImmatriculation());
            constat.setVehiculeB(vehiculeB);
        }

        return constat;
    }


}


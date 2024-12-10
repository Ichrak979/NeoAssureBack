package com.example.demo.SeviceImpl;

import com.example.demo.Repository.ConstatRepository;
import com.example.demo.Repository.VehiculeRepository;
import com.example.demo.Services.ConstatService;
import com.example.demo.Services.VehiculeService;
import com.example.demo.entities.Constat;
import com.example.demo.entities.ConstatData;
import com.example.demo.entities.ConstatDto;
import com.example.demo.entities.Vehicule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ConstatServiceImpl implements ConstatService {

    @Autowired
    private VehiculeService vehiculeService;
    private final ConstatRepository constatRepository;

    @Autowired
    public ConstatServiceImpl(ConstatRepository constatRepository) {
        this.constatRepository = constatRepository;
    }

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Override
    public List<Constat> getAllConstats() {
        List<Constat> constats = constatRepository.findAll(); // Récupère tous les constats
        System.out.println("Liste des constats : " + constats.get(0).getId()); // Affiche la liste des constats
        return constats; // Retourne la liste des constats
    }

    @Override
    public Optional<Constat> getConstatById(Long id) {
        return constatRepository.findById(id);
    }

    @Override
    public Constat saveConstat(com.example.demo.entities.ConstatDto dto) {
        return null;
    }

    Constat ConstatDto;
    /*@Override
    public Constat saveConstat(ConstatDto dto) {
        // Logic for saving Constat

        return ConstatDto;
    }*/

    @Override
    public Constat saveConstat(Constat constat) {
        System.out.println("Enregistrement du constat : " + constat);
        return constatRepository.save(constat);
    }


    @Override
    public void deleteConstat(Long id) {
        constatRepository.deleteById(id);
    }

    @Override
    public Constat updateConstat(Long id, Constat constatDetails) {
        return constatRepository.save(constatDetails);
    }

    @Override
    public boolean verifyAssurance(String immatriculation) {
        return false;
    }

    @Override
    public Constat uploadConstat(Constat file) {
        return null;
    }


    @Override
    public ConstatData loadConstatDataFromJson(String constatId) {
        // Load JSON logic (same as the original)
        return null;
    }

    public Constat convertToEntity(com.example.demo.entities.ConstatDto constatDto) {
        return null;
    }

    @Override
    public Constat traiterConstatJson(MultipartFile file) {
        // Handle JSON upload
        return null;
    }

    @Override
    public Map<String, Boolean> compareWithDatabase(ConstatData constatData) {
        return null;
    }


    @Override
    public void verifyVehicules(Constat constat) {
        boolean isVehiculeAInsured = vehiculeService.isVehiculeInsured(constat.getVehiculeA().getNumeroImmatriculation());
        boolean isVehiculeBInsured = vehiculeService.isVehiculeInsured(constat.getVehiculeB().getNumeroImmatriculation());

        constat.setAssureVehiculeA(String.valueOf(isVehiculeAInsured));
        constat.setAssureVehiculeB(String.valueOf(isVehiculeBInsured));
    }
    @Override
    public boolean vehiculeExistsByNumeroImmatriculation(String numeroImmatriculation) {
        // Vérifie si un véhicule avec ce numéro d'immatriculation existe dans la base
        return vehiculeRepository.findByNumeroImmatriculation(numeroImmatriculation).isPresent();
    }



}

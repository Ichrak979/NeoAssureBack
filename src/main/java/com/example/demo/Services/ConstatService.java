package com.example.demo.Services;

import com.example.demo.Repository.ConstatRepository;
import com.example.demo.Repository.VehiculeRepository;
import com.example.demo.entities.Constat;
import com.example.demo.entities.ConstatData;
import com.example.demo.entities.ConstatDto;
import com.example.demo.entities.Vehicule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ConstatService {
    List<Constat> getAllConstats();
    Optional<Constat> getConstatById(Long id);

    Constat saveConstat(ConstatDto dto);
    void verifyVehicules(Constat constat);
    Constat saveConstat(Constat constat);
    void deleteConstat(Long id);
    Constat updateConstat(Long id, Constat constatDetails);
    boolean verifyAssurance(String immatriculation);
    Constat uploadConstat(Constat file);
    Constat traiterConstatJson(MultipartFile file);

    // New method for comparing JSON data with the database
    Map<String, Boolean> compareWithDatabase(ConstatData constatData);


    boolean vehiculeExistsByNumeroImmatriculation(String numeroImmatriculation);


    ConstatData loadConstatDataFromJson(String constatId);




}


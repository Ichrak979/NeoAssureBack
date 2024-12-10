package com.example.demo.Services;

import com.example.demo.entities.Assurance;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AssuranceService {
    Assurance createAssurance(Assurance assurance);
    List<Assurance> getAllAssurances();
    Optional<Assurance> getAssuranceById(Long id);
    Assurance updateAssurance(Long id, Assurance assurance);
    void deleteAssurance(Long id);
    String verifierAssurance(String immatriculation, Date dateAccident);
}


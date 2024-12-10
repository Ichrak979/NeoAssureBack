package com.example.demo.Services;

import com.example.demo.Repository.TypeAssuranceRepository;
import com.example.demo.entities.TypeAssurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TypeAssuranceService {



    // Création d'un type d'assurance
  public TypeAssurance createTypeAssurance(TypeAssurance typeAssurance);

    // Récupérer tous les types d'assurance
    public List<TypeAssurance> getAllTypesAssurance() ;


    // Récupérer un type d'assurance par ID
    public Optional<TypeAssurance> getTypeAssuranceById(Long id);


    // Suppression d'un type d'assurance
    public void deleteTypeAssurance(Long id) ;

}
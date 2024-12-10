package com.example.demo.Repository;

import com.example.demo.entities.TypeAssurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAssuranceRepository extends JpaRepository<TypeAssurance, Long> {
    // Ajoutez ici des méthodes de recherche personnalisées si nécessaire, sans corps
}
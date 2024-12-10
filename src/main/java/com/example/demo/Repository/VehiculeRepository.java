package com.example.demo.Repository;


import com.example.demo.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
        @Query("SELECT v FROM Vehicule v WHERE v.numeroImmatriculation = :numeroImmatriculation")
        Optional<Vehicule> findByNumeroImmatriculation(@Param("numeroImmatriculation") String numeroImmatriculation);
        @Query(value = "SELECT * FROM vehicule", nativeQuery = true)
        List<Vehicule> findAllVehicules();

        // Optionnel : Rechercher un véhicule avec ses relations chargées en un seul appel
        @Query("SELECT v FROM Vehicule v JOIN FETCH v.proprietaire p LEFT JOIN FETCH p.assurances WHERE v.id = :id")
        Optional<Vehicule> findVehiculeWithDetails(@Param("id") Long id);

        Optional<Vehicule> findById(Long id);

}






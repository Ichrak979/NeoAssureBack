package com.example.demo.Repository;

import com.example.demo.entities.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
    @Query("SELECT a FROM Assurance a WHERE a.vehicule.numeroImmatriculation = :numeroImmatriculation")
    Optional<Assurance> findByVehiculeImmatriculation(@Param("numeroImmatriculation") String numeroImmatriculation);
    Optional<Assurance> findByVehiculeId(Long vehiculeId);
    @Query("SELECT a FROM Assurance a WHERE a.vehicule.id = :vehiculeId")
    List<Assurance> findByVehiculesId(@Param("vehiculeId") Long vehiculeId);

}

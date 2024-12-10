package com.example.demo.Repository;




import com.example.demo.entities.Propriétaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropriétaireRepository extends JpaRepository<Propriétaire, Long> {
}

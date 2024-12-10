package com.example.demo.Services;



import com.example.demo.entities.Propriétaire;

import java.util.List;
import java.util.Optional;

public interface PropriétaireService {
    Propriétaire createPersonne(Propriétaire personne);
    List<Propriétaire> getAllPersonnes();
    Optional<Propriétaire> getPersonneById(Long id);
    Propriétaire updatePersonne(Long id, Propriétaire personne);
    void deletePersonne(Long id);
}

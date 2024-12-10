package com.example.demo.SeviceImpl;

import com.example.demo.Repository.PropriétaireRepository;
import com.example.demo.Services.PropriétaireService;

import com.example.demo.entities.Propriétaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropriétaireServiceImpl implements PropriétaireService {

    @Autowired
    private PropriétaireRepository propriétaireRepository;

    @Override
    public Propriétaire  createPersonne(Propriétaire propriétaire) {
        return propriétaireRepository.save(propriétaire);
    }

    @Override
    public List<Propriétaire> getAllPersonnes() {
        return propriétaireRepository.findAll();
    }

    @Override
    public Optional<Propriétaire> getPersonneById(Long id) {
        return propriétaireRepository.findById(id);
    }

    @Override
    public Propriétaire updatePersonne(Long id, Propriétaire propriétaire) {
        if (propriétaireRepository.existsById(id)) {
            propriétaire.setId(id);
            return propriétaireRepository.save(propriétaire);
        }
        return null;
    }

    @Override
    public void deletePersonne(Long id) {
        propriétaireRepository.deleteById(id);
    }
}

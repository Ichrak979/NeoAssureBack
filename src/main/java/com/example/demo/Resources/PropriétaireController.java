package com.example.demo.Resources;

import com.example.demo.Services.PropriétaireService;

import com.example.demo.entities.Propriétaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Propriétaire")
public class PropriétaireController {

    @Autowired
    private PropriétaireService propriétaireService;

    @PostMapping
    public Propriétaire createPersonne(@RequestBody Propriétaire propriétaire) {
        return propriétaireService.createPersonne(propriétaire);
    }

    @GetMapping
    public List<Propriétaire> getAllPersonnes() {
        return propriétaireService.getAllPersonnes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propriétaire> getPersonneById(@PathVariable Long id) {
        return propriétaireService.getPersonneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Propriétaire> updatePersonne(@PathVariable Long id, @RequestBody Propriétaire propriétaire) {
        Propriétaire updatedPersonne = propriétaireService.updatePersonne(id, propriétaire);
        return updatedPersonne != null ? ResponseEntity.ok(updatedPersonne) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Long id) {
        propriétaireService.deletePersonne(id);
        return ResponseEntity.noContent().build();
    }
}

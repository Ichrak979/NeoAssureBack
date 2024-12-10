package com.example.demo.Resources;

import com.example.demo.Services.TypeAssuranceService;
import com.example.demo.entities.TypeAssurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/typesassurance")
public class TypeAssuranceController {

    @Autowired
    private TypeAssuranceService typeAssuranceService;

    // Création d'un nouveau type d'assurance
    @PostMapping
    public ResponseEntity<TypeAssurance> createTypeAssurance(@RequestBody TypeAssurance typeAssurance) {
        TypeAssurance createdTypeAssurance = typeAssuranceService.createTypeAssurance(typeAssurance);
        return ResponseEntity.ok(createdTypeAssurance);
    }

    // Récupérer tous les types d'assurance
    @GetMapping
    public ResponseEntity<List<TypeAssurance>> getTypesAssurance() {
        List<TypeAssurance> typesAssurance = typeAssuranceService.getAllTypesAssurance();
        return ResponseEntity.ok(typesAssurance);
    }

    // Récupérer les noms des types d'assurance
    @GetMapping("/nom")
    public ResponseEntity<List<String>> getNomTypesAssurance() {
        List<String> typeAssuranceNoms = typeAssuranceService.getAllTypesAssurance().stream()
                .map(TypeAssurance::getNom)
                .collect(Collectors.toList());
        return ResponseEntity.ok(typeAssuranceNoms);
    }

    // Récupérer un type d'assurance par ID
    @GetMapping("/{id}")
    public ResponseEntity<TypeAssurance> getTypeAssuranceById(@PathVariable Long id) {
        return typeAssuranceService.getTypeAssuranceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Suppression d'un type d'assurance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeAssurance(@PathVariable Long id) {
        typeAssuranceService.deleteTypeAssurance(id);
        return ResponseEntity.noContent().build();
    }
}
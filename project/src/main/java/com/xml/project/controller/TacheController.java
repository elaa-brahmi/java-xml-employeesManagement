package com.xml.project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.xml.project.model.generated.*;
import com.xml.project.model.generated.Tache;
import com.xml.project.service.TacheService;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("/taches")
public class TacheController {

    private final TacheService tacheService;

    public TacheController() {
        this.tacheService = new TacheService();
    }

    // Get all tasks
    @GetMapping
    public ResponseEntity<List<Tache>> getAllTaches() throws JAXBException {
        List<Tache> taches = tacheService.voirTaches();
        return ResponseEntity.ok(taches);
    }

    // Get a single task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tache> getTacheById(@PathVariable int id) throws JAXBException {
        Tache tache = tacheService.findTacheById(id);
        if (tache != null) {
            return ResponseEntity.ok(tache);
        }
        return ResponseEntity.notFound().build();
    }

    // Add a new task
    @PostMapping
    public ResponseEntity<String> addTache(@RequestBody Tache tache) throws JAXBException {
        tacheService.addTache(tache);
        return ResponseEntity.ok("Tache added successfully");
    }

    // Update an existing task
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTache(@PathVariable int id, @RequestBody Tache updatedTache) throws JAXBException {
        Tache existingTache = tacheService.findTacheById(id);
        if (existingTache != null) {
            updatedTache.setIdTache(id); // Ensure the ID remains unchanged
            tacheService.updateTache(id,updatedTache);
            return ResponseEntity.ok("Tache updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTache(@PathVariable int id) throws JAXBException {
        Tache tache = tacheService.findTacheById(id);
        if (tache != null) {
            tacheService.deleteTache(id);
            return ResponseEntity.ok("Tache deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}

package com.xml.project.controller;

import com.xml.project.model.Employee;
import com.xml.project.model.Technician;
import com.xml.project.service.TechnicianServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technicians")
public class TechnicianController {

    private final TechnicianServiceImpl technicianService;

    public TechnicianController() {
        this.technicianService = new TechnicianServiceImpl();
    }

    // Get all technicians
    @GetMapping
    public ResponseEntity<List<Employee>> getAllTechnicians() {
        List<Employee> technicians = technicianService.getAllTechnicians();
        return new ResponseEntity<>(technicians, HttpStatus.OK);
    }

    // Get a technician by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getTechnicianById(@PathVariable int id) {
    	Employee technician = technicianService.findTechnicianById(id);
        if (technician == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(technician, HttpStatus.OK);
    }

    // Add a new technician
    @PostMapping
    public ResponseEntity<Technician> addTechnician(@RequestBody Technician technician) {
        Technician addedTechnician = technicianService.addTechnician(technician);
        return new ResponseEntity<>(addedTechnician, HttpStatus.CREATED);
    }

    // Update an existing technician
    @PutMapping("/{id}")
    public ResponseEntity<Technician> updateTechnician(
            @PathVariable int id,
            @RequestBody Technician technician) {
        Technician updatedTechnician = technicianService.updateTechnician(id, technician);
        if (updatedTechnician == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTechnician, HttpStatus.OK);
    }

    // Delete a technician by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnician(@PathVariable int id) {
        boolean isDeleted = technicianService.deleteTechnicianById(id);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

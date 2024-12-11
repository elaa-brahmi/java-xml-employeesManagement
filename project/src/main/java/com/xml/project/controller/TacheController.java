package com.xml.project.controller;

import java.util.List;

import com.xml.project.service.EmployeeServiceImpl;
import com.xml.project.service.EquipmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.xml.project.model.generated.*;
import com.xml.project.model.generated.Tache;
import com.xml.project.service.TacheService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.JAXBException;

@Controller
@RequestMapping("/taches")
public class TacheController {
    public EmployeeServiceImpl employeeService;
    public EquipmentServiceImpl equipmentService;

    private final TacheService tacheService;
    @Autowired
    public TacheController(EmployeeServiceImpl employeeService, EquipmentServiceImpl equipmentService, TacheService tacheService) {
        this.tacheService = new TacheService();
        this.employeeService = employeeService;
        this.equipmentService = equipmentService;
    }

    // Get all tasks
    @GetMapping
    public String getAllTaches(Model model) throws JAXBException {
        List<Tache> taches = tacheService.voirTaches();
        model.addAttribute("taches", taches);
        return "tache/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id,Model model) throws JAXBException {
        Tache tache = tacheService.findTacheById(id);
        if (tache != null) {
            model.addAttribute("tache", tache);
            return "tache/updateForm";
        }
        else {
            model.addAttribute("error", "Project not found.");
            return "redirect:/taches"; // Redirect if the task doesn't exist
        }
    }
    @PostMapping("/{id}")
    public String updateTache(@PathVariable("id") int id, @ModelAttribute Tache updatedTache, RedirectAttributes redirectAttributes) throws JAXBException {
        tacheService.updateTache(id, updatedTache);
        if (updatedTache != null) {
            redirectAttributes.addFlashAttribute("success", "task updated successfully!");
            return "redirect:/taches"; // Redirect to the list view after updating
        } else {
            redirectAttributes.addFlashAttribute("error", "task not found");
            return "redirect:/taches"; // Return an error template
        }
    }
    // Add a new task
    @GetMapping("/new")
    public String showAddTaskForm(Model model) {
        model.addAttribute("tache", new Tache()); // Bind a new empty task object
        return "taches/new"; // Return the "new.html" form
    }

    // Handle adding a new employee
    @PostMapping
    public String addTache(@ModelAttribute Tache tache, RedirectAttributes redirectAttributes) throws JAXBException {
        try {
            tacheService.addTache(tache);
            redirectAttributes.addFlashAttribute("success", "task added successfully!");
            return "redirect:/taches"; // Redirect to the list view after adding
        }
        catch (ReusedIdException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/taches/new";
        }
    }

    // Update an existing task


    // Delete a task
    @DeleteMapping("/delete/{id}")
    public String deleteTache(@PathVariable("id") int id, RedirectAttributes redirectAttributes,Model model) throws JAXBException {
        boolean removed = tacheService.deleteTache(id);
        if (removed) {
            redirectAttributes.addFlashAttribute("success", "Employee deleted successfully!");
            return "redirect:/taches"; // Redirect to the list view after deleting
        } else {
            model.addAttribute("error", "Employee not found");
            return "taches/list"; // Return an error template
        }
    }
}

package com.xml.project.controller;

import java.util.List;

import com.xml.project.service.EmployeeServiceImpl;
import com.xml.project.service.EquipmentServiceImpl;
import com.xml.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.xml.project.model.generated.*;
import com.xml.project.model.generated.Tache;
import com.xml.project.service.TacheService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.JAXBException;

@Controller
@RequestMapping("/taches")
public class TacheController {
    private final ProjectService projectService;
    public EmployeeServiceImpl employeeService;
    public EquipmentServiceImpl equipmentService;

    private final TacheService tacheService;
    @Autowired
    public TacheController(EmployeeServiceImpl employeeService, EquipmentServiceImpl equipmentService, TacheService tacheService, ProjectService projectService) {
        this.tacheService = new TacheService();
        this.employeeService = employeeService;
        this.equipmentService = equipmentService;
        this.projectService = projectService;
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
            model.addAttribute("employees", employeeService.getAllEmployees());
            model.addAttribute("equipments", equipmentService.getAllEquipments());
            return "tache/updateForm";
        }
        else {
            model.addAttribute("error", "Project not found.");
            return "redirect:/taches"; // Redirect if the task doesn't exist
        }
    }
    @PostMapping("/edit/{id}")
    public String updateTache(@PathVariable("id") int id, @ModelAttribute Tache updatedTache, BindingResult result, RedirectAttributes redirectAttributes) throws JAXBException {
        try{
        System.out.println("Controller method reached for ID: " + id);
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println("Validation error: " + error.getDefaultMessage());
                System.out.println("Validation error value: " + result);
            });
            redirectAttributes.addFlashAttribute("index", "Invalid form data. Please fix the errors and try again.");
            return "redirect:/tache/updateForm";
        }
        else {
            System.out.println("helloooo");
            if (updatedTache != null) {
                Employee employee = employeeService.findEmployeeById(updatedTache.getEmployees().get(0).getIdEmployee());
                Equipment equipment = equipmentService.getEquipmentById(updatedTache.getEquipments().get(0).getIdEquipment());
                if(updatedTache.getEmployees().contains(employee)) {
                    updatedTache.getEmployees().clear();
                    updatedTache.getEmployees().add(employee);
                }
                if(!updatedTache.getEquipments().contains(equipment)) {
                    updatedTache.getEquipments().clear();
                    updatedTache.getEquipments().add(equipment);
                }
                tacheService.updateTache(id, updatedTache);
                redirectAttributes.addFlashAttribute("success", "task updated successfully!");
                return "redirect:/taches"; // Redirect to the list view after updating
            } else {
                redirectAttributes.addFlashAttribute("error", "task not found");
                return "redirect:/taches"; // Return an error template
            }
        }
        }
        catch(BusyEmployeeException e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/taches/edit/" + id;
            }
        catch(BrokenEquipmentException e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/taches/edit/" + id;
        }
        }

    // Add a new task
    @GetMapping("/new")
    public String showAddTaskForm(Model model) throws JAXBException {
            model.addAttribute("tache", new Tache()); // Bind a new empty task object
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("equipments", equipmentService.getAllEquipments());
        return "tache/new"; // Return the "new.html" form
    }

    // Handle adding a new employee
    @PostMapping("/new/{id}")
    public String addTache(@ModelAttribute Tache tache, @RequestParam("idProject") int idProject, RedirectAttributes redirectAttributes) throws JAXBException {
        try {
            Employee employee = employeeService.findEmployeeById(tache.getEmployees().get(0).getIdEmployee());
            Equipment equipment = equipmentService.getEquipmentById(tache.getEquipments().get(0).getIdEquipment());
            if(tache.getEmployees().contains(employee)) {
                tache.getEmployees().clear();
                tache.getEmployees().add(employee);
            }
            if(!tache.getEquipments().contains(equipment)) {
                tache.getEquipments().clear();
                tache.getEquipments().add(equipment);
            }
            tacheService.addTacheToAproject(tache,idProject);
//            Project project=projectService.findProjectById(idProject);
//            project.getTaches().add(tache);
            redirectAttributes.addFlashAttribute("success", "task added successfully to the project having id"+idProject);
            return "redirect:/taches"; // Redirect to the list view after adding
        }
        catch (ReusedIdException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/taches/new";
        }
        catch(BusyEmployeeException e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/taches/new";
        }
        catch(BrokenEquipmentException e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/taches/new";
        }
    }



    // Delete a task
    @GetMapping("/delete/{id}")
    public String deleteTache(@PathVariable("id") int id, RedirectAttributes redirectAttributes,Model model) throws JAXBException {
        boolean removed = tacheService.deleteTache(id);
        if (removed) {
            redirectAttributes.addFlashAttribute("success", "tache deleted successfully!");
            return "redirect:/taches"; // Redirect to the list view after deleting
        } else {
            model.addAttribute("error", "tache not found");
            return "tache/list"; // Return an error template
        }
    }
}

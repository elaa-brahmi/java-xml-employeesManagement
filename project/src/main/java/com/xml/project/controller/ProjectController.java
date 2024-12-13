package com.xml.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xml.project.model.generated.*;
import com.xml.project.service.EmployeeServiceImpl;
import com.xml.project.service.EquipmentServiceImpl;
import com.xml.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    public ProjectService projectService;
    public EmployeeServiceImpl employeeService;
    public EquipmentServiceImpl equipmentService;

    @Autowired
    public ProjectController(EmployeeServiceImpl employeeService, EquipmentServiceImpl equipmentService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.equipmentService = equipmentService;
        this.projectService = projectService;
    }



    @GetMapping
    public String listProjects(Model model) {
        try {
            List<Project> projects = projectService.voirProjects();
            model.addAttribute("projects", projects);
        } catch (JAXBException e) {
            model.addAttribute("error", "Error loading projects: " + e.getMessage());
        }
        return "projects/list"; // Adjust this to the correct view name
    }

    @GetMapping("/new")
    public String showAddProjectForm(Model model) throws JAXBException {
        Project project = new Project();
        Tache tache = new Tache();
        tache.setEmployees(new ArrayList<>());
        tache.setEquipments(new ArrayList<>());
        project.getTaches().add(tache);
        model.addAttribute("project", project); // or your project object
        model.addAttribute("employees", employeeService.getAllEmployees());
       model.addAttribute("equipments", equipmentService.getAllEquipments());
        return "projects/new"; // Adjust this to the correct view name
    }
    @PostMapping
    public String addProject(@ModelAttribute Project project,BindingResult result, RedirectAttributes redirectAttributes) {
       if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
               System.out.println("Validation error: " + error.getDefaultMessage());
            });
            redirectAttributes.addFlashAttribute("error", "Invalid form data. Please fix the errors and try again.");
            return "redirect:/projects/new";
       }
        try {
            System.out.println("Validation error: i am here ");
            if (project.getTaches().isEmpty() ||
                    project.getTaches().get(0).getEmployees().isEmpty() ||
                    project.getTaches().get(0).getEquipments().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Please select an employee and equipment.");
                return "redirect:/projects/new";
            }
            try {
            Tache tache = project.getTaches().get(0);
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
            projectService.addProject(project);
            redirectAttributes.addFlashAttribute("success", "project added successfully!");
                return "redirect:/projects"; // Redirect to the list view after adding
        } catch (ReusedIdException e) {
                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return "redirect:/projects/new";
        } catch (JAXBException e) {
                redirectAttributes.addFlashAttribute("error", "Error adding project: " + e.getMessage());
                return "redirect:/projects/new";
        }
            catch(BusyEmployeeException e){
                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return "redirect:/projects/new";
            }
            catch(BrokenEquipmentException e){
                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return "redirect:/projects/new";
            }
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding project: " + e.getMessage());
            return "redirect:/projects/new";
        }
    }
    @GetMapping("/edit/{id}")
    public String showEditProjectForm(@PathVariable("id") int id,RedirectAttributes redirectAttributes, Model model) throws JAXBException {

            Project project = projectService.findProjectById(id);
            System.out.println("here1 "+id);
            System.out.println("here1 "+project);
            if (project != null) {
                model.addAttribute("project", project);
                return "projects/edit";
            } else {
                redirectAttributes.addFlashAttribute("error", "project not found!");
                return "index"; // Redirect if the project doesn't exist
            }

    }

    @PostMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable("id") int id, @RequestParam("status") StatusProjectTache status,RedirectAttributes redirectAttributes, Model model) {
        try {
            System.out.println("here update status "+id);

            projectService.updateStatusProject(id, status);
            redirectAttributes.addFlashAttribute("success", "Project status updated successfully!");
            return "redirect:/projects";
        } catch (JAXBException e) {
            model.addAttribute("error", "Error updating status: " + e.getMessage());
            return "projects/list";

        }
    }
//    @PostMapping("/edit/{id}")
//    public String editProject(@PathVariable("id") int id, @ModelAttribute Project project, RedirectAttributes redirectAttributes) {
//        try {
//            System.out.println("here2 "+id);
//            projectService.updateStatusProject(id,project.getStatus());
//            redirectAttributes.addFlashAttribute("success", "project updated successfully!");
//            return "redirect:/projects/list"; // Redirect to the list view after updating
//        }  catch (JAXBException e) {
//            redirectAttributes.addFlashAttribute("error", "Error updating project: " + e.getMessage());
//            return "redirect:/projects/edit";
//        }
//    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") int id, Model model,  RedirectAttributes redirectAttributes) {
        try {
            boolean removed=projectService.deleteProject(id);
            if(removed) {
                redirectAttributes.addFlashAttribute("success", "Project deleted successfully!");
                return "redirect:/projects";
            }
            else{
                model.addAttribute("error", "Project not found.");
                return "redirect:/projects";
            }
        } catch (JAXBException e) {
            model.addAttribute("error", "Error deleting project: " + e.getMessage());

        }
        model.addAttribute("projects", getProjectsSafe()); // Reload the list
        return "projects/list";
    }



    // Helper method to safely retrieve the project list
    private List<Project> getProjectsSafe() {
        try {
            return projectService.voirProjects();
        } catch (JAXBException e) {
            return List.of(); // Return an empty list if there's an error
        }
    }
}

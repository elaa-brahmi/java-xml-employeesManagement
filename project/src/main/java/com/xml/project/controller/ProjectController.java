package com.xml.project.controller;

import com.xml.project.model.generated.*;
import com.xml.project.service.EmployeeServiceImpl;
import com.xml.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.JAXBException;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    public ProjectService projectService;
    public EmployeeServiceImpl employeeService;

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
        model.addAttribute("project", new Project()); // or your project object
        model.addAttribute("employees", employeeService.getAllEmployees());
        //model.addAttribute("equipments", EquipmentService.getAllEquipments());
        return "projects/new"; // Adjust this to the correct view name
    }

    @PostMapping
    public String addProject(@ModelAttribute Project project, RedirectAttributes redirectAttributes) {
        try {
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

    }

    @GetMapping("/edit/{id}")
    public String showEditProjectForm(@PathVariable int id, Model model) {
        try {
            Project project = projectService.findProjectById(id);
            if (project != null) {
                model.addAttribute("project", project);
                return "projects/edit";
            } else {
                model.addAttribute("error", "Project not found.");
                return "redirect:/projects"; // Redirect if the project doesn't exist
            }
        } catch (JAXBException e) {
            model.addAttribute("error", "Error loading project: " + e.getMessage());
            return "redirect:/projects";
        }

    }

    @PostMapping("/edit/{id}")
    public String editProject(@PathVariable int id, @ModelAttribute Project project, RedirectAttributes redirectAttributes) {
        try {
            projectService.updateStatusProject(id,project.getStatus()); // Remove the old project
            redirectAttributes.addFlashAttribute("success", "project updated successfully!");
            return "redirect:/projects"; // Redirect to the list view after updating
        }  catch (JAXBException e) {
            redirectAttributes.addFlashAttribute("error", "Error updating project: " + e.getMessage());
            return "redirect:/projects/edit";
        }


    }

//    @GetMapping("/delete/{id}")
//    public String deleteProject(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
//        try {
//            boolean removed=projectService.deleteProject(id);
//            if(removed) {
//                model.addAttribute("success", "Project deleted successfully!");
//            }
//            else{
//                model.addAttribute("error", "Project not found.");
//            }
//            return "redirect:/projects";
//        } catch (JAXBException e) {
//            model.addAttribute("error", "Error deleting project: " + e.getMessage());
//
//        }
//        model.addAttribute("projects", getProjectsSafe()); // Reload the list
//        return "projects/list";
//    }

    @PostMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable int id, @RequestParam StatusProjectTache status, Model model) {
        try {
            projectService.updateStatusProject(id, status);
            model.addAttribute("success", "Project status updated successfully!");
        } catch (JAXBException e) {
            model.addAttribute("error", "Error updating status: " + e.getMessage());
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

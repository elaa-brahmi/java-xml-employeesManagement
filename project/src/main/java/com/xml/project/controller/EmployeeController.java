package com.xml.project.controller;

import com.xml.project.model.generated.Employee;
import com.xml.project.service.EmployeeService;
import com.xml.project.model.generated.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.JAXBException;
import java.util.List;

@Controller // Use @Controller for HTML views
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Fetch all employees and return as an HTML view
    @GetMapping
    public String getAllEmployees(Model model) throws JAXBException {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees); // Add the list of employees to the model
        return "employees/list"; // Return the Thymeleaf template (e.g., "list.html" in the "employees" folder)
    }


    // Fetch a single employee by ID and return as an HTML view
    @GetMapping("/profile/{id}")
    public String getEmployeeById(@PathVariable("id") int id, Model model) throws JAXBException {
        Employee employee = employeeService.findEmployeeById(id);
        if (employee != null) {
            model.addAttribute("employee", employee); // Add the employee to the model
            return "employees/detail"; // Return the template (e.g., "detail.html")
        } else {
            model.addAttribute("error", "Employee not found");
            return "employees/list"; // Return an error template
        }
    }

    // Show a form for adding a new employee
    @GetMapping("/new")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee()); // Bind a new empty employee object
        return "employees/new"; // Return the "new.html" form
    }

    // Handle adding a new employee
    @PostMapping
    public String addEmployee(@ModelAttribute Employee newEmployee, RedirectAttributes redirectAttributes) throws JAXBException {
        try {
            employeeService.addEmployee(newEmployee);
            redirectAttributes.addFlashAttribute("success", "Employee added successfully!");
            return "redirect:/employees"; // Redirect to the list view after adding
        }
        catch (ReusedIdException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/employees/new";
        }
        }


    // Show a form for updating an employee by ID
    @GetMapping("/{id}/edit")
    public String showUpdateEmployeeForm(@PathVariable("id") int id, Model model) throws JAXBException {
        Employee employee = employeeService.findEmployeeById(id);
        if (employee != null) {
            model.addAttribute("employee", employee); // Add the employee to the model
            return "employees/edit"; // Return the "edit.html" form
        } else {
            model.addAttribute("error", "Employee not found");
            return "employees/error"; // Return an error template
        }
    }

    // Handle updating an employee
    @PostMapping("/{id}")
    public String updateEmployee(@PathVariable("id") int id, @ModelAttribute Employee updatedEmployee,RedirectAttributes redirectAttributes) throws JAXBException {
        try {
            Employee employee = employeeService.updateEmployeeById(id, updatedEmployee);
            if (employee != null) {
                redirectAttributes.addFlashAttribute("success", "Employee updated successfully!");

                return "redirect:/employees"; // Redirect to the list view after updating
            } else {
                redirectAttributes.addFlashAttribute("error", "Employee not found");
                return "redirect:/employees"; // Return an error template
            }
        } catch (ReusedIdException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/employees/" + id + "/edit"; // Redirect back to the edit form
        }
        }



    // Delete an employee by ID
    @GetMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable("id") int id, RedirectAttributes redirectAttributes,Model model) throws JAXBException {
        try {
            boolean removed = employeeService.deleteEmployeeById(id);
            if (removed) {
                redirectAttributes.addFlashAttribute("success", "Employee deleted successfully!");
                return "redirect:/employees"; // Redirect to the list view after deleting
            } else {
                model.addAttribute("error", "Employee not found");
                return "employees/list"; // Return an error template
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while deleting the employee: " + e.getMessage());
            return "employees/list"; // Return an error template
        }
    }

}

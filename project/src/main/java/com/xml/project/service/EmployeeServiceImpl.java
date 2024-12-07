package com.xml.project.service;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.xml.project.model.generated.Employee;
import com.xml.project.model.generated.Employees;
import com.xml.project.model.generated.User;
import com.xml.project.model.generated.Users;
import com.xml.project.model.generated.*;

import javax.xml.bind.JAXBException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public EmployeeServiceImpl() {

    }

    private final String XML_FILE_PATH = "employees.xml";
    private final String usersFile = "users.xml";

    private List<Employee> parseXMLEmployees() throws JAXBException { // fetch list of employees from employees.xml
        XMLService xmlService = new XMLService();
        Employees employees = xmlService.unmarshalXML(XML_FILE_PATH, Employees.class);

        if (employees == null || employees.getEmployee() == null) {
            // Initialize an empty list if XML file is empty or doesn't exist
            return new ArrayList<>();
        }

        return employees.getEmployee();
    }

    private List<User> parseXMLUsers() throws JAXBException { // fetch list of users from users.xml
        XMLService xmlService = new XMLService();
        Users users = xmlService.unmarshalXML(usersFile, Users.class);
        if (users == null || users.getUser() == null) {
            // Initialize an empty list if XML file is empty or doesn't exist
            return new ArrayList<>();
        }

        return users.getUser();
    }

    @Override
    public Employee findEmployeeById(int id) throws JAXBException {
        List<Employee> employees = parseXMLEmployees();
        System.out.println(employees.stream().filter(e -> e.getIdEmployee() == id).findFirst().orElse(null));
        return employees.stream().filter(e -> e.getIdEmployee() == id).findFirst().orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() throws JAXBException {
        return parseXMLEmployees();
    }

    @Override
    public boolean deleteEmployeeById(int id) throws JAXBException {
        // Parse XML to get current data
        List<Employee> employees = parseXMLEmployees();
        List<User> users = parseXMLUsers();
        // Find the employee to delete
        Employee emp = findEmployeeById(id);
        if (emp == null) {
            // Employee does not exist
            return false;
        }
        // Get the associated user ID
        String userId = emp.getIdUser();

        // Remove the employee and user
        boolean removedEmployee = employees.removeIf(e -> e.getIdEmployee() == id);
        boolean removedUser = users.removeIf(u -> u.getIdUser().equals(userId));

        // Save back to XML if both removals succeeded
        if (removedEmployee && removedUser) {
            saveToXML(employees);
            saveToXMLUsers(users);
            System.out.println("Employee with ID " + id + " deleted successfully from employees and uses files ");

            return true;
        }

        return false;
    }


    @Override
    public Employee updateEmployeeById(int id, Employee updatedEmployee) throws JAXBException {

        List<Employee> employees = parseXMLEmployees();
        List<User> users = parseXMLUsers();

        if (updatedEmployee == null) {
            throw new IllegalArgumentException("Invalid employee data provided for update.");
        }

        // Iterate over the list of employees to find the employee with the given ID
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getIdEmployee() == id) {
                // Update the employee details
                employees.set(i, updatedEmployee);

                // Also update the associated user information if needed
                for (User user : users) {
                    if (user.getIdUser().equals(updatedEmployee.getIdUser())) {
                        // Update user information with the data from the updatedEmployee
                        user.setLogin(updatedEmployee.getLogin());
                        user.setEmail(updatedEmployee.getEmail());
                        user.setNom(updatedEmployee.getNom());
                        user.setPrenom(updatedEmployee.getPrenom());
                        user.setPassword(updatedEmployee.getPassword()); // Consider hashing the password if needed
                        break; // Exit loop once the matching user is found and updated
                    }
                }

                // Save updated lists back to XML files
                saveToXML(employees);
                saveToXMLUsers(users);

                return updatedEmployee;
            }
        }

        // Return null if the employee with the specified ID was not found
        return null;
    }


    @Override
    public void addEmployee(Employee newEmployee) throws JAXBException {
        if (isIdReused(newEmployee.getIdEmployee())) {
            throw new ReusedIdException("This ID is reused, try with another ID");
        }
        List<Employee> employees = parseXMLEmployees();
        List<User> users = parseXMLUsers();
        User newUser = new User(newEmployee); // Use the User copy constructor

        if (users.stream().anyMatch(u -> u.getIdUser().equals(newEmployee.getIdUser()))) {
            throw new IllegalArgumentException("Duplicate User ID");
        }
        users.add(newUser);
        saveToXMLUsers(users);
        // Create and add a new Employee
        Employee newEmp = new Employee(newEmployee);
        // Set the employee-specific ID
        if (employees.stream().anyMatch(e -> e.getIdEmployee() == newEmployee.getIdEmployee())) {
            throw new IllegalArgumentException("Duplicate Employee ID");
        }
        employees.add(newEmp);
        saveToXML(employees);
        System.out.println("New Employee with id" + newEmployee.getIdEmployee() + " and User with id" + newEmployee.getIdUser() + " added successfully.");
    }

    private void saveToXML(List<Employee> employees) {
        try {
            Employees wrapper = new Employees();
            wrapper.setEmployee(employees);
            XMLService xmlService = new XMLService();
            xmlService.generateXMLFromObjects(wrapper, XML_FILE_PATH);
            System.out.println("Employees updated successfully and saved to " + XML_FILE_PATH);
        } catch (Exception e) {
            System.err.println("Error saving employees to XML at " + XML_FILE_PATH + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveToXMLUsers(List<User> users) {
        try {
            Users wrapper = new Users();
            wrapper.setUser(users);
            XMLService xmlService = new XMLService();
            xmlService.generateXMLFromObjects(wrapper, usersFile);
            System.out.println("Users updated successfully and saved to " + usersFile);
        } catch (Exception e) {
            System.err.println("Error saving users to XML at " + usersFile + ": " + e.getMessage());
            e.printStackTrace();
        }

    }

    private boolean isIdReused(int id) throws JAXBException {
        List<Employee> employees = getAllEmployees();
        // Check if the ID exists in employees or users
        for (Employee employee : employees) {
            if (employee.getIdEmployee() == id) {
                return true;
            }
        }

        return false;
    }

}


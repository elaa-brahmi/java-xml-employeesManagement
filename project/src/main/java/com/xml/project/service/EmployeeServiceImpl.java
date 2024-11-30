package com.xml.project.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.xml.project.model.Employee;
import com.xml.project.model.Employees;
import com.xml.project.model.User;
import com.xml.project.model.Users;

public class EmployeeServiceImpl implements EmployeeService {
	public EmployeeServiceImpl() {}
    private final String XML_FILE_PATH = "employees.xml";
    private final String usersFile = "users.xml";

    private List<Employee> parseXMLEmployees() { // fetch list of employees from employees.xml
        XMLService xmlService = new XMLService();
        Employees employees = xmlService.unmarshalXML(XML_FILE_PATH, Employees.class);

        if (employees == null || employees.getEmployees() == null) {
            // Initialize an empty list if XML file is empty or doesn't exist
            return new ArrayList<>();
        }

        return employees.getEmployees();
    }
    private List<User> parseXMLUsers() { // fetch list of users from users.xml
        XMLService xmlService = new XMLService();
        Users users = xmlService.unmarshalXML(usersFile, Users.class);

        if (users == null || users.getUsers() == null) {
            // Initialize an empty list if XML file is empty or doesn't exist
            return new ArrayList<>();
        }

        return users.getUsers();
    }

    @Override
    public Employee findEmployeeById(int id) {
        List<Employee> employees = parseXMLEmployees();
        System.out.println(employees.stream().filter(e -> e.getIdEmployee() == id).findFirst().orElse(null));
        return employees.stream().filter(e -> e.getIdEmployee() == id).findFirst().orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return parseXMLEmployees();
    }

    @Override
    public boolean deleteEmployeeById(int id) {
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
    public Employee updateEmployeeById(int id, Employee updatedEmployee) {
        List<Employee> employees = parseXMLEmployees();
        if (updatedEmployee == null || updatedEmployee.getIdUser() == null) {
            throw new IllegalArgumentException("Invalid employee data provided for update.");
        }


        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getIdEmployee() == id) {
                employees.set(i, updatedEmployee);
                saveToXML(employees);
                return updatedEmployee;
            }
        }
         

        return null;
    }

    @Override
    public void addEmployee(Employee newEmployee) {
        List<Employee> employees = parseXMLEmployees();
        List<User> users = parseXMLUsers();
        User newUser = new User();
        newUser.setIdUser(newEmployee.getIdUser());
        newUser.setLogin(newEmployee.getLogin());
        newUser.setEmail(newEmployee.getEmail());
        newUser.setNom(newEmployee.getNom());
        newUser.setPrenom(newEmployee.getPrenom());
        newUser.setRole(newEmployee.getRole());
        newUser.setPassword(newEmployee.getPassword());
        users.add(newUser);
        saveToXMLUsers(users);
        Employee newEmp = new Employee();
        newEmp.setIdUser(newEmployee.getIdUser());
        newEmp.setCertifications(newEmployee.getCertifications());
        newEmp.setIdEmployee(newEmployee.getIdEmployee());
        newEmp.setSkills(newEmployee.getSkills());
        newEmp.setSpeciality(newEmployee.getSpeciality());
        newEmp.setRole(newEmployee.getRole());
        newEmp.setStatus(newEmployee.getStatus());
        employees.add(newEmp);
        saveToXML(employees);
        System.out.println("New Employee and User added successfully.");
    }

    private void saveToXML(List<Employee> employees) {
        try {
            Employees wrapper = new Employees();
            wrapper.setEmployees(employees);

            XMLService xmlService = new XMLService();
            xmlService.generateXMLFromObjects(wrapper, XML_FILE_PATH);
        } catch (Exception e) {
            System.err.println("Error saving employees to XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void saveToXMLUsers(List<User> users) {
        try {
            Users wrapper = new Users();
            wrapper.setUsers(users);
            XMLService xmlService = new XMLService();
            xmlService.generateXMLFromObjects(wrapper, usersFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package com.xml.project.service;

import java.util.ArrayList;
import java.util.List;

import com.xml.project.model.Employee;
import com.xml.project.model.Employees;
import com.xml.project.model.Technician;
import com.xml.project.model.Technicians;
import com.xml.project.model.User;
import com.xml.project.model.Users;

public class TechnicianServiceImpl {
	public TechnicianServiceImpl() {}
	 private final String XML_FILE_PATH = "employees.xml";
	    private final String usersFile = "users.xml";
	    private final String techniciens = "techniciens.xml";
	    
	    private List<Technician> technicianxml() { // fetch list of employees from employees.xml
	        XMLService xmlService = new XMLService();
	        Technicians techs = xmlService.unmarshalXML(techniciens, Technicians.class);

	        if (techs == null || techs.getTechnicianList() == null) {
	            // Initialize an empty list if XML file is empty or doesn't exist
	            return new ArrayList<>();
	        }

	        return techs.getTechnicianList();
	    }
	    
	    
	    private List<Employee> parseXMLEmployees() { // fetch list of employees from employees.xml
	        XMLService xmlService = new XMLService();
	        Employees employees = xmlService.unmarshalXML(XML_FILE_PATH, Employees.class);

	        if (employees == null || employees.getEmployees() == null) {
	            // Initialize an empty list if XML file is empty or doesn't exist
	            return new ArrayList<>();
	        }

	        return employees.getEmployees();
	    }
	    
	    private List<Employee> parseXMLTechnicians() {
	        XMLService xmls = new XMLService();
	        Employees emps = xmls.unmarshalXML(XML_FILE_PATH, Employees.class);

	        if (emps == null || emps.getEmployees() == null) {
	            return new ArrayList<>();
	        }

	        List<Employee> finalList = new ArrayList<>();
	        for (Employee emp : emps.getEmployees()) {
	            if ("Maintenance Technician".equals(emp.getRole())) {
	                finalList.add(emp);
	            }
	        }

	        return finalList;
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
	    public List<Employee> getAllTechnicians(){
	    	return parseXMLTechnicians();
	    }
	    public Employee findTechnicianById(int id) {
	    	List<Employee> emps=getAllTechnicians();
	    	return emps.stream().filter(t->t.getIdEmployee()==id).findFirst().orElse(null);
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
	    
	    private void saveToXMLTech(List<Technician > techs) {
	        try {
	        	Technicians wrapper = new Technicians();
	            wrapper.setTechnicianList(techs);
	            XMLService xmlService = new XMLService();
	            xmlService.generateXMLFromObjects(wrapper, techniciens);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public boolean deleteTechnicianById(int id) {
	    	
	    	List<User> users=parseXMLUsers(); // all users
	    	List<Employee> emps=parseXMLTechnicians(); //all employees with role=technician
	    	List<Technician> techs=technicianxml(); // all techicians  from technicians.xml
	    	Employee emp=findTechnicianById(id);
	    	if (emp == null) {
	            // Employee does not exist
	            return false;
	        }
	    	boolean removeduser=users.removeIf(u->u.getIdUser()==emp.getIdUser());
	    	boolean removedTecnician=emps.removeIf(t->t.getIdEmployee()==emp.getIdEmployee());
	    	boolean removedtech=techs.removeIf(t->t.getIdEmployee()==emp.getIdEmployee());
	    	if(removeduser && removedTecnician && removedtech) {
	    		saveToXML(emps);
	            saveToXMLUsers(users);
	            saveToXMLTech(techs);
	    		return true;
	    		
	    	}
	    	 return false;
	    	
	    	
	    	
	    }
	    
	    public Technician updateTechnician(int id, Technician updatedTechnician) {
	        // Fetch current data from XML files
	        List<User> users = parseXMLUsers();
	        List<Employee> employees = parseXMLTechnicians();
	        List<Technician> technicians = technicianxml();

	        // Find and update the corresponding user
	        for (int i = 0; i < users.size(); i++) {
	            if (users.get(i).getIdUser().equals(updatedTechnician.getIdUser())) {
	                User updatedUser = users.get(i);
	                
	                updatedUser.setLogin(updatedTechnician.getLogin());
	                updatedUser.setEmail(updatedTechnician.getEmail());
	                updatedUser.setNom(updatedTechnician.getNom());
	                updatedUser.setPrenom(updatedTechnician.getPrenom());
	                updatedUser.setRole(updatedTechnician.getRole());
	                updatedUser.setPassword(updatedTechnician.getPassword());
	                users.set(i, updatedUser);
	                break;
	            }
	        }

	        // Find and update the corresponding employee
	        for (int i = 0; i < employees.size(); i++) {
	            if (employees.get(i).getIdEmployee() == id) {
	                Employee updatedEmployee = employees.get(i);
	                updatedEmployee.setSkills(updatedTechnician.getSkills());
	                updatedEmployee.setStatus(updatedTechnician.getStatus());
	                updatedEmployee.setSpeciality(updatedTechnician.getSpeciality());
	                updatedEmployee.setCertifications(updatedTechnician.getCertifications());
	                employees.set(i, updatedEmployee);
	                break;
	            }
	        }

	        
	        // Save updated data back to the XML files
	        saveToXMLUsers(users);
	        saveToXML(employees);
	        

	        // Return the updated technician object
	        return updatedTechnician;
	    }
	    public Technician addTechnician(Technician newTechnician) {
	        // Fetch current data from XML files
	        List<User> users = parseXMLUsers();
	        List<Employee> employees = parseXMLTechnicians();
	        List<Technician> technicians = technicianxml();

	        // Add the technician to the users list
	        User user = new User();
	        user.setIdUser(newTechnician.getIdUser());
	        user.setLogin(newTechnician.getLogin());
	        user.setEmail(newTechnician.getEmail());
	        user.setNom(newTechnician.getNom());
	        user.setPrenom(newTechnician.getPrenom());
	        user.setRole(newTechnician.getRole());
	        user.setPassword(newTechnician.getPassword());
	        users.add(user);

	        // Add the technician to the employees list
	        Employee employee = new Employee();
	        employee.setIdUser(newTechnician.getIdUser());
	        employee.setIdEmployee(newTechnician.getIdEmployee());
	        employee.setSkills(newTechnician.getSkills());
	        employee.setStatus(newTechnician.getStatus());
	        employee.setSpeciality(newTechnician.getSpeciality());
	        employee.setCertifications(newTechnician.getCertifications());
	        employee.setRole(newTechnician.getRole());
	        employees.add(employee);

	        // Add the technician to the technicians list
	        technicians.add(newTechnician);

	        // Save updated data back to the XML files
	        saveToXMLUsers(users);
	        saveToXML(employees);
	        saveToXMLTech(technicians);

	        // Return the added technician
	        return newTechnician;
	    }
}

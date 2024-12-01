package com.xml.project;


import com.xml.project.controller.TacheController;
import com.xml.project.model.Employee;
import com.xml.project.model.Employees;
import com.xml.project.model.Equipments;
import com.xml.project.model.Tache;
import com.xml.project.model.Taches;
import com.xml.project.model.Technicians;
import com.xml.project.model.Users;
import com.xml.project.service.EmployeeServiceImpl;
import com.xml.project.service.SampleDataService;
import com.xml.project.service.TacheService;
import com.xml.project.service.XMLService;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		 SampleDataService sampleDataService = new SampleDataService();
	        Users sampleUsers = sampleDataService.createSampleUsers();
	        Employees sampleEmployees = sampleDataService.createSampleEmployees();
	        Technicians sampleTechnician = sampleDataService.createSampleTechnicians();
	        Equipments sampleEquipment = sampleDataService.createSampleEquipment();

	        // Specify the output path for the XML file
	        String users = "C:\\pfaspringboot\\project\\users.xml";
	        String employees = "C:\\pfaspringboot\\project\\employees.xml";
	        String Technicians = "C:\\pfaspringboot\\project\\techniciens.xml";
	        String equipments = "C:\\pfaspringboot\\project\\equipments.xml";
	        String tachesf = "C:\\pfaspringboot\\project\\tache.xml";

	        // Generate the XML file
	        XMLService xmlService = new XMLService();
	        xmlService.generateXMLFromObjects(sampleUsers, users);
	        xmlService.generateXMLFromObjects(sampleEmployees, employees);
	        xmlService.generateXMLFromObjects(sampleTechnician, Technicians);
	        xmlService.generateXMLFromObjects(sampleEquipment, equipments);
	        Tache tache = new Tache();
	        tache.setIdTache(2);
	        tache.setIdEmployee(15);
	        tache.setIdEquipment(1);
	        tache.setDescription("This is a new task");
	        tache.setStatus("Pending");
	        tache.setDueDate("25-01-2000");

	        // Step 2: Create a List containing the Tache
	        List<Tache> tacheList = Collections.singletonList(tache);
	        // OR: Use ArrayList if you need to modify the list later
	        // List<Tache> tacheList = new ArrayList<>();
	        // tacheList.add(tache);

	        // Step 3: Create a Taches object and assign the list
	        Taches taches = new Taches();
	        taches.setTacheList(tacheList);
	        xmlService.generateXMLFromObjects(taches, tachesf);
	        
	        
	        Employee newEmployee = new Employee();
	        newEmployee.setIdUser("5000");
	        newEmployee.setIdEmployee(5001);
	        newEmployee.setLogin("jdoe");
	        newEmployee.setEmail("jdoe@example.com");
	        newEmployee.setNom("Doe");
	        newEmployee.setPrenom("John");
	        newEmployee.setRole("Employee");
	        newEmployee.setPassword("hashedPassword");
	        newEmployee.setSkills("Waste Management");
	        newEmployee.setStatus("Active");
	        newEmployee.setSpeciality("Recycling");
	        newEmployee.setCertifications("ISO 14001");

	        EmployeeServiceImpl service = new EmployeeServiceImpl();

	        TacheService serviceTache = new TacheService();
	        service.addEmployee(newEmployee);
	       service.findEmployeeById(18);
	       service.deleteEmployeeById(5001);
	        
	        System.out.println("Adding a new task...");
	        Tache newTache = new Tache();
	        newTache.setIdTache(1);
	        newTache.setDescription("Fix the conveyor belt");
	        newTache.setDueDate("2024-12-01");
	        newTache.setIdEmployee(101);
	        newTache.setIdEquipment(201);
	        newTache.setStatus("Pending");
	        serviceTache.addTache(newTache);

	        // 2. Verify the task was added
	        System.out.println("\nFetching all tasks...");
	        System.out.println(serviceTache.voirTaches());

	        // 3. Update the task
	        System.out.println("\nUpdating the task...");
	        Tache updatedTache = new Tache();
	        updatedTache.setDescription("Repair the conveyor belt urgently");
	        updatedTache.setDueDate("2024-12-05");
	        updatedTache.setIdEmployee(102); // Change assigned employee
	        updatedTache.setIdEquipment(201); // Keep the same equipment
	        updatedTache.setStatus("In Progress");
	        serviceTache.updateTache( updatedTache);

	        // 4. Verify the task was updated
	        System.out.println("\nFetching the updated task...");
	        System.out.println(serviceTache.findTacheById(1));
	        serviceTache.findTacheById(1);

	        // 5. Delete the task
	        System.out.println("\nDeleting the task...");
	       //serviceTache.deleteTache(2);

	        // 6. Verify the task was deleted
	        System.out.println("\nFetching all tasks...");
	        System.out.println(serviceTache.voirTaches());
	    
	       
	       
	        

	        SpringApplication.run(ProjectApplication.class, args);
	    }
		
	}



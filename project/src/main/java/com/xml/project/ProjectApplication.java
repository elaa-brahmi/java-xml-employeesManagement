package com.xml.project;


import com.xml.project.model.Employees;
import com.xml.project.model.Equipments;
import com.xml.project.model.Technicians;
import com.xml.project.model.Users;
import com.xml.project.service.SampleDataService;
import com.xml.project.service.XMLService;
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

	        // Generate the XML file
	        XMLService xmlService = new XMLService();
	        xmlService.generateXMLFromObjects(sampleUsers, users);
	        xmlService.generateXMLFromObjects(sampleEmployees, employees);
	        xmlService.generateXMLFromObjects(sampleTechnician, Technicians);
	        xmlService.generateXMLFromObjects(sampleEquipment, equipments);
	        SpringApplication.run(ProjectApplication.class, args);
	    }
		
	}



package com.xml.project;
import com.xml.project.model.generated.*;
import com.xml.project.service.ProjectService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xml.project.controller.TacheController;
import com.xml.project.service.EmployeeServiceImpl;
import com.xml.project.service.TacheService;
import com.xml.project.service.XMLService;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;

@SpringBootApplication
public class ProjectApplication {
	public static void main(String[] args) throws JAXBException {
	        String users = "C:\\pfaspringboot\\project\\users.xml";
	        String employees = "C:\\pfaspringboot\\project\\employees.xml";
	        String Technicians = "C:\\pfaspringboot\\project\\techniciens.xml";
	        String equipments = "C:\\pfaspringboot\\project\\equipments.xml";
	        String taches = "C:\\pfaspringboot\\project\\tache.xml";
			String projects = "C:\\pfaspringboot\\project\\projects.xml";
	        SpringApplication.run(ProjectApplication.class, args);




		//EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
	//	Employee emp=new Employee("user009","william009","william@gmail.com","William","Anderson","password009",9,"Machine Operation","busy","Electrical Technician","Electrical Systems Certification","maintenance_technician",0)
		//employeeService.updateEmployeeById(9, )
	//	employeeService.deleteEmployeeById(20);
//		String projectName = "xml Project";
//		StatusProjectTache projectStatus = StatusProjectTache.unfinished; // Choose FINISHED or UNFINISHED
//
//		// Create a new Project instance using the constructor
//		Project newProject = new Project(2,projectName, projectStatus);
//		ProjectService ps=new ProjectService();
//		ps.addProject(newProject);
	}
		
	}



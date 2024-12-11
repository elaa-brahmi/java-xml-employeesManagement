package com.xml.project;
import com.xml.project.model.generated.*;
import com.xml.project.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xml.project.controller.TacheController;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@SpringBootApplication
public class ProjectApplication {
	public static void main(String[] args) throws JAXBException, DatatypeConfigurationException {
	        String users = "C:\\pfaspringboot\\project\\users.xml";
	        String employees = "C:\\pfaspringboot\\project\\employees.xml";
	        String Technicians = "C:\\pfaspringboot\\project\\techniciens.xml";
	        String equipments = "C:\\pfaspringboot\\project\\equipments.xml";
	        String taches = "C:\\pfaspringboot\\project\\tache.xml";
			String projects = "C:\\pfaspringboot\\project\\projects.xml";
	        SpringApplication.run(ProjectApplication.class, args);
		Tache tache = new Tache();
		tache.setIdTache(5);
		tache.setStatus(StatusProjectTache.finished);
		tache.setDescription("waste sorting");
		tache.setIdProject(5);
		XMLGregorianCalendar startDate = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(new GregorianCalendar(2024, 11, 10));
		XMLGregorianCalendar endDate = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(new GregorianCalendar(2024, 11, 15));
		//tache.setStartDate(startDate);
		//tache.setEndDate(endDate);
		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		tache.getEmployees().add(employeeService.findEmployeeById(2));
		EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
		tache.getEquipments().add(equipmentService.getEquipmentById(2));
		Project p = new Project();
		p.setIdProject(5);
		p.setStatus(StatusProjectTache.finished);
		p.setName("sorting");
		p.getTaches().add(tache);
		ProjectService projectservice=new ProjectService();
		//projectservice.updateStatusProject(5,StatusProjectTache.finished);
		//projectservice.addProject(p);
		//projectservice.updateStatusProject(3, StatusProjectTache.unfinished);
		//System.out.println(projectservice.voirProjects().getFirst().getTaches().getFirst().getEmployees().getFirst().getNom());
		TacheService tacheService=new TacheService();
		for (Tache t : tacheService.voirTaches()){
			//System.out.println(t.getDescription());
			//System.out.println(t.getStatus());

			//System.out.println(t.getEmployees().get(0).getCertification());

		}

















			XMLService xmlService = new XMLService();
		List<Project> lprojects = xmlService.unmarshalXML(projects,Projects.class).getProject();
		for (Project project : lprojects) {
			//System.out.println("Project: " + project.getName());
			if (project.getTaches() != null) {
				for (Tache task : project.getTaches()) {
					//System.out.println("Task Description: " + task.getDescription());
				//	System.out.println("Task Description: " + task.getStatus());

				}
			} else {
				//System.out.println("No tasks found!");
			}
		}
}



	}
		




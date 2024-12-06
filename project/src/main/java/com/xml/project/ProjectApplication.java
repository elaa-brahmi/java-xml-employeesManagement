package com.xml.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xml.project.controller.TacheController;
import com.xml.project.model.generated.Employee;
import com.xml.project.model.generated.Employees;
import com.xml.project.model.generated.Equipments;
import com.xml.project.model.generated.Tache;
import com.xml.project.model.generated.Taches;
import com.xml.project.model.generated.Users;
import com.xml.project.service.EmployeeServiceImpl;
import com.xml.project.service.TacheService;
import com.xml.project.service.XMLService;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {
	public static void main(String[] args) {
	        String users = "C:\\pfaspringboot\\project\\users.xml";
	        String employees = "C:\\pfaspringboot\\project\\employees.xml";
	        String Technicians = "C:\\pfaspringboot\\project\\techniciens.xml";
	        String equipments = "C:\\pfaspringboot\\project\\equipments.xml";
	        String taches = "C:\\pfaspringboot\\project\\tache.xml";
	        SpringApplication.run(ProjectApplication.class, args);
	    }
		
	}



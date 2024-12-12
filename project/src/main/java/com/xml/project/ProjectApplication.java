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

	        SpringApplication.run(ProjectApplication.class, args);
			
	}
		
	}



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

		SpringApplication.run(ProjectApplication.class, args);

		ProjectService projectService = new ProjectService();
		TacheService tacheService = new TacheService();
	//System.out.println("tache"+	tacheService.findTacheById(55));
		//tacheService.deleteTache(55);
		//projectService.updateStatusProject(1,StatusProjectTache.finished);

	}
	}
		




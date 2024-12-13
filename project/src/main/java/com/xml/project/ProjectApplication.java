package com.xml.project;
import com.xml.project.model.generated.*;
import com.xml.project.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xml.project.controller.TacheController;

import java.io.File;
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
		File file = new File("C:\\pfaspringboot\\project\\employees.xml");
		System.out.println(file.exists() ? "File found!" : "File not found.");



	}
	}
		




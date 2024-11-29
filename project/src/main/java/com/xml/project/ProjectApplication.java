package com.xml.project;


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

	        // Specify the output path for the XML file
	        String outputPath = "C:\\pfaspringboot\\project\\users.xml";

	        // Generate the XML file
	        XMLService xmlService = new XMLService();
	        xmlService.generateXMLFromObjects(sampleUsers, outputPath);
	        SpringApplication.run(ProjectApplication.class, args);
	    }
		
	}



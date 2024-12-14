package com.xml.project.service;

import com.xml.project.model.generated.Tache;
import com.xml.project.model.generated.Taches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.List;
@Service
public class XMLService {
    public XMLService(){}
    public <T> void generateXMLFromObjects(T object, String outputPath) throws JAXBException { //write object to xml file
        try {
            File file = new File(outputPath);
            if (!file.exists()) {
                throw new IllegalArgumentException("File not found: " + outputPath);
            }
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, new File(outputPath));
            System.out.println("XML file created at: " + outputPath);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public <T> T unmarshalXML(String inputPath, Class<T> clazz) throws JAXBException {//read from xml
        T object = null;
        try {
            File file = new File(inputPath);
            if (!file.exists() ) {
                throw new IllegalArgumentException("File not found: " + inputPath);
            }
            if (file.length()==0) {
                System.out.println("File is empty: " + inputPath);
                try {
                    return clazz.getDeclaredConstructor().newInstance(); // Creates a new instance of the class
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            object = (T) unmarshaller.unmarshal(new File(inputPath));
            System.out.println("XML file unmarshalled successfully from: " + inputPath);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return object;
    }
    public void mergeXMLFiles(String newFilePath, String existingFilePath) throws IOException {
        try {
            // Read the new file (the uploaded XML file)
            File newFile = new File(newFilePath);
            if (!newFile.exists()) {
                throw new FileNotFoundException("New file not found: " + newFilePath);
            }
            // Read the existing XML file
            File existingFile = new File(existingFilePath);
            if (!existingFile.exists()) {
                throw new FileNotFoundException("Existing file not found: " + existingFilePath);
            }

            // Initialize JAXB context and unmarshaller
            JAXBContext context = JAXBContext.newInstance(Taches.class); // Replace with your root class
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Unmarshal the existing file
            Taches existingContent = (Taches) unmarshaller.unmarshal(existingFile);

            // Unmarshal the new uploaded file
            Taches newContent = (Taches) unmarshaller.unmarshal(newFile);

            // Now we can merge the content (assuming your root class contains a list of items to be merged)
            List<Tache> existingItems = existingContent.getTache(); // Replace with your getter
            List<Tache> newItems = newContent.getTache(); // Replace with your getter

            // Merge the lists (you can choose to merge differently depending on the logic you need)
            existingItems.addAll(newItems); // Append new items to the existing list

            // Save the merged content back to the existing file
            // Marshal the merged content back to XML
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // Pretty print the XML
            marshaller.marshal(existingContent, existingFile); // Write the merged content to the existing file

        } catch (JAXBException e) {
            e.printStackTrace();
            throw new IOException("Error unmarshalling XML files: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error merging XML files: " + e.getMessage());
        }
    }
}

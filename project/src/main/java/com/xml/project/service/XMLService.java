package com.xml.project.service;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLService {
	public XMLService() {}

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

}

package com.xml.project.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class XMLService {

    public <T> void generateXMLFromObjects(T object, String outputPath) { //write to xml file
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, new File(outputPath));
            System.out.println("XML file created at: " + outputPath);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public <T> T unmarshalXML(String inputPath, Class<T> clazz) {//read from xml
        T object = null;
        try {
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

package com.xml.project.service;

import com.xml.project.model.generated.*;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    public ProjectService() {}
    private final String projects = "projects.xml";
    private final String taches = "tache.xml";
    private final XMLService xmlService = new XMLService();



    public List<Project> voirProjects() throws JAXBException {// read from xml
        XMLService xmlService = new XMLService();
        Projects listeProjects = xmlService.unmarshalXML(projects, Projects.class);
        return (listeProjects != null && listeProjects.getProject() != null)
                ? listeProjects.getProject()
                : new ArrayList<>();
    }
    public void saveTaches(List<Tache> tachesList) {
        try {
            Taches wrapper = new Taches();
            wrapper.setTache(tachesList);
            xmlService.generateXMLFromObjects(wrapper, taches);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveProject(List<Project> liste) {
        try {
            Projects wrapper = new Projects();
            wrapper.setProject(liste);
            XMLService xmlService = new XMLService();
            xmlService.generateXMLFromObjects(wrapper, projects);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addProject(Project project) throws JAXBException {
        List<Project> listeproject=voirProjects();
        listeproject.add(project);

        saveProject(listeproject);
        if (project.getTaches() != null && !project.getTaches().isEmpty()) {
            saveTaches(project.getTaches());
            System.out.println("taches of project having id"+ project.getIdProject()+ "are added to tache.xml");

        }
        System.out.println("projet with id"+ project.getIdProject()+ "is added");


    }

    public Project findProjectById(int id) throws JAXBException {
        return voirProjects().stream()
                .filter(t -> t.getIdProject() == id)
                .findFirst()
                .orElse(null);
    }


    public void deleteProject(int id) throws JAXBException {
        List<Project> listeProjects = voirProjects();
        Project t = findProjectById(id);
        if (t != null) {
            listeProjects.remove(t);
            saveProject(listeProjects);
            System.out.println("project with ID " + id + " is deleted");
        } else {
            System.out.println("project with ID " + id + " not found");
        }
    }



    public void updateStatusProject(int id, StatusProjectTache status) throws JAXBException {
        if (status == null) {
            System.out.println("Cannot update project status: provided status is null.");
            return;
        }
        List<Project> listeprojets = voirProjects();
        for (int i = 0; i < listeprojets.size(); i++) {
            if (listeprojets.get(i).getIdProject() == id) {
                listeprojets.get(i).setStatus(status);
                saveProject(listeprojets);
                System.out.println("project with ID " + id + " is updated");
                return;
            }
        }
        System.out.println("project with ID " + id + " not found");
    }


}

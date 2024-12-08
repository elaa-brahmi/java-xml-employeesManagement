
package com.xml.project.service;

import com.xml.project.model.generated.*;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@Service
public class ProjectService {

    public ProjectService() {}
    private final String projects = "C:\\pfaspringboot\\project\\projects.xml";
    private final XMLService xmlService = new XMLService();

    public List<Project> voirProjects() throws JAXBException {// read from xml

        Projects listeProjects = xmlService.unmarshalXML(projects, Projects.class);
        return (listeProjects != null && listeProjects.getProject() != null)
                ? listeProjects.getProject()
                : new ArrayList<>();
    }
    public void saveProject(List<Project> liste) {
        try {
            Projects wrapper = new Projects();
            wrapper.setProject(liste);

            xmlService.generateXMLFromObjects(wrapper, projects);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean isIdReusedProject(int id) throws JAXBException {
        List<Project> projects = voirProjects();
        // Check if the ID exists in employees or users
        for (Project p : projects) {
            if (p.getIdProject() == id) {
                return true;
            }
        }

        return false;
    }

    public void addProject(Project project) throws JAXBException {
        List<Project> listeproject = voirProjects();
        if (isIdReusedProject(project.getIdProject())) {
            throw new ReusedIdException("This ID is reused, try with another ID");
        }
        listeproject.add(project);
        saveProject(listeproject);
        TacheService tacheService = new TacheService();
        if (project.getTaches() != null) {
            for (Tache tache : project.getTaches()) {
                tache.setIdProject(project.getIdProject()); // Ensure the task is linked to the correct project
                tacheService.addTache(tache);
                System.out.println("taches of project having id" + project.getIdProject() + "are added to tache.xml");
            }
        }
        System.out.println("projet with id" + project.getIdProject() + "is added");


    }

    public Project findProjectById(int id) throws JAXBException {
        return voirProjects().stream()
                .filter(t -> t.getIdProject() == id)
                .findFirst()
                .orElse(null);
    }


    public boolean deleteProject(int id) throws JAXBException {
        List<Project> listeProjects = voirProjects();
        Project t = findProjectById(id);
        TacheService tacheService = new TacheService();
       List<Tache> liste= tacheService.findTachesByIdProjet(id);
       for(Tache tache : liste) {
           tacheService.deleteTache(tache.getIdTache());
       }
       boolean removed=listeProjects.remove(t);
       saveProject(listeProjects);

       Logger logger = Logger.getLogger(ProjectService.class.getName());
       logger.info("Project with ID " + id + " is deleted.");
        if(removed) {
            return true;
        }

        return false;
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
                System.out.println("status of project with ID " + id + " is updated");
                return;
            }
        }
        System.out.println("project with ID " + id + " not found");
    }


}

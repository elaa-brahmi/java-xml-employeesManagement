
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
    private static final Logger logger = Logger.getLogger(TacheService.class.getName());


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
            logger.severe("Error saving Project: " + e.getMessage());
        }
    }
    private boolean isIdReusedProject(int id) throws JAXBException {
        List<Project> projects = voirProjects();
        // Check if the ID exists in employees or users
        return projects.stream().anyMatch(p->p.getIdProject() == id);}
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
                logger.info("Taches for project ID " + project.getIdProject() + " are added to tache.xml");
            }
        }
        if(project.getStatus()==StatusProjectTache.valueOf("finished")){
            int id=project.getTaches().get(0).getEmployees().get(0).getIdEmployee();
            EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
            employeeService.updateStatusEmployee(id,StatusEmployee.valueOf("free"));
        }
        logger.info("Project with ID " + project.getIdProject() + " is added");
    }

    public Project findProjectById(int id) throws JAXBException {
        return voirProjects().stream()
                .filter(t -> t.getIdProject() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Project with ID " + id + " not found"));
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
        if(removed) {
            saveProject(listeProjects);
            logger.info("Project with ID " + id + " is deleted.");
        }
        else{
            logger.warning("Project with ID " + id + " not found.");
        }
        return removed;
    }
    public void updateStatusProject(int id, StatusProjectTache status) throws JAXBException {
        if (status == null) {
            logger.warning("Cannot update project status: provided status is null.");
            return;
        }
        if(status==StatusProjectTache.fromValue("finished")){
            List<Project> listeprojets = voirProjects();
            for (int i = 0; i < listeprojets.size(); i++) {
                if (listeprojets.get(i).getIdProject() == id) {
                    listeprojets.get(i).getTaches().get(0).setStatus(StatusProjectTache.fromValue("finished"));
                    listeprojets.get(i).setStatus(status);
                    saveProject(listeprojets);
                    logger.info("Status of project with ID " + id + " is updated.");
                    return;
                }
            }


        }

        logger.warning("Project with ID " + id + " not found.");
    }
    public void updateProject(int id, Project project) throws JAXBException {


            List<Project> listeprojets = voirProjects();
            for (int i = 0; i < listeprojets.size(); i++) {
                if (listeprojets.get(i).getIdProject() == id) {
                    listeprojets.set(i, project);
                    saveProject(listeprojets);
                    logger.info("project with ID " + id + " is updated.");
                    return;
                }
            }
        logger.warning("Project with ID " + id + " not found.");
    }
}

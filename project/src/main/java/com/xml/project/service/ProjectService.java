
package com.xml.project.service;

import com.xml.project.model.generated.*;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
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
        TacheService tacheService = new TacheService();
        List<Tache> listeTaches=tacheService.voirTaches();
        for(Tache t : listeTaches) {
            LocalDate newStartDate = LocalDate.parse(project.getTaches().get(0).getStartDate(), DateTimeFormatter.ISO_DATE);
            LocalDate newEndDate = LocalDate.parse(project.getTaches().get(0).getEndDate(), DateTimeFormatter.ISO_DATE);
            LocalDate existingStartDate = LocalDate.parse(t.getStartDate(), DateTimeFormatter.ISO_DATE);
            LocalDate existingEndDate = LocalDate.parse(t.getEndDate(), DateTimeFormatter.ISO_DATE);
            if (project.getTaches().get(0).getEmployees().get(0).getIdEmployee() == t.getEmployees().get(0).getIdEmployee() && !(newEndDate.isBefore(existingStartDate) || newStartDate.isAfter(existingEndDate))) {
                throw new BusyEmployeeException("this employee is busy at that period of time , please choose another one");
            }
            if (project.getTaches().get(0).getEquipments().get(0).getIdEquipment() == t.getEquipments().get(0).getIdEquipment() && !(newEndDate.isBefore(existingStartDate) || newStartDate.isAfter(existingEndDate))) {
                throw new NotAvailableEquipement("this equipment is not available at that period of time ");

            }
        }
        if(project.getTaches().get(0).getEquipments().get(0).getStatus()==StatusEquipment.broken){
            throw new BrokenEquipmentException("this equipment is broken , please choose another one");
        }
        List<Project> listeproject = voirProjects();
        if (isIdReusedProject(project.getIdProject())) {
            throw new ReusedIdException("This ID is reused, try with another ID");
        }
        List<Tache> taches=project.getTaches();
        if (taches != null) {
            for (Tache tache : taches) {
                tache.setIdProject(project.getIdProject());
                tache.getEquipments().get(0).setIdTache(tache.getIdTache());
                tache.getEmployees().get(0).setIdTache(tache.getIdTache());// Ensure the task is linked to the correct project
                tacheService.addTache(tache);
                listeproject.add(project);
                saveProject(listeproject);
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
        // Fetch the project
        ProjectService projectService = new ProjectService();
        List<Project> listeProjects = projectService.voirProjects();
        Project projectToDelete = null;

        for (Project project : listeProjects) {
            if (project.getIdProject() == id) {
                projectToDelete = project;
                break;
            }
        }

        if (projectToDelete == null) {
            System.out.println("Project with ID " + id + " not found.");
            return false;
        }
        System.out.println("Project " + projectToDelete + " is found.");

        // Initialize services
        TacheService tacheService = new TacheService();
        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

        List<Employee> listeEmp = employeeService.getAllEmployees();
        List<Equipment> listeEqu = equipmentService.getAllEquipments();
        List<Tache> listeTaches = tacheService.voirTaches();

        // Delete tasks associated with the project
        boolean tasksDeleted = false;

        List<Tache> projectTaches = new ArrayList<>(projectToDelete.getTaches()); // Avoid modifying the original list during iteration
        for (Tache tache : projectTaches) {
            // Release employees associated with the task
            for (Employee employee : listeEmp) {
                if (employee.getIdTache() == tache.getIdTache()) {
                    employee.setIdTache(0);
                    employee.setStatus(StatusEmployee.free);
                }
            }
            employeeService.saveToXML(listeEmp);

            // Release equipment associated with the task
            for (Equipment eq : listeEqu) {
                if (eq.getIdTache() == tache.getIdTache()) {
                    eq.setIdTache(0);
                }
            }
            equipmentService.saveToXmlEquipments(listeEqu);
            // Remove the task from the global task list
            Tache taskToRemove = tacheService.findTacheById(tache.getIdTache());
            if (taskToRemove != null && listeTaches.remove(taskToRemove)) {
                tasksDeleted = true;
            }
        }
        if (tasksDeleted) {
            tacheService.saveTache(listeTaches);
            System.out.println("All tasks associated with Project ID " + id + " have been deleted.");
        }

        // Remove the project itself
        boolean removed = listeProjects.remove(projectToDelete);
        if (removed) {
            projectService.saveProject(listeProjects);
            System.out.println("Project with ID " + id + " is deleted.");
        } else {
            System.out.println("Project with ID " + id + " not found in the list.");
        }

        return removed;
    }

    public void updateStatusProject(int id, StatusProjectTache status) throws JAXBException {

        // Retrieve services and all relevant data
        Project project = findProjectById(id);
        TacheService tacheService = new TacheService();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();

        List<Employee> listeEmp = employeeService.getAllEmployees();
        List<Equipment> listeEqu = equipmentService.getAllEquipments();
        List<Tache> taches = tacheService.voirTaches();
        List<Project> listeprojets = voirProjects();

        // Find the project to update
        for (Project proj : listeprojets) {
            if (proj.getIdProject() == id) {

                // Update the project status to "finished"
                if (status == StatusProjectTache.finished) {
                    // Iterate over tasks of the project and update status
                    for (Tache tache : proj.getTaches()) {
                        // Update employee status and clear idTache
                        for (Employee employee : listeEmp) {
                            if (employee.getIdTache() == tache.getIdTache()) {
                                employee.setIdTache(0);  // Unassign the employee from the task
                                employee.setStatus(StatusEmployee.free);  // Set employee status to free
                            }
                        }

                        // Update equipment status and clear idTache
                        for (Equipment eq : listeEqu) {
                            if (eq.getIdTache() == tache.getIdTache()) {
                                eq.setIdTache(0);  // Unassign the equipment from the task
                            }
                        }

                        // Update task status and clear its employees and equipment references
                        tache.setStatus(StatusProjectTache.finished);  // Set task status to finished
                        tache.getEmployees().get(0).setIdTache(0);  // Unassign employee
                        tache.getEmployees().get(0).setStatus(StatusEmployee.free);  // Set employee status to free
                        tache.getEquipments().get(0).setIdTache(0);  // Unassign equipment
                    }

                    // Update tasks' status in the taches list as well
                    for (Tache tache : taches) {
                        if (tache.getIdProject() == id) {
                            tache.setStatus(StatusProjectTache.finished);  // Set task status to finished
                            // Make sure employees and equipment are also updated in the taches list
                            tache.getEmployees().get(0).setIdTache(0);  // Unassign employee
                            tache.getEmployees().get(0).setStatus(StatusEmployee.free);  // Set employee status to free
                            tache.getEquipments().get(0).setIdTache(0);  // Unassign equipment
                        }
                    }
                    // Save updated tasks
                    tacheService.saveTache(taches);
                }
                // Save updated employees and equipment
                employeeService.saveToXML(listeEmp);
                equipmentService.saveToXmlEquipments(listeEqu);

                // Update project status
                proj.setStatus(status);
                saveProject(listeprojets);

                logger.info("Status of project with ID " + id + " is updated.");
                return;
            }
        }
        // If project with the given ID was not found
        logger.warning("Project with ID " + id + " not found.");
    }
}

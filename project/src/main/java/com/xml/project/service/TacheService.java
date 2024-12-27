package com.xml.project.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.xml.project.model.generated.*;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
@Service
public class TacheService {


	 private final String taches = "tache.xml";
	private static final Logger logger = Logger.getLogger(TacheService.class.getName());

	public TacheService() {}
	 
	 public List<Tache> voirTaches() throws JAXBException {
		    XMLService xmlService = new XMLService();
		    Taches listeTaches = xmlService.unmarshalXML(taches, Taches.class);
		    return (listeTaches != null && listeTaches.getTache() != null)
		        ? listeTaches.getTache()
		        : new ArrayList<>();
		}

	 
	 public void saveTache(List<Tache> liste) {
		 try {
	            Taches wrapper = new Taches();
	            wrapper.setTache(liste);
	            XMLService xmlService = new XMLService();
	            xmlService.generateXMLFromObjects(wrapper, taches);
	        } catch (Exception e) {
			 logger.severe("Error saving Tache: " + e.getMessage());
	        }
	 }
	public void addTacheToAproject(Tache tache, int idProject) throws JAXBException {
		List<Tache> listeTaches = voirTaches();
		boolean idExist = listeTaches.stream().anyMatch(t -> t.getIdTache() == tache.getIdTache());
		if (idExist) {
			throw new ReusedIdException("Tache with ID " + tache.getIdTache() + " already exists");
		}
		for(Tache t: listeTaches) {
			LocalDate newStartDate = LocalDate.parse(tache.getStartDate(), DateTimeFormatter.ISO_DATE);
			LocalDate newEndDate = LocalDate.parse(tache.getEndDate(), DateTimeFormatter.ISO_DATE);
			LocalDate existingStartDate = LocalDate.parse(t.getStartDate(), DateTimeFormatter.ISO_DATE);
			LocalDate existingEndDate = LocalDate.parse(t.getEndDate(), DateTimeFormatter.ISO_DATE);
			if (tache.getEmployees().get(0).getIdEmployee() == t.getEmployees().get(0).getIdEmployee() && !(newEndDate.isBefore(existingStartDate) && newStartDate.isAfter(existingEndDate)) ) {
					throw new BusyEmployeeException("This employee is busy at that period of time,please choose another employee");
			}
			if(tache.getEquipments().get(0).getIdEquipment()==t.getEquipments().get(0).getIdEquipment() && !(newEndDate.isBefore(existingStartDate) && newStartDate.isAfter(existingEndDate))  ) {
				throw new NotAvailableEquipement("this equipment with ID "+tache.getEquipments().get(0).getIdEquipment()+" is not available at this moment , please choose another one !");
			}
		}
		if (tache.getEquipments().get(0).getStatus() == StatusEquipment.broken) {
			throw new BrokenEquipmentException("This equipment is broken, please choose another equipment");
		}
		if (tache.getEquipments().get(0).getStatus() == StatusEquipment.under_maintenance) {
			throw new UnderMaintenanceException("This equipment is under maintenance, please choose another equipment");
		}
		// Check if the project ID exists before proceeding
		ProjectService projectService = new ProjectService();
		List<Project> listeProjects = projectService.voirProjects();
		boolean projectFound = false;

		for (Project project : listeProjects) {
			if (project.getIdProject() == idProject) {
				project.getTaches().add(tache);
				projectFound = true;
				break;
			}
		}
		if (!projectFound) {
			throw new UnfoundIdException("The chosen project ID does not exist");
		}
		// Update employee and equipment status
		tache.getEmployees().get(0).setIdTache(tache.getIdTache());
		tache.getEquipments().get(0).setIdTache(tache.getIdTache());

		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		List<Employee> employees = employeeService.getAllEmployees();
		for (Employee employee : employees) {
			if (employee.getIdEmployee() == tache.getEmployees().get(0).getIdEmployee()) {
				employee.setIdTache(tache.getIdTache());
				employee.setStatus(StatusEmployee.busy);
				employeeService.saveToXML(employees);
				break;
			}
		}

		EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
		List<Equipment> equipments = equipmentService.getAllEquipments();
		for (Equipment eq : equipments) {
			if (eq.getIdEquipment() == tache.getEquipments().get(0).getIdEquipment()) {
				eq.setIdTache(tache.getIdTache());
				equipmentService.saveToXmlEquipments(equipments);
				break;
			}
		}

		tache.getEmployees().get(0).setStatus(StatusEmployee.busy);
		listeTaches.add(tache);
		saveTache(listeTaches);
		logger.info("Tache with ID " + tache.getIdTache() + " is added");

		// Save the updated project list
		projectService.saveProject(listeProjects);
		logger.info("Tache with ID " + tache.getIdTache() + " is added to the project with ID " + idProject);
	}

	public void addTache(Tache tache) throws JAXBException {
		 List<Tache> listeTaches=voirTaches();
		 boolean idExists = listeTaches.stream()
				 .anyMatch(existingTache -> existingTache.getIdTache() == tache.getIdTache());
		 if (idExists) {
			 throw new ReusedIdException("Tache with ID " + tache.getIdTache() + " already exists.");
		 }
		 if(tache.getEmployees().get(0).getStatus()==StatusEmployee.busy){
			 throw new BusyEmployeeException("this employee is busy , please choose another one");

		 }
		 if(tache.getEquipments().get(0).getStatus()==StatusEquipment.broken){
			 throw new BrokenEquipmentException("this equipment is broken , please choose another one");
		 }
		 if(tache.getEquipments().get(0).getStatus()==StatusEquipment.under_maintenance){
			 throw new UnderMaintenanceException("this equipment is currently under maintenance, please choose another one");
		 }
		 EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		 List<Employee> employees=employeeService.getAllEmployees();
		 for (Employee employee : employees) {
			 if (employee.getIdEmployee() == tache.getEmployees().get(0).getIdEmployee()) {
				 employee.setIdTache(tache.getIdTache());
				 employee.setStatus(StatusEmployee.valueOf("busy"));
				 employeeService.saveToXML(employees);
				 break;
			 }
		 }
		 EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
		 List<Equipment> equipments=equipmentService.getAllEquipments();
		 for (Equipment eq : equipments) {
			 if (eq.getIdEquipment() == tache.getEquipments().get(0).getIdEquipment()) {
				 eq.setIdTache(tache.getIdTache());
				 equipmentService.saveToXmlEquipments(equipments);
				 break;
			 }
		 }
		 tache.getEmployees().get(0).setIdTache(tache.getIdTache());
		 tache.getEquipments().get(0).setIdTache(tache.getIdTache());
		 tache.getEmployees().get(0).setStatus(StatusEmployee.busy);
		 listeTaches.add(tache);
		 saveTache(listeTaches);
		 logger.info("Tache with ID " + tache.getIdTache() + " is added.");
	 }





	public List<Tache> findTachesByIdProjet(int id) throws JAXBException {
		    return voirTaches().stream()
		        .filter(t -> t.getIdProject() == id).collect(Collectors.toList());

		}
		public Tache findTacheById(int id) throws JAXBException {
			return voirTaches().stream().filter(t-> t.getIdTache() == id).findFirst().orElseThrow(() -> new RuntimeException("Tache with ID " + id + " not found"));
		}

	 
	 public boolean deleteTache(int id) throws JAXBException {
		    Tache t = findTacheById(id);
			if(t==null){
				System.out.println("task not found");
				return false;
			}
			System.out.println("Tache  " + t + " is found.");
			EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
			EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		 	ProjectService projectService = new ProjectService();

			List<Employee> listeEmp=employeeService.getAllEmployees();
			for (Employee employee : listeEmp) {
				if (employee.getIdTache() == id) {
					employee.setIdTache(0);
					employee.setStatus(StatusEmployee.free);
					employeeService.saveToXML(listeEmp);
					break;
				}
			}
			List<Equipment> listeEqu=equipmentService.getAllEquipments();
			for (Equipment eq : listeEqu) {
				if (eq.getIdTache() == id) {
					eq.setIdTache(0);
					equipmentService.saveToXmlEquipments(listeEqu);
					break;
				}
			}
			List<Project> listeProjects=projectService.voirProjects();
			boolean updatedProject=false;
			for (Project project : listeProjects) {
				Iterator<Tache> iterator = project.getTaches().iterator();
				while (iterator.hasNext()) {
					Tache tache = iterator.next();
					if (tache.getIdTache() == id) {
						iterator.remove(); // Safely remove the task using the iterator
						updatedProject=true; //
					}
				}
			}
			if(updatedProject){
				projectService.saveProject(listeProjects);
			}
		 List<Tache> listeTaches = voirTaches();
			boolean removed=listeTaches.remove(t);
			if(removed){
				saveTache(listeTaches);
				System.out.println("Tache with ID " + id + " is deleted.");
			}
		     else {
		        System.out.println("Tache with ID " + id + " not found");
		    }
			return removed;
		}
	 public Tache updateTache(int id,Tache updatedTache) throws JAXBException {

		List<Tache> listeTaches = voirTaches();
		 for(Tache t: listeTaches) {
			 LocalDate newStartDate = LocalDate.parse(updatedTache.getStartDate(), DateTimeFormatter.ISO_DATE);
			 LocalDate newEndDate = LocalDate.parse(updatedTache.getEndDate(), DateTimeFormatter.ISO_DATE);
			 LocalDate existingStartDate = LocalDate.parse(t.getStartDate(), DateTimeFormatter.ISO_DATE);
			 LocalDate existingEndDate = LocalDate.parse(t.getEndDate(), DateTimeFormatter.ISO_DATE);
			 if ( updatedTache.getEmployees().get(0).getIdEmployee()!=findTacheById(id).getEmployees().get(0).getIdEmployee()  &&   updatedTache.getEmployees().get(0).getIdEmployee() == t.getEmployees().get(0).getIdEmployee() && !(newEndDate.isBefore(existingStartDate) && newStartDate.isAfter(existingEndDate)) ) {
				 throw new BusyEmployeeException("This employee is busy at that period of time,please choose another employee");
			 }
			 if( updatedTache.getEquipments().get(0).getIdEquipment()!=findTacheById(id).getEquipments().get(0).getIdEquipment() &&  updatedTache.getEquipments().get(0).getIdEquipment()==t.getEquipments().get(0).getIdEquipment() && !(newEndDate.isBefore(existingStartDate) && newStartDate.isAfter(existingEndDate))  ) {
				 throw new NotAvailableEquipement("this equipment with ID "+updatedTache.getEquipments().get(0).getIdEquipment()+" is not available at this moment , please choose another one !");
			 }
		 }

		if(updatedTache.getEquipments().get(0).getStatus()==StatusEquipment.broken){
			throw new BrokenEquipmentException("this equipment is broken , please choose another one");
		}
		 if(updatedTache.getEquipments().get(0).getStatus()==StatusEquipment.under_maintenance){
			 throw new UnderMaintenanceException("this equipment is under maintenance , please choose another one");
		 }
		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		List<Employee> listeEmp=employeeService.getAllEmployees();
		 for (Employee employee : listeEmp) {
				 // Check if this employee is different from the updated employee
				 if (employee.getIdTache() == id && employee.getIdEmployee() != updatedTache.getEmployees().get(0).getIdEmployee()) {
					 // Reset the old employee's status
					 employee.setIdTache(0);
					 employee.setStatus(StatusEmployee.free);
				 }
			 // Check if this employee is the one being assigned to the updated task
			 if (employee.getIdEmployee() == updatedTache.getEmployees().get(0).getIdEmployee()) {
				 // Update their task ID and status
				 employee.setIdTache(id);
				 employee.setStatus(StatusEmployee.busy);
			 }
		 }
		 employeeService.saveToXML(listeEmp);
		EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
		List<Equipment> listeEqu=equipmentService.getAllEquipments();
		for (Equipment eq : listeEqu) {
				//equipment updated
				if(eq.getIdTache()==id && eq.getIdEquipment()!=updatedTache.getEquipments().get(0).getIdEquipment()){
					eq.setIdTache(0);
				}
			if(eq.getIdEquipment()==updatedTache.getEquipments().get(0).getIdEquipment()){
				eq.setIdTache(id);
			}
		}
		 equipmentService.saveToXmlEquipments(listeEqu);

		ProjectService projectService=new ProjectService();
			List<Project> listeProjects=projectService.voirProjects();
		    for (int i = 0; i < listeTaches.size(); i++) {
		        if (listeTaches.get(i).getIdTache() == id) {
					updatedTache.getEquipments().get(0).setIdTache(id);
					updatedTache.getEmployees().get(0).setIdTache(id);
					updatedTache.getEmployees().get(0).setStatus(StatusEmployee.busy);
		            listeTaches.set(i, updatedTache);
					saveTache(listeTaches);
					logger.info("Tache with ID " + id + " is updated");
						break;
		        }
		    }
		 for(Project p:listeProjects){
			 for(Tache t:p.getTaches()){
				 if(t.getIdTache()==id){
					 p.getTaches().remove(t);
					 p.getTaches().add(updatedTache);
					 break;
				 }
			 }
		 }
		 projectService.saveProject(listeProjects);
		 return updatedTache;}
}

package com.xml.project.service;

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
	 public void addTacheToAproject(Tache tache,int idProject) throws JAXBException {
		List<Tache> listeTaches=voirTaches();
		boolean idExist=listeTaches.stream().anyMatch(t->t.getIdTache()==tache.getIdTache());
		if(idExist) {
			throw new ReusedIdException("tache with ID "+tache.getIdTache()+" already exists");
		}
		if(tache.getEmployees().get(0).getStatus()==StatusEmployee.busy){
			throw new BusyEmployeeException("this employee is busy, please choose another employee");
		}
		if(tache.getEquipments().get(0).getStatus()==StatusEquipment.broken){
			throw new BrokenEquipmentException("this equipment is broken, please choose another equipment");
		}
		if(tache.getEquipments().get(0).getStatus()==StatusEquipment.under_maintenance){
			throw new UnderMaintenanceException("this equipment is under maintenance, please choose another equipment");
		}
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
		 logger.info("tache with id "+tache.getIdTache()+" is added");
		 ProjectService projectService=new ProjectService();
		 List<Project> listeProjects=projectService.voirProjects();
		 boolean projectFound = false;
		 for (Project project : listeProjects) {
			 if (project.getIdProject() == idProject) {
				 project.getTaches().add(tache);
				 projectFound = true;
				 break;
			 }
		 }
		 if(projectFound) {
			 projectService.saveProject(listeProjects);
			 logger.info("tache with id "+tache.getIdTache()+" is added to the project with id "+idProject);
		 }
		 else{
			 throw new UnfoundIdException("the chosen ID project does not exist");
		 }
		 
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


	public void assignResourcesToTask(int taskId, List<Employee> employees, List<Equipment> equipments) throws JAXBException {
		List<Tache> taches = voirTaches();
		for (Tache tache : taches) {
			if (tache.getIdTache() == taskId) {
				tache.setEmployees(employees);
				tache.setEquipments(equipments);
				saveTache(taches);
				logger.info("Resources assigned to Tache ID " + taskId);
				return;
			}
		}
		logger.warning("Tache with ID " + taskId + " not found.");
	}


	public List<Tache> findTachesByIdProjet(int id) throws JAXBException {
		    return voirTaches().stream()
		        .filter(t -> t.getIdProject() == id).collect(Collectors.toList());

		}
		public Tache findTacheById(int id) throws JAXBException {
			return voirTaches().stream().filter(t-> t.getIdTache() == id).findFirst().orElseThrow(() -> new RuntimeException("Tache with ID " + id + " not found"));
		}

	 
	 public boolean deleteTache(int id) throws JAXBException {
		    List<Tache> listeTaches = voirTaches();
			boolean removed;
		    Tache t = findTacheById(id);
			System.out.println("Tache  " + t + " is found.");
			EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
			EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
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
			ProjectService projectService = new ProjectService();
			List<Project> listeProjects=projectService.voirProjects();
			for (Project project : listeProjects) {
				Iterator<Tache> iterator = project.getTaches().iterator();
				while (iterator.hasNext()) {
					Tache tache = iterator.next();
					if (tache.getIdTache() == id) {
						iterator.remove(); // Safely remove the task using the iterator
						break; // Exit since only one task is associated with the project
					}
				}
			}
			projectService.saveProject(listeProjects);
		    if (listeTaches.remove(t) ) {
				removed = true;
		        saveTache(listeTaches);
				System.out.println("Tache with ID " + id + " is deleted.");
		    } else {
		        System.out.println("Tache with ID " + id + " not found");
				removed=false;
		    }
			return removed;
		}
	 public Tache updateTache(int id,Tache updatedTache) throws JAXBException {
		if(updatedTache.getEquipments().get(0).getStatus()==StatusEquipment.broken){
			throw new BrokenEquipmentException("this equipment is broken , please choose another one");
		}
		if(updatedTache.getEmployees().get(0).getStatus()==StatusEmployee.busy){
			throw new BusyEmployeeException("this employee is busy , please choose another one");
		}
		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		List<Employee> listeEmp=employeeService.getAllEmployees();
		for (Employee employee : listeEmp) {
			if(employee.getIdEmployee()==updatedTache.getEmployees().get(0).getIdEmployee()){
				employee.setIdTache(id);
				employee.setStatus(StatusEmployee.busy);
				employeeService.saveToXML(listeEmp);
				break;

			}

		}
		EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
		List<Equipment> listeEqu=equipmentService.getAllEquipments();
		for (Equipment eq : listeEqu) {
			if(eq.getIdEquipment()==updatedTache.getEquipments().get(0).getIdEquipment()){
				eq.setIdTache(id);
				equipmentService.saveToXmlEquipments(listeEqu);
				break;

			}
		}


		ProjectService projectService=new ProjectService();
		    List<Tache> listeTaches = voirTaches();
			List<Project> listeProjects=projectService.voirProjects();
		    for (int i = 0; i < listeTaches.size(); i++) {
		        if (listeTaches.get(i).getIdTache() == id) {
		            listeTaches.set(i, updatedTache);
					listeTaches.get(i).getEmployees().get(0).setStatus(StatusEmployee.busy);
		            saveTache(listeTaches);
					logger.info("Tache with ID " + id + " is updated");
					break;
		        }
		    }
		 for(Project p:listeProjects){
			 if(p.getTaches().get(0).getIdTache()==id){
				 p.getTaches().clear();
				 p.getTaches().add(updatedTache);
				 projectService.saveProject(listeProjects);
				 break;
			 }
		 }
		 return updatedTache;
	}

//	public void updateStatusTache(int id,StatusProjectTache status) throws JAXBException {
//		List<Tache> listeTaches = voirTaches();
//		Tache t=findTacheById(id);
//				t.setStatus(status);
//				for(int i=0;i<listeTaches.size();i++) {
//					if (listeTaches.get(i).getIdTache() == id) {
//						listeTaches.set(i,t);
//					}
//				}
//				saveTache(listeTaches);
//				logger.info("Tache with ID " + id + " is updated");
//				return;
//	}


}

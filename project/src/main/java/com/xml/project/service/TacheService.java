package com.xml.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.xml.project.model.generated.*;

import javax.xml.bind.JAXBException;

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
	 
	 public void addTache(Tache tache) throws JAXBException {
		 List<Tache> listeTaches=voirTaches();
		 boolean idExists = listeTaches.stream()
				 .anyMatch(existingTache -> existingTache.getIdTache() == tache.getIdTache());
		 if (idExists) {
			 throw new ReusedIdException("Tache with ID " + tache.getIdTache() + " already exists.");
		 }
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

	 
	 public void deleteTache(int id) throws JAXBException {
		    List<Tache> listeTaches = voirTaches();
		    Tache t = findTacheById(id);
		    if (t != null && listeTaches.remove(t)) {
		        saveTache(listeTaches);
		        System.out.println("Tache with ID " + id + " is deleted");
		    } else {
		        System.out.println("Tache with ID " + id + " not found");
		    }
		}
	 public void updateTache(int id,Tache updatedTache) throws JAXBException {
		    List<Tache> listeTaches = voirTaches();
		    for (int i = 0; i < listeTaches.size(); i++) {
		        if (listeTaches.get(i).getIdTache() == id) {
		            listeTaches.set(i, updatedTache);
		            saveTache(listeTaches);
					logger.info("Tache with ID " + id + " is updated");
		            return;
		        }
		    }
		 logger.warning("Tache with ID " + id + " not found");
		}

	public void updateStatusTache(int id,StatusProjectTache status) throws JAXBException {
		List<Tache> listeTaches = voirTaches();
		Tache t=findTacheById(id);
				t.setStatus(status);
				for(int i=0;i<listeTaches.size();i++) {
					if (listeTaches.get(i).getIdTache() == id) {
						listeTaches.set(id,t);
					}
				}
				saveTache(listeTaches);
				logger.info("Tache with ID " + id + " is updated");
				return;
	}


}

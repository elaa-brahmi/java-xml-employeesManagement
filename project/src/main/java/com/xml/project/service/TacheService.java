package com.xml.project.service;

import java.util.ArrayList;
import java.util.List;

import com.xml.project.model.generated.Tache;
import com.xml.project.model.generated.Taches;

import com.xml.project.model.generated.Users;

public class TacheService {
	public TacheService() {}
	 private final String XML_FILE_PATH = "employees.xml";
	 private final String taches = "tache.xml";

	 
	 public List<Tache> voirTaches() {
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
	            e.printStackTrace();
	        }
		 
	 }
	 
	 public void addTache(Tache tache) {
		 List<Tache> listeTaches=voirTaches();
		 listeTaches.add(tache);
		 
		 saveTache(listeTaches);
		 System.out.println("tache with id"+ tache.getIdTache()+ "is added");
		 
		 
	 }
	 
	 public Tache findTacheById(int id) {
		    return voirTaches().stream()
		        .filter(t -> t.getIdTache() == id)
		        .findFirst()
		        .orElse(null);
		}

	 
	 public void deleteTache(int id) {
		    List<Tache> listeTaches = voirTaches();
		    Tache t = findTacheById(id);
		    if (t != null && listeTaches.remove(t)) {
		        saveTache(listeTaches);
		        System.out.println("Tache with ID " + id + " is deleted");
		    } else {
		        System.out.println("Tache with ID " + id + " not found");
		    }
		}

	 
	 
	 public void updateTache(int id,Tache updatedTache) {
		    List<Tache> listeTaches = voirTaches();
		    for (int i = 0; i < listeTaches.size(); i++) {
		        if (listeTaches.get(i).getIdTache() == id) {
		            listeTaches.set(i, updatedTache);
		            saveTache(listeTaches);
		            System.out.println("Tache with ID " + id + " is updated");
		            return;
		        }
		    }
		    System.out.println("Tache with ID " + id + " not found");
		}


}

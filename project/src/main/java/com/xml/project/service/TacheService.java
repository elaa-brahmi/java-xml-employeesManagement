package com.xml.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.xml.project.model.generated.*;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

@Service
public class TacheService {

	 private final String path = "project/tache.xml";

	public List<Tache> getAllTaches() throws JAXBException {
		XMLService xmlService = new XMLService();

		Taches taches = xmlService.unmarshalXML(path , Taches.class);

		List<Tache> tacheListList =taches.getTache();

		return new ArrayList<>(tacheListList);
	}


	public Tache getTacheById(int id) throws JAXBException {
		List<Tache> taches = getAllTaches();
		return taches.stream().filter(e -> e.getIdTache() == id).findFirst().orElse(null);
	}


	public void addTache(Tache newTache) throws JAXBException {

		List<Tache> tacheList = getAllTaches();
		tacheList.add(newTache);
		saveToXmlEquipments(tacheList);
	}

	public void saveToXmlEquipments(List<Tache> tacheList) throws JAXBException {
		Taches taches = new Taches();
		taches.setTache(tacheList);
		XMLService xmlService = new XMLService();
		xmlService.generateXMLFromObjects(taches,path);
	}


	public Tache updateTache(int id, Tache updatedTache) throws JAXBException {
		List<Tache> tacheList = getAllTaches();
		Tache t =null;
		for (Tache tache:tacheList){
			if (tache.getIdTache()==id){
				tache.setIdTache(updatedTache.getIdTache());
				tache.setDescription(updatedTache.getDescription());
				tache.setStartDate(updatedTache.getStartDate());
				tache.setEndDate(updatedTache.getEndDate());
				tache.setStatus(updatedTache.getStatus());

				tache.setEmployees(updatedTache.getEmployees()); // comment gérer ca ???????
				tache.setEquipments(updatedTache.getEquipments());

				t = tache;
				break;
			}
		}
		saveToXmlEquipments(tacheList);
		return t;
	}


	public boolean deleteTacheById(int id) throws JAXBException {
		List<Tache> tacheList = getAllTaches();

		Tache t = getTacheById(id);

		if (t == null){
			return false;
		}
		String tId = String.valueOf(t.getIdTache());

		boolean removedTache = tacheList.removeIf(t1-> String.valueOf(t1.getIdTache()).equals(tId)) ;
		if (removedTache){
			saveToXmlEquipments(tacheList);
			return true;
		}
		return false ;
	}

}

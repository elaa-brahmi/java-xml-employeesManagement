package com.xml.project.service;

import com.xml.project.model.generated.Employees;
import com.xml.project.model.generated.Equipment;
import com.xml.project.model.generated.Equipments;
import com.xml.project.model.generated.ReusedIdException;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class EquipmentServiceImpl implements EquipmentService{

    private final String path = "equipments.xml" ;

    @Override
    public List<Equipment> getAllEquipments() throws JAXBException {
        XMLService xmlService = new XMLService();

        Equipments equipments = xmlService.unmarshalXML(path , Equipments.class);

        List<Equipment> equipmentList =equipments.getEquipment();

        return new ArrayList<>(equipmentList);

    }

    @Override
    public Equipment getEquipmentById(int id) throws JAXBException {
        List<Equipment> equipments = getAllEquipments();
        return equipments.stream().filter(e -> e.getIdEquipment() == id).findFirst().orElse(null);
    }

    @Override
    public void addEquipment(Equipment newEquipment) throws JAXBException {

        List<Equipment> equipmentList = getAllEquipments();
        equipmentList.add(newEquipment);
        saveToXmlEquipments(equipmentList);
    }

    public void saveToXmlEquipments(List<Equipment> equipmentList) throws JAXBException {
        Equipments equipments = new Equipments();
        equipments.setEquipment(equipmentList);
        XMLService xmlService = new XMLService();
        xmlService.generateXMLFromObjects(equipments,path);
    }

    @Override
    public Equipment updateEquipment(int id, Equipment updatedEquipment) throws JAXBException {
        List<Equipment> equipmentList = getAllEquipments();
        Equipment eq =null;
        for (Equipment equipment:equipmentList){
            if (equipment.getIdEquipment()==id){
                equipment.setIdEquipment(updatedEquipment.getIdEquipment());
                equipment.setName(updatedEquipment.getName());
                equipment.setType(updatedEquipment.getType());
                equipment.setQuantity(updatedEquipment.getQuantity());
                equipment.setStatus(updatedEquipment.getStatus());
                equipment.setCategory(updatedEquipment.getCategory());
                equipment.setIdTache(updatedEquipment.getIdTache());
                eq = equipment;
                break;
            }
        }
        saveToXmlEquipments(equipmentList);
        return eq;
    }

    @Override
    public boolean deleteEquipmentById(int id) throws JAXBException {
        List<Equipment> equipmentList = getAllEquipments();

        Equipment eq = getEquipmentById(id);

        if (eq == null){
            return false;
        }
        String eqId = String.valueOf(eq.getIdEquipment());

        boolean removedEquipment = equipmentList.removeIf(eq1-> String.valueOf(eq1.getIdEquipment()).equals(eqId)) ;
        if (removedEquipment){
            saveToXmlEquipments(equipmentList);
            return true;
        }
        return false ;
    }

}
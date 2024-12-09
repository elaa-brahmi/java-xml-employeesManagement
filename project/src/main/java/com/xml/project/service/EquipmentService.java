package com.xml.project.service;

import com.xml.project.model.generated.Equipment;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface EquipmentService {
    List<Equipment> getAllEquipments() throws JAXBException;

    Equipment getEquipmentById(int id) throws JAXBException;

    void addEquipment(Equipment equipment) throws JAXBException;

    Equipment updateEquipment(int id, Equipment updatedEquipment) throws JAXBException;

    boolean deleteEquipmentById(int id) throws JAXBException;
}

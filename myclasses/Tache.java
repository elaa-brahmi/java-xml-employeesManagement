package com.xml.project.model;

import java.util.Date;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tache")
public class Tache {
    private int idTache;
    private int idEmployee;
    private int idEquipment;
    private String description;
    private String status;
    private String dueDate;
 
    public Tache(int idTache, int idEmployee, int idEquipment, String description, String status, String dueDate) {
		
		this.idTache = idTache;
		this.idEmployee = idEmployee;
		this.idEquipment = idEquipment;
		this.description = description;
		this.status = status;
		this.dueDate = dueDate;
	}
    public Tache() {}

    @XmlElement(name = "id_tache")
    public int getIdTache() {
        return idTache;
    }

    public void setIdTache(int idTache) {
        this.idTache = idTache;
    }

    @XmlElement(name = "id_employee")
    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    @XmlElement(name = "id_equipment")
    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "due_date")
    public String getDueDate() {
        return dueDate;
    }

    
	public void setDueDate(String string) {
        this.dueDate = string;
    }
}

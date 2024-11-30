package com.xml.project.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "technicien")
public class Technician extends Employee{
    public Technician(int idTechnicien, int idEmployee) {
		super();
		this.idTechnicien = idTechnicien;
		this.idEmployee = idEmployee;
	}
    public Technician() {}

	private int idTechnicien;
    private int idEmployee;

    @XmlElement(name = "id_technicien")
    public int getIdTechnicien() {
        return idTechnicien;
    }

    public void setIdTechnicien(int idTechnicien) {
        this.idTechnicien = idTechnicien;
    }

    @XmlElement(name = "id_employee")
    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
}

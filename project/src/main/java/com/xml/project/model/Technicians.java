package com.xml.project.model;

import java.util.List;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "technicians")
public class Technicians {
    private List<Technician> technicianList;

    @XmlElement(name = "technicien")
    public List<Technician> getTechnicianList() {
        return technicianList;
    }

    public void setTechnicianList(List<Technician> technicianList) {
        this.technicianList = technicianList;
    }

	public Technicians(List<Technician> technicianList) {
		super();
		this.technicianList = technicianList;
	}
	public Technicians() {}
}

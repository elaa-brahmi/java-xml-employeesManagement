
package com.xml.project.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
public class Employee {
    private String idUser;
    private int idEmployee;
    private List<String> skills;
    private String status;
   
    private String speciality;
    private List<String> certifications;
    private String role;
    @XmlElement(name = "id_user")
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	 @XmlElement
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	 @XmlElement
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	 @XmlElement
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 @XmlElement
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	 @XmlElement
	public List<String> getCertifications() {
		return certifications;
	}
	public void setCertifications(List<String> certifications) {
		this.certifications = certifications;
	}
	 @XmlElement
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}

    
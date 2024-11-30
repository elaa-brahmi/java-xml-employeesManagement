
package com.xml.project.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
public class Employee extends User {
    private String idUser;
    private int idEmployee;
    private String skills;
    private String status;
   
    private String speciality;
    private String certifications;
    private String role;
    public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
    public Employee(String login, String email, String nom, String prenom, String role, String password,String idUser, int idEmployee, String skills, String status, String speciality,
			String certifications, String roleE) {
		super(idUser,login,email,nom,prenom,role,password);
		this.idUser = idUser;
		this.idEmployee = idEmployee;
		this.skills = skills;
		this.status = status;
		this.speciality = speciality;
		this.certifications = certifications;
		this.role = roleE;
	}
	
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
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills2) {
		this.skills = skills2;
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
	public String getCertifications() {
		return certifications;
	}
	public void setCertifications(String certifications) {
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

    
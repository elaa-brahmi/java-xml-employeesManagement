package com.xml.project.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
public class Employee extends User {

    private int idEmployee;
    private List<String> skills;
    private String status;
    private String speciality;
    private String certifications;
    private String role;
    private List<Tache> taches; // List of Tache objects

    public Employee() {
        super();
    }

    public Employee(String idUser, String login, String email, String nom, String prenom, String password,
                    int idEmployee, List<String> skills, String status, String speciality,
                    String certifications, String role, List<Tache> taches) {
        super(idUser, login, email, nom, prenom, password);
        this.idEmployee = idEmployee;
        this.skills = skills;
        this.status = status;
        this.speciality = speciality;
        this.certifications = certifications;
        this.role = role;
        this.taches = taches;
    }

    @XmlElement(name = "idEmployee")
    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    @XmlElementWrapper(name = "skills") // Wrapper element for the list
    @XmlElement(name = "skill") // Element name for each skill
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @XmlElement(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "speciality")
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @XmlElement(name = "certification")
    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    @XmlElement(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @XmlElement(name = "email") // Ensures the "email" tag appears in the XML
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @XmlElement(name = "idUser") // Ensures the "idUser" tag appears in the XML
    @Override
    public String getIdUser() {
        return super.getIdUser();
    }

    @XmlElementWrapper(name = "taches") // Wrapper element for the list of tasks
    @XmlElement(name = "tache") // Element name for each Tache
    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }
}

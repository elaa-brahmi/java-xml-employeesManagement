package com.xml.project.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User {
    private String idUser;
    private String login;
    private String email;
    public String nom;
    public String prenom;
    private String password;

    public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String idUser, String login, String email, String nom, String prenom, String password) {
		
		this.idUser = idUser;
		this.login = login;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		
		this.password = password;
	}

	@XmlElement(name = "id_user")
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @XmlElement(name="login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @XmlElement(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name="nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement(name="prenom")
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    
    

    @XmlElement(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.12.16 at 05:39:04 PM WAT 
//


package com.xml.project.model.generated;
import com.xml.project.model.generated.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;


/**
 * <p>Java class for User complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="User"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idUser" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="login" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {
    "idUser",
    "login",
    "email",
    "nom",
    "prenom",
    "password"
})
@XmlSeeAlso({
    Employee.class
})
public class User {

    @XmlElement(required = true)
    protected String idUser;
    @XmlElement(required = true)
    protected String login;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String nom;
    @XmlElement(required = true)
    protected String prenom;
    @XmlElement(required = true)
    protected String password;

    /**
     * Gets the value of the idUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public User(){}
    public User(String idUser, String login, String email, String nom, String prenom, String password) {
        this.idUser = idUser;
        this.login = login;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;

    }
    public User(Employee employee){
        this.idUser = employee.getIdUser();
        this.login = employee.getLogin();
        this.email = employee.getEmail();
        this.nom = employee.getNom();
        this.prenom = employee.getPrenom();
        this.password = employee.getPassword();

    }
    public String getIdUser() {
        return idUser;
    }

    /**
     * Sets the value of the idUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdUser(String value) {
        this.idUser = value;
    }

    /**
     * Gets the value of the login property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the value of the login property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the nom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the value of the nom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Gets the value of the prenom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Sets the value of the prenom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrenom(String value) {
        this.prenom = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }

        // Step 2: Check for null and class equality
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Step 3: Cast and compare fields
        User other = (User) obj;
        return idUser== other.idUser && login== other.login && email== other.email && prenom==other.prenom && password== other.password && nom== other.nom;
    }

}

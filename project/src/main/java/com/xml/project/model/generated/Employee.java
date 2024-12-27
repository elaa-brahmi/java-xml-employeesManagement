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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;


/**
 * <p>Java class for Employee complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Employee"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}User"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idEmployee" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="skills" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="status" type="{}statusEmployee"/&gt;
 *         &lt;element name="speciality" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="certification" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="role" type="{}roleEmployee"/&gt;
 *         &lt;element name="idTache" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Employee", propOrder = {
        "idEmployee",
        "skills",
        "status",
        "speciality",
        "certification",
        "role",
        "idTache"
})
public class Employee
        extends User
{

    protected int idEmployee;
    @XmlElement(required = true)
    protected String skills;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected StatusEmployee status;
    @XmlElement(required = true)
    protected String speciality;
    @XmlElement(required = true)
    protected String certification;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected RoleEmployee role;
    protected int idTache;
    public Employee() {
        super();
    }
    public Employee(int idEmployee, String skills, StatusEmployee status, String speciality, String certification, RoleEmployee role, int idTache,String idUser, String login, String email, String nom, String prenom, String password) {
        super(idUser, login, email, nom, prenom, password);
        this.idEmployee = idEmployee;
        this.skills = skills;
        this.status = status;
        this.speciality = speciality;
        this.certification = certification;
        this.role = role;
        this.idTache = idTache;

    }
    public Employee(Employee employee) {
        super(employee);
        this.idEmployee = employee.getIdEmployee();
        this.skills = employee.getSkills();
        this.status = employee.getStatus();
        this.speciality = employee.getSpeciality();
        this.certification = employee.getCertification();
        this.role = employee.getRole();
        this.idTache = employee.getIdTache();

    }

    /**
     * Gets the value of the idEmployee property.
     *
     */
    public int getIdEmployee() {
        return idEmployee;
    }

    /**
     * Sets the value of the idEmployee property.
     *
     */
    public void setIdEmployee(int value) {
        this.idEmployee = value;
    }

    /**
     * Gets the value of the skills property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSkills() {
        return skills;
    }

    /**
     * Sets the value of the skills property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSkills(String value) {
        this.skills = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link StatusEmployee }
     *
     */
    public StatusEmployee getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link StatusEmployee }
     *
     */
    public void setStatus(StatusEmployee value) {
        this.status = value;
    }

    /**
     * Gets the value of the speciality property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSpeciality() {
        return speciality;
    }

    /**
     * Sets the value of the speciality property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSpeciality(String value) {
        this.speciality = value;
    }

    /**
     * Gets the value of the certification property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCertification() {
        return certification;
    }

    /**
     * Sets the value of the certification property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCertification(String value) {
        this.certification = value;
    }

    /**
     * Gets the value of the role property.
     *
     * @return
     *     possible object is
     *     {@link RoleEmployee }
     *
     */
    public RoleEmployee getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     *
     * @param value
     *     allowed object is
     *     {@link RoleEmployee }
     *
     */
    public void setRole(RoleEmployee value) {
        this.role = value;
    }

    /**
     * Gets the value of the idTache property.
     *
     */
    public int getIdTache() {
        return idTache;
    }

    /**
     * Sets the value of the idTache property.
     *
     */
    public void setIdTache(int value) {
        this.idTache = value;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        // Step 2: Check for null and class equality
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Step 3: Cast and compare fields
        Employee other = (Employee) obj;
        return idEmployee == other.idEmployee && Objects.equals(skills, other.skills) && Objects.equals(status, other.status) && speciality==other.speciality && role==other.role && Objects.equals(idTache, other.idTache) && certification==other.certification && super.equals(obj);

    }

}

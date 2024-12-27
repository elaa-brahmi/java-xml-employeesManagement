//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.12.16 at 05:39:04 PM WAT 
//


package com.xml.project.model.generated;
import com.xml.project.model.generated.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for tache complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tache"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idTache" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="status" type="{}statusProjectTache"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="start_date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="end_date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="idProject" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="employees" type="{}Employee" maxOccurs="unbounded"/&gt;
 *         &lt;element name="equipments" type="{}equipment" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tache", propOrder = {
    "idTache",
    "status",
    "description",
    "startDate",
    "endDate",
    "idProject",
    "employees",
    "equipments"
})
public class Tache {

    protected int idTache;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected StatusProjectTache status;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(name = "start_date", required = true)
    @XmlSchemaType(name = "date")
    protected String startDate;
    @XmlElement(name = "end_date", required = true)
    @XmlSchemaType(name = "date")
    protected String endDate;
    protected int idProject;
    @XmlElementWrapper(required = true,name="employees")
    @XmlElement(required = true,name="employee")
    protected List<Employee> employees;
    @XmlElementWrapper(required = true,name="equipments")
    @XmlElement(required = true,name="equipment")
    protected List<Equipment> equipments;

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

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusProjectTache }
     *     
     */
    public StatusProjectTache getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusProjectTache }
     *     
     */
    public void setStatus(StatusProjectTache value) {
        this.status = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the idProject property.
     * 
     */
    public int getIdProject() {
        return idProject;
    }

    /**
     * Sets the value of the idProject property.
     * 
     */
    public void setIdProject(int value) {
        this.idProject = value;
    }

    /**
     * Gets the value of the employees property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employees property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmployees().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Employee }
     * 
     * 
     */
    public List<Employee> getEmployees() {
        if (employees == null) {
            employees = new ArrayList<Employee>();
        }
        return this.employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Gets the value of the equipments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the equipments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEquipments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Equipment }
     * 
     * 
     */
    public List<Equipment> getEquipments() {
        if (equipments == null) {
            equipments = new ArrayList<Equipment>();
        }
        return this.equipments;
    }
    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Tache tache = (Tache) obj;

        return idTache == tache.idTache; // Compare tasks based on their ID
    }


}

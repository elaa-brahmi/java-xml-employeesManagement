//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2024.12.16 at 05:39:04 PM WAT
//


package com.xml.project.model.generated;
import com.xml.project.model.generated.*;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for project complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="project"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idProject" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="status" type="{}statusProjectTache"/&gt;
 *         &lt;element name="taches" type="{}tache" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "project", propOrder = {
        "idProject",
        "name",
        "status",
        "taches"
})
public class Project {

    protected int idProject;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected StatusProjectTache status;
    @XmlElementWrapper(name="taches")
    @XmlElement(required = true,name="tache")
    protected List<Tache> taches;

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
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the taches property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taches property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaches().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tache }
     *
     *
     */
    public List<Tache> getTaches() {
        if (taches == null) {
            taches = new ArrayList<Tache>();
        }
        return this.taches;
    }
    public void setTaches(List<Tache> taches) {
        this.taches = taches;

    }

}

package com.xml.project.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "taches")
@XmlAccessorType(XmlAccessType.FIELD)
public class Taches {

    @XmlElement(name = "tache") // Spécifie le nom de l'élément XML
    private List<Tache> tacheList;

    // Getter
    public List<Tache> getTacheList() {
        return tacheList;
    }

    // Setter
    public void setTacheList(List<Tache> tacheList) {
        this.tacheList = tacheList;
    }
}

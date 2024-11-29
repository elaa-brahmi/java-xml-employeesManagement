package com.xml.project.model;

import java.util.List;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notes")
public class Notes {
    private List<NoteEmployee> noteList;

    @XmlElement(name = "note")
    public List<NoteEmployee> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<NoteEmployee> noteList) {
        this.noteList = noteList;
    }
}

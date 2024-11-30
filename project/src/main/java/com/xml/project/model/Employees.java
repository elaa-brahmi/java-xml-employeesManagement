package com.xml.project.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees") // Root element of the XML file
public class Employees {
    public Employees(List<Employee> employees) {
		super();
		this.employees = employees;
	}
    public Employees() {}

	private List<Employee> employees;

    @XmlElement(name = "employee") // Maps each <Employee> element in the XML
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

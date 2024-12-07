package com.xml.project.service;

import java.util.List;

import com.xml.project.model.generated.Employee;

import javax.xml.bind.JAXBException;

public interface EmployeeService {
	Employee findEmployeeById(int id) throws JAXBException;
    List<Employee> getAllEmployees() throws JAXBException;
    boolean deleteEmployeeById(int id) throws JAXBException;
    Employee updateEmployeeById(int id, Employee updatedEmployee) throws JAXBException;
    void addEmployee(Employee newEmployee) throws JAXBException;

}

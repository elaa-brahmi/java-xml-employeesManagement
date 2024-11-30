package com.xml.project.service;

import java.util.List;

import com.xml.project.model.Employee;

public interface EmployeeService {
	Employee findEmployeeById(int id);
    List<Employee> getAllEmployees();
    boolean deleteEmployeeById(int id);
    Employee updateEmployeeById(int id, Employee updatedEmployee);
    void addEmployee(Employee newEmployee);

}

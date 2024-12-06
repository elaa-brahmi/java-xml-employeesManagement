package com.xml.project.service;

import com.xml.project.model.generated.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.aot.hint.TypeReference.listOf;

public class SampleDataService {
	public Employee createEmployee(String idUser, int idEmployee, List<String> skills, String status,
            String speciality, String certifications, String role) {
		Employee employee = new Employee();
		employee.setIdUser(idUser);
		employee.setIdEmployee(idEmployee);
		employee.setSkills(skills);
		employee.setStatus(StatusEmployee.valueOf(status));
		employee.setSpeciality(speciality);
		employee.setCertification(certifications);
		employee.setRole(RoleEmployee.valueOf(role));
		return employee;
}
	public  User createUser(String idUser, String login, String email, String nom, String prenom, String role, String password) {
        // Create a new User object
        User user = new User();
        user.setIdUser(idUser);
        user.setLogin(login);
        user.setEmail(email);
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setPassword(password);
        // Return the created User object
        return user;
	}
	private Equipment createEquipment(int idEquipment, String name, String type, int quantity, String status, String category,int id_tache) {
	    // Create a new Equipment instance
	    Equipment equipment = new Equipment();
	    
	    // Set the fields of the Equipment object using the method parameters
	    equipment.setIdEquipment(idEquipment);
	    equipment.setName(name);
	    equipment.setType(type);
	    equipment.setQuantity(quantity);
	    equipment.setStatus(StatusEquipment.valueOf(status));
	    equipment.setCategory(category);
        equipment.setIdTache(id_tache);
	    // Return the fully created Equipment object
	    return equipment;
	}
    public Users createSampleUsers() {
    	
        // Create a list of User objects
        List<User> userList = new ArrayList<>();
        // Add sample users
        userList.add(createUser("user001", "doe", "doe@gmail.com", "John", "Doe", "employee", "password123"));
        userList.add(createUser("user002", "schmidt", "schmidt@gmail.com", "Jake", "Schmidt", "employee", "password012"));
        userList.add(createUser("user003", "smith", "smith@gmail.com", "Jack", "Smith", "admin", "admin2024"));//admin
        userList.add(createUser("user004", "brown", "brown@gmail.com", "James", "Brown", "maintenance technician", "password345"));
        userList.add(createUser("user005", "taylor", "taylor@gmail.com", "Susan", "Taylor", "employee", "password456"));
        userList.add(createUser("user006", "johnson", "johnson@gmail.com", "Michael", "Johnson", "employee", "password789"));
        userList.add(createUser("user007", "anderson", "anderson@gmail.com", "Mark", "Anderson", "maintenance technician", "password1234"));
        userList.add(createUser("user008", "clark", "clark@gmail.com", "Emma", "Clark", "maintenance technician", "password5678"));
        userList.add(createUser("user009", "andrew", "andrew@gmail.com", "Andrew", "Johnson", "employee", "password001"));
        userList.add(createUser("user010", "garcia", "garcia@gmail.com", "Maria", "Garcia", "employee", "password002"));
        userList.add(createUser("user011", "martinez", "martinez@gmail.com", "Laura", "Martinez", "employee", "password003"));
        userList.add(createUser("user012", "lee", "lee@gmail.com", "Kevin", "Lee", "employee", "password004"));
        userList.add(createUser("user013", "white", "white@gmail.com", "Sophia", "White", "employee", "password005"));
        userList.add(createUser("user014", "harris", "harris@gmail.com", "Emily", "Harris", "employee", "password006"));
        userList.add(createUser("user015", "adams", "adams@gmail.com", "Benjamin", "Adams", "maintenance technician", "password013"));
        userList.add(createUser("user016", "cooper", "cooper@gmail.com", "Charlotte", "Cooper", "maintenance technician", "password014"));
        userList.add(createUser("user017", "turner", "turner@gmail.com", "Daniel", "Turner", "maintenance technician", "password015"));
        userList.add(createUser("user018", "morgan", "morgan@gmail.com", "Ella", "Morgan", "maintenance technician", "password016"));
        userList.add(createUser("user019", "baker", "baker@gmail.com", "Henry", "Baker", "maintenance technician", "password017"));
        userList.add(createUser("user020", "ross", "ross@gmail.com", "Amelia", "Ross", "maintenance technician", "password018"));
        // Create a Users object and set the user list
        Users users = new Users();
        users.setUser(userList);

        return users;
    }
    public Employees createSampleEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        // Sample data for employees
        employeeList.add(createEmployee("user001", 1,List.of("sorting","recycling"),"busy", "safety", "Sorting Specialist",  "Employee"));
        employeeList.add(createEmployee("user002", 2, List.of("sorting","recycling"), "free", "Sorting Specialist", "Recycling Process Certification", "Employee"));
        employeeList.add(createEmployee("user005", 3, List.of("sorting","recycling"), "busy", "Logistics Coordinator", "Logistics Management", "Employee"));
        employeeList.add(createEmployee("user006", 4,  List.of("sorting","recycling"), "free", "Sorting Specialist", "Recycling Process Certification", "Employee"));
        employeeList.add(createEmployee("user009", 5, List.of("sorting","recycling"), "free", "Sorting Specialist", "Waste Management Certification", "Employee"));
        employeeList.add(createEmployee("user010", 6, List.of("sorting","recycling"), "busy", "Recycling Specialist", "Recycling Process Certification", "Employee"));
        employeeList.add(createEmployee("user011", 7, List.of("sorting","recycling"), "free", "Sorting Specialist", "Safety Management", "Employee"));
        employeeList.add(createEmployee("user012", 8, List.of("sorting","recycling"), "busy", "Recycling Specialist", "Waste Management Certification", "Employee"));
        employeeList.add(createEmployee("user013", 9, List.of("sorting","recycling"), "free", "Sorting Specialist", "Recycling Process Certification", "Employee"));
        employeeList.add(createEmployee("user014", 10,  List.of("sorting","recycling"), "busy", "Sorting Specialist", "Waste Management Certification", "Employee"));
        employeeList.add(createEmployee("user007", 11, List.of("sorting","recycling"), "free", "Equipment Technician", "Electrical Maintenance Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user008", 12, List.of("sorting","recycling"), "busy", "Equipment Technician", "Mechanical Repairs Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user004", 13,  List.of("sorting","recycling"), "free", "Electrical Technician", "Electrical Systems Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user015", 14, List.of("sorting","recycling"), "busy", "Mechanical Technician", "Machine Diagnostics Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user016", 15, List.of("sorting","recycling"), "free", "Machine Technician","Machine Safety Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user017", 16,  List.of("sorting","recycling"), "busy", "Electrical Technician","Preventive Maintenance Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user018", 17, List.of("sorting","recycling"), "free", "Heavy Equipment Technician","Heavy Equipment Maintenance Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user019", 18, List.of("sorting","recycling"), "busy", "Mechanical Technician","Machine Safety Compliance Certification", "Maintenance Technician"));

        // Create an Employees object and set the employee list
        Employees employees = new Employees();
        employees.setEmployee(employeeList);
        return employees;
    }


	

	
}

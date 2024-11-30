package com.xml.project.service;

import com.xml.project.model.Employee;
import com.xml.project.model.Employees;
import com.xml.project.model.Equipment;
import com.xml.project.model.Equipments;
import com.xml.project.model.Technician;
import com.xml.project.model.Technicians;
import com.xml.project.model.User;
import com.xml.project.model.Users;
import java.util.ArrayList;
import java.util.List;

public class SampleDataService {
	public Employee createEmployee(String idUser, int idEmployee, String skills, String status, 
            String speciality, String certifications, String role) {
		Employee employee = new Employee();
		employee.setIdUser(idUser);
		employee.setIdEmployee(idEmployee);
		employee.setSkills(skills);
		employee.setStatus(status);
		employee.setSpeciality(speciality);
		employee.setCertifications(certifications);
		employee.setRole(role);
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
        user.setRole(role);
        user.setPassword(password);
        
        // Return the created User object
        return user;
    
	}
	private Technician createTechnician(int i, int j) {
		 Technician technician = new Technician();
		 technician.setIdTechnicien(i);
		 technician.setIdEmployee(j);
		
		return technician;
	}
	private Equipment createEquipment(int idEquipment, String name, String type, int quantity, String status, String category) {
	    // Create a new Equipment instance
	    Equipment equipment = new Equipment();
	    
	    // Set the fields of the Equipment object using the method parameters
	    equipment.setIdEquipment(idEquipment);
	    equipment.setName(name);
	    equipment.setType(type);
	    equipment.setQuantity(quantity);
	    equipment.setStatus(status);
	    equipment.setCategory(category);
	    
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
        users.setUsers(userList);

        return users;
    }
    public Employees createSampleEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        // Sample data for employees
        employeeList.add(createEmployee("user001", 1, "Waste Management", "busy", "Sorting Specialist", "Waste Management Certification", "Employee"));
        employeeList.add(createEmployee("user002", 2, "Sorting", "available", "Sorting Specialist", "Recycling Process Certification", "Employee"));
        employeeList.add(createEmployee("user005", 3, "Logistics", "busy", "Logistics Coordinator", "Logistics Management", "Employee"));
        employeeList.add(createEmployee("user006", 4,  "Teamwork", "available", "Sorting Specialist", "Recycling Process Certification", "Employee"));
        employeeList.add(createEmployee("user009", 5, "Waste Management", "available", "Sorting Specialist", "Waste Management Certification", "Employee"));
        employeeList.add(createEmployee("user010", 6, "Recycling", "busy", "Recycling Specialist", "Recycling Process Certification", "Employee"));
        employeeList.add(createEmployee("user011", 7, "Safety", "available", "Sorting Specialist", "Safety Management", "Employee"));
        employeeList.add(createEmployee("user012", 8, "Customer Service", "busy", "Recycling Specialist", "Waste Management Certification", "Employee"));
        employeeList.add(createEmployee("user013", 9, "Sorting", "available", "Sorting Specialist", "Recycling Process Certification", "Employee"));
        employeeList.add(createEmployee("user014", 10,  "Waste Management", "busy", "Sorting Specialist", "Waste Management Certification", "Employee"));
        
        employeeList.add(createEmployee("user007", 11, "Equipment Maintenance", "available", "Equipment Technician", "Electrical Maintenance Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user008", 12, "Electrical Repairs", "busy", "Equipment Technician", "Mechanical Repairs Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user004", 13,  "Machine Operation", "available", "Electrical Technician", "Electrical Systems Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user015", 14, "Diagnostic Tools", "busy", "Mechanical Technician", "Machine Diagnostics Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user016", 15, "Safety Procedures", "available", "Machine Technician","Machine Safety Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user017", 16,  "Preventive Maintenance", "busy", "Electrical Technician","Preventive Maintenance Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user018", 17, "Heavy Equipment", "available", "Heavy Equipment Technician","Heavy Equipment Maintenance Certification", "Maintenance Technician"));
        employeeList.add(createEmployee("user019", 18, "Safety Compliance", "busy", "Mechanical Technician","Machine Safety Compliance Certification", "Maintenance Technician"));
      


        // Create an Employees object and set the employee list
        Employees employees = new Employees();
        employees.setEmployees(employeeList);

        return employees;
    }
    public Technicians createSampleTechnicians() {
        List<Technician> technicianList = new ArrayList<>();

        
        technicianList.add(createTechnician(1, 11));
        technicianList.add(createTechnician(2, 12));
        technicianList.add(createTechnician(3, 13));
        technicianList.add(createTechnician(4, 14));
        technicianList.add(createTechnician(5, 15));
        technicianList.add(createTechnician(6, 16));
        technicianList.add(createTechnician(7, 17));
        technicianList.add(createTechnician(8, 18));
      


        // Create an Employees object and set the employee list
        Technicians technicians = new Technicians();
        technicians.setTechnicianList(technicianList);

        return technicians;
    }
    public Equipments createSampleEquipment() {
        List<Equipment> equipmentList = new ArrayList<>();

       
      
        
        equipmentList.add(createEquipment(1, "Compactor","Heavy Machinery",22,"Operational","Waste Management"));
        equipmentList.add(createEquipment(2, "Sorting Conveyor","Automatic Machine",10,"In Service","Waste Sorting"));
        equipmentList.add(createEquipment(3, "Shredder","Mechanical Machine",10,"Under Maintenance","Equipment Maintenance"));
        
      


        // Create an Employees object and set the employee list
        Equipments equipments = new Equipments();
        equipments.setEquipmentList(equipmentList);

        return equipments;
    }

	

	
}

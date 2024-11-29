package com.xml.project.service;

import com.xml.project.model.User;
import com.xml.project.model.Users;
import java.util.ArrayList;
import java.util.List;

public class SampleDataService {
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
    public Users createSampleUsers() {
    	
        // Create a list of User objects
        List<User> userList = new ArrayList<>();

        // Add sample users
        userList.add(createUser("user001", "doe", "doe@gmail.com", "John", "Doe", "employee", "password123"));
        userList.add(createUser("user002", "schmidt", "schmidt@gmail.com", "Jake", "Schmidt", "employee", "password012"));
        userList.add(createUser("user003", "smith", "smith@gmail.com", "Jack", "Smith", "admin", "admin2024"));
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

	
}

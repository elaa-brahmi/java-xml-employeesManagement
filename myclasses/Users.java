
package com.xml.project.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users") // Root element of the XML file
public class Users {
    public List<User> users;

    
    public Users() {}
    public Users(List<User> users) {
		
		this.users = users;
	}
    @XmlElement(name = "user") // Maps each <user> element in the XML
    public List<User> getUsers() {
        return users;
    }
    

	public void setUsers(List<User> users) {
        this.users = users;
    }
}

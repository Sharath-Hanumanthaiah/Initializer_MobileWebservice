package com.initializers.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "UserDetails")
public class UserDetails {
	
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
 
//	@Id
	private String id;
	@Id
	@Field("id")
	private Long previousApiId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String status;
	
	
	public Long getPreviousApiId() {
		return previousApiId;
	}
	public void setPreviousApiId(Long previousApiId) {
		this.previousApiId = previousApiId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

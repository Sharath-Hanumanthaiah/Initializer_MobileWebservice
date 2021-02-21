package com.initializers.api.model.input;

public class UserDetailsInput {
	
	private Long previousApiId;
	private String firstName;
	private String lastName;
	private String email;
	public Long getPreviousApiId() {
		return previousApiId;
	}
	public void setPreviousApiId(Long previousApiId) {
		this.previousApiId = previousApiId;
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
	
	
}

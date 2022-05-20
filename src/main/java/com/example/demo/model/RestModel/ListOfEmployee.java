package com.example.demo.model.RestModel;

import java.util.ArrayList;

public class ListOfEmployee {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String department;
	private String gender;
	private String linkedIn;
	private Long phone;
	private ArrayList<String> role;
	
	public ListOfEmployee(String id, String firstName, String email,
			String department,ArrayList<String> role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.email = email;
		this.department = department;
		this.role = role;
	}
	
	public ListOfEmployee(String firstName, String lastName, String email, String department, String gender,
			String linkedIn, Long phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
		this.gender = gender;
		this.linkedIn = linkedIn;
		this.phone = phone;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public ArrayList<String> getRole() {
		return role;
	}
	public void setRole(ArrayList<String> role) {
		this.role = role;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
}

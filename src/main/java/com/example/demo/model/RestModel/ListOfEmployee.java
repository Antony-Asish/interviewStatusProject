package com.example.demo.model.RestModel;

import java.util.ArrayList;

public class ListOfEmployee {
	private String id;
	private String firstName;
	private String email;
	private String department;
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
	
}

package com.example.demo.Response;

public class ListOfEmployee {
	private String id;
	private String firstName;
	private String email;
	private String department;
	private String roll;
	
	public ListOfEmployee(String id, String firstName, String email,
			String department, String roll) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.email = email;
		this.department = department;
		this.roll = roll;
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
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
}

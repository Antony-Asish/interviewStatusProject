package com.example.demo.model.RestModel;

public class ShowClientList {

	private String id;
	private String firstName;
	private String email;
	private Long Phone;
	
	public ShowClientList(String id, String firstName, String email, Long phone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.email = email;
		Phone = phone;
	}
	public ShowClientList() {
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
	public Long getPhone() {
		return Phone;
	}
	public void setPhone(Long phone) {
		Phone = phone;
	}
	
}

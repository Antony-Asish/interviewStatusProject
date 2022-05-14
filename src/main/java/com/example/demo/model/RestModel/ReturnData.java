package com.example.demo.model.RestModel;

public class ReturnData {
	private String id;
	private String userName;
	private String firstName;
	private String email;
	private Long phone;
	
	public ReturnData(String id, String userName, String firstName, String email,Long phone) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
}

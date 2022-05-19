package com.example.demo.model.RestModel;

public class ListOfCandidate {
	private String id;
	private String firstName;
	private String status;
	private Long phone;
	private String job;

	public ListOfCandidate(String id, String firstName, String status, Long phone, String job) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.status = status;
		this.phone = phone;
		this.job = job;
	}

	public ListOfCandidate() {
		// TODO Auto-generated constructor stub
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
}

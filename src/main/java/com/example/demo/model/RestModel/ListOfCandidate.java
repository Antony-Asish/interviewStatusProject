package com.example.demo.model.RestModel;

import java.util.ArrayList;

public class ListOfCandidate {
	private String id;
	private String firstName;
	private String email;
	private Long phone;
	private ArrayList<String> skill;
	private String job;
	
	public ListOfCandidate(String id, String firstName, String email, Long phone, ArrayList<String> skill, String job) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
		this.skill = skill;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<String> getSkill() {
		return skill;
	}
	public void setSkill(ArrayList<String> skill) {
		this.skill = skill;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
}

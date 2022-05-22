
package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.model.RestModel.Company;
import com.example.demo.model.RestModel.Education_Detail;
import com.example.demo.model.RestModel.address;

@Document("Candidate_Detail")
public class CandidateDetail {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private Date dob;
	private String gender;
	private String linkedIn;
	private ArrayList<Education_Detail> qualification;
	private ArrayList<Company> company;
	private ArrayList<String> skill;
	private String status;
	private Long phone;
	private address address;
	private String job;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public ArrayList<Education_Detail> getQualification() {
		return qualification;
	}
	public void setQualification(ArrayList<Education_Detail> qualification) {
		this.qualification = qualification;
	}
	public ArrayList<String> getSkill() {
		return skill;
	}
	public void setSkill(ArrayList<String> skill) {
		this.skill = skill;
	}
	
	public ArrayList<Company> getCompany() {
		return company;
	}
	public void setCompany(ArrayList<Company> company) {
		this.company = company;
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
	
	public address getAddress() {
		return address;
	}
	public void setAddress(address address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
}

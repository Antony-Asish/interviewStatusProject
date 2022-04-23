package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.dummyClass.Company;
import com.example.demo.dummyClass.Education_Detail;
import com.example.demo.dummyClass.address;

@Document("Employee_Detail")
public class EmployeeDetail {
	@Id
	private String id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String cpassword;
	private Date dob;
	private String gender;
	private String linkedIn;
	private String roll;
	private ArrayList<Education_Detail> degree;
	private Company[] company;
	private ArrayList<String> skill;
	private String department;
	private Long phone;
	private address address;
	private int otp;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public ArrayList<Education_Detail> getDegree() {
		return degree;
	}
	public void setDegree(ArrayList<Education_Detail> degree) {
		this.degree = degree;
	}
	public ArrayList<String> getSkill() {
		return skill;
	}
	public void setSkill(ArrayList<String> skill) {
		this.skill = skill;
	}
	
	public Company[] getCompany() {
		return company;
	}
	public void setCompany(Company[] company) {
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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

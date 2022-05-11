package com.example.demo.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.dummyClass.Salary;

@Document("Job_Description")
public class JobDescription {
	@Id
	private String id;
	private String position;
	private String experience;
	private ArrayList<String> skill;
	private Salary salary;
	private ArrayList<String> location;
	private ArrayList<String> qualification;
	private String employeeId;
	private ArrayList<String> points;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public ArrayList<String> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<String> points) {
		this.points = points;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public ArrayList<String> getSkill() {
		return skill;
	}
	public void setSkill(ArrayList<String> skill) {
		this.skill = skill;
	}
	public Salary getSalary() {
		return salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	public ArrayList<String> getLocation() {
		return location;
	}
	public void setLocation(ArrayList<String> location) {
		this.location = location;
	}
	public ArrayList<String> getQualification() {
		return qualification;
	}
	public void setQualification(ArrayList<String> qualification) {
		this.qualification = qualification;
	}
	
}

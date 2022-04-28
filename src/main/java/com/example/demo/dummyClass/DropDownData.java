package com.example.demo.dummyClass;

import java.util.ArrayList;

public class DropDownData {
	private ArrayList<String> position;
	private ArrayList<String> year;
	private ArrayList<String> skill;
	private Salary salary;
	private ArrayList<String> location;
	private ArrayList<String> qualification;
	
	public DropDownData(ArrayList<String> position2, ArrayList<String> year2, ArrayList<String> skill2,
			ArrayList<String> location2, ArrayList<String> qualification2) {
		this.location=location2;
		this.position=position2;
		this.year=year2;
		this.qualification=qualification2;
		this.skill=skill2;
	}
	public ArrayList<String> getQualification() {
		return qualification;
	}
	public void setQualification(ArrayList<String> qualification) {
		this.qualification = qualification;
	}
	public ArrayList<String> getPosition() {
		return position;
	}
	public void setPosition(ArrayList<String> position) {
		this.position = position;
	}
	public ArrayList<String> getYear() {
		return year;
	}
	public void setYear(ArrayList<String> year) {
		this.year = year;
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
}

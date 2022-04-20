package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class EmployeeCandidate {
	@Id
	private String empId;
	private Date interviewDate;
	private ArrayList<String> candidateId;
	
	public Date getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public ArrayList<String> getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(ArrayList<String> candidateId) {
		this.candidateId = candidateId;
	}
}

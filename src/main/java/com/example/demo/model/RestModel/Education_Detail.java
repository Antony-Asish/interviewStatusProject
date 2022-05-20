package com.example.demo.model.RestModel;

public class Education_Detail {
	
	private String collegeName;
	private String degree;
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	@Override
	public String toString() {
		return "CollegeName : "+this.collegeName+","+" Degree : "+this.degree;
	}
}

package com.example.demo.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("DropDown_Data")
public class DropDownData {
	@Id
	private String dropDownName;
	private ArrayList<String> data;
	
	public String getDropDownName() {
		return dropDownName;
	}
	public void setDropDownName(String dropDownName) {
		this.dropDownName = dropDownName;
	}
	public ArrayList<String> getData() {
		return data;
	}
	public void setData(ArrayList<String> data) {
		this.data = data;
	}
}

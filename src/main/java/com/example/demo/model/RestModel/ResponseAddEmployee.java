package com.example.demo.model.RestModel;

import com.example.demo.model.EmployeeDetail;

public class ResponseAddEmployee {
	private String msg;
	private EmployeeDetail data;
	
	public ResponseAddEmployee(String msg, EmployeeDetail data) {
		super();
		this.msg = msg;
		this.data = data;
	}
	public ResponseAddEmployee(String msg) {
		super();
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public EmployeeDetail getData() {
		return data;
	}
	public void setData(EmployeeDetail data) {
		this.data = data;
	}
}

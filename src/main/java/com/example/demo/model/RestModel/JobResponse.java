package com.example.demo.model.RestModel;

import com.example.demo.model.Job;

public class JobResponse {
	
	private String msg;
	private Job data;
	
	public JobResponse(String msg, Job data) {
		super();
		this.msg = msg;
		this.data = data;
	}
	public JobResponse(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Job getData() {
		return data;
	}
	public void setData(Job data) {
		this.data = data;
	}
}

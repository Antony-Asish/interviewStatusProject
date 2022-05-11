package com.example.demo.Response;

import com.example.demo.model.JobDescription;

public class JobDescriptionResponse {
	
	private String msg;
	private JobDescription data;
	
	public JobDescriptionResponse(String msg, JobDescription data) {
		super();
		this.msg = msg;
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public JobDescription getData() {
		return data;
	}
	public void setData(JobDescription data) {
		this.data = data;
	}
}

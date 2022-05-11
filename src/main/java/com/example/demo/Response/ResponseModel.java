package com.example.demo.Response;

import com.example.demo.dummyClass.ReturnData;

public class ResponseModel {
	private String msg;
	private ReturnData data;
	
	public ResponseModel() {
		// TODO Auto-generated constructor stub
	}
	public ResponseModel(String msg,ReturnData data) {
		super();
		this.msg = msg;
		this.data = data;
	}
	public ResponseModel(String msg) {
		super();
		this.msg = msg;
	}
	public ReturnData getData() {
		return data;
	}
	public void setData(ReturnData data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}

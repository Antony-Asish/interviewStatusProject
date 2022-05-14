package com.example.demo.model.RestModel;

import com.example.demo.model.CandidateDetail;

public class ResponseAddCandidate {

	private String msg;
	private CandidateDetail data;
	
	public ResponseAddCandidate(String msg, CandidateDetail ob) {
		super();
		this.msg = msg;
		this.data = ob;
	}
	
	public ResponseAddCandidate(String msg) {
		super();
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public CandidateDetail getData() {
		return data;
	}
	public void setData(CandidateDetail data) {
		this.data = data;
	}
	
}

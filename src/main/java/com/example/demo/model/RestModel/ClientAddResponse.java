package com.example.demo.model.RestModel;

import com.example.demo.model.ClientDetail;

public class ClientAddResponse {
	private String msg;
	private ClientDetail data;
	
	public ClientAddResponse(String msg, ClientDetail data) {
		super();
		this.msg = msg;
		this.data = data;
	}
	
	public ClientAddResponse(String msg) {
		super();
		this.msg = msg;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ClientDetail getData() {
		return data;
	}

	public void setData(ClientDetail data) {
		this.data = data;
	}
}

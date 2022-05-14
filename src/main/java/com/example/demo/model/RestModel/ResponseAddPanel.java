package com.example.demo.model.RestModel;

import com.example.demo.model.PanelDetail;

public class ResponseAddPanel {
	private String msg;
	private PanelDetail data;
	
	public ResponseAddPanel(String string) {
		this.msg=string;
	}

	public ResponseAddPanel(String string, PanelDetail insert) {
		this.msg=string;
		this.data=insert;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public PanelDetail getData() {
		return data;
	}

	public void setData(PanelDetail data) {
		this.data = data;
	}
}

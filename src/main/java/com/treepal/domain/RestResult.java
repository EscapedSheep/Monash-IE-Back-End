package com.treepal.domain;

public class RestResult {
	private int code;
	
	private String msg;
	
	private Object data;
	
	public RestResult() {
		
	}

	public int getCode() {
		return code;
	}

	public RestResult setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public RestResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public RestResult setData(Object data) {
		this.data = data;
		return this;
	}
	
}

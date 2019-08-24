package com.treepal.domain;

public enum Response {
	
	SUCCESS(200,"SUCCESS"),
	FAIL(400, "FAIL"),
	NOT_FOUND(404, "CAN NOT FOUND THE RESOURCE"),
	INTERNAL_SERVER_ERROR(500, "SERVER ERROR");
	
	
	private int code;
	private String msg;
	
	Response(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
	
}

package com.treepal.domain;

/**
*  Set static web response
*
* @author  Maida Ge(Mark)
* @version 3.0
* @date   2019-10-07
*/
public enum Response {
	
	SUCCESS(200,"SUCCESS"),
	FAIL(400, "FAIL"),
	NOT_FOUND(404, "CAN NOT FOUND THE RESOURCE"),
	INTERNAL_SERVER_ERROR(500, "SERVER ERROR"),
	WRONGIMAGE(300,"NO WORDS REC");
	
	
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

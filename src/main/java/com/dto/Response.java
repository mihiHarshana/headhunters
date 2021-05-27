package com.dto;
/*
 * 200 - OK
 * 204 - system error
 * 500 - Exception or error or Internal Server Error
 */
public class Response {

	public int code;
	public int id;
	public String message;
	public Object object;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Response(int code, int id, String message, Object object) {
		this.code = code;
		this.id = id;
		this.message = message;
		this.object = object;
	}
}

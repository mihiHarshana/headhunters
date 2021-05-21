package com.dto;

public class Response {

	public int code;
	public int id;
	public String type;
	public String message;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Response(int code, int id, String type, String message) {
		this.code = code;
		this.id = id;
		this.type = type;
		this.message = message;
	}
}

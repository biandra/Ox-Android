package com.globallogic.ox.domain;

public class ServerErrorResponse {

	private String message;
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ServerErrorResponse(String message, String code) {
		super();
		this.setMessage(message);
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

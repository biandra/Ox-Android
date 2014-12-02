package com.globallogic.ox.domain;

public class ServerErrorInfo {

	private int httpStatusCode;
	private ServerErrorResponse errorResponse;
	private String message;

	public ServerErrorInfo() {
	}
	
	public ServerErrorInfo(int httpStatusCode, ServerErrorResponse errorResponse, String message) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.errorResponse = errorResponse;
		this.message = message;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public ServerErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ServerErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
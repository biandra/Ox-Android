package com.globallogic.ox.app.services;

public interface ServiceError {

	Throwable getCause();

	String getMessage();

	String getLocalizedMessage();
}

package com.globallogic.ox.app.services;

public interface ServiceListener<T> {

	void onRequestStarted();

//	void onServerError(ServerErrorInfo errorInfo);

	void onConnectionError();

	void onSessionError();

//	void onRequestError(ServiceError error);
	
	void onRequestFinished(T result);
}
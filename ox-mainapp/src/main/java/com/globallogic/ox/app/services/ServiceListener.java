package com.globallogic.ox.app.services;

import com.globallogic.ox.domain.ServerErrorInfo;
import com.globallogic.ox.exceptions.ParseError;

public interface ServiceListener<T> {

	void onRequestStarted();

	void onServerError(ServerErrorInfo errorInfo);

	void onConnectionError();

	void onSessionError();

	void onRequestError(ServiceError error);
	
	void onParseError(ParseError error);

	void onRequestFinished(T result);
}
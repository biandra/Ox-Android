package com.globallogic.ox.app.services;

import com.globallogic.ox.domain.ServerErrorInfo;
import com.globallogic.ox.exceptions.ParseError;

public interface ServiceListener<T> {

	void onRequestStarted();

	void onServerError(ServerErrorInfo errorInfo);

	void onConnectionError();

	void onSessionError();

	void onRequestFinished(T result);

	void onParseError(ParseError error);
}
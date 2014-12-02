package com.globallogic.ox.app.services;

import java.util.List;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.NetworkResponse;
import com.globallogic.ox.parsing.IJSONParser;
import com.globallogic.ox.domain.ServerErrorInfo;
import com.globallogic.ox.domain.ServerErrorResponse;
import com.globallogic.ox.exceptions.ParseError;
import com.google.inject.Inject;

public class VolleyErrorHelper {

	@Inject
	private IJSONParser jsonParser;

	/**
	 * Determines whether the error is related to network
	 * 
	 * @param error
	 * @return
	 */
	public boolean isNetworkProblem(Object error) {
		return (error instanceof NetworkError)	|| (error instanceof NoConnectionError);
	}

	/**
	 * Determines whether the the server has returned partial data or not.
	 */
	public boolean isPartialData(Object error) {
		if (isServerProblem(error)) {
			ServerError serverError = (ServerError) error;
			return serverError.networkResponse.statusCode == 206;
		}
		return false;
	}

	/**
	 * Determines whether the error is related to server
	 * 
	 * @param error
	 * @return
	 */
	public boolean isServerProblem(Object error) {
		return (error instanceof ServerError);
	}

	/**
	 * Determines whether the error is related to an authentication error
	 * 
	 * @param error
	 * @return
	 */
	public boolean isSessionProblem(Object error) {
		return error instanceof AuthFailureError;
	}

	/***
	 * Determines whether the error is related to timeout error
	 * 
	 * @param error
	 * @return
	 */
	public boolean isTimeoutProblem(Object error) {
		return error instanceof TimeoutError;
	}

	/**
	 * Handles the server error, tries to determine whether to show a stock
	 * message or to show a message retrieved from the server.
	 * 
	 * @param err
	 * @param context
	 * @return
	 */
	public ServerErrorInfo handleServerError(Object err) {
		VolleyError error = (VolleyError) err;

		ServerErrorInfo serverErrorInfo = new ServerErrorInfo();

		NetworkResponse response = error.networkResponse;

		if (response != null) {
			switch (response.statusCode) {
			case 403:
			case 409:
			case 400:
			case 415:
			case 500:
				try {
					createErrorResponse(serverErrorInfo, response);
				} catch (Exception e) {
					serverErrorInfo.setHttpStatusCode(response.statusCode);
				}
				break;
			default:
				serverErrorInfo.setHttpStatusCode(response.statusCode);
				break;
			}
		}
		return serverErrorInfo;
	}

	private void createErrorResponse(ServerErrorInfo serverErrorInfo, NetworkResponse response) throws ParseError {
		String dataString = new String(response.data);
		ServerErrorResponse errorResponse = jsonParser.toObject(dataString,	ServerErrorResponse.class);
		serverErrorInfo.setErrorResponse(errorResponse);
		serverErrorInfo.setHttpStatusCode(response.statusCode);
		serverErrorInfo.setMessage(errorResponse.getMessage());
	}

	/**
	 * Handles the server error, tries to determine whether to show a stock
	 * message or to show a message retrieved from the server.
	 * 
	 * @param <T>
	 * 
	 * @param err
	 * @param context
	 * @return
	 */
	public <T> void handlePartialData(Object err, Class<T> clazz, ServiceListener<T> listener) {
		VolleyError error = (VolleyError) err;

		NetworkResponse response = error.networkResponse;

		if (response != null) {
			try {
				String dataString = new String(response.data);
				T object = jsonParser.toObject(dataString, clazz);
				listener.onRequestFinished(object);
			} catch (ParseError e) {
				listener.onParseError(e);
			}
		} else {
			listener.onServerError(new ServerErrorInfo(206, null, null));
		}
	}

	/**
	 * Handles the server error, tries to determine whether to show a stock
	 * message or to show a message retrieved from the server.
	 * 
	 * @param <T>
	 * 
	 * @param err
	 * @param context
	 * @return
	 */
	public <T> void handleArrayPartialData(Object err, Class<T> clazz,	ServiceListener<List<T>> listener) {
		VolleyError error = (VolleyError) err;

		NetworkResponse response = error.networkResponse;

		if (response != null) {
			try {
				String dataString = new String(response.data);
				List<T> object = jsonParser.toList(dataString, clazz);

				listener.onRequestFinished(object);
			} catch (ParseError e) {
				listener.onParseError(e);
			}
		} else {
			listener.onServerError(new ServerErrorInfo(206, null, null));
		}
	}
}
package com.globallogic.ox.app.services;

import java.util.HashMap;

public interface WSConnection {

	
	/**
	 * Makes a GET request to the given URL expecting a JSONObject as response.
	 * Notifies the given listener with the request result.
	 * 
	 * @param URL
	 * @param listener
	 * @param clazz
	 */
	<T> void makeObjectGetRequest(String url, final ServiceListener<T> listener, Class<T> clazz);

	/**
	 * Makes a POST request to the given URL, using the given params as request
	 * params, expecting a JSONObject as response. Notifies the given listener
	 * with the request result.
	 * 
	 * @param URL
	 * @param listener
	 * @param clazz
	 */
	<T> void makeObjectPostRequest(String url, final ServiceListener<T> listener, HashMap<String, String> params, Class<T> clazz);

}

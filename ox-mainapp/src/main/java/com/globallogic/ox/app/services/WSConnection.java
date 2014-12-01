package com.globallogic.ox.app.services;

import java.util.HashMap;
import java.util.List;

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
	 * Makes a GET request to the given URL expecting a JSONArray as response.
	 * Notifies the listener with the request result.
	 * 
	 * @param url
	 * @param listener
	 * @param clazz
	 * @param mock
	 *            This indicates if the request should be mock or not. This is
	 *            configured by the properties file and depends also on the
	 *            global mock configuration.
	 */
	<T> void makeArrayGetRequest(String url, final ServiceListener<List<T>> listener, Class<T> clazz);
	
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

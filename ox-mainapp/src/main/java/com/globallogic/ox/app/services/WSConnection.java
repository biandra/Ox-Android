package com.globallogic.ox.app.services;

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
}

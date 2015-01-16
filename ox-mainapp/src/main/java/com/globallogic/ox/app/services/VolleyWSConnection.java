package com.globallogic.ox.app.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.MyJsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.globallogic.ox.app.constant.Constants;
import com.globallogic.ox.exceptions.ParseError;
import com.globallogic.ox.parsing.IJSONParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class VolleyWSConnection implements WSConnection{

	private boolean cacheEnabled = true;
	private RequestQueue requestQueue;
	private RetryPolicy retryPolicy;
	private static final int CONNECTION_TIMEUOT = 60000;
	private IJSONParser jsonParser;
	private VolleyErrorHelper volleyErrorHelper;
	
	
	@Inject
	public VolleyWSConnection(
			Context context,
			VolleyErrorHelper volleyErrorHelper,
			IJSONParser jsonParser) {
		this.jsonParser = jsonParser;
		this.volleyErrorHelper = volleyErrorHelper;
		
		requestQueue = Volley.newRequestQueue(context);
		retryPolicy = new DefaultRetryPolicy(CONNECTION_TIMEUOT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
	}
	
	@Override
	public <T> void makeObjectGetRequest(String url, ServiceListener<T> listener, Class<T> clazz) {
		makeObjectRequest(Method.GET, url, listener, null, clazz);
	}

	@Override
	public <T> void makeArrayGetRequest(String url,	ServiceListener<List<T>> listener, Class<T> clazz) {
		JsonArrayRequest req = null;

		req = new JsonArrayRequest(url, getArrayListener(listener, clazz),	getErrorArrayListener(listener, clazz)) {

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put(Constants.TOKEN_PARAM_NAME,	Constants.TOKEN_PAREM_VALUE + " " + "accessToken");
				return headers;
			}
		};
		req.setShouldCache(cacheEnabled);
		listener.onRequestStarted();
		addToRequestQueue(req);
	}
	
	@Override
	public <T> void makeObjectPostRequest(String url, ServiceListener<T> listener, HashMap<String, String> params, Class<T> clazz) {
		makeObjectRequest(Method.POST,url, listener, params, clazz);
	}

	private <T> void makeObjectRequest(Integer method, String url, final ServiceListener<T> listener, HashMap<String, String> params, final Class<T> clazz) {
		JSONObject jsonRequest = null;
		MyJsonObjectRequest req = null;
		
		if (params != null) {
			jsonRequest = new JSONObject(params);
		}
		
		req = new MyJsonObjectRequest(method, url, jsonRequest, getListener(listener,	clazz), getErrorListener(listener, clazz)){

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put(Constants.TOKEN_PARAM_NAME,	Constants.TOKEN_PAREM_VALUE + " " + "accessToken");
				return headers;
			}
		};

		req.setShouldCache(cacheEnabled);
		listener.onRequestStarted();
		addToRequestQueue(req);
	}
	
//	private <T> void makeObjectRequest(Integer method, String url, final ServiceListener<T> listener, HashMap<String, String> params, final Class<T> clazz) {
//		JSONObject jsonRequest = null;
//		JsonObjectRequest req = null;
//		
//		if (params != null) {
//			jsonRequest = new JSONObject(params);
//		}
//		req = new JsonObjectRequest(method, url, jsonRequest, getListener(listener,	clazz), getErrorListener(listener, clazz)) {
//
//			@Override
//			public Map<String, String> getHeaders() throws AuthFailureError {
//				HashMap<String, String> headers = new HashMap<String, String>();
//				headers.put(Constants.TOKEN_PARAM_NAME,	Constants.TOKEN_PAREM_VALUE + " " + "accessToken");
//				return headers;
//			}
//		};
//
//		req.setShouldCache(cacheEnabled);
//		listener.onRequestStarted();
//		addToRequestQueue(req);
//	}
	
	/**
	 * Adds the request to the corresponding request queue 
	 * @param request
	 */
	private <T> void addToRequestQueue(Request<T> request) {
		request.setRetryPolicy(retryPolicy);
		requestQueue.add(request);
	}
	
	private <T> Listener<JSONObject> getListener(final ServiceListener<T> listener, final Class<T> clazz) {
		return new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				T object = null;
				try {
					if (object != null){
						object = jsonParser.toObject(response.toString(), clazz);
					}
					listener.onRequestFinished(object);
				} catch (ParseError e) {
					listener.onParseError(e);
				}
			}
		};
	}
	
	
	private <T> Listener<JSONArray> getArrayListener(final ServiceListener<List<T>> listener, final Class<T> clazz) {
		return new Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				List<T> object;
				try {
					object = jsonParser.toList(response.toString(),	clazz);
					listener.onRequestFinished(object);
				} catch (ParseError e) {
					listener.onParseError(e);
				}
			}
		};
	}
	
	private <T> ErrorListener getErrorListener(final ServiceListener<T> listener,	final Class<T> clazz) {
		return new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				handleError(listener, error, clazz);
			}
		};
	}
	
	private <T> ErrorListener getErrorArrayListener(final ServiceListener<List<T>> listener, final Class<T> clazz) {
		return new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				handleArrayError(listener, error, clazz);
			}
		};
	}
	
	private <T> void handleError(ServiceListener<T> listener, VolleyError error, Class<T> clazz) {
		if (volleyErrorHelper.isNetworkProblem(error)) {
			listener.onConnectionError();
		} else if (volleyErrorHelper.isPartialData(error)) {
			volleyErrorHelper.handlePartialData(error, clazz, listener);
		} else if (volleyErrorHelper.isTimeoutProblem(error)) {
			listener.onServerError(volleyErrorHelper.handleServerError(error));
		} else if (volleyErrorHelper.isServerProblem(error)) {
			listener.onServerError(volleyErrorHelper.handleServerError(error));
		} else if (volleyErrorHelper.isSessionProblem(error)) {
			listener.onSessionError();
		}
	}
	
	private <T> void handleArrayError(ServiceListener<List<T>> listener, VolleyError error, Class<T> clazz) {
		if (volleyErrorHelper.isNetworkProblem(error)) {
			listener.onConnectionError();
		} else if (volleyErrorHelper.isPartialData(error)) {
			volleyErrorHelper.handleArrayPartialData(error, clazz, listener);
		} else if (volleyErrorHelper.isTimeoutProblem(error)) {
			listener.onServerError(volleyErrorHelper.handleServerError(error));
		} else if (volleyErrorHelper.isServerProblem(error)) {
			listener.onServerError(volleyErrorHelper.handleServerError(error));
		} else if (volleyErrorHelper.isSessionProblem(error)) {
			listener.onSessionError();
		}
	}

}

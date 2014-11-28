package com.globallogic.ox.app.services;

import com.globallogic.ox.domain.User;
import com.globallogic.ox.parsing.IJSONParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ServicesImpl implements ServicesInterface{

	protected WSConnection connection;
	private IJSONParser jsonParser;
	
	@Inject
	public ServicesImpl(WSConnection connection, IJSONParser jsonParser) {
		this.jsonParser = jsonParser;
		this.connection = connection;
	}
	
	@Override
	public void getAcccount(ServiceListener<User> listener, Class<User> clazz) {
		String url = "";
		connection.makeObjectGetRequest(url, listener, clazz);
	}

}

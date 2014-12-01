package com.globallogic.ox.app.services;

import com.globallogic.ox.domain.Account;
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
	public void getToken(ServiceListener<Account> listener, Class<Account> clazz) {
		String url = "";
		connection.makeObjectGetRequest(url, listener, clazz);
	}

}

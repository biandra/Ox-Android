package com.globallogic.ox.app.services;

import java.util.List;

import com.globallogic.ox.domain.Stage;
import com.globallogic.ox.domain.Project;
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

//	@Override
//	public void getToken(ServiceListener<ServerErrorResponse> listener, Account account, Class<ServerErrorResponse> clazz) {
//		String url = "";
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("email", account.geteMail());
//		params.put("password", account.getPassword());
//
//		connection.makeObjectPostRequest(url, listener, params, clazz);
//	}

	@Override
	public void getProjects(ServiceListener<List<Project>> listener, Class<Project> clazz) {
		String url = "http://172.17.201.94:9091/Ox-Server/me/projects";
		connection.makeArrayGetRequest(url, listener, clazz);
	}

	@Override
	public void getStages(ServiceListener<List<Stage>> listener, int idProject, Class<Stage> clazz) {
		String url = "http://172.17.201.94:9091/Ox-Server/me/projects/" + idProject + "/stages";
		connection.makeArrayGetRequest(url, listener, clazz);
	}
	

}

package com.globallogic.ox.app.services;

import java.util.List;

import com.globallogic.ox.domain.Project;


public interface ServicesInterface {

//	void getToken(ServiceListener<ServerErrorResponse> listener, Account account, Class<ServerErrorResponse> clazz);
	void getProject(ServiceListener<List<Project>> listener, Class<Project> clazz);
	
}

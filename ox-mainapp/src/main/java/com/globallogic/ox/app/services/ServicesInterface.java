package com.globallogic.ox.app.services;

import java.util.List;

import com.globallogic.ox.domain.ServerErrorResponse;
import com.globallogic.ox.domain.Stage;
import com.globallogic.ox.domain.Project;


public interface ServicesInterface {

//	void getToken(ServiceListener<ServerErrorResponse> listener, Account account, Class<ServerErrorResponse> clazz);
	
	void getProjects(ServiceListener<List<Project>> listener, Class<Project> clazz);
	
	void runProject(ServiceListener<ServerErrorResponse> listener, int idProject, Class<ServerErrorResponse> clazz);

	void getStages(ServiceListener<List<Stage>> listener, int idProject, Class<Stage> clazz);
	
}

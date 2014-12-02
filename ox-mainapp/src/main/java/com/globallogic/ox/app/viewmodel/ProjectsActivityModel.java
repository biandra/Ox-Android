package com.globallogic.ox.app.viewmodel;

import java.util.List;

import com.globallogic.ox.app.services.ServiceListener;
import com.globallogic.ox.app.services.ServicesInterface;
import com.globallogic.ox.app.utils.RoboguiceUtils;
import com.globallogic.ox.app.viewlistener.ProjectsActivityListener;
import com.globallogic.ox.domain.Project;
import com.globallogic.ox.domain.ServerErrorInfo;
import com.globallogic.ox.exceptions.ParseError;
import com.google.inject.Inject;

public class ProjectsActivityModel {
	
	@Inject
	private ServicesInterface service;
	
	private ProjectsActivityListener view;

	public ProjectsActivityModel(ProjectsActivityListener view) {
		super();
		RoboguiceUtils.inject(this);
		this.view = view;
	}

	public void getProjects() {
		service.getProject(new ServiceListener<List<Project>>(){

			@Override
			public void onRequestStarted() {
				view.onGetProjectsStarted();
			}

			@Override
			public void onConnectionError() {
				view.onGetProjectsError();
			}

			@Override
			public void onSessionError() {
				view.onGetProjectsError();
			}

			@Override
			public void onRequestFinished(List<Project> result) {
				view.onGetProjectsFinished();
			}

			@Override
			public void onParseError(ParseError error) {
				view.onGetProjectsError();
			}

			@Override
			public void onServerError(ServerErrorInfo errorInfo) {
				view.onServerError(errorInfo);
			}
			
		}, Project.class);
	}
}

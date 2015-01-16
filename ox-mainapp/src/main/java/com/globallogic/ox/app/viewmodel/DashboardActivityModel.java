package com.globallogic.ox.app.viewmodel;

import java.util.List;

import com.globallogic.ox.app.services.ServiceError;
import com.globallogic.ox.app.services.ServiceListener;
import com.globallogic.ox.app.services.ServicesInterface;
import com.globallogic.ox.app.utils.RoboguiceUtils;
import com.globallogic.ox.app.viewlistener.DashboardActivityListener;
import com.globallogic.ox.domain.Project;
import com.globallogic.ox.domain.ServerErrorInfo;
import com.globallogic.ox.exceptions.ParseError;
import com.google.inject.Inject;

public class DashboardActivityModel {
	
	@Inject
	private ServicesInterface service;
	
	private DashboardActivityListener view;

	public DashboardActivityModel(DashboardActivityListener view) {
		super();
		RoboguiceUtils.inject(this);
		this.view = view;
	}

	public void getPipelines() {
		service.getProjects(new ServiceListener<List<Project>>(){

			@Override
			public void onRequestStarted() {
				view.onGetDashboardStarted();
			}

			@Override
			public void onConnectionError() {
				view.onGetDashboardError();
			}

			@Override
			public void onSessionError() {
				view.onGetDashboardError();
			}

			@Override
			public void onRequestFinished(List<Project> pipelines) {
				view.onGetDashboardFinished(pipelines);
			}

			@Override
			public void onParseError(ParseError error) {
				view.onGetDashboardError();
			}

			@Override
			public void onServerError(ServerErrorInfo errorInfo) {
				view.onServerError(errorInfo);
			}

			@Override
			public void onRequestError(ServiceError error) {
				view.onGetDashboardError();
			}
			
		}, Project.class);
	}
	
}

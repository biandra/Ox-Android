package com.globallogic.ox.app.viewmodel;

import java.util.List;

import com.globallogic.ox.app.services.ServiceError;
import com.globallogic.ox.app.services.ServiceListener;
import com.globallogic.ox.app.services.ServicesInterface;
import com.globallogic.ox.app.utils.RoboguiceUtils;
import com.globallogic.ox.app.viewlistener.ViewHolderPipelineListener;
import com.globallogic.ox.domain.Project;
import com.globallogic.ox.domain.ServerErrorInfo;
import com.globallogic.ox.domain.ServerErrorResponse;
import com.globallogic.ox.exceptions.ParseError;
import com.google.inject.Inject;

public class ViewHolderPipelineModel {
	
	@Inject
	private ServicesInterface service;
	
	private ViewHolderPipelineListener view;
	
	public ViewHolderPipelineModel(ViewHolderPipelineListener view) {
		super();
		RoboguiceUtils.inject(this);
		this.view = view;
	}
	
	public void run(final int idProject) {
		service.runProject(new ServiceListener<ServerErrorResponse>(){

			@Override
			public void onRequestStarted() {
				view.onPostViewHolderPipelineStarted();
			}

			@Override
			public void onServerError(ServerErrorInfo errorInfo) {
				view.onPostViewHolderPipelineError();
			}

			@Override
			public void onConnectionError() {
				view.onPostViewHolderPipelineError();
			}

			@Override
			public void onSessionError() {
				view.onPostViewHolderPipelineError();
			}

			@Override
			public void onRequestFinished(ServerErrorResponse result) {
				view.onPostViewHolderPipelineFinished();
			}

			@Override
			public void onParseError(ParseError error) {
				view.onPostViewHolderPipelineError();
			}

			@Override
			public void onRequestError(ServiceError error) {
				view.onPostViewHolderPipelineError();
			}
		}, idProject, ServerErrorResponse.class);
	}
	
	public void getPipeline(int idProject) {
		service.getProject(new ServiceListener<Project>(){

			@Override
			public void onRequestStarted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onServerError(ServerErrorInfo errorInfo) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onConnectionError() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSessionError() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onRequestError(ServiceError error) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onParseError(ParseError error) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onRequestFinished(Project project) {
				view.onGetViewHolderPipelineFinished(project);
			}
		},idProject, Project.class);
	}
}

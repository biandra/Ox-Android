package com.globallogic.ox.app.viewmodel;

import java.util.List;
import com.globallogic.ox.app.services.ServiceListener;
import com.globallogic.ox.app.services.ServicesInterface;
import com.globallogic.ox.app.utils.RoboguiceUtils;
import com.globallogic.ox.app.viewlistener.PipelineActivityListener;
import com.globallogic.ox.domain.Stage;
import com.globallogic.ox.domain.ServerErrorInfo;
import com.globallogic.ox.exceptions.ParseError;
import com.google.inject.Inject;

public class PipelineActivityModel {

	@Inject
	private ServicesInterface service;
	
	private PipelineActivityListener view;

	public PipelineActivityModel(PipelineActivityListener view) {
		super();
		RoboguiceUtils.inject(this);
		this.view = view;
	}

	public void getStages(int idProject) {
		service.getStages(new ServiceListener<List<Stage>>(){

			@Override
			public void onRequestStarted() {
				view.onGetPipelineStarted();
			}

			@Override
			public void onServerError(ServerErrorInfo errorInfo) {
				view.onServerError(errorInfo);
			}

			@Override
			public void onConnectionError() {
				view.onGetPipelineError();
			}

			@Override
			public void onSessionError() {
				view.onGetPipelineError();
			}

			@Override
			public void onRequestFinished(List<Stage> stages) {
				view.onGetPipelineFinished(stages);
			}

			@Override
			public void onParseError(ParseError error) {
				view.onGetPipelineError();
			}
		}, idProject, Stage.class);
	}
}

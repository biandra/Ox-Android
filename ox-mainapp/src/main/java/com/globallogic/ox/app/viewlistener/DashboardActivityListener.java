package com.globallogic.ox.app.viewlistener;

import java.util.List;

import com.globallogic.ox.domain.Project;
import com.globallogic.ox.domain.ServerErrorInfo;

public interface DashboardActivityListener {

	void onGetDashboardStarted();
	void onGetDashboardFinished(List<Project> pipelines);
	void onGetDashboardError();
	void onServerError(ServerErrorInfo errorInfo);;
}

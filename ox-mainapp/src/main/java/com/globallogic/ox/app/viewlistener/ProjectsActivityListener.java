package com.globallogic.ox.app.viewlistener;

import java.util.List;

import com.globallogic.ox.domain.Project;
import com.globallogic.ox.domain.ServerErrorInfo;

public interface ProjectsActivityListener {

	void onGetProjectsStarted();
	void onGetProjectsFinished(List<Project> projects);
	void onGetProjectsError();
	void onServerError(ServerErrorInfo errorInfo);;
}

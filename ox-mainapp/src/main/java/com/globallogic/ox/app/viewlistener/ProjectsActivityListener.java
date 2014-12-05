package com.globallogic.ox.app.viewlistener;

import com.globallogic.ox.domain.ServerErrorInfo;

public interface ProjectsActivityListener {

	void onGetProjectsStarted();
	void onGetProjectsFinished();
	void onGetProjectsError();
	void onServerError(ServerErrorInfo errorInfo);;
}

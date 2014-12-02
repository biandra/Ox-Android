package com.globallogic.ox.app.viewlistener;

import com.globallogic.ox.domain.ServerErrorInfo;

import android.app.Activity;

public interface ProjectsActivityListener {

	Activity getActivity();
	void onGetProjectsStarted();
	void onGetProjectsFinished();
	void onGetProjectsError();
	void onServerError(ServerErrorInfo errorInfo);;
}

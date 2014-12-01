package com.globallogic.ox.app.viewlistener;

import android.app.Activity;

public interface ProjectsActivityListener {

	Activity getActivity();
	void onGetProjectsStarted();
	void onGetProjectsFinished();
	void onGetProjectsError();
	
}

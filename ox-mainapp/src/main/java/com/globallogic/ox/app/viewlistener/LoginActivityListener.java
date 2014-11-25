package com.globallogic.ox.app.viewlistener;

import android.app.Activity;

public interface LoginActivityListener {

	Activity getActivity();
	void onGetLoginStarted();
	void onGetLoginFinished();
	void onGetLoginError();
}

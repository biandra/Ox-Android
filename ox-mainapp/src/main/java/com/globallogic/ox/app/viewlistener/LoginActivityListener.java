package com.globallogic.ox.app.viewlistener;

import com.globallogic.ox.domain.ServerErrorInfo;

import android.app.Activity;

public interface LoginActivityListener {

	Activity getActivity();
	void onGetLoginStarted();
	void onGetLoginFinished();
	void onGetLoginError();
	void onServerError(ServerErrorInfo errorInfo);
}

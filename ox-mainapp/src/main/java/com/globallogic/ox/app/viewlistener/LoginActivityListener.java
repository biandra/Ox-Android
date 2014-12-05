package com.globallogic.ox.app.viewlistener;

import com.globallogic.ox.domain.ServerErrorInfo;

public interface LoginActivityListener {

	void onGetLoginStarted();
	void onGetLoginFinished();
	void onGetLoginError();
	void onServerError(ServerErrorInfo errorInfo);
}

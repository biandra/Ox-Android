package com.globallogic.ox.app.viewlistener;

import com.globallogic.ox.domain.ServerErrorInfo;

public interface ViewHolderPipelineListener {

	void onPostViewHolderPipelineStarted();
	void onPostViewHolderPipelineFinished();
	void onPostViewHolderPipelineError();
	void onServerError(ServerErrorInfo errorInfo);
}

package com.globallogic.ox.app.viewlistener;

import java.util.List;
import com.globallogic.ox.domain.Stage;
import com.globallogic.ox.domain.ServerErrorInfo;

public interface PipelineActivityListener {

	void onGetPipelineStarted();
	void onGetPipelineFinished(List<Stage> stages);
	void onGetPipelineError();
	void onServerError(ServerErrorInfo errorInfo);
}

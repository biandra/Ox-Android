package com.globallogic.ox.app.activities;

import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.globallogic.ox.R;
import com.globallogic.ox.app.activities.base.BaseActivity;
import com.globallogic.ox.app.viewlistener.ProjectsActivityListener;
import com.globallogic.ox.app.viewmodel.ProjectsActivityModel;
import com.globallogic.ox.domain.ServerErrorInfo;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class ProjectsActivity extends BaseActivity implements ProjectsActivityListener{

	private ProjectsActivityModel model;
	
	@InjectView(R.id.view_Projects_List)
	private View projectsList;
	
	@InjectView(R.id.projects_request_loading)
	private RelativeLayout progressDialog;
	
	@InjectView(R.id.projects_scrollview)
	private PullToRefreshScrollView pullToRefreshScroll;
	
	@InjectView(R.id.projects_pull_to_refresh_hint)
	private View pullToRefreshHintView;
	
	@InjectView(R.id.projects_container_pull_to_refresh)
	private LinearLayout containerPullToRefresh;
	
	public ProjectsActivity() {
		super(R.string.app_name, R.layout.projects_activity);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  
		model = new ProjectsActivityModel(this);
		model.getProjects();
		
		pullToRefreshScroll.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> arg0) {
				model.getProjects();
			}
		});
		
		pullToRefreshScroll.setOnPullEventListener(new OnPullEventListener<ScrollView>() {
			@Override
			public void onPullEvent(PullToRefreshBase<ScrollView> arg0,	State state, Mode mode) {
				if (state.equals(State.PULL_TO_REFRESH)) {
					pullToRefreshHintView.setVisibility(View.GONE);
				} else if (state.equals(State.RESET)) {
					pullToRefreshHintView.setVisibility(View.VISIBLE);
				}
			}
		});
    }

	@Override
	public Activity getActivity() {
		return this;
	}

	@Override
	public void onGetProjectsStarted() {
		setViewLoading();
	}

	@Override
	public void onGetProjectsFinished() {
		setViewProjets();
	}

	@Override
	public void onGetProjectsError() {
		setViewError();
	}

	@Override
	public void onServerError(ServerErrorInfo errorInfo) {
		setViewError();
	}
	
	private void setViewError() {
		pullToRefreshScroll.onRefreshComplete();
		pullToRefreshScroll.setMode(Mode.PULL_FROM_START);
		projectsList.setVisibility(View.GONE);
		progressDialog.setVisibility(View.GONE);
		pullToRefreshHintView.setVisibility(View.VISIBLE);
		containerPullToRefresh.setVisibility(View.VISIBLE);
	}

	private void setViewLoading() {
		projectsList.setVisibility(View.GONE);
		progressDialog.setVisibility(View.VISIBLE);
	}
	
	private void setViewProjets() {
		pullToRefreshScroll.onRefreshComplete();
		pullToRefreshScroll.setMode(Mode.DISABLED);
		projectsList.setVisibility(View.VISIBLE);
		progressDialog.setVisibility(View.GONE);
		pullToRefreshHintView.setVisibility(View.GONE);
		containerPullToRefresh.setVisibility(View.VISIBLE);
	}
}

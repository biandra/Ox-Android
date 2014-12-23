package com.globallogic.ox.app.activities;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.globallogic.ox.R;
import com.globallogic.ox.app.activities.base.BaseActivitySlideMenuActionBarMenu;
import com.globallogic.ox.app.adapter.ProjectAdapter;
import com.globallogic.ox.app.viewlistener.DashboardActivityListener;
import com.globallogic.ox.app.viewmodel.DashboardActivityModel;
import com.globallogic.ox.domain.Project;
import com.globallogic.ox.domain.ServerErrorInfo;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class DashboardActivity extends BaseActivitySlideMenuActionBarMenu implements DashboardActivityListener{

	private DashboardActivityModel model;
	
	@InjectView(R.id.view_Dashboard_List)
	private View pipelinesList;
	
	@InjectView(R.id.dashboard_request_loading)
	private RelativeLayout progressDialog;
	
	@InjectView(R.id.dashboard_scrollview)
	private PullToRefreshScrollView pullToRefreshScroll;
	
	@InjectView(R.id.dashboard_pull_to_refresh_hint)
	private View pullToRefreshHintView;
	
	@InjectView(R.id.dashboard_container_pull_to_refresh)
	private LinearLayout containerPullToRefresh;
	
	@InjectView(R.id.listView_Dashboard)
	private ListView listView;
	
	public DashboardActivity() {
		super(R.string.app_dashboard, R.layout.dashboard_activity);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  
		model = new DashboardActivityModel(this);
		model.getPipelines();
		
		pullToRefreshScroll.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> arg0) {
				model.getPipelines();
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
	public void onGetDashboardStarted() {
		setViewLoading();
	}

	@Override
	public void onGetDashboardFinished(List<Project> pipelines) {
		setViewProjets(pipelines);
	}

	@Override
	public void onGetDashboardError() {
//		setViewError();
		
		List<Project> pipelines = new ArrayList<Project>();
		Project p = new Project();
		p.setId(1);
		Project p2 = new Project();
		p.setId(2);
		pipelines.add(p);
		pipelines.add(p2);
		setViewProjets(pipelines);
	}

	@Override
	public void onServerError(ServerErrorInfo errorInfo) {
//		setViewError();
		
		List<Project> pipelines = new ArrayList<Project>();
		Project p = new Project();
		p.setId(1);
		Project p2 = new Project();
		p.setId(2);
		pipelines.add(p);
		pipelines.add(p2);
		setViewProjets(pipelines);
	}
	
	private void setViewError() {
		pullToRefreshScroll.onRefreshComplete();
		pullToRefreshScroll.setMode(Mode.PULL_FROM_START);
		pipelinesList.setVisibility(View.GONE);
		progressDialog.setVisibility(View.GONE);
		pullToRefreshHintView.setVisibility(View.VISIBLE);
		containerPullToRefresh.setVisibility(View.VISIBLE);
	}

	private void setViewLoading() {
		pullToRefreshScroll.setMode(Mode.DISABLED);
		pipelinesList.setVisibility(View.GONE);
		progressDialog.setVisibility(View.VISIBLE);
	}
	
	private void setViewProjets(List<Project> pipelines) {
		pullToRefreshScroll.onRefreshComplete();
		pullToRefreshScroll.setMode(Mode.DISABLED);
		pipelinesList.setVisibility(View.VISIBLE);
		progressDialog.setVisibility(View.GONE);
		pullToRefreshHintView.setVisibility(View.GONE);
		containerPullToRefresh.setVisibility(View.VISIBLE);
		
		listView.setAdapter(new ProjectAdapter(this, pipelines));
	}
}

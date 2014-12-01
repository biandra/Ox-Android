package com.globallogic.ox.app.activities;

import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.globallogic.ox.R;
import com.globallogic.ox.app.activities.base.BaseActivity;
import com.globallogic.ox.app.viewlistener.ProjectsActivityListener;
import com.globallogic.ox.app.viewmodel.ProjectsActivityModel;

public class ProjectsActivity extends BaseActivity implements ProjectsActivityListener{

	private ProjectsActivityModel model;
	
	@InjectView(R.id.view_Projects_List)
	private View viewProjectsList;
	
	public ProjectsActivity() {
		super(R.string.app_name, R.layout.jenkins_projects_list_activity);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  
		model = new ProjectsActivityModel(this);
		model.getProjects();
    }

	@Override
	public Activity getActivity() {
		return this;
	}

	@Override
	public void onGetProjectsStarted() {
	
	}

	@Override
	public void onGetProjectsFinished() {
		
	}

	@Override
	public void onGetProjectsError() {
		
	}

}

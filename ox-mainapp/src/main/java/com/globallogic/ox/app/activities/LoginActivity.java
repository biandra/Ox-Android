package com.globallogic.ox.app.activities;

import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.globallogic.ox.R;
import com.globallogic.ox.app.activities.base.BaseActivity;
import com.globallogic.ox.app.viewlistener.LoginActivityListener;
import com.globallogic.ox.app.viewmodel.LoginActivityModel;

public class LoginActivity extends BaseActivity implements LoginActivityListener{

	private LoginActivityModel model;

	@InjectView(R.id.login_view)
	private View mainView;
	
	public LoginActivity() {
		super(R.string.app_name, R.layout.login_activity);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		model = new LoginActivityModel(this);
		model.getLogin();
    }

	@Override
	public Activity getActivity() {
		return this;
	}

	@Override
	public void onGetLoginStarted() {
	}

	@Override
	public void onGetLoginFinished() {
		showProjects();
	}

	@Override
	public void onGetLoginError() {
	}
	
	private void showProjects() {
		// TODO Auto-generated method stub
		
	}
}


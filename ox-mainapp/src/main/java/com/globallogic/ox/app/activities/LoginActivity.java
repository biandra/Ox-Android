package com.globallogic.ox.app.activities;

import android.app.Activity;
import android.os.Bundle;

import com.globallogic.ox.app.activities.base.BaseActivity;
import com.globallogic.ox.app.viewlistener.LoginActivityListener;
import com.globallogic.ox.app.viewmodel.LoginActivityModel;

public class LoginActivity extends BaseActivity implements LoginActivityListener{

	private LoginActivityModel model;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        super.onCreate(savedInstanceState);

		model = new LoginActivityModel(this);
		model.getLogin();
//        setContentView(R.layout.activity_main);
    }

	@Override
	public Activity getActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onGetLoginStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetLoginFinished() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetLoginError() {
		// TODO Auto-generated method stub
		
	}

}


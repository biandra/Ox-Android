package com.globallogic.ox.app.viewmodel;

import com.globallogic.ox.app.viewlistener.LoginActivityListener;

public class LoginActivityModel {
	
	private LoginActivityListener view;

	public LoginActivityModel(LoginActivityListener view) {
		super();
//		RoboguiceUtils.inject(this);
		this.view = view;
	}

	public void getLogin() {
		// TODO Auto-generated method stub
		
	}

}

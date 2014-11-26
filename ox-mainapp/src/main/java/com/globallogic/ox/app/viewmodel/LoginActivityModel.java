package com.globallogic.ox.app.viewmodel;

import com.globallogic.ox.app.services.ServiceListener;
import com.globallogic.ox.app.services.ServicesInterface;
import com.globallogic.ox.app.utils.RoboguiceUtils;
import com.globallogic.ox.app.viewlistener.LoginActivityListener;
import com.globallogic.ox.domain.User;
import com.google.inject.Inject;

public class LoginActivityModel {
	
	@Inject
	private ServicesInterface service;
	
	private LoginActivityListener view;

	public LoginActivityModel(LoginActivityListener view) {
		super();
		RoboguiceUtils.inject(this);
		this.view = view;
	}

	public void getLogin() {
		service.getLogin(new ServiceListener<User>() {

			@Override
			public void onRequestStarted() {
				view.onGetLoginStarted();
			}

			@Override
			public void onConnectionError() {
			}

			@Override
			public void onSessionError() {
			}

			@Override
			public void onRequestFinished(User result) {
			}
			
		}, User.class);
		
	}

}

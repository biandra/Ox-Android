package com.globallogic.ox.app.viewmodel;

import com.globallogic.ox.app.services.ServiceListener;
import com.globallogic.ox.app.services.ServicesInterface;
import com.globallogic.ox.app.utils.RoboguiceUtils;
import com.globallogic.ox.app.viewlistener.LoginActivityListener;
import com.globallogic.ox.domain.Account;
import com.globallogic.ox.domain.ServerErrorInfo;
import com.globallogic.ox.domain.ServerErrorResponse;
import com.globallogic.ox.exceptions.ParseError;
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

	private ServiceListener<ServerErrorResponse> listenerAccount = new ServiceListener<ServerErrorResponse>() {
		@Override
		public void onRequestStarted() {
			view.onGetLoginStarted();
		}

		@Override
		public void onConnectionError() {
			view.onGetLoginError();
		}

		@Override
		public void onSessionError() {
			view.onGetLoginError();
		}

		@Override
		public void onRequestFinished(ServerErrorResponse serverErrorResponse) {
			view.onGetLoginFinished();
		}

		@Override
		public void onParseError(ParseError error) {
			view.onGetLoginError();
		}

		@Override
		public void onServerError(ServerErrorInfo errorInfo) {
			view.onServerError(errorInfo);
		}
	};
	
	public void getToken(Account account) {
//		service.getToken(listenerAccount, account, ServerErrorResponse.class);
	}

}

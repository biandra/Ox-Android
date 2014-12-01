package com.globallogic.ox.app.activities;

import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.globallogic.ox.R;
import com.globallogic.ox.app.activities.base.BaseActivity;
import com.globallogic.ox.app.viewlistener.LoginActivityListener;
import com.globallogic.ox.app.viewmodel.LoginActivityModel;
import com.globallogic.ox.domain.Account;

public class LoginActivity extends BaseActivity implements LoginActivityListener{

	private LoginActivityModel model;

	@InjectView(R.id.view_Login)
	private View viewLogin;
	
	@InjectView(R.id.button_Login)
	private Button buttonLogin;
	
	@InjectView(R.id.editText_Email)
	private EditText editTextEmail;
	
	@InjectView(R.id.editText_Password)
	private EditText editTextPassword;
	
	public LoginActivity() {
		super(R.string.app_name, R.layout.login_activity);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  
		model = new LoginActivityModel(this);
		buttonLogin.setOnClickListener(login);
    }
    
    private View.OnClickListener login = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Account account = new Account();
			account.seteMail(editTextEmail.getText().toString());
			account.setPassword(editTextPassword.getText().toString());
			model.getToken(account);
			
			//TODO:remove luego de implementar login
			Intent projectsList = new Intent(LoginActivity.this, ProjectsActivity.class);
			startActivity(projectsList);
		}
	};

	@Override
	public Activity getActivity() {
		return this;
	}

	@Override
	public void onGetLoginStarted() {
//		setLoadingView();
	}


	@Override
	public void onGetLoginFinished() {
		Intent projectsList = new Intent(LoginActivity.this, ProjectsActivity.class);
		startActivity(projectsList);
	}

	@Override
	public void onGetLoginError() {
//		setErrorView();
	}

}


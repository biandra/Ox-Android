package com.globallogic.ox.app.activities.base;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.globallogic.ox.R;
import com.globallogic.ox.app.OxApp;


public class BaseFragment extends SherlockFragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.header_));
	}

	@Override
	protected void onStart() {
		super.onStart();
		OxApp.getInstance().setCurrentActivity(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		OxApp.getInstance().activityResumed();
	}

	@Override
	protected void onPause() {
		super.onPause();
		OxApp.getInstance().activityPaused();
	}
}

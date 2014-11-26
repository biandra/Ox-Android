package com.globallogic.ox.app.activities.base;

import android.os.Bundle;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.actionbarsherlock.view.Window;
import com.globallogic.ox.R;


public class BaseActivity extends RoboSherlockFragmentActivity{
	
	private int mTitleRes;
	private int mLayout;
	
	public BaseActivity(int titleRes, int layoutId) {
		mTitleRes = titleRes;
		mLayout = layoutId;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		getSupportActionBar().hide();
		setTitle(mTitleRes);
		setContentView(mLayout);
		getSupportActionBar().show();
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.header_));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setDisplayUseLogoEnabled(false);
		getSupportActionBar().setIcon(android.R.color.transparent);
	}

//	@Override
//	protected void onStart() {
//		super.onStart();
//		OxApp.getInstance().setCurrentActivity(this);
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		OxApp.getInstance().activityResumed();
//	}
//
//	@Override
//	protected void onPause() {
//		super.onPause();
//		OxApp.getInstance().activityPaused();
//	}
}

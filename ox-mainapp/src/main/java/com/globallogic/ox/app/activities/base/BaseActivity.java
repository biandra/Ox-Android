package com.globallogic.ox.app.activities.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.Window;
import com.globallogic.ox.R;
import com.globallogic.ox.app.OxApp;


public class BaseActivity extends RoboSherlockFragmentActivity{
	
	private int mTitleRes;
	private int mLayout;
	private static Button notifCount;
	private static int mNotifCount = 0; 
	
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);

	    View count = menu.findItem(R.id.badge).getActionView();
	    notifCount = (Button) count.findViewById(R.id.notif_count);
	    notifCount.setText(String.valueOf(mNotifCount));
	    return super.onCreateOptionsMenu(menu);
	}
	
	public void setNotifCount(int count){
	    mNotifCount = count;
//	    invalidateOptionsMenu();
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    ((OxApp) OxApp.getAppContext()).activityResumed();
	}

	@Override
	protected void onPause() {
		super.onPause();
	    ((OxApp) OxApp.getAppContext()).activityPaused();
	}
}

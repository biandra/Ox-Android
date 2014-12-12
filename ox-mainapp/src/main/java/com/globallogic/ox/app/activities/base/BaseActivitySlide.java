package com.globallogic.ox.app.activities.base;

import com.actionbarsherlock.view.Window;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import android.os.Bundle;

public class BaseActivitySlide extends RoboSherlockFragmentActivity{

	protected int mTitleRes;
	protected int mLayout;
	
	public BaseActivitySlide(int titleRes, int layoutId) {
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
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setIcon(android.R.color.transparent);
	}

}

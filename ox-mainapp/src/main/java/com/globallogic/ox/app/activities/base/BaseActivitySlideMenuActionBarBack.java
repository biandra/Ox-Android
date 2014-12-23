package com.globallogic.ox.app.activities.base;

import android.os.Bundle;

public class BaseActivitySlideMenuActionBarBack extends BaseActivitySlide{

	public BaseActivitySlideMenuActionBarBack(int titleRes, int layoutId) {
		super(titleRes, layoutId);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		createSlideMenu();
//		getSlideMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	}
	
	@Override
	protected void actionHomeButton() {
		finish();
	}

}

package com.globallogic.ox.app.activities.base;

import android.os.Bundle;

public class BaseActivitySlideMenuActionBarMenu extends BaseActivitySlide{
	
	public BaseActivitySlideMenuActionBarMenu(int titleRes, int layoutId) {
		super(titleRes, layoutId);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createSlideMenu();
	}	

}

package com.globallogic.ox.app.viewlistener;

import android.app.Activity;

public interface SlideMenuListener {

	public Activity getActivity();

	public void closeSideMenu();

	public void startRequest();

	public void finishRequestSuccess(String name);

	public void finishRequestError();
}

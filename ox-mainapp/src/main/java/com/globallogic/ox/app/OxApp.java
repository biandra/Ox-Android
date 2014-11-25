package com.globallogic.ox.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class OxApp extends Application{

	private static OxApp instance;
	private Activity currentActivity;
	private boolean activityVisible;

	public OxApp() {
		super();
		instance = this;
	}

	public static Context getAppContext() {
		return instance.getApplicationContext();
	}

	public Activity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(Activity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public static OxApp getInstance() {
		return instance;
	}

	public static void setInstance(OxApp instance) {
		OxApp.instance = instance;
	}
	
	public void activityResumed() {
		activityVisible = true;
		//ToastUtils.cancelToast();
	}

	public void activityPaused() {
		activityVisible = false;
	}

	public boolean isActivityVisible() {
		return activityVisible;
	}
	
	public int getAppVersion() throws NameNotFoundException
	{
		PackageInfo packageInfo = getAppContext().getPackageManager().getPackageInfo(getAppContext().getPackageName(), 0);
		return packageInfo.versionCode;
	}
	
}

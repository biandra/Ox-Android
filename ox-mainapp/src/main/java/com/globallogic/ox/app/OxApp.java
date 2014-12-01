package com.globallogic.ox.app;

import roboguice.RoboGuice;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class OxApp extends Application{

	private static OxApp instance;
//	private Activity currentActivity;
	private boolean activityVisible;

	public OxApp() {
//		super();
		instance = this;
	}

	public static Context getAppContext() {
		return instance.getApplicationContext();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		RoboGuice.injectMembers(this, this);
	}

//	public Activity getCurrentActivity() {
//		return currentActivity;
//	}
//
//	public void setCurrentActivity(Activity currentActivity) {
//		this.currentActivity = currentActivity;
//	}

	public void activityResumed() {
		activityVisible = true;
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

package com.globallogic.ox.app;

import android.app.Application;
import android.content.Context;

public class OxApp extends Application{

	private static OxApp instance;

	public OxApp() {
		instance = this;
	}

	public static Context getAppContext() {
		return instance.getApplicationContext();
	}

	
}

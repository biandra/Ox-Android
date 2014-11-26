package com.globallogic.ox.app.utils;

import com.globallogic.ox.app.OxApp;

import roboguice.RoboGuice;
import android.content.Context;

public class RoboguiceUtils {

	private static Context ctx = OxApp.getAppContext();
	
	public static void inject(Object injectable) {
		RoboGuice.getInjector(ctx).injectMembers(injectable);
	}
	
}
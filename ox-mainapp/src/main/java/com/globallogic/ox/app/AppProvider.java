package com.globallogic.ox.app;

import roboguice.inject.RoboApplicationProvider;
import android.app.Application;

public class AppProvider extends RoboApplicationProvider<OxApp> {

	private Application app;
	
	public AppProvider(Application app) {
		this.app = app;
	}
	
	@Override
	public OxApp get() {
		return (OxApp)app;
	}
}

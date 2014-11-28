package com.globallogic.ox.app.modules;

import android.content.Context;

import com.globallogic.ox.app.AppProvider;
import com.globallogic.ox.app.OxApp;
import com.globallogic.ox.app.services.ServicesImpl;
import com.globallogic.ox.app.services.ServicesInterface;
import com.globallogic.ox.app.services.VolleyWSConnection;
import com.globallogic.ox.app.services.WSConnection;
import com.globallogic.ox.parsing.IJSONParser;
import com.globallogic.ox.parsing.JacksonParser;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;

public class ApplicationModule extends AbstractModule {

	private final OxApp application;

	@Inject
	public ApplicationModule(final Context context) {
		super();
		this.application = (OxApp) context;
	}

	@Override
	protected void configure() {
		bind(AppProvider.class).toInstance(new AppProvider(application));
		bind(OxApp.class).toProvider(AppProvider.class);
		bind(IJSONParser.class).to(JacksonParser.class);
		bind(WSConnection.class).to(VolleyWSConnection.class);
		bind(ServicesInterface.class).to(ServicesImpl.class);
	}

}

package com.globallogic.ox.app.viewmodel;

import com.globallogic.ox.app.utils.RoboguiceUtils;
import com.globallogic.ox.app.viewlistener.SlideMenuListener;

//TODO: model para obtener informacion del usuario
public class SlideMenuModel {

	private SlideMenuListener view;
	
	public SlideMenuModel(SlideMenuListener view) {
		super();
		RoboguiceUtils.inject(this);
		this.view = view;
	}
}

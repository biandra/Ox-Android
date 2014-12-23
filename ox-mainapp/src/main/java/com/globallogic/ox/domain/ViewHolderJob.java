package com.globallogic.ox.domain;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class ViewHolderJob extends View{
	
	public ViewHolderJob(Context context) {
		super(context);
	}

	private ViewFlipper flipper;
	private Button buttonShow;

	public ViewFlipper getFlipper() {
		return flipper;
	}

	public void setFlipper(ViewFlipper flipper) {
		this.flipper = flipper;
	}

	public Button getButtonShow() {
		return buttonShow;
	}

	public void setButtonShow(Button buttonShow) {
		this.buttonShow = buttonShow;
	}
}

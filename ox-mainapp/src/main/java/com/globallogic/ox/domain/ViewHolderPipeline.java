package com.globallogic.ox.domain;

import android.widget.Button;
import android.widget.ViewFlipper;

public class ViewHolderPipeline {
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

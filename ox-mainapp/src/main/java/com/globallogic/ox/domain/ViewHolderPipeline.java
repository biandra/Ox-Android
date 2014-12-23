package com.globallogic.ox.domain;

import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ViewHolderPipeline {
	private ViewFlipper flipper;
	private Button buttonShow;
	private TextView name;
	private TextView number;

	public TextView getNumber() {
		return number;
	}

	public void setNumber(TextView number) {
		this.number = number;
	}

	public TextView getName() {
		return name;
	}

	public void setName(TextView name) {
		this.name = name;
	}

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

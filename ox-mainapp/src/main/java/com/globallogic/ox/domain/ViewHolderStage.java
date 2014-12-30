package com.globallogic.ox.domain;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class ViewHolderStage extends View{
	
	public ViewHolderStage(Context context) {
		super(context);
	}

	private ViewFlipper flipper;
	private Button buttonRun;

	public ViewFlipper getFlipper() {
		return flipper;
	}

	public void setFlipper(ViewFlipper flipper) {
		this.flipper = flipper;
	}

	public Button getButtonRun() {
		return buttonRun;
	}

	public void setButtonRun(Button buttonRun) {
		this.buttonRun = buttonRun;
	}
}

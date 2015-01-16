package com.globallogic.ox.domain;

import android.graphics.drawable.GradientDrawable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.globallogic.ox.app.viewlistener.ViewHolderPipelineListener;
import com.globallogic.ox.app.viewmodel.ViewHolderPipelineModel;

public class ViewHolderPipeline implements ViewHolderPipelineListener{
	
	private ViewFlipper flipper;
	private Button buttonShow;
	private Button buttonRun;
	private TextView name;
	private TextView number;
	private GradientDrawable shape;
	
	private ViewHolderPipelineModel model;

	public ViewHolderPipeline(){
		this.setModel(new ViewHolderPipelineModel(this));
	}
	
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

	public Button getButtonRun() {
		return buttonRun;
	}

	public void setButtonRun(Button buttonRun) {
		this.buttonRun = buttonRun;
	}

	public ViewHolderPipelineModel getModel() {
		return model;
	}
	
	public void setModel(ViewHolderPipelineModel model) {
		this.model = model;
	}
	
	public GradientDrawable getShape() {
		return shape;
	}
	
	public void setShape(GradientDrawable shape) {
		
		this.shape = shape;
	}

	@Override
	public void onPostViewHolderPipelineStarted() {
		name.setText("COMIENZA");
	}

	@Override
	public void onPostViewHolderPipelineError() {
		name.setText("ERROR 1");
	}

	@Override
	public void onServerError(ServerErrorInfo errorInfo) {
		name.setText("ERROR 2");
	}

	@Override
	public void onPostViewHolderPipelineFinished() {
		name.setText("FINALIZADO");
	}
}

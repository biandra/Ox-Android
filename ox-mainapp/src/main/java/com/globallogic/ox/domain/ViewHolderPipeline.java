package com.globallogic.ox.domain;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.globallogic.ox.app.activities.DashboardActivity;
import com.globallogic.ox.app.viewlistener.ViewHolderPipelineListener;
import com.globallogic.ox.app.viewmodel.ViewHolderPipelineModel;

public class ViewHolderPipeline implements ViewHolderPipelineListener{
	
	private ViewFlipper flipper;
	private Button buttonShow;
	private Button buttonRun;
	private TextView name;
	private TextView number;
	private GradientDrawable shape;
	
	private DashboardActivity activity;
	
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(DashboardActivity activity) {
		this.activity = activity;
	}

	boolean blink = false;
	Thread thread =new Thread(){
	     @Override
	     public void run(){
		      try
		      {
			       while(true)
			       {
				       Thread.sleep(500);
				       activity.runOnUiThread(new Runnable() {
					        @Override
					        public void run() {
				                if (blink) {
					            	blink = false;
					            	shape.setColor(Color.parseColor("#3a936f"));
					            	//TODO: es la forma q encontre para refrescar el cambio
					            	name.setText(name.getText());
				                }
					            else{
					            	blink = true;
					            	shape.setColor(Color.parseColor("#999999"));
					            	name.setText(name.getText());
					            }
				           }
				       });
			       }
		      }catch (InterruptedException e) {
		    	  // TODO: handle exception
		      }
	     }
	};
	
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
	}

	@Override
	public void onPostViewHolderPipelineError() {
	}

	@Override
	public void onServerError(ServerErrorInfo errorInfo) {
	}

	@Override
	public void onPostViewHolderPipelineFinished() {
		thread.start();
	}
	
}

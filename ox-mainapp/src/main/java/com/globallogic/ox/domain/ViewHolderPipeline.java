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
	
	private static final int BLINK_TIME = 500;
	private ViewFlipper flipper;
	private Button buttonShow;
	private Button buttonRun;
	private TextView name;
	private TextView number;
	private GradientDrawable shape;
	
	private DashboardActivity activity;
	private boolean blink = false;
    private int averageTime;
    private float progress;
	
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(DashboardActivity activity) {
		this.activity = activity;
	}

	Thread thread =new Thread(){
	     @Override
	     public void run(){
		      try
		      {
			       while(isInProgress())
			       {
				       Thread.sleep(BLINK_TIME);
				       activity.runOnUiThread(new Runnable() {
					        @Override
					        public void run() {
				                if (blink) {
					            	blink = false;
					            	shape.setAlpha(100);
					            	//TODO: es la forma q encontre para refrescar el cambio
					            	name.setText(name.getText());
				                }
					            else{
					            	blink = true;
					            	name.setText(name.getText());
					            	shape.setAlpha(255);
					            }
				           }
				       });
			       }
			       shape.setAlpha(255);
		      }catch (InterruptedException e) {
		    	  // TODO: handle exception
		      }
	     }

	};

	private boolean isInProgress() {
		boolean inProgress = false;
		int periodsNumber = (int)(averageTime/BLINK_TIME);
		float progressRemaining = 100 - progress;
		if ((periodsNumber != 0) && (float)(progressRemaining / (float)((float)100 / periodsNumber)) > 0){
			progress = progress + (float)((float)100 / periodsNumber);
			inProgress = true;
		}
		return inProgress;
	}
	
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
		this.setProgress(0);
		thread.start();
	}

	public int getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(int averageTime) {
		this.averageTime = averageTime;
	}

	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}
	
}

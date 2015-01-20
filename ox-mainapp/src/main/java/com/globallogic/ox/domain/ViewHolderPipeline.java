package com.globallogic.ox.domain;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.globallogic.ox.app.activities.DashboardActivity;
import com.globallogic.ox.app.viewlistener.ViewHolderPipelineListener;
import com.globallogic.ox.app.viewmodel.ViewHolderPipelineModel;

public class ViewHolderPipeline implements ViewHolderPipelineListener{
	
	private static final String BUILDING = "building";
	private static final String SUCCESS = "success";
	
	private static final int BLINK_TIME = 500;
	private ViewFlipper flipper;
	private Button buttonShow;
	private Button buttonRun;
	private TextView name;
	private TextView number;
	private GradientDrawable shape;
	
	private DashboardActivity activity;
	private ViewHolderPipelineModel model;
	private boolean blink = false;
	private Thread thread;

	private Project project;
    private float runProgress;
	
    public Project getProject(){
    	return project;
    }
    
    public void setProject(Project project){
    	this.project = project;
    }
    
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(DashboardActivity activity) {
		this.activity = activity;
	}

	private void createThread(){
		thread =new Thread(){
		     @Override
		     public void run(){
			      try
			      {
				       while(isInProgress()){
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
				       activity.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								shape.setAlpha(255);
								ViewHolderPipeline.this.buttonRun.setVisibility(View.VISIBLE);
								model.getPipeline(project.getId());
							}
				       });
			      }catch (InterruptedException e) {
			    	  // TODO: handle exception
			      }
		     }
	
		};
	}

	private boolean isInProgress() {
		boolean inProgress = false;
		int periodsNumber = (int)(project.getStatics().getTime()/BLINK_TIME);
		float progressRemaining = 100 - runProgress;
		if ((periodsNumber != 0) && (float)(progressRemaining / (float)((float)100 / periodsNumber)) > 0){
			runProgress = runProgress + (float)((float)100 / periodsNumber);
			inProgress = true;
		}
		return inProgress;
	}
	
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
		this.runProgress = 0;
		buttonRun.setVisibility(View.GONE);
		
		if(thread != null ) {
			thread.interrupt();
	    }
		createThread();
		thread.start();
	}

	@Override
	public void onGetViewHolderPipelineFinished(Project project) {
        if ((project.getStatus() == null) || (SUCCESS.compareTo(project.getStatus()) == 0) || (BUILDING.compareTo(project.getStatus()) == 0)) {
       	 	shape.setColor(Color.parseColor("#3a936f"));
		} else {
			shape.setColor(Color.parseColor("#7f2626"));
		}
	}
	
}

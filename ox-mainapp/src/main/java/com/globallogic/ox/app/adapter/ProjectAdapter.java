package com.globallogic.ox.app.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.globallogic.ox.R;
import com.globallogic.ox.app.activities.DashboardActivity;
import com.globallogic.ox.app.activities.PipelineActivity;
import com.globallogic.ox.app.component.animationFlip.AnimationFactory;
import com.globallogic.ox.app.component.animationFlip.AnimationFactory.FlipDirection;
import com.globallogic.ox.domain.Project;
import com.globallogic.ox.domain.ViewHolderPipeline;


public class ProjectAdapter extends BaseAdapter{

	private static final String SUCCESS = "success";
	private Context context;
    private List<Project> items;
    private DashboardActivity activity;
	
    public ProjectAdapter(Context context, DashboardActivity activity, List<Project> items) {
    	this.activity = activity;
        this.context = context;
        this.items = items;
    }
    
	@Override
	public int getCount() {
		return this.items.size();
	}

	@Override
	public Object getItem(int position) {
		return this.items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		View rowView = convertView;
//		 
//        if (convertView == null) {
//            // Create a new view into the list.
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            rowView = inflater.inflate(R.layout.list_item, parent, false);
//        }
// 
//        // Set data into the view.
//        ImageView ivItem = (ImageView) rowView.findViewById(R.id.ivItem);
//        TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
// 
//        Item item = this.items.get(position);
//        tvTitle.setText(item.getTitle());
//        ivItem.setImageResource(item.getImage());
// 
//        return rowView;
//	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

	    final ViewFlipper flipperTemp;
	    final Project item = (Project) getItem(position);
	    final ViewHolderPipeline viewHolder;
	    
		if (convertView == null) {
	    	// Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.pipeline, parent, false);
            
	        viewHolder = new ViewHolderPipeline();
	        viewHolder.setActivity(activity);
	        viewHolder.setFlipper((ViewFlipper) convertView.findViewById(R.id.viewFlipper_pipeline));
	        viewHolder.getFlipper().setDisplayedChild(0);
	        viewHolder.setButtonShow((Button) convertView.findViewById(R.id.button_pipeline_show));
	        viewHolder.setButtonRun((Button) convertView.findViewById(R.id.button_pipeline_run));
	        viewHolder.setName((TextView) convertView.findViewById(R.id.textView_pipeline_name));
	        viewHolder.getName().setText(item.getName());
	        viewHolder.setNumber((TextView) convertView.findViewById(R.id.textView_pipeline_number));
	        viewHolder.getNumber().setText(item.getStatics().getNumber());
	        
	        LayerDrawable bgDrawable = (LayerDrawable)convertView.findViewById(R.id.viewFlipper_pipeline_front).getBackground();
	        GradientDrawable shape = (GradientDrawable) bgDrawable.findDrawableByLayerId(R.id.shape_id);
	        viewHolder.setShape(shape);
	        
	        if ((item.getStatus() == null) || (SUCCESS.compareTo(item.getStatus()) == 0)) {
	        	 shape.setColor(Color.parseColor("#3a936f"));
			} else {
				shape.setColor(Color.parseColor("#7f2626"));
			}
	        
	        convertView.setTag(viewHolder);
	    } else {
	        viewHolder = (ViewHolderPipeline) convertView.getTag();
	        viewHolder.getFlipper().setDisplayedChild(0);
	    }


	    flipperTemp = (ViewFlipper) convertView.findViewById(R.id.viewFlipper_pipeline);
	    flipperTemp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
					AnimationFactory.flipTransition(flipperTemp, FlipDirection.LEFT_RIGHT);
			}
		});
	    
	    viewHolder.getButtonShow().setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
				Intent intent = new Intent(context, PipelineActivity.class);
				intent.putExtra("projectId", item.getId());
				intent.putExtra("projectName", item.getName());
				context.startActivity(intent);
	        }
	    });
	    
	    viewHolder.getButtonRun().setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	        	flipperTemp.showNext();
	        	viewHolder.getModel().postRun(item.getId());
	        }
	    });
	    
	    //TODO: cambiar este hardcodeo
	    convertView.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;

	    return convertView;
	}

}

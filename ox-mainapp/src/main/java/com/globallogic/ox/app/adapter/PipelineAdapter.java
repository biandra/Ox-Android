package com.globallogic.ox.app.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.globallogic.ox.R;
import com.globallogic.ox.app.activities.PipelineActivity;
import com.globallogic.ox.app.component.animationFlip.AnimationFactory;
import com.globallogic.ox.app.component.animationFlip.AnimationFactory.FlipDirection;
import com.globallogic.ox.domain.Project;
import com.globallogic.ox.domain.ViewHolder;


public class PipelineAdapter extends BaseAdapter{

	private Context context;
    private List<Project> items;
	
    public PipelineAdapter(Context context, List<Project> items) {
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

	    ViewHolder viewHolder;
		if (convertView == null) {
	    	// Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.pipeline, parent, false);

	        viewHolder = new ViewHolder();
	        viewHolder.setFlipper((ViewFlipper) convertView.findViewById(R.id.viewFlipper_pipeline));
	        viewHolder.getFlipper().setDisplayedChild(0);
	        viewHolder.setButtonShow((Button) convertView.findViewById(R.id.button_pipeline_show));
	        
	        convertView.setTag(viewHolder);
	    } else {
	        viewHolder = (ViewHolder) convertView.getTag();
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
//	            flipperTemp.showNext();
	        	Project item = (Project) getItem(position);
				Intent intent = new Intent(context, PipelineActivity.class);
				intent.putExtra("idMotel", item.getId());
				context.startActivity(intent);
	        }
	    });
	    
	    //TODO: cambiar este hardcodeo
	    convertView.getLayoutParams().height = 100;

	    return convertView;
	}

}

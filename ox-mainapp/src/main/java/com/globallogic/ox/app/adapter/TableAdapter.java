package com.globallogic.ox.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
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
import com.globallogic.ox.app.activities.PipelineActivity;
import com.globallogic.ox.app.component.animationFlip.AnimationFactory;
import com.globallogic.ox.app.component.animationFlip.AnimationFactory.FlipDirection;
import com.globallogic.ox.domain.Stage;
import com.globallogic.ox.domain.Table;
import com.globallogic.ox.domain.TableRow;
import com.globallogic.ox.domain.ViewHolderStage;

public class TableAdapter extends BaseAdapter {
	
        private Context context;
    
        private Table table;
        
        private static final String SUCCESS = "success";
        
        public TableAdapter(Context context, Table table) {
                this.context = context;
                this.table = table;
        }
        @Override
        public int getCount() {
                return table.getColumn().size();
        }
        @Override
        public long getItemId(int position) {
                return position;
        }
        public TableRow getItem(int position) {
                return table.getColumn().get(position);
        }
        public View getView(int position, View convertView, ViewGroup parent) {
                TableRow tableRow = table.getColumn().get(position);
                
                LinearLayout layoutTableRowView = new LinearLayout(context);
                layoutTableRowView.setOrientation(LinearLayout.HORIZONTAL);
                
                for (int i = 0; i < tableRow.getCell().size(); i++) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View layoutCellView = inflater.inflate(R.layout.stage, parent, false);
                	
                    //TODO: cambiar este hardcodeo
                	LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                	layoutParams.weight=1;
                	
                	if (tableRow.getCell().get(i).getStage() != null){
                		
                		Stage stage = tableRow.getCell().get(i).getStage();
                		
                		ViewHolderStage viewHolder = new ViewHolderStage(context);
                		viewHolder.setFlipper((ViewFlipper) layoutCellView.findViewById(R.id.viewFlipper_stage));
                		viewHolder.getFlipper().setDisplayedChild(0);
                		viewHolder.setButtonRun((Button) layoutCellView.findViewById(R.id.button_stage_run));
            	        viewHolder.setName((TextView) layoutCellView.findViewById(R.id.textView_stage_name));
            	        viewHolder.getName().setText(stage.getType());
            	        viewHolder.setNumber((TextView) layoutCellView.findViewById(R.id.textView_stage_number));
            	        viewHolder.getNumber().setText("0");
                		((ViewGroup) layoutCellView).addView(viewHolder);
                		layoutTableRowView.setGravity(Gravity.CENTER);
                		layoutTableRowView.addView(layoutCellView, layoutParams);
                		
            	        LayerDrawable bgDrawable = (LayerDrawable)layoutCellView.findViewById(R.id.viewFlipper_stage_front).getBackground();
            	        GradientDrawable shape = (GradientDrawable) bgDrawable.findDrawableByLayerId(R.id.shape_id);
            	        
            	        if ((stage.getStatus() == null) || (SUCCESS.compareTo(stage.getStatus()) == 0)) {
            	        	 shape.setColor(Color.parseColor("#3a936f"));
            			} else {
            				shape.setColor(Color.parseColor("#7f2626"));
            			}
                		
                		final ViewFlipper flipperTemp = (ViewFlipper) layoutCellView.findViewById(R.id.viewFlipper_stage);
                		flipperTemp.setOnClickListener(new OnClickListener() {
                			
                			@Override
                			public void onClick(View v) {
                				AnimationFactory.flipTransition(flipperTemp, FlipDirection.LEFT_RIGHT);
                			}
                		});
                		
                	    viewHolder.getButtonRun().setOnClickListener(new OnClickListener() {
                	        public void onClick(View v) {
                	        	//Run Stage
                	        }
                	    });
                	}
                	else{
                		//TODO: cambiar esto!!
                		layoutCellView.setVisibility(View.INVISIBLE);
                		layoutTableRowView.addView(layoutCellView, layoutParams);
                	}	
                	
                }
                
    	        return layoutTableRowView;
        }

}
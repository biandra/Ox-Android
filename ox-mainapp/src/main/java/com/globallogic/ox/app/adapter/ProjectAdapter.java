package com.globallogic.ox.app.adapter;

import java.util.List;

import com.globallogic.ox.R;
import com.globallogic.ox.app.component.animationFlip.AnimationFactory;
import com.globallogic.ox.app.component.animationFlip.AnimationFactory.FlipDirection;
import com.globallogic.ox.domain.Project;
import com.globallogic.ox.domain.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ViewFlipper;


public class ProjectAdapter extends BaseAdapter{

	private Context context;
    private List<Project> items;
	
    public ProjectAdapter(Context context, List<Project> items) {
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
	public View getView(int position, View convertView, ViewGroup parent) {

	    final ViewFlipper flipperTemp;

	    ViewHolder viewHolder;
		if (convertView == null) {
	    	// Create a new view into the list.
//	        convertView = mLayoutInflater.inflate(mLayout, null);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_project, parent, false);

	        viewHolder = new ViewHolder();
	        viewHolder.setFlipper((ViewFlipper) convertView.findViewById(R.id.facturacion_fragment_reference_number_container));
	        viewHolder.getFlipper().setDisplayedChild(0);
//	        viewHolder.v2FieldName = (TextView) convertView.findViewById(R.id.tvLongName);
//	        viewHolder.button01 = (ImageButton) convertView.findViewById(R.id.imageButton1);    
	        convertView.setTag(viewHolder);

	    } else {
	        viewHolder = (ViewHolder) convertView.getTag();
	        viewHolder.getFlipper().setDisplayedChild(0);
	    }


	    flipperTemp = (ViewFlipper) convertView.findViewById(R.id.facturacion_fragment_reference_number_container);
	    flipperTemp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
					AnimationFactory.flipTransition(flipperTemp, FlipDirection.LEFT_RIGHT);
			}
		});
	    
//	    viewHolder.v2FieldName.setText(items.get(position).get("name"));        
//	    viewHolder.button01.setOnClickListener(new OnClickListener() {
//	        public void onClick(View v) {
//	            flipperTemp.showNext();
//	        }
//	    });
	    
	    //TODO: cambiar este hardcodeo
	    convertView.getLayoutParams().height = 100;

	    return convertView;
	}

}

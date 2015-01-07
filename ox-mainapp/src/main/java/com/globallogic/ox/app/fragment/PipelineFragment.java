package com.globallogic.ox.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.globallogic.ox.R;
import com.globallogic.ox.app.adapter.TableAdapter;
import com.globallogic.ox.domain.Table;

public class PipelineFragment extends Fragment {

	private int commonScrolly = 0;
	private Table table;
	
    public PipelineFragment(Table table){
    	this.table = table;
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		LinearLayout layout = new LinearLayout(getActivity());
		layout.addView(inflater.inflate(R.layout.table_pipeline, container, false) ); 
		
		final ListView lv = (ListView) layout.findViewById(R.id.list_view);
        
        TableAdapter tableAdapter = new TableAdapter(getActivity(), table);
        lv.setAdapter(tableAdapter); 
//        lv.setOnScrollListener(new OnScrollListener() {
//			
//			@Override
//			public void onScrollStateChanged(AbsListView view, int scrollState) {
//			}
//			
//			@Override
//			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
////				Toast.makeText(getActivity(),totalItemCount, Toast.LENGTH_SHORT).show();
//				View c = lv.getChildAt(0);
//				if (c != null) {
//					commonScrolly = -c.getTop() + lv.getFirstVisiblePosition() * c.getHeight();
//					Log.i("a", "scrolling stopped in position"+ commonScrolly);
//				}
//			}
//		});
        lv.setSelectionFromTop(commonScrolly, 0);
		
	    return layout;
	}

}

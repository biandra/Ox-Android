package com.globallogic.ox.app.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.globallogic.ox.R;
import com.globallogic.ox.app.adapter.TableAdapter;
import com.globallogic.ox.domain.Stage;
import com.globallogic.ox.domain.TableCell;
import com.globallogic.ox.domain.TableRow;

public class PipelineFragment extends Fragment {
//    private static final String KEY_CONTENT = "TestFragment:Content";

	private int commonScrolly = 0;
	
    public static PipelineFragment newInstance(String content) {
        PipelineFragment fragment = new PipelineFragment();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(content).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        fragment.mContent = builder.toString();

        return fragment;
    }

    private String mContent = "???";

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
//            mContent = savedInstanceState.getString(KEY_CONTENT);
//        }
//    }

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		LinearLayout layout = new LinearLayout(getActivity());
		layout.addView(inflater.inflate(R.layout.table_pipeline, container, false) ); 
		
		final ListView lv = (ListView) layout.findViewById(R.id.list_view);
        
        ArrayList<TableRow> table = new ArrayList<TableRow>();
        
        TableCell[] cells = new TableCell[2];
        for (int i = 0; i < cells.length; i++) {
        	Stage stage = new Stage();
        	stage.setId(i);
        	cells[i] = new TableCell(stage, 100, 100);
        }
        
        for (int i = 0; i < 5; i++)
        	table.add(new TableRow(cells));
        
        TableAdapter tableAdapter = new TableAdapter(getActivity(), table);
        lv.setAdapter(tableAdapter); 
        lv.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//				Toast.makeText(getActivity(),totalItemCount, Toast.LENGTH_SHORT).show();
				View c = lv.getChildAt(0);
				if (c != null) {
					commonScrolly = -c.getTop() + lv.getFirstVisiblePosition() * c.getHeight();
					Log.i("a", "scrolling stopped in position"+ commonScrolly);
				}
			}
		});
        lv.setSelectionFromTop(commonScrolly, 0);
		
	    return layout;
	}

    //TODO: recuperar el estado de la vista
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString(KEY_CONTENT, mContent);
//    }
}

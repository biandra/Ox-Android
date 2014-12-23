package com.globallogic.ox.app.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.globallogic.ox.R;
import com.globallogic.ox.app.adapter.TableAdapter;
import com.globallogic.ox.domain.Job;
import com.globallogic.ox.domain.TableCell;
import com.globallogic.ox.domain.TableRow;

public class PipelineFragment extends Fragment {
//    private static final String KEY_CONTENT = "TestFragment:Content";

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
    		
			ListView lv = (ListView) layout.findViewById(R.id.list_view);
            
            ArrayList<TableRow> table = new ArrayList<TableRow>();
            
            TableCell[] cells = new TableCell[2];
            for (int i = 0; i < cells.length; i++) {
            	Job job = new Job();
            	job.setId(i);
            	cells[i] = new TableCell(job, 100, 100);
            }
            
            for (int i = 0; i < 5; i++)
            	table.add(new TableRow(cells));
            
            TableAdapter tableAdapter = new TableAdapter(getActivity(), table);
            lv.setAdapter(tableAdapter); 
    		
    	    return layout;
    	}

    //TODO: recuperar el estado de la vista
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString(KEY_CONTENT, mContent);
//    }
}

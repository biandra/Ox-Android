package com.globallogic.ox.app.adapter;

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.globallogic.ox.app.fragment.PipelineFragment;
import com.globallogic.ox.domain.Table;

public class PipelineFragmentAdapter extends FragmentPagerAdapter{

    private List<Table> tables;

    public PipelineFragmentAdapter(FragmentManager fm, List<Table> tables) {
        super(fm);
        this.tables = tables;
    }

	@Override
    public Fragment getItem(int position) {
		return new PipelineFragment(this.tables.get(position));
    }

    @Override
    public int getCount() {
        return tables.size();
    }

    //TODO
//	public void fragmentBecameVisible() {
//		Log.i("a", "simula onResume");
//	}


}

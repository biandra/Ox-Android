package com.globallogic.ox.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.globallogic.ox.app.fragment.PipelineFragment;

public class PipelineFragmentAdapter extends FragmentPagerAdapter{

    protected static final String[] CONTENT = new String[] { "This", "Is", "A", "Test", };

    private int mCount = CONTENT.length;

    public PipelineFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PipelineFragment.newInstance(CONTENT[position % CONTENT.length]);
    }

    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }
    
    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return PipelineFragmentAdapter.CONTENT[position % CONTENT.length];
    }

    //TODO
	public void fragmentBecameVisible() {
		Log.i("a", "simula onResume");
	}


}

package com.globallogic.ox.app.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.globallogic.ox.R;
import com.globallogic.ox.app.activities.base.BaseActivitySlideMenuActionBarBack;
import com.globallogic.ox.app.adapter.PipelineFragmentAdapter;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class PipelineActivity extends BaseActivitySlideMenuActionBarBack{

	PipelineFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;

    public PipelineActivity() {
    	super(R.string.app_dashboard, R.layout.pipeline_activity);
    }
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mAdapter = new PipelineFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int arg0) {
				mAdapter.fragmentBecameVisible();
			}
        	
        });
	}

}

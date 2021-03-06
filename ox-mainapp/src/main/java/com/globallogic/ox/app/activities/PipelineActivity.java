package com.globallogic.ox.app.activities;

import java.util.List;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.globallogic.ox.R;
import com.globallogic.ox.app.activities.base.BaseActivitySlideMenuActionBarBack;
import com.globallogic.ox.app.adapter.PipelineFragmentAdapter;
import com.globallogic.ox.app.converter.TablesConverter;
import com.globallogic.ox.app.viewlistener.PipelineActivityListener;
import com.globallogic.ox.app.viewmodel.PipelineActivityModel;
import com.globallogic.ox.domain.ServerErrorInfo;
import com.globallogic.ox.domain.Stage;
import com.globallogic.ox.domain.Table;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.viewpagerindicator.CirclePageIndicator;

public class PipelineActivity extends BaseActivitySlideMenuActionBarBack implements PipelineActivityListener{

    private PipelineFragmentAdapter mAdapter;
    private PipelineActivityModel model;
    private int projectId;
    private TablesConverter tablesConverter;
    
    @InjectView(R.id.view_Pipeline_Pages)
    private View pipelinesPages;
    
	@InjectView(R.id.view_Pipeline_Error)
	private View errorView;
    
    @InjectView(R.id.pipeline_request_loading)
    private RelativeLayout progressDialog;
    
    @InjectView(R.id.pipeline_scrollview)
    private PullToRefreshScrollView pullToRefreshScroll;
    
    @InjectView(R.id.pipeline_pull_to_refresh_hint)
    private View pullToRefreshHintView;
    
    @InjectView(R.id.pipeline_container_pull_to_refresh)
    private LinearLayout containerPullToRefresh;
    
    @InjectView(R.id.pager)
    private ViewPager mPager;
    
    @InjectView(R.id.indicator)
    private CirclePageIndicator mIndicator;
    
    public PipelineActivity() {
        super(R.string.app_dashboard, R.layout.pipeline_activity);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle bundle = getIntent().getExtras();
        projectId = bundle.getInt("projectId");
        setTitle(bundle.getString("projectName"));
        
        //TODO:listener cuando cambio de viewPage. Ver como arreglarlo
//        mIndicator.setOnPageChangeListener(new OnPageChangeListener(){
//
//          @Override
//          public void onPageScrollStateChanged(int arg0) {
//          }
//
//          @Override
//          public void onPageScrolled(int arg0, float arg1, int arg2) {
//          }
//
//          @Override
//          public void onPageSelected(int arg0) {
//              mAdapter.fragmentBecameVisible();
//          }
//          
//        });
        
        model = new PipelineActivityModel(this);
        model.getStages(projectId);
        this.tablesConverter = new TablesConverter();
        
        pullToRefreshScroll.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> arg0) {
                model.getStages(projectId);
            }
        });
        
        pullToRefreshScroll.setOnPullEventListener(new OnPullEventListener<ScrollView>() {
            @Override
            public void onPullEvent(PullToRefreshBase<ScrollView> arg0, State state, Mode mode) {
                if (state.equals(State.PULL_TO_REFRESH)) {
                    pullToRefreshHintView.setVisibility(View.GONE);
                } else if (state.equals(State.RESET)) {
                    pullToRefreshHintView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onGetPipelineStarted() {
        setViewLoading();
    }

    @Override
    public void onGetPipelineFinished(List<Stage> stages) {
        setViewProjets(stages);
    }

    @Override
    public void onGetPipelineError() {
        setViewError();
//    	setViewProjets(new ArrayList<Stage>());
    }

    @Override
    public void onServerError(ServerErrorInfo errorInfo) {
        setViewError();
//    	setViewProjets(new ArrayList<Stage>());
    }

    private void setViewError() {
        pullToRefreshScroll.onRefreshComplete();
        pullToRefreshScroll.setMode(Mode.PULL_FROM_START);
        pipelinesPages.setVisibility(View.GONE);
        progressDialog.setVisibility(View.GONE);
        pullToRefreshHintView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.VISIBLE);
        containerPullToRefresh.setVisibility(View.VISIBLE);
    }

    private void setViewLoading() {
        pullToRefreshScroll.setMode(Mode.DISABLED);
        pipelinesPages.setVisibility(View.GONE);
        progressDialog.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }
    
    private void setViewProjets(List<Stage> stages) {
        pullToRefreshScroll.onRefreshComplete();
        pullToRefreshScroll.setMode(Mode.DISABLED);
        pipelinesPages.setVisibility(View.VISIBLE);
        progressDialog.setVisibility(View.GONE);
        pullToRefreshHintView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        containerPullToRefresh.setVisibility(View.VISIBLE);
        List<Table> tables = tablesConverter.convert(stages);
        if (tables.size() < 2) {
        	mIndicator.setVisibility(View.GONE);
		}
        else {
        	mIndicator.setVisibility(View.VISIBLE);
        }
        mAdapter = new PipelineFragmentAdapter(getSupportFragmentManager(), tables);
        mPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mPager);
    }
    
}

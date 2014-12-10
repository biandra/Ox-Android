package com.globallogic.ox.app.activities.base;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.globallogic.ox.R;
import com.globallogic.ox.app.OxApp;
import com.globallogic.ox.app.fragment.SlideMenuMainFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


public class BaseActivity extends RoboSherlockFragmentActivity{
	
	private int mTitleRes;
	private int mLayout;
	private SlidingMenu slideMenu;
	private Fragment mFrag;
//	private static Button notifCount;
//	private static int mNotifCount = 0; 
	
	public BaseActivity(int titleRes, int layoutId) {
		mTitleRes = titleRes;
		mLayout = layoutId;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		getSupportActionBar().hide();
		setTitle(mTitleRes);
		setContentView(mLayout);
		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setDisplayUseLogoEnabled(false);
		getSupportActionBar().setIcon(android.R.color.transparent);
		
		createSlideMenu();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			actionHomeButton();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);

		//TODO: notificaciones dentro de la apk
//	    View count = menu.findItem(R.id.badge).getActionView();
//	    notifCount = (Button) count.findViewById(R.id.notif_count);
//	    notifCount.setText(String.valueOf(mNotifCount));
//	    return super.onCreateOptionsMenu(menu);
		return true;
	}

	public void createSlideMenu() {
		// customize the SlidingMenu
		slideMenu = new SlidingMenu(this);
		slideMenu.setShadowWidthRes(R.dimen.shadow_width);
		slideMenu.setShadowDrawable(R.drawable.shadow);
		slideMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slideMenu.setFadeDegree(0.35f);
		slideMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		slideMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slideMenu.setMenu(R.layout.frame_parent_menu);

		FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
		mFrag = new SlideMenuMainFragment();
		t.replace(R.id.menu_frame, mFrag);
		t.commit();
	}
	
//	@Override
//	protected void onStart() {
//		super.onStart();
//		OxApp.getInstance().setCurrentActivity(this);
//	}
	
	
//	public void setNotifCount(int count){
//	    mNotifCount = count;
////	    invalidateOptionsMenu();
//	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    ((OxApp) OxApp.getAppContext()).activityResumed();
	}

	@Override
	protected void onPause() {
		super.onPause();
	    ((OxApp) OxApp.getAppContext()).activityPaused();
	}

	public void closeSideMenu() {
		slideMenu.toggle();
	}

	private void actionHomeButton() {
		slideMenu.toggle();
	}
}

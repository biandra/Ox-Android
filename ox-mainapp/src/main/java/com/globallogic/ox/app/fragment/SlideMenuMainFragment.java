package com.globallogic.ox.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.globallogic.ox.app.activities.base.BaseActivitySlideMenu;
import com.globallogic.ox.app.component.progressbarwheel.ProgressWheel;
import com.globallogic.ox.app.viewlistener.SlideMenuListener;
import com.globallogic.ox.app.viewmodel.SlideMenuModel;
import com.globallogic.ox.R;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

//TODO: Recuperar informacion del usuario
public class SlideMenuMainFragment extends RoboFragment implements SlideMenuListener {

	private SlideMenuModel model;
	
	@InjectView(R.id.menu_slide_progress_user)
	private ProgressWheel progressBar;
	@InjectView(R.id.menu_slide_logout)
	private LinearLayout logout;
	@InjectView(R.id.menu_slide_name_user)
	private TextView userText;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return (FrameLayout) inflater.inflate(R.layout.slidemenu_main_menu, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				model.logout();
			}
		});
		
		model = new SlideMenuModel(this);
//		model.requestUser();
	}
	
	@Override
	public void onDetach() {
//		model.cancelRequests();
		super.onDetach();
	}
	
	@Override
	public void closeSideMenu() {
		BaseActivitySlideMenu act = (BaseActivitySlideMenu) getActivity();
		act.closeSideMenu();
	}

	@Override
	public void startRequest() {
		progressBar.setVisibility(View.VISIBLE);
		userText.setVisibility(View.GONE);
	}

	@Override
	public void finishRequestSuccess(String name) {
		if (isAdded()) {
			progressBar.setVisibility(View.GONE);
			userText.setVisibility(View.VISIBLE);
			userText.setText(name);
		}
	}

	@Override
	public void finishRequestError() {
		if (isAdded()) {
			progressBar.setVisibility(View.GONE);
			userText.setVisibility(View.VISIBLE);
			userText.setText(getResources().getString(R.string.menu_slide_my_profile_item));
		}
	}

}

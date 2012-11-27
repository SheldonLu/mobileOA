package com.mzone.oa.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mzone.oa.ui.R;
// 欢迎界面
public class WelcomeFragment extends Fragment {
	
	private Context context;
	
	public WelcomeFragment() { 
	}
	
	public WelcomeFragment(Context context) {
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.welcome_fragment, null);
		
		return view;
	}
}

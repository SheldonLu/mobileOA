package com.mzone.oa.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mzone.oa.ui.R;

public class SearchFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			final ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.search, null);
		return view;
	}

}

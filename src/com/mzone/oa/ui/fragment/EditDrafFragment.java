package com.mzone.oa.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mzone.oa.ui.R;

public class EditDrafFragment extends Fragment {
	
	Spinner mSpinner = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.editdraf_fragment, null);
		mSpinner = (Spinner) view.findViewById(R.id.spinner_level);  
		// 建立数据源  
		String[] mItems = getResources().getStringArray(R.array.levelname);  
		// 建立Adapter并且绑定数据源  
		ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_spinner_item, mItems);  
		//绑定 Adapter到控件  
		_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner.setAdapter(_Adapter);  
		return view;
	}
	
}

package com.mzone.oa.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mzone.oa.ui.R;
import com.mzone.oa.ui.TodoDocumentDetailActivity;

// 待办公文
public class TodoDocumentFragment extends Fragment implements
		OnItemClickListener {
	private Context context;

	public TodoDocumentFragment() {
	}

	public TodoDocumentFragment(Context context) {
		this.context = context;
	}

	private ListView mListView;
	private SimpleAdapter mAdapter;
	private String[] mFrom;
	private int[] mTo;
	private List<Map<String, String>> mData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tododocument, null);
		mListView = (ListView) view.findViewById(R.id.listview);
		mTo = new int[] { R.id.title_txt, R.id.datetime_txt, R.id.content_txt };
		mFrom = new String[] { "title_txt", "datetime_txt", "content_txt" };
		mData = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();

		map.put(mFrom[0], "关于申报意见");
		map.put(mFrom[1], "2012-10-21 10:12:44");
		map.put(mFrom[2], "同意");
		mData.add(map);
		map = new HashMap<String, String>();
		map.put(mFrom[0], "关于申报意见");
		map.put(mFrom[1], "2012-10-21 10:12:44");
		map.put(mFrom[2], "同意");
		mData.add(map);
		map = new HashMap<String, String>();
		map.put(mFrom[0], "关于申报意见");
		map.put(mFrom[1], "2012-10-21 10:12:44");
		map.put(mFrom[2], "同意同意同意同意同意同意同意同意同意同意同意同意同意同意同意同意同意同意同意");
		mData.add(map);

		mAdapter = new SimpleAdapter(context, mData,
				R.layout.tododocument_item, mFrom, mTo);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long arg3) {
		if(position<mData.size()){
			Intent intent = new Intent(context,TodoDocumentDetailActivity.class);
			startActivity(intent);
			getActivity().overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right); 
			
		}
	}
	
	

}

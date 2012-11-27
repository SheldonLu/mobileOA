package com.mzone.oa.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ContactActivity extends Activity {
	private ListView mListView;
	private SimpleAdapter mAdapter;
	private String[] mFrom;
	private int[] mTo;
	private List<Map<String, String>> mData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_list);
		
		
		mListView = (ListView) findViewById(R.id.listview);
		mTo = new int[] { R.id.username_txt, R.id.selected_ck };
		mFrom = new String[] { "username_txt", "selected_ck"};
		mData = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
	}

}

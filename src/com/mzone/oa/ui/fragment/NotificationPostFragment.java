package com.mzone.oa.ui.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ListView;

import com.mzone.oa.adapter.NotificationPostAdapter;
import com.mzone.oa.adapter.TabAdapter;
import com.mzone.oa.bean.NotificationPostBean;
import com.mzone.oa.ui.R;
// 通知公告 分类+查询
public class NotificationPostFragment extends Fragment {
	private static final String[] TAB_NAMES = {
		"公告1",
		"公告2",
		"公告3",
		"公告4",
		"公告5",
	};
	
	private Gallery gallery;
	private TabAdapter textAdapter;
	private Context context;
	private ListView listview;
	
	public NotificationPostFragment() { 
	}
	
	public NotificationPostFragment(Context context) {
		this.context = context;
//		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.notification_post_fragment, null);
		listview = (ListView)view.findViewById(R.id.listview);
		gallery = (Gallery) view.findViewById(R.id.gallery);
		textAdapter = new TabAdapter(context, Arrays.asList(TAB_NAMES));
		gallery.setAdapter(textAdapter);
		gallery.setSelection(34);
		
		NotificationPostAdapter pbAdapter = new NotificationPostAdapter(getActivity(), R.layout.list_item_notification, getList());
		listview.setAdapter(pbAdapter);
		
		gallery.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TabAdapter adapter = (TabAdapter)parent.getAdapter();
				adapter.setSelectedTab(position);
				switch(position %TAB_NAMES.length ){
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				}
				
			}});
		
		return view;
	}
	
	private List<NotificationPostBean> getList(){
		List<NotificationPostBean> list = new ArrayList<NotificationPostBean>();
		NotificationPostBean pb1 = new NotificationPostBean();
		pb1.title = "公告1";
		pb1.desc = "desc1";
		list.add(pb1);
		
		pb1 = new NotificationPostBean();
		pb1.title = "公告2";
		pb1.desc = "desc2";
		list.add(pb1);
		
		pb1 = new NotificationPostBean();
		pb1.title = "公告3";
		pb1.desc = "desc3";
		list.add(pb1);
		
		pb1 = new NotificationPostBean();
		pb1.title = "公告4";
		pb1.desc = "desc4";
		list.add(pb1);
		
		pb1 = new NotificationPostBean();
		pb1.title = "公告5";
		pb1.desc = "desc5";
		list.add(pb1);
		
		return list;
	}
}

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

import com.mzone.oa.adapter.AddressBookAdapter;
import com.mzone.oa.adapter.TabAdapter;
import com.mzone.oa.bean.AddressBookBean;
import com.mzone.oa.ui.R;
// 全市通讯录 分类+查询
public class AddressBookFragment extends Fragment {
	private static final String[] TAB_NAMES = {
		"通讯录1",
		"通讯录2",
		"通讯录3",
		"通讯录4",
		"通讯录5",
	};
	
	private Gallery gallery;
	private TabAdapter textAdapter;
	private Context context;
	private ListView listview;
	
	public AddressBookFragment() { 
	}
	
	public AddressBookFragment(Context context) {
		this.context = context;
//		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.addressbook_fragment, null);
		listview = (ListView)view.findViewById(R.id.listview);
		gallery = (Gallery) view.findViewById(R.id.gallery);
		textAdapter = new TabAdapter(context, Arrays.asList(TAB_NAMES));
		gallery.setAdapter(textAdapter);
		gallery.setSelection(34);
		
		AddressBookAdapter abAdapter = new AddressBookAdapter(getActivity(), R.layout.list_item_addressbook, getList());
		listview.setAdapter(abAdapter);
		
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
	
	private List<AddressBookBean> getList(){
		List<AddressBookBean> list = new ArrayList<AddressBookBean>();
		AddressBookBean pb1 = new AddressBookBean();
		pb1.title = "小花";
		pb1.desc = "13958025411";
		list.add(pb1);
		
		pb1 = new AddressBookBean();
		pb1.title = "小黑";
		pb1.desc = "13958025412";
		list.add(pb1);
		
		pb1 = new AddressBookBean();
		pb1.title = "花花";
		pb1.desc = "13958025413";
		list.add(pb1);
		
		pb1 = new AddressBookBean();
		pb1.title = "小陈";
		pb1.desc = "13958025414";
		list.add(pb1);
		
		pb1 = new AddressBookBean();
		pb1.title = "小刘";
		pb1.desc = "13958025415";
		list.add(pb1);
		
		return list;
	}
}

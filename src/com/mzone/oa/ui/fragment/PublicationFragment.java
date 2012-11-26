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

import com.mzone.oa.adapter.PublicationAdapter;
import com.mzone.oa.adapter.TabAdapter;
import com.mzone.oa.bean.PublicationBean;
import com.mzone.oa.ui.R;
// 信息刊物 分类+查询
public class PublicationFragment extends Fragment {
	
	private static final String[] TAB_NAMES = {
		"刊物1",
		"刊物2",
		"刊物3",
		"刊物4",
		"刊物5",
	};
	
	private Gallery gallery;
	private TabAdapter textAdapter;
	private Context context;
	private ListView listview;
	
	public PublicationFragment() { 
	}
	
	public PublicationFragment(Context context) {
		this.context = context;
//		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.publication_fragment, null);
		listview = (ListView)view.findViewById(R.id.listview);
		gallery = (Gallery) view.findViewById(R.id.gallery);
		textAdapter = new TabAdapter(context, Arrays.asList(TAB_NAMES));
		gallery.setAdapter(textAdapter);
		gallery.setSelection(34);
		
		PublicationAdapter pbAdapter = new PublicationAdapter(getActivity(), R.layout.list_item_publication, getList());
		listview.setAdapter(pbAdapter);
		gallery.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TabAdapter adapter = (TabAdapter)parent.getAdapter();
				adapter.setSelectedTab(position);
				// TODO
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
	
	private List<PublicationBean> getList(){
		List<PublicationBean> list = new ArrayList<PublicationBean>();
		PublicationBean pb1 = new PublicationBean();
		pb1.title = "title1";
		pb1.desc = "desc1";
		list.add(pb1);
		
		pb1 = new PublicationBean();
		pb1.title = "title2";
		pb1.desc = "desc2";
		list.add(pb1);
		
		pb1 = new PublicationBean();
		pb1.title = "title3";
		pb1.desc = "desc3";
		list.add(pb1);
		
		pb1 = new PublicationBean();
		pb1.title = "title4";
		pb1.desc = "desc4";
		list.add(pb1);
		
		pb1 = new PublicationBean();
		pb1.title = "title5";
		pb1.desc = "desc5";
		list.add(pb1);
		
		return list;
	}
}

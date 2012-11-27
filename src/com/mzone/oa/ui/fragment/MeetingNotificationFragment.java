package com.mzone.oa.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import com.mzone.oa.adapter.MeetingNotificationAdapter;
import com.mzone.oa.adapter.PublicationAdapter;
import com.mzone.oa.bean.MeetingNotificationBean;
import com.mzone.oa.bean.PublicationBean;
import com.mzone.oa.ui.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
// 会议通知
public class MeetingNotificationFragment extends Fragment {

	private ListView listview;
	
	public MeetingNotificationFragment(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.meetingnotification_fragment, null);
		listview = (ListView)view.findViewById(R.id.listview);
		
		MeetingNotificationAdapter pbAdapter = new MeetingNotificationAdapter(getActivity(), R.layout.list_item_meetingnotification, getList());
		listview.setAdapter(pbAdapter);
		
		return view;
	}
	
	private List<MeetingNotificationBean> getList(){
		List<MeetingNotificationBean> list = new ArrayList<MeetingNotificationBean>();
		MeetingNotificationBean pb1 = new MeetingNotificationBean();
		pb1.title = "会议1";
		pb1.time = "2012-12-01";
		pb1.address = "富阳地方1";
		list.add(pb1);
		
		pb1 = new MeetingNotificationBean();
		pb1.title = "会议2";
		pb1.time = "2012-12-02";
		pb1.address = "富阳地方2";
		list.add(pb1);
		
		pb1 = new MeetingNotificationBean();
		pb1.title = "会议3";
		pb1.time = "2012-12-03";
		pb1.address = "富阳地方3";
		list.add(pb1);
		
		pb1 = new MeetingNotificationBean();
		pb1.title = "会议4";
		pb1.time = "2012-12-04";
		pb1.address = "富阳地方4";
		list.add(pb1);
		
		pb1 = new MeetingNotificationBean();
		pb1.title = "会议5";
		pb1.time = "2012-12-05";
		pb1.address = "富阳地方5";
		list.add(pb1);
		
		return list;
	}
}

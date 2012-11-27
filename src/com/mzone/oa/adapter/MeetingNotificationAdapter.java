package com.mzone.oa.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mzone.oa.bean.MeetingNotificationBean;
import com.mzone.oa.ui.R;

public class MeetingNotificationAdapter extends ArrayAdapter<MeetingNotificationBean> {

	private List<MeetingNotificationBean> list;
	
	public MeetingNotificationAdapter(Context context, int textViewResourceId,
			List<MeetingNotificationBean> list) {
		super(context, textViewResourceId, list);
		this.list = list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String inflater = Context.LAYOUT_INFLATER_SERVICE; 
		LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
		
		if (convertView == null) {
			convertView = vi.inflate(R.layout.list_item_meetingnotification, parent,
					false);
		}
		
		TextView title = (TextView)convertView.findViewById(R.id.title);
		TextView time = (TextView)convertView.findViewById(R.id.time);
		TextView address = (TextView)convertView.findViewById(R.id.address);
		
		
		MeetingNotificationBean pb = list.get(position);
		title.setText(pb.title);
		time.setText(pb.time);
		address.setText(pb.address);
		
		return convertView;
	}

}

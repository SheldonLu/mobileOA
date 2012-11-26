package com.mzone.oa.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mzone.oa.bean.NotificationPostBean;
import com.mzone.oa.ui.R;

public class NotificationPostAdapter extends ArrayAdapter<NotificationPostBean> {

	private List<NotificationPostBean> list;
	
	public NotificationPostAdapter(Context context, int textViewResourceId,
			List<NotificationPostBean> list) {
		super(context, textViewResourceId, list);
		this.list = list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String inflater = Context.LAYOUT_INFLATER_SERVICE; 
		LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
		
		if (convertView == null) {
			convertView = vi.inflate(R.layout.list_item_notification, parent,
					false);
		}
		
		TextView title = (TextView)convertView.findViewById(R.id.title);
		TextView desc = (TextView)convertView.findViewById(R.id.desc);
		
		NotificationPostBean ab = list.get(position);
		title.setText(ab.title);
		desc.setText(ab.desc);
		
		return convertView;
	}

}

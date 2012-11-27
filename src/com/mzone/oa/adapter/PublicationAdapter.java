package com.mzone.oa.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mzone.oa.bean.PublicationBean;
import com.mzone.oa.ui.R;

public class PublicationAdapter extends ArrayAdapter<PublicationBean> {

	private List<PublicationBean> list;
	
	public PublicationAdapter(Context context, int textViewResourceId,
			List<PublicationBean> list) {
		super(context, textViewResourceId, list);
		this.list = list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String inflater = Context.LAYOUT_INFLATER_SERVICE; 
		LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
		
		if (convertView == null) {
			convertView = vi.inflate(R.layout.list_item_publication, parent,
					false);
		}
		
		TextView title = (TextView)convertView.findViewById(R.id.title);
		TextView desc = (TextView)convertView.findViewById(R.id.desc);
		
		PublicationBean pb = list.get(position);
		title.setText(pb.title);
		desc.setText(pb.desc);
		
		return convertView;
	}

}

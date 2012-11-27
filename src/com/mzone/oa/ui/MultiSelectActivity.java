package com.mzone.oa.ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author zuolongsnail@163.com MultiSelectActivity
 */
public class MultiSelectActivity extends Activity implements
		OnItemClickListener {
	
	public static String CALLBACK="";
	
	private static final String TAG = "MultiSelectActivity";
	private ListView contactsDelList;

	private Cursor cursor;
	private ContactsDeleteAdapter contactsDeleteAdapter;

	private static final String[] PROJECTION = new String[] {
			ContactsContract.Contacts._ID,
			ContactsContract.Contacts.DISPLAY_NAME };
	private static final int CONTACTS_ID_INDEX = 0;
	private static final int DISPLAY_NAME_INDEX = 1;
	private ContactsDeleteListItemViews holderViews;

	private final class ContactsDeleteListItemViews {
		TextView nameView;
		CheckBox delCheckBox;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_list);
		CALLBACK="";
		contactsDelList = (ListView) findViewById(R.id.listview);

		contactsDelList.setOnItemClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		refreshData();
	}

	// 查询数据库
	private void refreshData() {
		Uri uri = Uri.parse("content://com.android.contacts/contacts");// 查找手机所有联系人

		cursor = getContentResolver().query(uri, PROJECTION, null, null, null);
		contactsDeleteAdapter = new ContactsDeleteAdapter(this, cursor);
		contactsDelList.setAdapter(contactsDeleteAdapter);
	}

	class ContactsDeleteAdapter extends BaseAdapter {
		Cursor cur;
		Map<Integer, Boolean> selectedMap;
		HashSet<String> delContactsIdSet;

		public ContactsDeleteAdapter(Context context, Cursor c) {
			cur = c;
			// 保存每条记录是否被选中的状态
			selectedMap = new HashMap<Integer, Boolean>();
			// 保存被选中记录作数据库表中的Id
			delContactsIdSet = new HashSet<String>();

			for (int i = 0; i < cur.getCount(); i++) {
				selectedMap.put(i, false);
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(MultiSelectActivity.this)
						.inflate(R.layout.contact_list_item, null);
				holderViews = new ContactsDeleteListItemViews();
				holderViews.nameView = (TextView) convertView
						.findViewById(R.id.username_txt);
				holderViews.delCheckBox = (CheckBox) convertView
						.findViewById(R.id.selected_ck);
				convertView.setTag(holderViews);
			}
			cur.moveToPosition(position);
			ContactsDeleteListItemViews views = (ContactsDeleteListItemViews) convertView
					.getTag();
			final String name = cur.getString(DISPLAY_NAME_INDEX);
			views.nameView.setText(name);
			views.delCheckBox.setChecked(selectedMap.get(position));
			// 保存记录Id
			if (selectedMap.get(position)) {
				delContactsIdSet.add(String.valueOf(cur
						.getString(DISPLAY_NAME_INDEX)));
			} else {
				delContactsIdSet.remove(String.valueOf(cur
						.getString(DISPLAY_NAME_INDEX)));
			}
			return convertView;
		}

		@Override
		public int getCount() {
			return cur.getCount();
		}

		@Override
		public Object getItem(int position) {
			if (cur.moveToPosition(position)) {
				return cur;
			} else {
				return null;
			}
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id) {
		Log.i(TAG, "onItemClick");
		ContactsDeleteListItemViews views = (ContactsDeleteListItemViews) view
				.getTag();
		views.delCheckBox.toggle();
		contactsDeleteAdapter.selectedMap.put(position,
				views.delCheckBox.isChecked());
		contactsDeleteAdapter.notifyDataSetChanged();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
//		Iterator<String> it=contactsDeleteAdapter.delContactsIdSet.iterator();
//		String str="";
//		while(it.hasNext()){
//			str+=it.next()+",";
//		}
//		Toast.makeText(this,"", Toast.LENGTH_SHORT)
//		.show();
//		
		Bundle bundle = new Bundle();
        bundle.putString("name", contactsDeleteAdapter.delContactsIdSet.toString());
        Intent intent = new Intent();
        intent.putExtras(bundle);
        // 返回intent
        setResult(RESULT_OK, intent);
        CALLBACK= contactsDeleteAdapter.delContactsIdSet.toString();
        finish();
	}
	
	
}
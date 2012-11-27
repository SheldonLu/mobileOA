package com.mzone.oa.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

/**

 */
public class MultiSelectActivity extends Activity implements
		OnItemClickListener {
	private List<String> mPerson = new ArrayList<String>();

	public static final int CONTACT_RESULT_CODE = 22;
	public static String CALLBACK = "";

	private static final String TAG = "MultiSelectActivity";
	private ListView contactsDelList;

	private ContactsDeleteAdapter contactsDeleteAdapter;

	private ContactsDeleteListItemViews holderViews;

	private final class ContactsDeleteListItemViews {
		TextView nameView;
		CheckBox delCheckBox;
	}

	private ImageButton mBack;
	private ImageButton mOk;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.contact_list);
		CALLBACK = "";
		mPerson.add("王进喜");
		mPerson.add("李成龙");
		mPerson.add("王建国");
		mPerson.add("赵斌");
		mPerson.add("王学斌");
		mPerson.add("马应元");

		
		mBack=(ImageButton)findViewById(R.id.btn_back);
		mOk=(ImageButton)findViewById(R.id.btn_ok);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				contactsDeleteAdapter.delContactsIdSet.clear();
				onBackPressed();
			}
		});
		mOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
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
		// Uri uri = Uri.parse("content://com.android.contacts/contacts");//
		// 查找手机所有联系人

		// cursor = getContentResolver().query(uri, PROJECTION, null, null,
		// null);
		contactsDeleteAdapter = new ContactsDeleteAdapter(this);
		contactsDelList.setAdapter(contactsDeleteAdapter);
	}

	class ContactsDeleteAdapter extends BaseAdapter {
		Map<Integer, Boolean> selectedMap;
		HashSet<String> delContactsIdSet;

		public ContactsDeleteAdapter(Context context) {

			// 保存每条记录是否被选中的状态
			selectedMap = new HashMap<Integer, Boolean>();
			// 保存被选中记录作数据库表中的Id
			delContactsIdSet = new HashSet<String>();

			for (int i = 0; i < mPerson.size(); i++) {
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

			ContactsDeleteListItemViews views = (ContactsDeleteListItemViews) convertView
					.getTag();
			final String name = mPerson.get(position);
			views.nameView.setText(name);
			views.delCheckBox.setChecked(selectedMap.get(position));
			// 保存记录Id
			if (selectedMap.get(position)) {
				delContactsIdSet.add(name);
			} else {
				delContactsIdSet.remove(name);
			}
			return convertView;
		}

		@Override
		public int getCount() {
			return mPerson.size();
		}

		@Override
		public Object getItem(int position) {
			return mPerson.get(position);
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
		// Iterator<String>
		// it=contactsDeleteAdapter.delContactsIdSet.iterator();
		// String str="";
		// while(it.hasNext()){
		// str+=it.next()+",";
		// }
		// Toast.makeText(this,"", Toast.LENGTH_SHORT)
		// .show();
		//
		Bundle bundle = new Bundle();
		bundle.putString("name",
				contactsDeleteAdapter.delContactsIdSet.toString());
		Intent intent = new Intent();
		intent.putExtras(bundle);
		// 返回intent
		setResult(RESULT_OK, intent);
		CALLBACK = contactsDeleteAdapter.delContactsIdSet.toString();
		finish();
	}

}

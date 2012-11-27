package com.mzone.oa.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.TimeUtils;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mzone.oa.bean.TodoDocumentBean;
import com.mzone.oa.ui.MultiSelectActivity;
import com.mzone.oa.ui.R;
import com.mzone.oa.util.Util;

public class EditDrafFragment extends Fragment {

	private EditText mTitleEdt;
	private EditText mContentEdt;
	private EditText mPersonEdt;
	private Button mPublicBtn;
	private String mFilePath;
	private Spinner mSpinner = null;
	private Button mAttachmentBtn = null;
	private String[] mItems;
	public static final int FILE_RESULT_CODE = 2;

	private CallBack mCallBack = null;

	public EditDrafFragment(CallBack mCallBack) {
		super();
		this.mCallBack = mCallBack;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			final ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.editdraf_fragment, null);
		mSpinner = (Spinner) view.findViewById(R.id.spinner_level);
		mAttachmentBtn = (Button) view.findViewById(R.id.btn_attachment);
		mTitleEdt = (EditText) view.findViewById(R.id.txt_title);
		mContentEdt = (EditText) view.findViewById(R.id.txt_content);
		mPersonEdt = (EditText) view.findViewById(R.id.txt_person);
		mPublicBtn = (Button)view.findViewById(R.id.btn_publish);
		// 建立数据源
		mItems = getResources().getStringArray(R.array.levelname);
		// 建立Adapter并且绑定数据源
		ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(
				container.getContext(), android.R.layout.simple_spinner_item,
				mItems);
		// 绑定 Adapter到控件
		_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner.setAdapter(_Adapter);
		mAttachmentBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallBack.openFile();
			}
		});

		mPersonEdt.setFocusable(false);
		mPersonEdt.setFocusableInTouchMode(false);
		mPersonEdt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(getActivity(),
						MultiSelectActivity.class),
						MultiSelectActivity.CONTACT_RESULT_CODE);
			}
		});
		mPublicBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String title = mTitleEdt.getText().toString();
				String content = mTitleEdt.getText().toString();
				String level = mItems[mSpinner.getSelectedItemPosition()];
				String person = mPersonEdt.getText().toString();
				String attachment = mAttachmentBtn.getText().toString();
				if(TextUtils.isEmpty(title)) {
					Toast.makeText(container.getContext(), "标题不能为空！", Toast.LENGTH_SHORT).show();
					return;
				}
				if(TextUtils.isEmpty(content)) {
					Toast.makeText(container.getContext(), "内容不能为空！", Toast.LENGTH_SHORT).show();
					return;
				}
				if(TextUtils.isEmpty(person)) {
					Toast.makeText(container.getContext(), "经办人不能为空！", Toast.LENGTH_SHORT).show();
					return;
				}
				TodoDocumentBean todo = new TodoDocumentBean();
				todo.title = title;
				todo.dengji = level;
				todo.desc = content;
				todo.jinbanren = person;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				todo.time = sdf.format(Calendar.getInstance().getTime());
				if(!TextUtils.isEmpty(attachment) && !attachment.equals("选择文件")) {
					todo.fileName = attachment;
					todo.filePath = mFilePath;
				}
				Util.docBeans.add(todo);
			}
		});
		return view;
	}

	public interface CallBack {
		void openFile();
	}
	
	public void setAttachmentText(String text, String filepath){
		mFilePath = filepath;
		mAttachmentBtn.setText(text);
	}

	public void setPersonText(String str) {
		mPersonEdt.setText(str);
	}
}

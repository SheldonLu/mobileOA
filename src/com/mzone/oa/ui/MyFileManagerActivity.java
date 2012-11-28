package com.mzone.oa.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyFileManagerActivity extends ListActivity {

	private List<String> mItems = null;
	private List<String> mPaths = null;
	private String mRootPath = "/";
	// private String mCurPath = "/";
	private TextView mPath;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.fileselect);
		findViewById(R.id.btn_back).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		mPath = (TextView) findViewById(R.id.mPath);
		mRootPath = getSDPath();
		if (mRootPath == null) {
			Toast.makeText(this, "请插入SD卡!", Toast.LENGTH_SHORT).show();
			finish();
		} else {
			getFileDir(mRootPath);
		}
	}

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
	}

	public static  void openFile(Context ctx, File f) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		String type = getMIMEType(f);
		intent.setDataAndType(Uri.fromFile(f), type);
		ctx.startActivity(intent);
	}

	private static String getMIMEType(File f) {
		String type = "";
		String fName = f.getName();
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();
		if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
				|| end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
			type = "audio";
		} else if (end.equals("3gp") || end.equals("mp4")) {
			type = "video";
		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			type = "image";
		} else {
			type = "*";
		}
		type += "/*";
		
		if(end.equals("doc")){
			type="application/msword";
		}else if(end.equals("docx")){
			type="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		}
		return type;
	}

	private void getFileDir(String filePath) {
		mPath.setText(filePath);
		mItems = new ArrayList<String>();
		mPaths = new ArrayList<String>();
		File f = new File(filePath);
		File[] files = f.listFiles();
		if (!filePath.equals(mRootPath)) {
			mItems.add("b1");
			mPaths.add(mRootPath);
			mItems.add("b2");
			mPaths.add(f.getParent());
		}
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			mItems.add(file.getName());
			mPaths.add(file.getPath());
		}
		setListAdapter(new MyAdapter(this, mItems, mPaths));
	}

	private String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取根目录
		}
		if (sdDir == null) {
			// Toast.makeText(context,
			// "No SDCard inside!",Toast.LENGTH_SHORT).show();
			return "/";
		}
		return sdDir.toString();

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		File file = new File(mPaths.get(position));
		if (file.isDirectory()) {
			// mCurPath = mPaths.get(position);
			getFileDir(mPaths.get(position));
		} else {
			// 可以打开文件
			Bundle bd = new Bundle();
			bd.putString("filePath", file.getAbsolutePath());
			bd.putString("fileName", file.getName());
			Intent it = new Intent();
			it.putExtras(bd);
			setResult(RESULT_OK, it);
			finish();
		}
	}
}
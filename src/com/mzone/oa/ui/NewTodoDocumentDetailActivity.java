package com.mzone.oa.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class NewTodoDocumentDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.newtododoc_detail);
		Button attan = (Button) findViewById(R.id.btn_attachment);

		attan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean sdCardExist = Environment.getExternalStorageState()
						.equals(android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
				if (sdCardExist) {
					File dir = Environment.getExternalStorageDirectory();
					String path = dir.getAbsolutePath();
					File file = new File(path + "/富阳市人民政府关于富阳市农村村民建房管理的若干意见.docx");
					try {
						if (!file.exists()) {
							file.createNewFile();
							inputstreamtofile(
									getResources().openRawResource(R.raw.test),
									file);
						}
						MyFileManagerActivity.openFile(NewTodoDocumentDetailActivity.this, file);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		
		((Button)findViewById(R.id.txt_fb)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				finish();
			}
		});
		
	}

	public void inputstreamtofile(InputStream ins, File file) throws Exception {
		OutputStream os = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		ins.close();
	}
}

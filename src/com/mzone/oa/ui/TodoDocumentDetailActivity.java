package com.mzone.oa.ui;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mzone.oa.bean.TodoDocumentBean;
import com.mzone.oa.util.Util;

public class TodoDocumentDetailActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tododoc_detail);
		findViewById(R.id.btn_back).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		final TodoDocumentBean tb = Util.clickBean;
		
		((TextView)findViewById(R.id.txt_title)).setText(tb.title);
		((TextView)findViewById(R.id.txt_content)).setText(tb.desc);
		((TextView)findViewById(R.id.spinner_level)).setText(tb.dengji);
		((TextView)findViewById(R.id.txt_jinbanren)).setText(tb.jinbanren);
		
		final EditText et = (EditText)findViewById(R.id.txt_yj);
		
		
		((Button)findViewById(R.id.txt_fb)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String yj = et.getText().toString();
				Util.clickBean.suggess = yj;
				
				finish();
				
			}
		});
		
		Button attachment = (Button)findViewById(R.id.btn_attachment);
		
		if(tb.filePath == null || tb.filePath.length() == 0){
			attachment.setText("无附件");
		}else{
			attachment.setText(tb.fileName);
			attachment.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					MyFileManagerActivity.openFile(TodoDocumentDetailActivity.this, new File(tb.filePath));
				}
			});
			
		}
		
		
		
	}
	
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right); 
	}
}

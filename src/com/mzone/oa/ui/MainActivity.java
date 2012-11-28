package com.mzone.oa.ui;

import java.util.ArrayList;

import net.simonvt.widget.MenuDrawer;
import net.simonvt.widget.MenuDrawerManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mzone.oa.bean.TodoDocumentBean;
import com.mzone.oa.ui.fragment.AddressBookFragment;
import com.mzone.oa.ui.fragment.EditDrafFragment;
import com.mzone.oa.ui.fragment.EmailFragment;
import com.mzone.oa.ui.fragment.MeetingNotificationFragment;
import com.mzone.oa.ui.fragment.NotificationPostFragment;
import com.mzone.oa.ui.fragment.PublicationFragment;
import com.mzone.oa.ui.fragment.SearchFragment;
import com.mzone.oa.ui.fragment.ToReadDocumentFragment;
import com.mzone.oa.ui.fragment.TodoDocumentFragment;

public class MainActivity extends FragmentActivity implements
		View.OnClickListener, EditDrafFragment.CallBack {
	
	public static ArrayList<TodoDocumentBean> docBeans;

	public static TodoDocumentBean clickBean;

	static{
		docBeans = new ArrayList<TodoDocumentBean>();
		TodoDocumentBean tb = new TodoDocumentBean();
		tb.title = "政协换届以来工作总结";
		tb.time = "12-10-21 10:12:44";
		tb.suggess = "已阅";
		tb.dengji = "等级1";
		tb.desc = "今年以来，按照县委县政府的总体部署和县政协常委会工作要点安排，县政协认真履行工作";
		tb.jinbanren = "陈国华";
		
		docBeans.add(tb);
		
		tb = new TodoDocumentBean();
		tb.title = "在党校学习贯彻十八大精神会议上的讲话";
		tb.time = "12-10-22 11:12:44";
		tb.suggess = "通过";
		tb.dengji = "等级2";
		tb.desc = "镇原县委党校学习贯彻党的十八大精神专题研讨会召开，6位教学骨干围绕精心准备的讲稿，先后发言";
		tb.jinbanren = "李建国";
		
		docBeans.add(tb);
	}

	private static final String STATE_MENUDRAWER = "com.mzone.oa.ui.MainActivity.menuDrawer";
	private static final String STATE_ACTIVE_VIEW_ID = "com.mzone.oa.ui.MainActivity.activeViewId";

	private MenuDrawerManager mMenuDrawer;
	private TextView mContentTextView;

	private int mActiveViewId;

	private int screenWidth;
	private RelativeLayout searchLayout;

	@Override
	public void onCreate(Bundle inState) {
		super.onCreate(inState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		// 窗口的宽度
		screenWidth = dm.widthPixels;

		if (inState != null) {
			mActiveViewId = inState.getInt(STATE_ACTIVE_VIEW_ID);
		}

		mMenuDrawer = new MenuDrawerManager(this, MenuDrawer.MENU_DRAG_WINDOW);
		mMenuDrawer.setContentView(R.layout.main);
		mMenuDrawer.setMenuView(R.layout.menu_scrollview);

		MenuScrollView msv = (MenuScrollView) mMenuDrawer.getMenuView();
		msv.setOnScrollChangedListener(new MenuScrollView.OnScrollChangedListener() {
			@Override
			public void onScrollChanged() {
				mMenuDrawer.getMenuDrawer().invalidate();
			}
		});

		searchLayout = (RelativeLayout) findViewById(R.id.searchLayout);

		mContentTextView = (TextView) findViewById(R.id.contentText);

		findViewById(R.id.item1).setOnClickListener(this);
		findViewById(R.id.item2).setOnClickListener(this);
		findViewById(R.id.item3).setOnClickListener(this);
		findViewById(R.id.item4).setOnClickListener(this);
		findViewById(R.id.item5).setOnClickListener(this);
		findViewById(R.id.item6).setOnClickListener(this);
		findViewById(R.id.item7).setOnClickListener(this);
		findViewById(R.id.item8).setOnClickListener(this);
		findViewById(R.id.item9).setOnClickListener(this);
		ImageButton imageButton = (ImageButton) findViewById(R.id.btn_menu);
		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int drawerState = mMenuDrawer.getDrawerState();
				if (drawerState != MenuDrawer.STATE_OPEN
						&& drawerState != MenuDrawer.STATE_OPENING) {
					mMenuDrawer.openMenu();
					return;
				}

			}
		});

		TextView activeView = (TextView) findViewById(mActiveViewId);
		if (activeView != null) {
			mMenuDrawer.setActiveView(activeView);
			mContentTextView.setText(activeView.getText());
			// TODO 替换Fragment
		}

		// This will animate the drawer open and closed until the user manually
		// drags it. Usually this would only be
		// called on first launch.
		mMenuDrawer.getMenuDrawer().peekDrawer();
		mMenuDrawer.getMenuDrawer().setDropShadowEnabled(false);
		findViewById(R.id.item1).performClick();
	}

	@Override
	protected void onRestoreInstanceState(Bundle inState) {
		super.onRestoreInstanceState(inState);
		mMenuDrawer.onRestoreDrawerState(inState
				.getParcelable(STATE_MENUDRAWER));
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable(STATE_MENUDRAWER,
				mMenuDrawer.onSaveDrawerState());
		outState.putInt(STATE_ACTIVE_VIEW_ID, mActiveViewId);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mMenuDrawer.toggleMenu();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		final int drawerState = mMenuDrawer.getDrawerState();
		if (drawerState == MenuDrawer.STATE_OPEN
				|| drawerState == MenuDrawer.STATE_OPENING) {
			mMenuDrawer.closeMenu();
			return;
		}

		super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		mMenuDrawer.setActiveView(v);
		mContentTextView.setText(((TextView) v).getText());
		mMenuDrawer.closeMenu();
		mActiveViewId = v.getId();
		Fragment newContent = null;
		switch (mActiveViewId) {
		case R.id.item8:
			// 拟稿
			setSearchVisibility(false);
			newContent = new EditDrafFragment(this);
			break;
		case R.id.item1:
			// 待办公文
			setSearchVisibility(false);
			newContent = new TodoDocumentFragment(this);
			break;
		case R.id.item2:
			// 待阅公文
			setSearchVisibility(false);
			newContent = new ToReadDocumentFragment();
			break;
		case R.id.item3:
			// 会议通知
			setSearchVisibility(false);
			newContent = new MeetingNotificationFragment();
			break;
		case R.id.item4:
			// 通知公告
			setSearchVisibility(true);
			newContent = new NotificationPostFragment(this);
			break;
		case R.id.item5:
			// 通讯录
			setSearchVisibility(true);
			newContent = new AddressBookFragment(this);
			break;
		case R.id.item6:
			// 邮件系统
			setSearchVisibility(false);
			newContent = new MeetingNotificationFragment();
			break;
		case R.id.item7:
			// 信息刊物
			setSearchVisibility(true);
			newContent = new EmailFragment();
			break;
		case R.id.item9:
			// 信息刊物
			setSearchVisibility(true);
			newContent = new SearchFragment();
			break;
		case R.id.btn_menu:
			int drawerState = mMenuDrawer.getDrawerState();
			if (drawerState != MenuDrawer.STATE_OPEN
					&& drawerState != MenuDrawer.STATE_OPENING) {
				// mMenuDrawer.closeMenu();
				mMenuDrawer.openMenu();
				return;
			}
			break;

		default:
			break;
		}

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, newContent).commit();
	}

	private void setSearchVisibility(boolean show) {
		searchLayout.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	public void openFile() {
		Intent intent = new Intent(MainActivity.this,
				MyFileManagerActivity.class);
		startActivityForResult(intent, EditDrafFragment.FILE_RESULT_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (EditDrafFragment.FILE_RESULT_CODE == requestCode) {
			Bundle bundle = null;
			if (data != null && (bundle = data.getExtras()) != null) {
				((EditDrafFragment) getSupportFragmentManager()
						.findFragmentById(R.id.content_frame))
						.setAttachmentText(bundle.getString("fileName"),bundle.getString("filePath"));
			}
		} else  {
			if (data != null) {
				Bundle extras = data.getExtras();
				if (extras != null && extras.getString("name") != null) {
					Toast.makeText(this, extras.getString("name"),
							Toast.LENGTH_SHORT).show();
				}
			} else {
//				Toast.makeText(this, MultiSelectActivity.CALLBACK,
//						Toast.LENGTH_SHORT).show();
				((EditDrafFragment) getSupportFragmentManager()
						.findFragmentById(R.id.content_frame))
						.setPersonText(MultiSelectActivity.CALLBACK.substring(
								1, MultiSelectActivity.CALLBACK.length() - 1));
			}
		}

	}

	@Override
	public void onSucess() {
		findViewById(R.id.item1).performClick();
	}

}

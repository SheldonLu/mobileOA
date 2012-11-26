package com.mzone.oa.ui;

import com.mzone.oa.ui.fragment.AddressBookFragment;
import com.mzone.oa.ui.fragment.ColorFragment;
import com.mzone.oa.ui.fragment.MeetingNotificationFragment;
import com.mzone.oa.ui.fragment.NotificationPostFragment;
import com.mzone.oa.ui.fragment.PublicationFragment;
import com.mzone.oa.ui.fragment.WelcomeFragment;

import net.simonvt.widget.MenuDrawer;
import net.simonvt.widget.MenuDrawerManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

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
        
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
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
        
        searchLayout = (RelativeLayout)findViewById(R.id.searchLayout);

        mContentTextView = (TextView) findViewById(R.id.contentText);

        findViewById(R.id.item1).setOnClickListener(this);
        findViewById(R.id.item2).setOnClickListener(this);
        findViewById(R.id.item3).setOnClickListener(this);
        findViewById(R.id.item4).setOnClickListener(this);
        findViewById(R.id.item5).setOnClickListener(this);
        findViewById(R.id.item6).setOnClickListener(this);
        findViewById(R.id.item7).setOnClickListener(this);

        
        TextView activeView = (TextView) findViewById(mActiveViewId);
        if (activeView != null) {
            mMenuDrawer.setActiveView(activeView);
            mContentTextView.setText(activeView.getText());
            // TODO 替换Fragment
        }else{
        	Fragment welcome = new WelcomeFragment(this);
        	getSupportFragmentManager()
        	.beginTransaction()
        	.replace(R.id.content_frame, welcome)
        	.commit();
        }
        
        

        // This will animate the drawer open and closed until the user manually drags it. Usually this would only be
        // called on first launch.
        mMenuDrawer.getMenuDrawer().peekDrawer();
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        mMenuDrawer.onRestoreDrawerState(inState.getParcelable(STATE_MENUDRAWER));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE_MENUDRAWER, mMenuDrawer.onSaveDrawerState());
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
        if (drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING) {
            mMenuDrawer.closeMenu();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        mMenuDrawer.setActiveView(v);
        mContentTextView.setText(((TextView) v).getText());
//        mMenuDrawer.closeMenu();
        mActiveViewId = v.getId();
        Fragment newContent = null;
        switch (mActiveViewId) {
		case R.id.item1:
			// 待办公文
			setSearchVisibility(false);
			newContent = new ColorFragment(R.color.red);
			break;
		case R.id.item2:
			// 待阅公文
			setSearchVisibility(false);
			newContent = new ColorFragment(R.color.green);
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
			newContent = new ColorFragment(R.color.red);
			break;
		case R.id.item7:
			// 信息刊物
			setSearchVisibility(true);
			newContent = new PublicationFragment(this);
			break;

		default:
			break;
		}
        
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, newContent)
		.commit();
    }
    
    private void setSearchVisibility(boolean show){
    	searchLayout.setVisibility(show?View.VISIBLE:View.GONE);
    }
}

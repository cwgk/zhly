package com.cwgk.zhly;


import java.util.List;
import com.cwgk.adapter.MyPagerAdapter;
import com.cwgk.dao.FirstItem;
import com.cwgk.dao.ListViewDao;
import com.cwgk.util.MyApplication;
import com.cwgk.util.SettingUtils;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity
{

	private PagerSlidingTabStrip tabs;
	private DisplayMetrics dm;

	private LinearLayout mainLl;
	private ListViewDao firstItemDao;
	private int colorSwitch;
	private ImageButton setBtn;
	private long exitTime = 0; 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("Tag", "dm="+dm);
		
		dm = getResources().getDisplayMetrics();
		firstItemDao = new ListViewDao(this);
		List<FirstItem> newsItems = firstItemDao.firstList();
		mainLl = (LinearLayout)findViewById(R.id.main_head_ll);
		ViewPager pager = (ViewPager) findViewById(R.id.id_pager);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		setBtn = (ImageButton)findViewById(R.id.setBtn);
		
		colorSwitch = SettingUtils.getThemeColor(MyApplication.getContext(), SettingUtils.THEME, 0);
		
		switch(colorSwitch){
		case 0:
			mainLl.setBackgroundResource(R.color.light_blue);
			break;
		case 1:
			mainLl.setBackgroundResource(R.color.green);
			break;
		case 2:
			mainLl.setBackgroundResource(R.color.light_orange);
			break;
		case 3:
			mainLl.setBackgroundResource(R.color.purple);
			break;
		}
		
		setBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,SettingActivity.class);
				startActivity(intent);
				MainActivity.this.finish();
			}
		});
		pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),newsItems));
		tabs.setViewPager(pager);
		setTabsValue();
		
	}
	private void setTabsValue() {
		// 设置Tab是自动填充满屏幕的
		tabs.setShouldExpand(true);
		// 设置Tab的分割线是透明的
		tabs.setDividerColor(Color.TRANSPARENT);
		// 设置Tab底部线的高度
		tabs.setUnderlineHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 1, dm));
		// 设置Tab Indicator的高度
		tabs.setIndicatorHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, dm));
		// 设置Tab标题文字的大小
		tabs.setTextSize((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 16, dm));
		// 设置Tab Indicator的颜色
		tabs.setIndicatorColor(Color.parseColor("#45c01a"));
		// 设置选中Tab文字的颜色 (这是我自定义的一个方法)
		tabs.setSelectedTextColor(Color.parseColor("#45c01a"));
		// 取消点击Tab时的背景色
		tabs.setTabBackground(0);
	}
   
    
	@Override    
	public boolean onKeyDown(int keyCode, KeyEvent event) {    
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){    
	                    
	    if((System.currentTimeMillis()-exitTime) > 2000){    
	        Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                 
	        exitTime = System.currentTimeMillis();    
	    }    
	    else{    
	    	this.finish();    
	        System.exit(0);    
	    }    
	                    
	        return true; //返回true表示执行结束不需继续执行父类按键响应  
	    }    
	    return super.onKeyDown(keyCode, event);    
	}    
	
}

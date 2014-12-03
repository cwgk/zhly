package com.cwgk.zhly;

import java.util.List;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import org.taptwo.android.widget.CircleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;
import com.cwgk.adapter.ContentImageAdapter;
import com.cwgk.adapter.ImageAdapter;
import com.cwgk.dao.ListViewDao;
import com.cwgk.util.MyApplication;
import com.cwgk.util.SettingUtils;
import dalvik.system.VMRuntime;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContentActivity extends Activity {
	private final static float TARGET_HEAP_UTILIZATION = 0.75f;
	private final static int CWJ_HEAP_SIZE = 8 * 1024 * 1024;
	private ViewFlow viewFlow;
	private TextView head_title, title, contentText;
	private ImageButton returnBtn;
	private String head_text, secondTitle;
	private ContentImageAdapter mAdapter;
	private ImageAdapter adapter;
	private List<String> list = null;
	private CircleFlowIndicator indic;
	private FrameLayout fl;
	private Boolean isAutoFlow = true;
	private Boolean isLoadNetImage = true;
	private LinearLayout contentLl;
	private int colorSwitch;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_layout);
		
		VMRuntime.getRuntime()
				.setTargetHeapUtilization(TARGET_HEAP_UTILIZATION);
		VMRuntime.getRuntime().setMinimumHeapSize(CWJ_HEAP_SIZE);
		Bundle extras = getIntent().getExtras();
		head_text = extras.getString("Title");
		secondTitle = extras.getString("SecondTitle");
		AdView adView = new AdView(this,AdSize.FIT_SCREEN);
		LinearLayout adLayout = (LinearLayout)findViewById(R.id.adLayout);
		adLayout.addView(adView);
		list = ListViewDao.getImageSet(secondTitle);
		isAutoFlow = SettingUtils.get(this, SettingUtils.AUTO_PLAY,true);
		isLoadNetImage = SettingUtils.get(this, SettingUtils.IS_LOAD_NET,true);
		inintView();
		if(list.size() == 1 && list.get(0).isEmpty()){
			fl.setVisibility(View.GONE);
			viewFlow.setVisibility(View.GONE);
			indic.setVisibility(View.GONE);
		}else{
			viewflow();
		}
		
	
	}

	private void inintView() {
		fl = (FrameLayout)findViewById(R.id.framelayout);
		contentLl = (LinearLayout)findViewById(R.id.second_head_ll);
		viewFlow = (ViewFlow) findViewById(R.id.viewflow);
		head_title = (TextView) findViewById(R.id.secondTV);
		title = (TextView) findViewById(R.id.content_title);
		contentText = (TextView) findViewById(R.id.content_text);
		returnBtn = (ImageButton) findViewById(R.id.back_btn);
		indic = (CircleFlowIndicator) findViewById(R.id.viewflowindic);
		
		returnBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		colorSwitch = SettingUtils.getThemeColor(MyApplication.getContext(),
				SettingUtils.THEME, 0);

		switch (colorSwitch) {
		case 0:
			contentLl.setBackgroundResource(R.color.light_blue);
			break;
		case 1:
			contentLl.setBackgroundResource(R.color.green);
			break;
		case 2:
			contentLl.setBackgroundResource(R.color.light_orange);
			break;
		case 3:
			contentLl.setBackgroundResource(R.color.purple);
			break;
		}
		head_title.setText(head_text);
		title.setText(secondTitle);
		contentText.setText(ListViewDao.getContent(secondTitle));
	}

	private void viewflow() {
	
		String[] newArray = new String[list.size()];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = list.get(i);
		}
		if(isLoadNetImage){
			mAdapter = new ContentImageAdapter(ContentActivity.this, newArray);
			viewFlow.setAdapter(mAdapter);
			if (newArray.length <= 8 && newArray.length > 0) {
				viewFlow.setViewCounts(newArray.length);
			} else {
				viewFlow.setViewCounts(8);
			}
		}else{
			adapter = new ImageAdapter(ContentActivity.this);
			viewFlow.setAdapter(adapter);
			viewFlow.setViewCounts(4);
		}
		viewFlow.setmSideBuffer(2); // 隐藏图片张数为1，避免oom
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(4000);
		viewFlow.setSelection(0); // 设置初始位置
		if(isAutoFlow){
			viewFlow.startAutoFlowTimer(); // 启动自动播放
		}

	}
	

}

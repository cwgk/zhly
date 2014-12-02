package com.cwgk.zhly;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.taptwo.android.widget.CircleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.cwgk.adapter.ContentImageAdapter;
import com.cwgk.adapter.ImageAdapter;
import com.cwgk.adapter.Images;
import com.cwgk.dao.ImagePath;
import com.cwgk.dao.ListViewDao;
import com.cwgk.dao.ThirdItem;
import com.cwgk.util.BitmapCache;
import com.cwgk.util.GetImageSource;
import com.cwgk.util.DownImage;
import com.cwgk.util.MyApplication;
import com.cwgk.util.SettingUtils;

import dalvik.system.VMRuntime;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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
	private ArrayList<String> imagePath;
	private int imageLength;
	private TextView head_title, title, contentText;
	private ImageButton returnBtn;
	private String head_text, secondTitle;
	private ContentImageAdapter mAdapter;
	private ImageAdapter adapter;
	String[] imagesUrl;
	private List<ImagePath> datas;
	private ListViewDao imagePathDao;
	private String path;
	List<String> list = null;
	CircleFlowIndicator indic;
	FrameLayout fl;
	private Boolean isAutoFlow = true;
	private Boolean isLoadNetImage = true;
	private LinearLayout contentLl;
	private int position;
	private int colorSwitch;
	
	
	//Set<String> imageSet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_layout);
		VMRuntime.getRuntime()
				.setTargetHeapUtilization(TARGET_HEAP_UTILIZATION);
		VMRuntime.getRuntime().setMinimumHeapSize(CWJ_HEAP_SIZE);
		Bundle extras = getIntent().getExtras();
		head_text = extras.getString("Title");
		// path = extras.getString("Path");
		secondTitle = extras.getString("SecondTitle");
		imagePathDao = new ListViewDao(this);
		//imagesUrl = ListViewDao.getContentImageUrl(secondTitle);
		list = ListViewDao.getImageSet(secondTitle);
		// datas = imagePathDao.getContentImageUrl(secondTitle);
		/*
		 * images = new Images(); images.imageUrl = new ArrayList<String>();
		 * images.imageUrl.add(ListViewDao.getContentImageUrl(secondTitle));
		 */
		isAutoFlow = SettingUtils.get(this, SettingUtils.AUTO_PLAY,true);
		isLoadNetImage = SettingUtils.get(this, SettingUtils.IS_LOAD_NET,true);
		inintView();
		Log.i("Tag", "list.size========"+list.size());
		Log.i("Tag", "list.get(0)========"+list.get(0));
		if(list.size() == 1 && list.get(0).isEmpty()){
			fl.setVisibility(View.GONE);
			viewFlow.setVisibility(View.GONE);
			indic.setVisibility(View.GONE);
			Log.i("Tag", "list="+list.size());
		}else{
			viewflow();
		}
		
		/*
		 * RequestQueue mQueue = Volley.newRequestQueue(this); ImageLoader
		 * imageLoader = new ImageLoader(mQueue, new BitmapCache());
		 * ImageListener listener = ImageLoader.getImageListener(imageView,
		 * R.drawable.default_image, R.drawable.failed_image); imageLoader.get(
		 * "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
		 * listener, 200, 200);
		 */
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
		Log.i("Tag", "secondTitle=" + secondTitle);
		contentText.setText(ListViewDao.getContent(secondTitle));
	}

	private void viewflow() {
	
		String[] newArray = new String[list.size()];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = list.get(i);
		}
		//Log.i("Tag", "除去空值的新数组就是：" + newArray);
		for (int i = 0; i < newArray.length; i++) {
			Log.i("Tag", "newArray=" + newArray[i]);
		}
		Log.i("Tag", "isLoadNetImage=" + isLoadNetImage);
		if(isLoadNetImage){
			mAdapter = new ContentImageAdapter(ContentActivity.this, newArray);
			viewFlow.setAdapter(mAdapter);
			if (newArray.length <= 8 && newArray.length > 0) {
				viewFlow.setViewCounts(newArray.length);
			} else {
				viewFlow.setViewCounts(8);
			}
		}else{
			//position = SettingUtils.getPosition(ContentActivity.this, SettingUtils.POSITION, 0);
			adapter = new ImageAdapter(ContentActivity.this);
			viewFlow.setAdapter(adapter);
			viewFlow.setViewCounts(4);
		}
		
		viewFlow.setmSideBuffer(2); // 隐藏图片张数为1，避免oom
		// 设置小圆点数目
		
		//viewFlow.setViewCounts(newArray.length);
		

		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(4000);
		
		
		viewFlow.setSelection(0); // 设置初始位置
		// viewFlow.setSelection(0); //设置初始位置
		Log.i("Tag", "isAutoFlow=" + isAutoFlow);
		if(isAutoFlow){
			viewFlow.startAutoFlowTimer(); // 启动自动播放
		}
		

	}
	
	/*public int getImageSize(){
		list = new ArrayList<String>();
		for (int i = 0; i < imagesUrl.length && imagesUrl.length > 0; i++) {
			if (imagesUrl[i] == null
					|| "".equals(imagesUrl[i].trim().toString())) {
				continue;
			} else {
				list.add(imagesUrl[i]);
			}
		}
		return list.size();
	}*/

}

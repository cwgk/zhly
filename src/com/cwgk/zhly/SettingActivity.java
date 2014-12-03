package com.cwgk.zhly;

import java.util.ArrayList;
import com.cwgk.adapter.MyspinnerAdapter;
import com.cwgk.adapter.ToggleListener;
import com.cwgk.util.MyApplication;
import com.cwgk.util.SettingUtils;
import com.cwgk.util.UpdateManager;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SettingActivity extends Activity {
	private ImageButton returnBtn;
	private TextView title;
	private ToggleButton imgSwitch, netSwitch;
	private ArrayList<String> list = new ArrayList<String>();
	private MyspinnerAdapter adapter;
	private LinearLayout spinnerlayout;
	private LinearLayout layout;
	private ListView listView;
	private PopupWindow popupWindow;
	private TextView textView, versionName,update;
	private LinearLayout setLl;
	private int colorSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_layout);
		list.add("蓝色");
		list.add("绿色");
		list.add("橙色");
		list.add("紫色");
		initView();
		setImageSwitch();
		setNetSwitch();
		setListeners();
		
		adapter = new MyspinnerAdapter(SettingActivity.this, list);
		// 默认设置下拉框的标题为数据的第一个
		textView.setText((CharSequence) adapter.getItem(0));
		title.setText("设置");
		colorSwitch = SettingUtils.getThemeColor(MyApplication.getContext(),
				SettingUtils.THEME, 0);

		switch (colorSwitch) {
		case 0:
			textView.setText(list.get(0));
			setLl.setBackgroundResource(R.color.light_blue);
			spinnerlayout.setBackgroundResource(R.color.light_blue);
			break;
		case 1:
			textView.setText(list.get(1));
			setLl.setBackgroundResource(R.color.green);
			spinnerlayout.setBackgroundResource(R.color.green);
			break;
		case 2:
			textView.setText(list.get(2));
			setLl.setBackgroundResource(R.color.light_orange);
			spinnerlayout.setBackgroundResource(R.color.light_orange);
			break;
		case 3:
			textView.setText(list.get(3));
			setLl.setBackgroundResource(R.color.purple);
			spinnerlayout.setBackgroundResource(R.color.purple);
			break;
		}
		returnBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SettingActivity.this,MainActivity.class);
				startActivity(intent);
				SettingActivity.this.finish();
			}
		});
		update.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UpdateManager manager = new UpdateManager(SettingActivity.this);
				// 检查软件更新
				manager.checkUpdate();
			}
		});
		spinnerlayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showWindow(v);

			}
		});
	}

	private void setNetSwitch() {
		boolean isLoadNet = SettingUtils.get(this, SettingUtils.IS_LOAD_NET,
				true);

		netSwitch.setChecked(isLoadNet);
	}

	private void setImageSwitch() {
		boolean isAutoPlay = SettingUtils.get(this, SettingUtils.AUTO_PLAY,
				true);
		imgSwitch.setChecked(isAutoPlay);

	}

	private void initView() {
		returnBtn = (ImageButton) findViewById(R.id.back_btn);
		title = (TextView) findViewById(R.id.secondTV);
		setLl = (LinearLayout)findViewById(R.id.second_head_ll);
		imgSwitch = (ToggleButton) findViewById(R.id.imgTogBtn);
		netSwitch = (ToggleButton) findViewById(R.id.netTogBtn);
		textView = (TextView) findViewById(R.id.text);
		versionName = (TextView) findViewById(R.id.versonName);
		spinnerlayout = (LinearLayout) findViewById(R.id.spinnerid);
		update = (TextView) findViewById(R.id.update);
		versionName.setText(SettingUtils.getVersionName(MyApplication.getContext(), SettingUtils.VERSION_NAME, "智慧旅游1.0"));
	}
 
	
	private void setListeners() {
		imgSwitch.setOnCheckedChangeListener(new ToggleListener(this, "自动播放",
				imgSwitch));
		netSwitch.setOnCheckedChangeListener(new ToggleListener(this, "加载网络资源",
				netSwitch));
	}

	public void showWindow(View v) {
		// 找到布局文件
		layout = (LinearLayout) LayoutInflater.from(this).inflate(
				R.layout.mypinner_dropdown, null);
		// 实例化listView
		listView = (ListView) layout.findViewById(R.id.listView);
		// 设置listView的适配器
		listView.setAdapter(adapter);
		// 实例化一个PopuWindow对象
		popupWindow = new PopupWindow(v);
		// 设置弹框的宽度为布局文件的宽
		popupWindow.setWidth(spinnerlayout.getWidth());
		// 高度随着内容变化
		popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置一个透明的背景，不然无法实现点击弹框外，弹框消失
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// 设置点击弹框外部，弹框消失
		popupWindow.setOutsideTouchable(true);
		// 设置焦点
		popupWindow.setFocusable(true);
		// 设置所在布局
		popupWindow.setContentView(layout);
		// 设置弹框出现的位置，在v的正下方横轴偏移textview的宽度，为了对齐~纵轴不偏移
		popupWindow.showAsDropDown(v, 0, 0);
		// listView的item点击事件
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				textView.setText(list.get(arg2));// 设置所选的item作为下拉框的标题
				SettingUtils.setThemeColor(MyApplication.getContext(), SettingUtils.THEME, arg2);
				popupWindow.dismiss();
				popupWindow = null;
			}
		});

	}
}

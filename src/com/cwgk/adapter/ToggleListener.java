package com.cwgk.adapter;


import com.cwgk.util.SettingUtils;
import com.cwgk.zhly.ContentActivity;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.animation.TranslateAnimation;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

/**
 * 状态按钮的监听事件
 * 
 * @author wwj
 * 
 */
public class ToggleListener implements OnCheckedChangeListener {
	private Context context;
	private String settingName;
	private ToggleButton toggle;

	public ToggleListener(Context context, String settingName,
			ToggleButton toggle) {
		this.context = context;
		this.settingName = settingName;
		this.toggle = toggle;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// 保存设置
		if ("自动播放".equals(settingName)) {
			SettingUtils.set(context, SettingUtils.AUTO_PLAY, isChecked);
			 if (isChecked) { 
				 Log.i("Tag", "自动播放isChecked="+isChecked);
			 } else { 
				 Log.i("Tag", "自动播放isChecked="+isChecked);
			}
		} else if ("加载网络资源".equals(settingName)) {
			SettingUtils.set(context, SettingUtils.IS_LOAD_NET, isChecked);
			 if (isChecked) { 
				 Log.i("Tag", "加载网络资源isChecked="+isChecked);
			 } else { 
				 Log.i("Tag", "加载网络资源isChecked="+isChecked);
			}
		}
		
		
	}

}

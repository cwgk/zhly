package com.cwgk.adapter;


import com.cwgk.util.SettingUtils;
import android.content.Context;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class ToggleListener implements OnCheckedChangeListener {
	private Context context;
	private String settingName;

	public ToggleListener(Context context, String settingName,
			ToggleButton toggle) {
		this.context = context;
		this.settingName = settingName;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// 保存设置
		if ("自动播放".equals(settingName)) {
			SettingUtils.set(context, SettingUtils.AUTO_PLAY, isChecked);
		} else if ("加载网络资源".equals(settingName)) {
			SettingUtils.set(context, SettingUtils.IS_LOAD_NET, isChecked);
		}
		
		
	}

}

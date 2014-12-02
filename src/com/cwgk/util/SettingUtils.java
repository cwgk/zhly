package com.cwgk.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

public class SettingUtils {
	public static final String AUTO_PLAY = "auto_play";	// 自动播放
	public static final String IS_LOAD_NET = "is_load_net";	// 加载网络资源
	public static final String POSITION = "position";	// 加载网络资源
	public static final String THEME = "theme";	// 加载网络资源
	public static final String VERSION_NAME = "version_name";	// 加载网络资源
	
	
	/**
	 * 获取配置
	 * @param context
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static boolean get(Context context, String name, boolean defaultValue) {
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		boolean value = prefs.getBoolean(name, defaultValue);
		return value;
	}
	
	/**
	 * 保存用户配置
	 * @param context
	 * @param name
	 * @param value
	 * @return
	 */
	public static boolean set(Context context, String name, boolean value) {
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = prefs.edit();
		editor.putBoolean(name, value);
		return editor.commit();	//提交
	}
	
	public static int getPosition(Context context, String name,int defaultValue){
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		int position = prefs.getInt(name, defaultValue);
		return position;
	}
	public static boolean setPosition(Context context, String name, int value){
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = prefs.edit();
		editor.putInt(name, value);
		return editor.commit();	
	}
	public static int getThemeColor(Context context, String name,int defaultValue){
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		int position = prefs.getInt(name, defaultValue);
		return position;
	}
	public static boolean setThemeColor(Context context, String name, int value){
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = prefs.edit();
		editor.putInt(name, value);
		return editor.commit();	
	}
	public static String getVersionName(Context context, String name,String defaultValue){
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		String versionName = prefs.getString(name, defaultValue);
		return versionName;
	}
	public static boolean setVersionName(Context context, String name, String value){
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = prefs.edit();
		editor.putString(name, value);
		return editor.commit();	
	}
}

package com.cwgk.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.net.Uri;
import android.util.Log;

public class DownImage {
	private DownUtil downUtil;
	public Uri downImage(String name, String imageUrl, String imageDir)
			throws Exception {
		try {
			File f = new File(imageDir);
			if (!f.exists()) {
				f.mkdirs();
			}
			final String cache = imageDir + "/" + name;
			// FileUtils.readConfiguration();
			final String urlnow = imageUrl;
			Log.i("Tag", "urlnow===" + urlnow + "cache= " + cache);
			// 开启多线程下载，图片只要一个线程，不然图片乱的
			File file = new File(cache);
			if (file.exists()) {
				return Uri.fromFile(file);// Uri.fromFile(path)这个方法能得到文件的URI
			} else {
				Log.i("Tag", "stratDown");
				downUtil = new DownUtil(urlnow, cache, 1);
				downUtil.download();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}

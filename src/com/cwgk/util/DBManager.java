package com.cwgk.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DBManager {
	private final int BUFFER_SIZE = 1024;
	private SQLiteDatabase database;
	private Context context;
	AssetManager am;
	 public static String SDPATH = Environment.getExternalStorageDirectory()
	            + "/zhly/";//获取文件夹

	public DBManager(Context context) {
		this.context = context;
		am = this.context.getAssets();
	}

	public SQLiteDatabase openDatabase() {
		//deleteDir();
		File sdFile = Environment.getExternalStorageDirectory();
		
		File gpsPath = new File(sdFile.getPath() + "/zhly/data.db");
		//File gpsPath = new File("/mnt/shared/ShareScdg/pms/zhly.db");
		Log.i("Tag", "sdFile.getPath()=" + sdFile.getPath());
		if (!gpsPath.exists()) {
			Log.i("Tag", "gpsPath.exists()=" + gpsPath.exists());
			try {
				// 创建目录
				File pmsPaht = new File(sdFile.getPath() + "/zhly/");

				if (!pmsPaht.exists()) {
					pmsPaht.mkdirs();
					Log.i("Tag", "pmsPaht: =" + pmsPaht.getPath());
				} else {
					//deleteDir(pmsPaht);
				}
				//AssetManager am = this.context.getAssets();
				InputStream is = am.open("data.mp3");

				FileOutputStream fos = new FileOutputStream(gpsPath);

				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.flush();

				fos.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		database = SQLiteDatabase.openOrCreateDatabase(gpsPath, null);

		return database;
	}

/*	 public static void deleteDir() {
	        File dir = new File(SDPATH);
	        if (dir == null || !dir.exists() || !dir.isDirectory())
	            return;
	         
	        for (File file : dir.listFiles()) {
	            if (file.isFile())
	                file.delete(); // 删除所有文件
	            else if (file.isDirectory())
	                deleteDir(); // 递规的方式删除文件夹
	        }
	        dir.delete();// 删除目录本身
	    }*/
	public void close() {
		if (database != null) {
			this.database.close();
		}
	}
}

package com.cwgk.zhly;

import java.io.OutputStream;

import com.cwgk.util.DBManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LoginActivity extends Activity {

	private SharedPreferences preferences;
	private ImageView iv;
	private LinearLayout ll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		ll = (LinearLayout)findViewById(R.id.loginLl);
		//iv = (ImageView)findViewById(R.id.loginImage);
		preferences = getSharedPreferences("count", Context.MODE_PRIVATE);
		int count = preferences.getInt("count", 0);
		if (count == 0) {
			ll.setBackgroundResource(R.drawable.login);
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						createDB();
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Intent intent = new Intent("com.cwgk.zhly.MAIN_VIEW");  
					startActivity(intent);
					LoginActivity.this.finish();

				}
			}).start();

		}else{
			Intent intent = new Intent("com.cwgk.zhly.MAIN_VIEW");
			startActivity(intent);
			LoginActivity.this.finish();
		}

		Editor editor = preferences.edit();
		// 存入数据
		editor.putInt("count", ++count);
		// 提交修改
		editor.commit();

	}

	public void createDB() {
		DBManager dbm = new DBManager(this);
		dbm.openDatabase();
		dbm.close();
	}
}
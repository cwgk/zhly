package com.cwgk.zhly;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.cwgk.adapter.Images;
import com.cwgk.adapter.SecondItemAdapter;
import com.cwgk.adapter.ThirdItemAdapter;
import com.cwgk.dao.ListViewDao;
import com.cwgk.dao.SecondItem;
import com.cwgk.dao.ThirdItem;
import com.cwgk.util.DownUtil;
import com.cwgk.util.MyApplication;
import com.cwgk.util.SettingUtils;
import com.cwgk.zhly.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SecondActivity extends Activity {
	private int page;
	private ListView secondList;
	private ThirdItemAdapter adapter;
	private ListViewDao secondItemDao;
	private List<ThirdItem> datas;
	private ImageButton returnBtn;
	private TextView titleText;
	private String title;
	private DownUtil downUtil;
	private LinearLayout secondLl;
	private int colorSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_layout);
		Bundle extras = getIntent().getExtras();
		page = extras.getInt("Page");
		title = extras.getString("title");
		Log.i("Tag", "page=" + page + "    title=" + title);
		secondList = (ListView) findViewById(R.id.second_lv);
		returnBtn = (ImageButton) findViewById(R.id.back_btn);
		titleText = (TextView) findViewById(R.id.secondTV);
		secondLl = (LinearLayout) findViewById(R.id.second_head_ll);
		
		colorSwitch = SettingUtils.getThemeColor(MyApplication.getContext(),
				SettingUtils.THEME, 0);

		switch (colorSwitch) {
		case 0:
			secondLl.setBackgroundResource(R.color.light_blue);
			break;
		case 1:
			secondLl.setBackgroundResource(R.color.green);
			break;
		case 2:
			secondLl.setBackgroundResource(R.color.light_orange);
			break;
		case 3:
			secondLl.setBackgroundResource(R.color.purple);
			break;
		}
		titleText.setText(title);
		returnBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		secondItemDao = new ListViewDao(this);
		datas = secondItemDao.thirdList(page);
		adapter = new ThirdItemAdapter(this, datas);
		secondList.setAdapter(adapter);
		secondList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(SecondActivity.this,
						ContentActivity.class);
				// String paths =
				// ListViewDao.getContentImageUrl(datas.get(position).getTitle());
				intent.putExtra("Title", title);
				// intent.putExtra("Path", paths);
				intent.putExtra("SecondTitle", datas.get(position).getTitle());

				// downImage();
				/*
				 * Images images = new Images(); images.imageUrl = new
				 * ArrayList<String>();
				 * images.imageUrl.add(ListViewDao.getContentImageUrl
				 * (datas.get(position).getTitle()));
				 */
				startActivity(intent);
			}
		});
	}

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

package com.cwgk.fragment;

import java.util.List;

import com.cwgk.adapter.SecondItemAdapter;
import com.cwgk.dao.ListViewDao;
import com.cwgk.dao.SecondItem;
import com.cwgk.zhly.R;
import com.cwgk.zhly.SecondActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;

/**
 * 通讯录Fragment的界面
 * 
 * http://blog.csdn.net/guolin_blog/article/details/26365683
 * 
 * @author guolin
 */
public class SecondFragment extends Fragment {
	private View messageLayout;
	private ListView mListView;
	private ListViewDao firstItemDao;
	private SecondItemAdapter adapter;
	private List<SecondItem> datas;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		messageLayout = inflater.inflate(R.layout.first_layout, container,
				false);
		mListView = (ListView) messageLayout.findViewById(R.id.first_lv);
		firstItemDao = new ListViewDao(getActivity());
		datas = firstItemDao.secondList(2);
		adapter = new SecondItemAdapter(getActivity(), datas,1);
		mListView.setAdapter(adapter);
	
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), SecondActivity.class);
				intent.putExtra("Page", ListViewDao.getId(2).get(position)); 
				intent.putExtra("title", datas.get(position).getTitle());
				startActivity(intent);
			}
		});
		return messageLayout;
	}

}

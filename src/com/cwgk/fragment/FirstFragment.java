package com.cwgk.fragment;

import java.util.List;
import com.cwgk.adapter.SecondItemAdapter;
import com.cwgk.dao.ListViewDao;
import com.cwgk.dao.SecondItem;
import com.cwgk.zhly.R;
import com.cwgk.zhly.SecondActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FirstFragment extends Fragment {
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
		datas = firstItemDao.secondList(1);
		
		adapter = new SecondItemAdapter(getActivity(), datas,0);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), SecondActivity.class);
				intent.putExtra("Page", ListViewDao.getId(1).get(position)); 
				intent.putExtra("title", datas.get(position).getTitle());
				startActivity(intent);
			}
		});
		return messageLayout;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	
	}

	

}

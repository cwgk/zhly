package com.cwgk.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cwgk.dao.ThirdItem;
import com.cwgk.zhly.R;

public class ThirdItemAdapter extends BaseAdapter
{

	private LayoutInflater mInflater;
	private List<ThirdItem> mDatas;

	/**
	 * ʹ����github��Դ��ImageLoad��������ݼ���
	 */

	public ThirdItemAdapter(Context context, List<ThirdItem> datas)
	{
		this.mDatas = datas;
		mInflater = LayoutInflater.from(context);
	}

	public void addAll(List<ThirdItem> mDatas)
	{
		this.mDatas.addAll(mDatas);
	}

	public void setDatas(List<ThirdItem> mDatas)
	{
		this.mDatas.clear();
		this.mDatas.addAll(mDatas);
	}

	@Override
	public int getCount()
	{
		return mDatas.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.second_item_layout, null);
			holder = new ViewHolder();

			holder.mContent = (TextView) convertView.findViewById(R.id.second_item_tv);

			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		ThirdItem thirdItem = mDatas.get(position);
		holder.mContent.setText(thirdItem.getTitle());

		return convertView;
	}

	private final class ViewHolder
	{
		//TextView mTitle;
		TextView mContent;
		//TextView mDate;
	}

}

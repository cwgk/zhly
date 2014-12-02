package com.cwgk.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwgk.dao.SecondItem;
import com.cwgk.zhly.R;
import com.zhy.bean.NewsItem;
import com.zhy.csdn.DataUtil;

public class SecondItemAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<SecondItem> mDatas;
	private int menuId;
	private int[] firstViewImages = new int[] { R.drawable.whgk,
			R.drawable.dfwh, R.drawable.msfq, R.drawable.dldm,
			R.drawable.rwls, R.drawable.fzgh, R.drawable.whlyxxzxzx,
			R.drawable.wmcsjs, R.drawable.lybzhjs };
	private int[] secondViewImages = new int[] { R.drawable.ajjq,
			R.drawable.tsjq, R.drawable.jply, R.drawable.tsly, R.drawable.cyxx,
			R.drawable.lyykt, R.drawable.ajjq };
	private int[] thirdViewImages = new int[] { R.drawable.xjjd,
			R.drawable.jjxjd, R.drawable.lydjc, R.drawable.tsjd,
			 };
	private int[] fourthViewImages = new int[] { R.drawable.hbc,
			R.drawable.chuanc, R.drawable.xc, R.drawable.yc, R.drawable.hg,
			R.drawable.hx, R.drawable.sk, R.drawable.ygfw, R.drawable.zbms };
	private int[] fifthViewImages = new int[] { R.drawable.yjy, R.drawable.ktv,
			R.drawable.jb, R.drawable.yyhs, R.drawable.xybj, R.drawable.zbyl };
	private int[] sixthViewImages = new int[] { R.drawable.whtc,
			R.drawable.whlzh, R.drawable.ggsq,
			R.drawable.jhlsq, R.drawable.jdksq, R.drawable.ljhsq,
			R.drawable.wjwsq, R.drawable.wgsq, R.drawable.xdsq,
			R.drawable.znlsq, R.drawable.zjcsq, R.drawable.fwzwhyc };
	private int[] seventhViewImages = new int[] { R.drawable.gjxl,
			R.drawable.gjzd, R.drawable.gdjt, R.drawable.gjld, R.drawable.jpxx,
			R.drawable.hcpxx, R.drawable.jszx };
	private int[] eighthViewImages = new int[] { R.drawable.mfzxc,
			R.drawable.smzj, R.drawable.jqhd, R.drawable.lnwh, R.drawable.qnwh,
			R.drawable.whzj, R.drawable.yxlsjz, R.drawable.lhklyzxc };
	private int[] ninthViewImages = new int[] { R.drawable.cydh,
			 R.drawable.tqyb,R.drawable.yljz, R.drawable.gaxf,
			R.drawable.jdcjy, R.drawable.hczxx, R.drawable.jcxx };
	private int[][] viewImages = new int[][] { firstViewImages,
			secondViewImages, thirdViewImages, fourthViewImages,
			fifthViewImages, sixthViewImages, seventhViewImages,
			eighthViewImages, ninthViewImages };

	public SecondItemAdapter(Context context, List<SecondItem> datas, int menuId) {
		this.mDatas = datas;
		this.menuId = menuId;
		mInflater = LayoutInflater.from(context);
		/*
		 * options = new
		 * DisplayImageOptions.Builder().showStubImage(R.drawable.images)
		 * .showImageForEmptyUri
		 * (R.drawable.images).showImageOnFail(R.drawable.images
		 * ).cacheInMemory() .cacheOnDisc().displayer(new
		 * RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300))
		 * .build();
		 */
	}

	public void addAll(List<SecondItem> mDatas) {
		this.mDatas.addAll(mDatas);
	}

	public void setDatas(List<SecondItem> mDatas) {
		this.mDatas.clear();
		this.mDatas.addAll(mDatas);
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_layout, null);
			holder = new ViewHolder();

			holder.mContent = (TextView) convertView
					.findViewById(R.id.first_item_tv);
			holder.mImg = (ImageView) convertView
					.findViewById(R.id.first_item_image);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SecondItem secondItem = mDatas.get(position);
		// holder.mTitle.setText(DataUtil.ToDBC(newsItem.getTitle()));
		holder.mContent.setText(secondItem.getTitle());
		holder.mImg.setImageResource(viewImages[menuId][position]);
		// holder.mDate.setText(newsItem.getDate());
		if (secondItem.getImgLink() != null) {
			holder.mImg.setVisibility(View.VISIBLE);
			// imageLoader.displayImage(secondItem.getImgLink(), holder.mImg,
			// options);
		} else {
			holder.mImg.setVisibility(View.GONE);
		}

		return convertView;
	}

	private final class ViewHolder {
		// TextView mTitle;
		TextView mContent;
		ImageView mImg;
		// TextView mDate;
	}

}

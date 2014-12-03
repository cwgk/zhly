package com.cwgk.adapter;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.cwgk.util.BitmapCache;
import com.cwgk.zhly.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

public class ContentImageAdapter extends BaseAdapter {
	
	ViewHolder holder;
	private Context mContext;
	private LayoutInflater mInflater;
	RequestQueue mQueue;
	private String[] imagethumburls;
	private String imgPath; 
	public static Boolean imageIsOK = false;
	
	
	public ContentImageAdapter(Context context, String[] imagethumburls) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.imagethumburls = imagethumburls;
		mQueue = Volley.newRequestQueue(context);  
		 
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE; // 返回很大的值使得getView中的position不断增大来实现循环

	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache()); 
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.image_item, null);
			holder = new ViewHolder();
			holder.mImageView = (MyImageProgressBar) convertView
					.findViewById(R.id.imgView);
			//holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar); 
		} 
		
		if(imagethumburls.length != 0){
			imgPath = imagethumburls[position%imagethumburls.length];
			String url = null;
			try {
				url = "http://www.iwuhan12301.com/Images/b-Image/" + URLEncoder.encode(imgPath,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			((MyImageProgressBar) convertView.findViewById(R.id.imgView)).setDefaultImage(R.drawable.default_bg);
			((MyImageProgressBar) convertView.findViewById(R.id.imgView)).setErrorImage(R.drawable.actionbar_bg);
			((MyImageProgressBar) convertView.findViewById(R.id.imgView)).setImage(url, imageLoader);
			
		}
		
		return convertView;
	}
	class ViewHolder {
		MyImageProgressBar mImageView;
		private ProgressBar progressBar;
	}

}

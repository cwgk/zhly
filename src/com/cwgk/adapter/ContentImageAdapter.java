package com.cwgk.adapter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import libcore.io.DiskLruCache;
import libcore.io.DiskLruCache.Snapshot;

import org.taptwo.android.widget.ViewFlow;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.cwgk.dao.ImagePath;
import com.cwgk.util.BitmapCache;
import com.cwgk.util.GetBitmap;
import com.cwgk.zhly.R;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ContentImageAdapter extends BaseAdapter {
	
	
	ViewHolder holder;
    private ViewFlow mViewFlow;
    
	private ImageView iv,imageView;
	private Bitmap bm;
	private ArrayList<String> imgsPath;
	private Context mContext;
	private LayoutInflater mInflater;
	private int count = 0;
	RequestQueue mQueue;
	private String[] imagethumburls;
	private String imgPath; 
	public static Boolean imageIsOK = false;
	
	
	public ContentImageAdapter(Context context, String[] imagethumburls) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//imgsPath = imagePath;
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
			Log.i("Tag", "imgPath="+imgPath);
			((MyImageProgressBar) convertView.findViewById(R.id.imgView)).setDefaultImage(R.drawable.default_bg);
			((MyImageProgressBar) convertView.findViewById(R.id.imgView)).setErrorImage(R.drawable.actionbar_bg);
			((MyImageProgressBar) convertView.findViewById(R.id.imgView)).setImage(url, imageLoader);
			
		}
		
		/*if(position == 0){
			
			if (position%imagethumburls.length != 0 && count == 0) {
				
					
					imgPath = imagethumburls[position%imagethumburls.length];
			
			}
			count++;
		}else if (position%imagethumburls.length != 0) {
			imgPath = imagethumburls[position%imagethumburls.length];
		}*/
		/*((MyImageProgressBar) convertView.findViewById(R.id.imgView)).setDefaultImageResId(R.drawable.ic_launcher);
		((MyImageProgressBar) convertView.findViewById(R.id.imgView)).setErrorImageResId(R.drawable.cydh);
		((MyImageProgressBar) convertView.findViewById(R.id.imgView)).setImageUrl(imgPath, imageLoader);*/
		
		
		/*if(imageIsOK){
			Log.i("Tag", "imageIsOK="+imageIsOK);
			holder.progressBar.setVisibility(View.VISIBLE);
			((MyImageProgressBar) convertView.findViewById(R.id.imgView)).setDefaultImageResId(R.drawable.ic_launcher);
			((MyImageProgressBar) convertView.findViewById(R.id.imgView)).setErrorImageResId(R.drawable.cydh);
		}else{
			Log.i("Tag", "imageIsOK="+imageIsOK);
			holder.progressBar.setVisibility(View.GONE);
			((MyImageProgressBar) convertView.findViewById(R.id.imgView)).setImageUrl(imgPath, imageLoader);
		}
		*/
		
		/*holder.mImageView.setDefaultImageResId(R.drawable.ic_launcher);  
		holder.mImageView.setErrorImageResId(R.drawable.ic_launcher);  
		holder.mImageView.setImageUrl(imgPath,  
		                imageLoader);*/
		/*if (convertView == null) {
			convertView = mInflater.inflate(R.layout.image_item, null);
			iv = (ImageView) convertView
					.findViewById(R.id.imgView);
		} 
		if(position == 0){
			
			if (imgsPath.size() != 0 && count == 0) {
				try {
					bm = GetBitmap.readBitMap(mContext, imgsPath.get(position % imgsPath.size()));
				} catch (FileNotFoundException e) { // TODO Auto-generated catch
													// block
					e.printStackTrace();
				}
			}
			count++;
		}else if (imgsPath.size() != 0) {
			try {
				bm = GetBitmap.readBitMap(mContext, imgsPath.get(position % imgsPath.size()));
			} catch (FileNotFoundException e) { // TODO Auto-generated catch
												// block
				e.printStackTrace();
			}
		}
		
		
		Log.i("Tag", "bm======bm===== " + bm);
			((ImageView) convertView.findViewById(R.id.imgView))
					.setImageBitmap(bm);
			
			if (bm != null && bm.isRecycled()) {
				bm.recycle();
				bm = null;
				System.gc();
			}
			
*/
		return convertView;
	}
	class ViewHolder {
		MyImageProgressBar mImageView;
		private ProgressBar progressBar;
	}

}

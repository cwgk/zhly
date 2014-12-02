package com.cwgk.adapter;

import com.android.volley.toolbox.ImageLoader;
import com.cwgk.zhly.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class MyImageProgressBar extends FrameLayout{
	private MyNetworkImageView mImageView;
	private ProgressBar pb;
	public static Boolean isVisible = false;
	public MyImageProgressBar(Context context) {
		super(context);
	}
	
	public MyImageProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.image_progressbar_layout, this, true);  
        mImageView = (MyNetworkImageView)findViewById(R.id.myImageView);
       // pb = (ProgressBar)findViewById(R.id.myProgressBar);
	}
	
	public MyImageProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	/*public void setProgressBarisVisible(){
		if(!isVisible){
		    Log.i("Tag", "isVisible="+isVisible);
			pb.setVisibility(View.VISIBLE);
		}else{
			Log.i("Tag", "isVisible="+isVisible);
			pb.setVisibility(View.INVISIBLE);
		}
	}*/
	
	public void setDefaultImage(int resId){
	
		mImageView.setDefaultImageResId(resId);
		//setProgressBarisVisible();
	}
	public void setErrorImage(int resId){
		
		mImageView.setErrorImageResId(resId);
		//setProgressBarisVisible();
	}
	public void setImage(String imgPath,ImageLoader imageLoader){
		
		mImageView.setImageUrl(imgPath, imageLoader);
		
		//setProgressBarisVisible();
		//Log.i("Tag", "mImageView.getDrawable().getCurrent().getConstantState()="+mImageView.getDrawable().getCurrent().getConstantState());
		/*if(mImageView.getDrawable().equals(getResources().getDrawable(R.drawable.default_bg))){
			Log.i("Tag", "PB1");
			pb.setVisibility(View.VISIBLE);
		}else{
			Log.i("Tag", "PB2");
			pb.setVisibility(View.INVISIBLE);
		}*/
	}


}

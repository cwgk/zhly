package com.cwgk.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.cwgk.dao.FirstItem;
import com.cwgk.fragment.EighthFragment;
import com.cwgk.fragment.FifthFragment;
import com.cwgk.fragment.FirstFragment;
import com.cwgk.fragment.FourthFragment;
import com.cwgk.fragment.NinthFragment;
import com.cwgk.fragment.SecondFragment;
import com.cwgk.fragment.SeventhFragment;
import com.cwgk.fragment.SixthFragment;
import com.cwgk.fragment.ThirdFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {
	private FirstFragment firstFragment;
	private SecondFragment secondFragment;
	private ThirdFragment thirdFragment;
	private FourthFragment fourthFragment;
	private FifthFragment fifthFragment;
	private SixthFragment sixFragment;
	private SeventhFragment sevenFragment;
	private EighthFragment eighthFragment;
	private NinthFragment ninFragment;
	
	private List<FirstItem> mDatas;
	
	public MyPagerAdapter(FragmentManager fm,List<FirstItem> datas) {
		super(fm);
		this.mDatas = datas;
	}


	@Override
	public CharSequence getPageTitle(int position) {
		return mDatas.get(position).getTitle();
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}
	
	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			if (firstFragment == null) {
				firstFragment = new FirstFragment();
			}
			return firstFragment;
		case 1:
			if (secondFragment == null) {
				secondFragment = new SecondFragment();
			}
			return secondFragment;
		case 2:
			if (thirdFragment == null) {
				thirdFragment = new ThirdFragment();
			}
			return thirdFragment;
		case 3:
			if (fourthFragment == null) {
				fourthFragment = new FourthFragment();
			}
			return fourthFragment;
		case 4:
			if (fifthFragment == null) {
				fifthFragment = new FifthFragment();
			}
			return fifthFragment;
		case 5:
			if (sixFragment == null) {
				sixFragment = new SixthFragment();
			}
			return sixFragment;
	/*	case 6:
			if (sevenFragment == null) {
				sevenFragment = new SeventhFragment();
			}
			return sevenFragment;*/
		case 6:
			if (eighthFragment == null) {
				eighthFragment = new EighthFragment();
			}
			return eighthFragment;
		case 7:
			if (ninFragment == null) {
				ninFragment = new NinthFragment();
			}
			return ninFragment;
		default:
			return null;
		}
	}
}
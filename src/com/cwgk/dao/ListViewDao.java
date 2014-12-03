package com.cwgk.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.cwgk.util.DBManager;

public class ListViewDao {

	private static DBManager dbm;
	private static List<Integer> list;
	static RequestQueue mQueue;

	public ListViewDao(Context context) {
		dbm = new DBManager(context);
		mQueue = Volley.newRequestQueue(context);
	}


	public List<FirstItem> firstList() {

		List<FirstItem> firstItems = new ArrayList<FirstItem>();
		try {
			String sql = "select * from MainMenu where IsEnable = 1 order by ListOrderNo";
			SQLiteDatabase db = dbm.openDatabase();
			Cursor c = db.rawQuery(sql, null);
			FirstItem firstItem = null;
			while (c.moveToNext()) {
				firstItem = new FirstItem();

				String title = c.getString(1);
				String imgLink = c.getString(2);
				firstItem.setTitle(title);
				firstItem.setImgLink(imgLink);
				firstItems.add(firstItem);
			}
			c.close();
			db.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return firstItems;

	}

	public List<SecondItem> secondList(int page) {

		List<SecondItem> secondItems = new ArrayList<SecondItem>();
		try {
			String sql = "select * from SecondaryMenu where MenuId =" + page
					+ " and IsEnable = '1'";
			SQLiteDatabase db = dbm.openDatabase();
			Cursor c = db.rawQuery(sql, null);
			SecondItem secondItem = null;
			while (c.moveToNext()) {
				secondItem = new SecondItem();

				String title = c.getString(7);
				String imgLink = c.getString(3);
				secondItem.setTitle(title);
				secondItem.setImgLink(imgLink);
				secondItems.add(secondItem);
			}
			c.close();
			db.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return secondItems;

	}

	public static Set<Integer> getThirdMenuId(int page) {
		Set<Integer> secondaryMenuIds = new HashSet<Integer>();
		try {
			String sql = "select * from DataItem where MenuId = " + page
					+ " and IsEnable = '1'";
			SQLiteDatabase db = dbm.openDatabase();
			Cursor c = db.rawQuery(sql, null);
			while (c.moveToNext()) {
				int thridId = c.getInt(8);
				secondaryMenuIds.add(thridId);
			}
			c.close();
			db.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return secondaryMenuIds;
	}

	public static List<Integer> getId(int page) {
		List<Integer> secondaryMenuIds = new ArrayList<Integer>();

		try {
			String sql = "select * from SecondaryMenu where MenuId = " + page
					+ " and IsEnable = '1'";
			SQLiteDatabase db = dbm.openDatabase();
			Cursor c = db.rawQuery(sql, null);
			while (c.moveToNext()) {
				int thridId = c.getInt(21);
				secondaryMenuIds.add(thridId);

			}
			c.close();
			db.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Integer> tempList = new ArrayList<Integer>();
		for (Integer i : secondaryMenuIds) {
			if (!tempList.contains(i)) {
				tempList.add(i);
			}
		}
		return tempList;
	}

	public static List<Integer> getMenuId(int menuId) {
		list = new ArrayList<Integer>();
		Iterator<Integer> it = ListViewDao.getThirdMenuId(menuId).iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	public List<ThirdItem> thirdList(int integer) {

		List<ThirdItem> thirdItems = new ArrayList<ThirdItem>();
		try {
			String sql = "select * from DataItem where SecondaryMenuId ="
					+ integer;
			SQLiteDatabase db = dbm.openDatabase();
			Cursor c = db.rawQuery(sql, null);
			ThirdItem thirdItem = null;
			while (c.moveToNext()) {
				thirdItem = new ThirdItem();

				String title = c.getString(4);
				thirdItem.setTitle(title);
				thirdItems.add(thirdItem);
			}
			c.close();
			db.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return thirdItems;

	}

	public static String getContent(String title) {

		String contentText = null;
		try {
			String sql = "select * from DataItem where Name like '" + title
					+ "'";
			SQLiteDatabase db = dbm.openDatabase();
			Cursor c = db.rawQuery(sql, null);
			while (c.moveToNext()) {

				contentText = c.getString(6);
			}
			c.close();
			db.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentText;

	}

	public static List<String> getImageSet(String title) {
		List<String> imageIds = new ArrayList<String>();
		try {
			String sql = "select * from DataItem where Name like '" + title
					+ "'";
			;
			SQLiteDatabase db = dbm.openDatabase();
			Cursor c = db.rawQuery(sql, null);
			while (c.moveToNext()) {
				String str = c.getString(3);
				String[] strarray = str.split("\\|");
				for (int i = 0; i < strarray.length; i++) {

					imageIds.add(strarray[i]);
				}
				c.close();
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> tempList = new ArrayList<String>();
		for (String i : imageIds) {
			if (!tempList.contains(i)) {
				tempList.add(i);
			}
		}
		return tempList;
	}


}

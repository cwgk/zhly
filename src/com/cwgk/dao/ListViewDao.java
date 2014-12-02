package com.cwgk.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONException;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.cwgk.util.DBManager;
import com.cwgk.util.DownUtil;
import com.zhy.bean.NewsItem;

public class ListViewDao {

	private static DBManager dbm;
	private static List<Integer> list;
	private static DownUtil downUtil;
	static RequestQueue mQueue;

	public ListViewDao(Context context) {
		dbm = new DBManager(context);
		mQueue = Volley.newRequestQueue(context);
	}

	/*public void add(FirstItem firstItem) {
		String sql = "insert into tb_newsItem (title,imgLink) values(?,?) ;";
		SQLiteDatabase db = dbm.openDatabase();
		db.execSQL(sql,
				new Object[] { firstItem.getTitle(), firstItem.getImgLink() });
		db.close();
	}

	public void deleteAll(int newsType) {
		String sql = "delete from tb_newsItem where newstype = ?";
		SQLiteDatabase db = dbm.openDatabase();
		db.execSQL(sql, new Object[] { newsType });
		db.close();
	}

	public void add(List<FirstItem> firstItems) {
		for (FirstItem firstItem : firstItems) {
			add(firstItem);
		}
	}*/

	public void query() {
		// String sql = "select * from Client limit 5";
		String sql = "select * from MainMenu";

		// DBManager dbm = new DBManager(getActivity());
		SQLiteDatabase db = dbm.openDatabase();
		Cursor cur = db.rawQuery(sql, null);

		while (cur.moveToNext()) {
			String latitude = cur.getString(1);
			Log.i("Tag", "一级菜单：" + latitude);
		}
		cur.close();
		db.close();
	}

	public List<FirstItem> firstList(/* int newsType, int currentPage */) {

		List<FirstItem> firstItems = new ArrayList<FirstItem>();
		try {
			String sql = "select * from MainMenu where IsEnable = 1 order by ListOrderNo";
			// String sql =
			// "select * from MainMenu where ListOrderNo is not NULL and SystemId = 2 order by ListOrderNo";
			SQLiteDatabase db = dbm.openDatabase();
			Cursor c = db.rawQuery(sql, null);
			FirstItem firstItem = null;
			while (c.moveToNext()) {
				firstItem = new FirstItem();

				String title = c.getString(1);
				String imgLink = c.getString(2);
				Log.i("Tag", "一级菜单：" + title);
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
			/*
			 * String sql = "select * from SecondaryMenu where MenuId = " + page
			 * + " and IsEnable = 1 order by ListOrderNo";
			 */
			SQLiteDatabase db = dbm.openDatabase();
			Cursor c = db.rawQuery(sql, null);
			SecondItem secondItem = null;
			while (c.moveToNext()) {
				secondItem = new SecondItem();

				String title = c.getString(7);
				String imgLink = c.getString(3);
				Log.i("Tag", "二级级菜单：" + title);
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
				Log.i("Tag", "三级菜单ID：" + secondaryMenuIds);
			}
			c.close();
			db.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.i("Tag", "secondaryMenuIds.size()=" + secondaryMenuIds.size());
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
		Log.i("Tag", "secondaryMenuIds.size()=" + secondaryMenuIds.size());
		List<Integer> tempList = new ArrayList<Integer>();
		for (Integer i : secondaryMenuIds) {
			if (!tempList.contains(i)) {
				tempList.add(i);
			}
		}
		Log.i("Tag", "三级菜单ID：" + tempList);
		return tempList;
	}

	public static List<Integer> getMenuId(int menuId) {
		list = new ArrayList<Integer>();
		Iterator<Integer> it = ListViewDao.getThirdMenuId(menuId).iterator();
		while (it.hasNext()) {
			list.add(it.next());
			Log.i("Tag", "list=" + list);
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
				Log.i("Tag", "三级菜单：" + title);
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
				Log.i("Tag", "四级内容：：" + contentText);
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
					Log.i("Tag", "四级图片地址:" + strarray[i]);
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
		Log.i("Tag", "tempList=" + tempList);
		return tempList;
	}

	public static String[] getContentImageUrl(String title) {

		String[] imagePaths = new String[20];// = new ArrayList<ImagePath>();
		try {
			String sql = "select * from DataItem where Name like '" + title
					+ "'";
			SQLiteDatabase db = dbm.openDatabase();
			Cursor c = db.rawQuery(sql, null);
			// ImagePath imagePath;
			while (c.moveToNext()) {
				// imagePath = new ImagePath();
				String str = c.getString(3);
				String[] strarray = str.split("\\|");
				imagePaths = new String[str.length()];
				for (int i = 0; i < strarray.length; i++) {
					String p = strarray[i];
					if (p != null) {
						imagePaths[i] = p;
						Log.i("Tag", "四级菜单图片地址：" + p);
					}

					// String imageFiles = "/mnt/shared/ShareScdg/pms/" + title;
					// downImage(strarray[i],p,imageFiles);
					// imagePaths.add(imagePath);
				}
				// String imageUrl = "http://www.iwuhan12301.com/Images/Image/"
				// + c.getString(3);

			}
			c.close();
			db.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Log.i("Tag", "imagePaths.length=" + imagePaths.length);
		return imagePaths;

	}

	/*
	 * public static String getContentImageUrl(String title) {
	 * 
	 * String imageFiles = null;// = new ArrayList<ImagePath>(); try { String
	 * sql = "select * from DataItem where Name like '" + title + "'";
	 * SQLiteDatabase db = dbm.openDatabase(); Cursor c = db.rawQuery(sql,
	 * null); //ImagePath imagePath; while (c.moveToNext()) { //imagePath = new
	 * ImagePath(); String str= c.getString(3); final String[]
	 * strarray=str.split("\\|"); imageFiles = "/mnt/shared/ShareScdg/pms/" +
	 * title; final String imgFiles = imageFiles;
	 * 
	 * //imagePaths = new String[str.length()]; for (int i = 0; i <
	 * strarray.length; i++) { String p =
	 * "http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg"; //String
	 * p = "http://www.iwuhan12301.com/Images/Image/" + strarray[i]; final
	 * String str1 = strarray[i]; ImageRequest imageRequest = new ImageRequest(
	 * p, new Response.Listener<Bitmap>() {
	 * 
	 * @Override public void onResponse(Bitmap response) { //
	 * imageView.setImageBitmap(response); saveMyBitmap(imgFiles,str1,response);
	 * } }, 0, 0, Config.RGB_565, new Response.ErrorListener() {
	 * 
	 * @Override public void onErrorResponse(VolleyError error) {
	 * //imageView.setImageResource(R.drawable.default_image); } });
	 * mQueue.add(imageRequest); //imagePaths[i] = p; Log.i("Tag", "四级内容：：" +
	 * p); imageFiles = "/mnt/shared/ShareScdg/pms/" + title; final String
	 * imgFiles = imageFiles; final String str1 = strarray[i]; new Thread() {
	 * public void run() { try { downImage(str1,p,imgFiles); } catch (Exception
	 * e) { e.printStackTrace(); } Log.i("Tag", "s=====lcdy=====s"); }
	 * 
	 * 
	 * }.start(); downImage(strarray[i],p,imageFiles);
	 * //imagePaths.add(imagePath); } //String imageUrl =
	 * "http://www.iwuhan12301.com/Images/Image/" + c.getString(3);
	 * 
	 * 
	 * } c.close(); db.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return imageFiles;
	 * 
	 * }
	 */
	public static void saveMyBitmap(String imgPath, String name, Bitmap mBitmap) {
		File file = new File(imgPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		Log.i("Tag", "imgPath========" + imgPath);

		File f = new File(imgPath + "/" + name);
		/*
		 * try { f.createNewFile(); } catch (IOException e) { // TODO
		 * Auto-generated catch block //
		 * DebugMessage.put("在保存图片时出错："+e.toString()); }
		 */
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Uri downImage(String name, String imageUrl, String imageDir)
			throws Exception {
		try {
			File f = new File(imageDir);
			if (!f.exists()) {
				f.mkdirs();
			}
			final String cache = imageDir + "/" + name;
			// FileUtils.readConfiguration();
			final String urlnow = imageUrl;
			Log.i("Tag", "urlnow===" + urlnow + "       cache= " + cache);
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

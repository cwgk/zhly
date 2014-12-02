package com.cwgk.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GetBitmap {
	static Bitmap bitmap,defBitmap;
	public static Bitmap readBitMap(Context context, String resId)
			throws FileNotFoundException {
		defBitmap=BitmapFactory.decodeStream(context.getClass().getResourceAsStream("/res/drawable/test1.jpg"));
		try {
			// 获取资源图片
			InputStream is = new FileInputStream(resId);
			BufferedInputStream bis = new BufferedInputStream(is);

			BitmapFactory.Options opt = new BitmapFactory.Options();

			opt.inPreferredConfig = Bitmap.Config.RGB_565;
			opt.inPurgeable = true;
			//opt.inSampleSize = 2;
			opt.inTempStorage = new byte[1024 * 1024 * 5]; // 5MB的临时存储空间
			opt.inInputShareable = true;
	
			bitmap = BitmapFactory.decodeStream(bis, null, opt);
			if (bitmap != null && bitmap.isRecycled()) {
				bitmap.recycle();
				bitmap = null;
				System.gc();
			}
			
			bis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (OutOfMemoryError e) {
		    
		}
		if (bitmap == null) {
		    // 如果实例化失败 返回默认的Bitmap对象
		    return defBitmap;
		}

		return bitmap;
	}
}

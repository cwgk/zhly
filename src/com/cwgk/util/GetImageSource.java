package com.cwgk.util;

import java.io.File;
import java.util.ArrayList;


public class GetImageSource {
	static ArrayList<String> mList;
	public static ArrayList<String> imgList;
	public static ArrayList<String> getImageSource(String path) {
		ArrayList<String> imgList = new ArrayList<String>();
		File f = new File(path);
		if(f.exists()){
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (checkIsImageFile(file.getPath())) {
					imgList.add(file.getPath());
				}

			}
		}
		return imgList;
	}
	private static boolean checkIsImageFile(String fName) {
		boolean isImageFormat;
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();
		if (end.equals("jpg") ||end.equals("JPG") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			isImageFormat = true;
		} else {
			isImageFormat = false;
		}

		return isImageFormat;
	}

	public static int getImageLength(String path) {
		ArrayList<String> imgList = new ArrayList<String>();
		File f = new File(path);
		if(f.exists()){
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (checkIsImageFile(file.getPath())) {
					imgList.add(file.getPath());
				}

			}
		}
		return imgList.size();
	}
}

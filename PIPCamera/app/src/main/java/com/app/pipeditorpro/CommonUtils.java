package com.app.pipeditorpro;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;

public class CommonUtils 
{
	public static String saveToGallery(Bitmap paramBitmap, String paramString,ContentResolver paramContentResolver, 
			String type) 
	{
		String str = "";
		try {
			File localFile1 = new File(Environment.getExternalStorageDirectory().toString()+ "/"
					+ ApplicationProperties.Root_Directory_Name);
			if (!localFile1.exists()) {
				localFile1.mkdirs();
			}
			File localFile2;
			FileOutputStream localFileOutputStream;
			if (type.equals("png")) {
				localFile2 = new File(localFile1 + "/" + paramString + ".png");
				str = localFile1 + "/" + paramString + ".png";
				localFileOutputStream = new FileOutputStream(localFile2);
				paramBitmap.compress(Bitmap.CompressFormat.PNG, 100,
						localFileOutputStream);

			} else {

				localFile2 = new File(localFile1 + "/" + paramString + ".jpg");
				str = localFile1 + "/" + paramString + ".jpg";
				localFileOutputStream = new FileOutputStream(localFile2);
				paramBitmap.compress(Bitmap.CompressFormat.JPEG, 60,
						localFileOutputStream);

			}

			localFileOutputStream.flush();
			localFileOutputStream.close();

			if (type.equals("png")) {
				ContentValues localContentValues = new ContentValues();
				localContentValues.put("_display_name", paramString);
				localContentValues.put("mime_type", "image/png");
				localContentValues.put("orientation", Integer.valueOf(0));
				localContentValues.put("bucket_id", "Friendship Day Photo Frames");
				localContentValues.put("bucket_display_name", paramString);
				localContentValues.put("_data", localFile2.getAbsolutePath());
				paramContentResolver.insert(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
						localContentValues);
			}
			return str;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return str;
	}

	public static String saveToGallery(Bitmap paramBitmap, String sfe, String paramString,
			ContentResolver paramContentResolver, String type) 
	{
		String str = "";
		try {
			File localFile1 = new File(Environment.getExternalStorageDirectory().toString() + "/"
					+ ApplicationProperties.Root_Directory_Name + "/" + sfe );
			if (!localFile1.exists()) {
				localFile1.mkdirs();
			}
			File localFile2;
			FileOutputStream localFileOutputStream;
			if (type.equals("png")) {
				localFile2 = new File(localFile1 + "/" + paramString + ".png");
				str = localFile1 + "/" + paramString + ".png";
				localFileOutputStream = new FileOutputStream(localFile2);
				paramBitmap.compress(Bitmap.CompressFormat.PNG, 100,
						localFileOutputStream);

			} else {

				localFile2 = new File(localFile1 + "/" + paramString + ".jpg");
				str = localFile1 + "/" + paramString + ".jpg";
				localFileOutputStream = new FileOutputStream(localFile2);
				paramBitmap.compress(Bitmap.CompressFormat.JPEG, 60,
						localFileOutputStream);

			}

			localFileOutputStream.flush();
			localFileOutputStream.close();

			if (type.equals("png")) {
				ContentValues localContentValues = new ContentValues();
				localContentValues.put("_display_name", paramString);
				localContentValues.put("mime_type", "image/png");
				localContentValues.put("orientation", Integer.valueOf(0));
				localContentValues.put("bucket_id", "Friendship Day Photo Frames");
				localContentValues.put("bucket_display_name", paramString);
				localContentValues.put("_data", localFile2.getAbsolutePath());
				paramContentResolver.insert(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
						localContentValues);
			}
			return str;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return str;
	}
}

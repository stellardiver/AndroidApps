package com.xiaopo.flying.sticker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


class BitmapUtil {
    private static final String TAG = "BitmapUtil";

    public static String saveImageToGallery(Bitmap bmp) {
        if (bmp == null) {
            return "";
        }
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Playalot");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();

    }

    // 把文件插入到系统图库
    public static void notifySystemGallery(Context context, File file) {
        if (file == null || !file.exists()) {
            return;
        }

        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), file.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
    }
}

package com.app.pipeditorpro;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class InternalStorageContentProvider extends ContentProvider 
{
    public static final Uri content_uri;
    private static final HashMap<String, String> hashmap;
    static 
    {
        content_uri = Uri.parse("content://eu.janmuller.android.simplecropimage.example/");
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashmap = hashMap;
        hashMap.put(".jpg", "image/jpeg");
        hashmap.put(".jpeg", "image/jpeg");
    }
    @Override
    public boolean onCreate() 
    {
        try 
        {
            File file = new File(getContext().getFilesDir(), "Wedding_dress.jpg");
            if (!file.exists()) 
            {
                file.createNewFile();
                getContext().getContentResolver().notifyChange(content_uri, null);
            }
            return true;
        } catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getType(Uri uri) 
    {
        String uri2 = uri.toString();
        for (String str : hashmap.keySet()) 
        {
            if (uri2.endsWith(str)) 
            {
                return (String) hashmap.get(str);
            }
        }
        return null;
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException 
    {
        File file = new File(getContext().getFilesDir(), "Wedding_dress.jpg");
        if (file.exists()) 
        {
            return ParcelFileDescriptor.open(file, 805306368);
        }
        throw new FileNotFoundException(uri.getPath());
    }

    @Override
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}

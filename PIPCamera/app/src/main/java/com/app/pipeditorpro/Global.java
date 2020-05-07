package com.app.pipeditorpro;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.multidex.MultiDex;

import com.app.pipeditorpro.graphics.ImageProcessor;

public class Global extends Application {

    Bitmap image;
    private  byte[] textId;

    private boolean fromCamera;
    private boolean isText;

    private Uri selectedImageUri;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private int totalCount;

    private static Global mInstance;

    @Override
    protected void attachBaseContext(Context base)
    {  super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        ImageProcessor.getInstance().setApplicationCotnext(
                getApplicationContext());
        prefs = getSharedPreferences(ApplicationProperties.AppPref,0);
        editor = prefs.edit();
        totalCount = prefs.getInt("appcounter", 0);
        totalCount++;
        editor.putInt("appcounter", totalCount);
        editor.commit();

    }

    public static synchronized Global getInstance() {
        return mInstance;
    }


    public Bitmap getImage() {
        return this.image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public boolean isFromCamera() {
        return this.fromCamera;
    }

    public  byte[] getTextId() {
        return textId;
    }

    public void setTextId( byte[] textId) {
        this.textId = textId;
    }

    public boolean isText() {
        return isText;
    }

    public void setText(boolean text) {
        isText = text;
    }

    public void setFromCamera(boolean paramBoolean) {
        this.fromCamera = paramBoolean;
    }

    public void setSelectedImageUri(Uri paramUri) {
        this.selectedImageUri = paramUri;
    }

    private int color = 0xFFFF0000;
    private int position = 0;
    private String text = "";
    private int textSize = 20;
    private int x = 1417;
    private int y = 413;

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}


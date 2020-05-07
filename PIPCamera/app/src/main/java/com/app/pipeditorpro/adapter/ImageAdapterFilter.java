package com.app.pipeditorpro.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.app.pipeditorpro.R;
import com.app.pipeditorpro.graphics.CommandsPreset;

public class ImageAdapterFilter extends BaseAdapter
{
    int galleryItemBackground;
    private Context context;
    private Integer[] mImageIds = CommandsPreset.ImageIds;

    public ImageAdapterFilter(Context c) {
        context = c;
        TypedArray attr = context.obtainStyledAttributes(R.styleable.FiltersGallery);
        galleryItemBackground = attr.getResourceId(R.styleable.FiltersGallery_android_galleryItemBackground, 0);
        attr.recycle();
    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);

        imageView.setImageResource(mImageIds[position]);
        imageView.setLayoutParams(new Gallery.LayoutParams(200, 200));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundResource(galleryItemBackground);

        return imageView;
    }
}

package com.app.pipeditorpro.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.app.pipeditorpro.R;

public final class ColorAdapter extends BaseAdapter {
    public String[] f71a;
    private Context f72b;

    public ColorAdapter(Context context) {
        this.f72b = context;
        this.f71a = context.getResources().getStringArray(R.array.colorArray);
    }

    public final int getCount() {
        return this.f71a.length;
    }

    public final Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(this.f72b);
        imageView.setLayoutParams(new LayoutParams(100, 100));
        imageView.setScaleType(ScaleType.FIT_XY);
        imageView.setBackgroundColor(Color.parseColor(this.f71a[i]));
        return imageView;
    }
}

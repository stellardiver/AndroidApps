package com.app.pipeditorpro.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.app.pipeditorpro.R;

public final class ImageAdapter_Text extends BaseAdapter {
    private Context f77a;
    private Integer[] f78b;

    public ImageAdapter_Text(Context context) {

        this.f78b = new Integer[]{Integer.valueOf(R.drawable.bg_pattern_01),
                Integer.valueOf(R.drawable.bg_pattern_02),
                Integer.valueOf(R.drawable.bg_pattern_03),
                Integer.valueOf(R.drawable.bg_pattern_04),
                Integer.valueOf(R.drawable.bg_pattern_05),
                Integer.valueOf(R.drawable.bg_pattern_06),
                Integer.valueOf(R.drawable.bg_pattern_07),
                Integer.valueOf(R.drawable.bg_pattern_08),
                Integer.valueOf(R.drawable.bg_pattern_09),
                Integer.valueOf(R.drawable.bg_pattern_010),
                Integer.valueOf(R.drawable.bg_pattern_011),
                Integer.valueOf(R.drawable.bg_pattern_012),
                Integer.valueOf(R.drawable.bg_pattern_013),
                Integer.valueOf(R.drawable.bg_pattern_014),
                Integer.valueOf(R.drawable.bg_pattern_015),
                Integer.valueOf(R.drawable.bg_pattern_016)};
        this.f77a = context;
    }

    public final int getCount() {
        return this.f78b.length;
    }

    public final Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final int getItemViewType(int i) {
        return 0;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(this.f77a);
        imageView.setImageResource(this.f78b[i].intValue());
        imageView.setLayoutParams(new LayoutParams(100, 100));
        imageView.setBackgroundColor(17170447);
        imageView.setScaleType(ScaleType.FIT_XY);
        return imageView;
    }

    public final int getViewTypeCount() {
        return 0;
    }

    public final boolean hasStableIds() {
        return false;
    }

    public final boolean isEmpty() {
        return false;
    }

    public final void registerDataSetObserver(DataSetObserver dataSetObserver) {
    }

    public final void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
    }

    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}

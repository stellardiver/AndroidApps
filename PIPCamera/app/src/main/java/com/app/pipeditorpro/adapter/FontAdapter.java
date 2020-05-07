package com.app.pipeditorpro.adapter;

import android.content.Context;
import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.pipeditorpro.R;

public final class FontAdapter extends BaseAdapter {
    public static String[] f73b;
    public TextView f74a;
    Context f75c;
    public Typeface f76d;

    public FontAdapter(Context context) {
        this.f75c = context;
        f73b = context.getResources().getStringArray(R.array.FontFamily);
    }

    public final int getCount() {
        return f73b.length;
    }

    public final Object getItem(int i) {
        return null;
    }

    public final long getItemId(int i) {
        return 0;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            this.f74a = new TextView(this.f75c);
        } else {
            this.f74a = (TextView) view;
        }
        this.f76d = Typeface.createFromAsset(this.f75c.getAssets(), f73b[i]);
        this.f74a.setTypeface(this.f76d);
        this.f74a.setText("Aa");
        this.f74a.setPadding(6, 6, 6, 6);
        this.f74a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f74a.setBackgroundColor(-1);
        this.f74a.setGravity(17);
        this.f74a.setTextSize(2, 20.0f);
        this.f74a.setWidth(100);
        this.f74a.setHeight(100);
        return this.f74a;
    }
}

package com.app.pipeditorpro.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.app.pipeditorpro.Edit_creation;
import com.app.pipeditorpro.R;

public class GalleryImageAdapter  extends BaseAdapter
{
    Context mContext;
    public static int[] GalleryImagesList = new int[]
    {
            R.drawable.s_frame_default_preview,R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,
            R.drawable.b5,R.drawable.b6,R.drawable.b7,R.drawable.b8,
            R.drawable.b9,R.drawable.b10,R.drawable.b11,R.drawable.b12,
            R.drawable.b13,R.drawable.b14,R.drawable.b15,R.drawable.b16,
            R.drawable.b17,R.drawable.b18,R.drawable.b19,R.drawable.b20,
            R.drawable.b21,R.drawable.b22,R.drawable.b23,R.drawable.b24,
            R.drawable.b25,R.drawable.b26,R.drawable.b27,R.drawable.b28,
            R.drawable.b29,R.drawable.b30,R.drawable.b31,R.drawable.b32,
            R.drawable.b33,R.drawable.b34,R.drawable.b35,R.drawable.b36,
            R.drawable.b37,R.drawable.b38,
    };

    public GalleryImageAdapter(Edit_creation gallary_open) {
        this.mContext = gallary_open;
    }

    @Override
    public int getCount() {
        return GalleryImagesList.length;
    }

    @Override
    public Object getItem(int i) {
        return GalleryImagesList[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ImageView img = new ImageView(this.mContext);
        img.setImageResource(GalleryImagesList[i]);
        img.setLayoutParams(new Gallery.LayoutParams(200, 200));
        img.setPadding(5,5,5,5);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setBackground(mContext.getResources().getDrawable(R.color.color_bottom_bar));
        return img;
    }
}

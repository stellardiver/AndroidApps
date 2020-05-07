package com.app.pipeditorpro.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.app.pipeditorpro.Edit_creation;
import com.app.pipeditorpro.R;

public class GalleryFrameImageAdapter extends BaseAdapter
{
    Context mContext;
    public static int[] GalleryImagesList2 = new int[]
    {
            R.drawable.s_frame_default_preview,
            R.drawable.s_frame_0,R.drawable.s_frame_1,
            R.drawable.s_frame_2,R.drawable.s_frame_3,
            R.drawable.s_frame_4,R.drawable.s_frame_5,
            R.drawable.s_frame_6,R.drawable.s_frame_7
            ,R.drawable.s_frame_8,R.drawable.s_frame_9,
            R.drawable.s_frame_10,R.drawable.s_frame_11,
            R.drawable.s_frame_12,R.drawable.s_frame_13,
            R.drawable.s_frame_14,R.drawable.s_frame_15,
            R.drawable.s_frame_16,R.drawable.s_frame_17,
            R.drawable.s_frame_18,R.drawable.s_frame_19,
            R.drawable.s_frame_20,R.drawable.s_frame_21,
            R.drawable.s_frame_22,R.drawable.s_frame_23,
            R.drawable.s_frame_24,R.drawable.s_frame_25,
            R.drawable.s_frame_26,R.drawable.s_frame_27,
            R.drawable.s_frame_28,R.drawable.s_frame_29,
            R.drawable.border_0,R.drawable.border_1,
            R.drawable.border_2,R.drawable.border_3,
            R.drawable.border_4,R.drawable.border_5,
            R.drawable.border_6,R.drawable.border_7,
            R.drawable.border_8,R.drawable.border_9,
            R.drawable.border_10,R.drawable.border_11,
            R.drawable.border_12,R.drawable.border_13,
            R.drawable.border_14,R.drawable.border_15,
            R.drawable.border_16,R.drawable.border_17,
            R.drawable.border_18,R.drawable.border_19,
            R.drawable.border_20,R.drawable.border_21,
            R.drawable.border_22,R.drawable.border_23,
            R.drawable.border_24,R.drawable.border_25,
            R.drawable.border_26,R.drawable.border_27,
            R.drawable.border_28,R.drawable.border_29,
            R.drawable.border_30,R.drawable.border_31,
            R.drawable.border_32,R.drawable.border_33,
            R.drawable.border_34,R.drawable.border_35,
            R.drawable.border_36,R.drawable.border_37,
            R.drawable.border_38,R.drawable.border_39,
            R.drawable.border_40,R.drawable.border_41,
            R.drawable.border_42,R.drawable.border_43,
            R.drawable.border_44,R.drawable.border_45,
            R.drawable.border_46,R.drawable.border_47,
            R.drawable.border_48,R.drawable.border_49,
            R.drawable.border_50,R.drawable.border_51,
            R.drawable.border_52,R.drawable.border_53,
            R.drawable.border_54,R.drawable.border_55
    };

    public GalleryFrameImageAdapter(Edit_creation gallary_open) {
        this.mContext = gallary_open;
    }

    @Override
    public int getCount() {
        return GalleryImagesList2.length;
    }

    @Override
    public Object getItem(int i) {
        return GalleryImagesList2[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ImageView img = new ImageView(this.mContext);
        img.setImageResource(GalleryImagesList2[i]);
        img.setLayoutParams(new Gallery.LayoutParams(200, 200));
        img.setPadding(5,5,5,5);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setBackground(mContext.getResources().getDrawable(R.color.color_bottom_bar));
        return img;
    }
}

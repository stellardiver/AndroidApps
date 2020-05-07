package com.app.pipeditorpro.adapter;

import android.app.Activity;
import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.pipeditorpro.MyCreation;
import com.app.pipeditorpro.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    LayoutInflater mLayoutInflater;
    public static ArrayList<ImgItem> filepath;
    private Activity activity;

    public ViewPagerAdapter(MyCreation singleViewActivity, ArrayList<ImgItem> filePathStrings) {

            activity = singleViewActivity;
            filepath = filePathStrings;
            mLayoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.filepath.size();
    }

    public Object getItem(int position) {
        return filepath.get(position);
    }

    public long getItemId(int position) {
        return filepath.indexOf(getItem(position));
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View itemView = mLayoutInflater.inflate(R.layout.act_viewpager_item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        int padding = 0;
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER);

        Picasso.with(activity).load(new File(filepath.get(position).getAb_txt_img())).fit().into(imageView);
        container.addView(itemView);

        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

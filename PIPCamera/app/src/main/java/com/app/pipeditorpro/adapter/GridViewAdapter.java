package com.app.pipeditorpro.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.app.pipeditorpro.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
	 
	private Activity activity;
	public static ArrayList<ImgItem> filepath;

	private static LayoutInflater inflater = null;

	public GridViewAdapter(Activity a, ArrayList<ImgItem> fpath)
	{
		activity = a;
		filepath = fpath;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return filepath.size();
	}

	@Override
	public Object getItem(int position) {
		return filepath.get(position);
	}
	 
	@Override
	public long getItemId(int position) {
		return filepath.indexOf(getItem(position));
	}

	public View getView(final int position, View convertView, ViewGroup parent)
	{
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.act_gridview_item, null);

			ImageView image = (ImageView) vi.findViewById(R.id.image2);

			Picasso.with(activity).load(new File(filepath.get(position).getAb_txt_img())).fit().error(R.drawable.noimagedata).into(image);

		return vi;
	}
} 
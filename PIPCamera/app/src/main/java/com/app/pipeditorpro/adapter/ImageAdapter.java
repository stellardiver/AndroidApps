package com.app.pipeditorpro.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.app.pipeditorpro.R;

public class ImageAdapter extends BaseAdapter
{
	private static int HEIGHT_SHOW = 130;
	private static int WIDTH_SHOW = 150;
	private Context mContext;
	public static Integer[] mThumbIds;

	public ImageAdapter(Context paramContext)
	{
		Integer[] arrayOfInteger = new Integer[87];

		arrayOfInteger[0] = Integer.valueOf(R.drawable.s_frame_default_preview);
		arrayOfInteger[1] = Integer.valueOf(R.drawable.s_frame_0);
		arrayOfInteger[2] = Integer.valueOf(R.drawable.s_frame_1);
		arrayOfInteger[3] = Integer.valueOf(R.drawable.s_frame_2);
		arrayOfInteger[4] = Integer.valueOf(R.drawable.s_frame_3);
		arrayOfInteger[5] = Integer.valueOf(R.drawable.s_frame_4);
		arrayOfInteger[6] = Integer.valueOf(R.drawable.s_frame_5);
		arrayOfInteger[7] = Integer.valueOf(R.drawable.s_frame_6);
		arrayOfInteger[8] = Integer.valueOf(R.drawable.s_frame_7);
		arrayOfInteger[9] = Integer.valueOf(R.drawable.s_frame_8);
		arrayOfInteger[10] = Integer.valueOf(R.drawable.s_frame_9);
		arrayOfInteger[11] = Integer.valueOf(R.drawable.s_frame_10);
		arrayOfInteger[12] = Integer.valueOf(R.drawable.s_frame_11);
		arrayOfInteger[13] = Integer.valueOf(R.drawable.s_frame_12);
		arrayOfInteger[14] = Integer.valueOf(R.drawable.s_frame_13);
		arrayOfInteger[15] = Integer.valueOf(R.drawable.s_frame_14);
		arrayOfInteger[16] = Integer.valueOf(R.drawable.s_frame_15);
		arrayOfInteger[17] = Integer.valueOf(R.drawable.s_frame_16);
		arrayOfInteger[18] = Integer.valueOf(R.drawable.s_frame_17);
		arrayOfInteger[19] = Integer.valueOf(R.drawable.s_frame_18);
		arrayOfInteger[20] = Integer.valueOf(R.drawable.s_frame_19);
		arrayOfInteger[21] = Integer.valueOf(R.drawable.s_frame_20);
		arrayOfInteger[22] = Integer.valueOf(R.drawable.s_frame_21);
		arrayOfInteger[23] = Integer.valueOf(R.drawable.s_frame_22);
		arrayOfInteger[24] = Integer.valueOf(R.drawable.s_frame_23);
		arrayOfInteger[25] = Integer.valueOf(R.drawable.s_frame_24);
		arrayOfInteger[26] = Integer.valueOf(R.drawable.s_frame_25);
		arrayOfInteger[27] = Integer.valueOf(R.drawable.s_frame_26);
		arrayOfInteger[28] = Integer.valueOf(R.drawable.s_frame_27);
		arrayOfInteger[29] = Integer.valueOf(R.drawable.s_frame_28);
		arrayOfInteger[30] = Integer.valueOf(R.drawable.s_frame_29);
		arrayOfInteger[31] = Integer.valueOf(R.drawable.border_0);
		arrayOfInteger[32] = Integer.valueOf(R.drawable.border_1);
		arrayOfInteger[33] = Integer.valueOf(R.drawable.border_2);
		arrayOfInteger[34] = Integer.valueOf(R.drawable.border_3);
		arrayOfInteger[35] = Integer.valueOf(R.drawable.border_4);
		arrayOfInteger[36]= Integer.valueOf(R.drawable.border_5);
		arrayOfInteger[37] = Integer.valueOf(R.drawable.border_6);
		arrayOfInteger[38] = Integer.valueOf(R.drawable.border_7);
		arrayOfInteger[39] = Integer.valueOf(R.drawable.border_8);
		arrayOfInteger[40] = Integer.valueOf(R.drawable.border_9);
		arrayOfInteger[41] = Integer.valueOf(R.drawable.border_10);
		arrayOfInteger[42] = Integer.valueOf(R.drawable.border_11);
		arrayOfInteger[43] = Integer.valueOf(R.drawable.border_12);
		arrayOfInteger[44] = Integer.valueOf(R.drawable.border_13);
		arrayOfInteger[45] = Integer.valueOf(R.drawable.border_14);
		arrayOfInteger[46] = Integer.valueOf(R.drawable.border_15);
		arrayOfInteger[47] = Integer.valueOf(R.drawable.border_16);
		arrayOfInteger[48] = Integer.valueOf(R.drawable.border_17);
		arrayOfInteger[49] = Integer.valueOf(R.drawable.border_18);
		arrayOfInteger[50] = Integer.valueOf(R.drawable.border_19);
		arrayOfInteger[51] = Integer.valueOf(R.drawable.border_20);
		arrayOfInteger[52] = Integer.valueOf(R.drawable.border_21);
		arrayOfInteger[53] = Integer.valueOf(R.drawable.border_22);
		arrayOfInteger[54] = Integer.valueOf(R.drawable.border_23);
		arrayOfInteger[55] = Integer.valueOf(R.drawable.border_24);
		arrayOfInteger[56] = Integer.valueOf(R.drawable.border_25);
		arrayOfInteger[57] = Integer.valueOf(R.drawable.border_26);
		arrayOfInteger[58] = Integer.valueOf(R.drawable.border_27);
		arrayOfInteger[59] = Integer.valueOf(R.drawable.border_28);
		arrayOfInteger[60] = Integer.valueOf(R.drawable.border_29);
		arrayOfInteger[61] = Integer.valueOf(R.drawable.border_30);
		arrayOfInteger[62] = Integer.valueOf(R.drawable.border_31);
		arrayOfInteger[63] = Integer.valueOf(R.drawable.border_32);
		arrayOfInteger[64] = Integer.valueOf(R.drawable.border_33);
		arrayOfInteger[65] = Integer.valueOf(R.drawable.border_34);
		arrayOfInteger[66] = Integer.valueOf(R.drawable.border_35);
		arrayOfInteger[67] = Integer.valueOf(R.drawable.border_36);
		arrayOfInteger[68] = Integer.valueOf(R.drawable.border_37);
		arrayOfInteger[69] = Integer.valueOf(R.drawable.border_38);
		arrayOfInteger[70] = Integer.valueOf(R.drawable.border_39);
		arrayOfInteger[71] = Integer.valueOf(R.drawable.border_40);
		arrayOfInteger[72] = Integer.valueOf(R.drawable.border_41);
		arrayOfInteger[73] = Integer.valueOf(R.drawable.border_42);
		arrayOfInteger[74] = Integer.valueOf(R.drawable.border_43);
		arrayOfInteger[75] = Integer.valueOf(R.drawable.border_44);
		arrayOfInteger[76] = Integer.valueOf(R.drawable.border_45);
		arrayOfInteger[77] = Integer.valueOf(R.drawable.border_46);
		arrayOfInteger[78] = Integer.valueOf(R.drawable.border_47);
		arrayOfInteger[79] = Integer.valueOf(R.drawable.border_48);
		arrayOfInteger[80] = Integer.valueOf(R.drawable.border_49);
		arrayOfInteger[81] = Integer.valueOf(R.drawable.border_50);
		arrayOfInteger[82] = Integer.valueOf(R.drawable.border_51);
		arrayOfInteger[83] = Integer.valueOf(R.drawable.border_52);
		arrayOfInteger[84] = Integer.valueOf(R.drawable.border_53);
		arrayOfInteger[85] = Integer.valueOf(R.drawable.border_54);
		arrayOfInteger[86] = Integer.valueOf(R.drawable.border_55);

		this.mThumbIds = arrayOfInteger;
		this.mContext = paramContext;
	}

	public int getCount()
	{
		return this.mThumbIds.length;
	}

	public Object getItem(int paramInt) {
		return null;
	}

	public long getItemId(int paramInt) {
		return this.mThumbIds[paramInt].intValue();
	}

	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
	{
		if (paramView == null)
		{
			paramView = LayoutInflater.from(this.mContext).inflate(R.layout.act_frame_item, null);
		}

		ImageView localImageView = (ImageView) paramView.findViewById(R.id.frame_image);
		BitmapFactory.Options localOptions = new BitmapFactory.Options();
		localOptions.inSampleSize = 5;

		Bitmap localBitmap = BitmapFactory.decodeResource(
				this.mContext.getResources(),
				this.mThumbIds[paramInt].intValue(), localOptions);
		int j = 0;
		int i = 0;
		if (localBitmap.getHeight() > localBitmap.getWidth()) {
			j = HEIGHT_SHOW;
			i = HEIGHT_SHOW * localBitmap.getWidth() / localBitmap.getHeight();
		}
		for (;;)
		{
			i = WIDTH_SHOW;
			j = WIDTH_SHOW * localBitmap.getHeight() / localBitmap.getWidth();
			localImageView.setImageBitmap(Bitmap.createScaledBitmap(localBitmap, i, j, false));
			return paramView;

		}
	}
}

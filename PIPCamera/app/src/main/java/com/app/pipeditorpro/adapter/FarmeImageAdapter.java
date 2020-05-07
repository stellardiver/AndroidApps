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

public class FarmeImageAdapter extends BaseAdapter
{
    private static int HEIGHT_SHOW = 130;
    private static int WIDTH_SHOW = 150;
    private Context mContext;
    public static Integer[] mThumbIdsframe;

    public FarmeImageAdapter(Context paramContext)
    {
        Integer[] arrayOfIntegerframe = new Integer[87];

        arrayOfIntegerframe[0] = Integer.valueOf(R.drawable.s_frame_default_preview);
        arrayOfIntegerframe[1] = Integer.valueOf(R.drawable.s_frame_0);
        arrayOfIntegerframe[2] = Integer.valueOf(R.drawable.s_frame_1);
        arrayOfIntegerframe[3] = Integer.valueOf(R.drawable.s_frame_2);
        arrayOfIntegerframe[4] = Integer.valueOf(R.drawable.s_frame_3);
        arrayOfIntegerframe[5] = Integer.valueOf(R.drawable.s_frame_4);
        arrayOfIntegerframe[6] = Integer.valueOf(R.drawable.s_frame_5);
        arrayOfIntegerframe[7] = Integer.valueOf(R.drawable.s_frame_6);
        arrayOfIntegerframe[8] = Integer.valueOf(R.drawable.s_frame_7);
        arrayOfIntegerframe[9] = Integer.valueOf(R.drawable.s_frame_8);
        arrayOfIntegerframe[10] = Integer.valueOf(R.drawable.s_frame_9);
        arrayOfIntegerframe[11] = Integer.valueOf(R.drawable.s_frame_10);
        arrayOfIntegerframe[12] = Integer.valueOf(R.drawable.s_frame_11);
        arrayOfIntegerframe[13] = Integer.valueOf(R.drawable.s_frame_12);
        arrayOfIntegerframe[14] = Integer.valueOf(R.drawable.s_frame_13);
        arrayOfIntegerframe[15] = Integer.valueOf(R.drawable.s_frame_14);
        arrayOfIntegerframe[16] = Integer.valueOf(R.drawable.s_frame_15);
        arrayOfIntegerframe[17] = Integer.valueOf(R.drawable.s_frame_16);
        arrayOfIntegerframe[18] = Integer.valueOf(R.drawable.s_frame_17);
        arrayOfIntegerframe[19] = Integer.valueOf(R.drawable.s_frame_18);
        arrayOfIntegerframe[20] = Integer.valueOf(R.drawable.s_frame_19);
        arrayOfIntegerframe[21] = Integer.valueOf(R.drawable.s_frame_20);
        arrayOfIntegerframe[22] = Integer.valueOf(R.drawable.s_frame_21);
        arrayOfIntegerframe[23] = Integer.valueOf(R.drawable.s_frame_22);
        arrayOfIntegerframe[24] = Integer.valueOf(R.drawable.s_frame_23);
        arrayOfIntegerframe[25] = Integer.valueOf(R.drawable.s_frame_24);
        arrayOfIntegerframe[26] = Integer.valueOf(R.drawable.s_frame_25);
        arrayOfIntegerframe[27] = Integer.valueOf(R.drawable.s_frame_26);
        arrayOfIntegerframe[28] = Integer.valueOf(R.drawable.s_frame_27);
        arrayOfIntegerframe[29] = Integer.valueOf(R.drawable.s_frame_28);
        arrayOfIntegerframe[30] = Integer.valueOf(R.drawable.s_frame_29);
        arrayOfIntegerframe[31] = Integer.valueOf(R.drawable.border_0);
        arrayOfIntegerframe[32] = Integer.valueOf(R.drawable.border_1);
        arrayOfIntegerframe[33] = Integer.valueOf(R.drawable.border_2);
        arrayOfIntegerframe[34] = Integer.valueOf(R.drawable.border_3);
        arrayOfIntegerframe[35] = Integer.valueOf(R.drawable.border_4);
        arrayOfIntegerframe[36]= Integer.valueOf(R.drawable.border_5);
        arrayOfIntegerframe[37] = Integer.valueOf(R.drawable.border_6);
        arrayOfIntegerframe[38] = Integer.valueOf(R.drawable.border_7);
        arrayOfIntegerframe[39] = Integer.valueOf(R.drawable.border_8);
        arrayOfIntegerframe[40] = Integer.valueOf(R.drawable.border_9);
        arrayOfIntegerframe[41] = Integer.valueOf(R.drawable.border_10);
        arrayOfIntegerframe[42] = Integer.valueOf(R.drawable.border_11);
        arrayOfIntegerframe[43] = Integer.valueOf(R.drawable.border_12);
        arrayOfIntegerframe[44] = Integer.valueOf(R.drawable.border_13);
        arrayOfIntegerframe[45] = Integer.valueOf(R.drawable.border_14);
        arrayOfIntegerframe[46] = Integer.valueOf(R.drawable.border_15);
        arrayOfIntegerframe[47] = Integer.valueOf(R.drawable.border_16);
        arrayOfIntegerframe[48] = Integer.valueOf(R.drawable.border_17);
        arrayOfIntegerframe[49] = Integer.valueOf(R.drawable.border_18);
        arrayOfIntegerframe[50] = Integer.valueOf(R.drawable.border_19);
        arrayOfIntegerframe[51] = Integer.valueOf(R.drawable.border_20);
        arrayOfIntegerframe[52] = Integer.valueOf(R.drawable.border_21);
        arrayOfIntegerframe[53] = Integer.valueOf(R.drawable.border_22);
        arrayOfIntegerframe[54] = Integer.valueOf(R.drawable.border_23);
        arrayOfIntegerframe[55] = Integer.valueOf(R.drawable.border_24);
        arrayOfIntegerframe[56] = Integer.valueOf(R.drawable.border_25);
        arrayOfIntegerframe[57] = Integer.valueOf(R.drawable.border_26);
        arrayOfIntegerframe[58] = Integer.valueOf(R.drawable.border_27);
        arrayOfIntegerframe[59] = Integer.valueOf(R.drawable.border_28);
        arrayOfIntegerframe[60] = Integer.valueOf(R.drawable.border_29);
        arrayOfIntegerframe[61] = Integer.valueOf(R.drawable.border_30);
        arrayOfIntegerframe[62] = Integer.valueOf(R.drawable.border_31);
        arrayOfIntegerframe[63] = Integer.valueOf(R.drawable.border_32);
        arrayOfIntegerframe[64] = Integer.valueOf(R.drawable.border_33);
        arrayOfIntegerframe[65] = Integer.valueOf(R.drawable.border_34);
        arrayOfIntegerframe[66] = Integer.valueOf(R.drawable.border_35);
        arrayOfIntegerframe[67] = Integer.valueOf(R.drawable.border_36);
        arrayOfIntegerframe[68] = Integer.valueOf(R.drawable.border_37);
        arrayOfIntegerframe[69] = Integer.valueOf(R.drawable.border_38);
        arrayOfIntegerframe[70] = Integer.valueOf(R.drawable.border_39);
        arrayOfIntegerframe[71] = Integer.valueOf(R.drawable.border_40);
        arrayOfIntegerframe[72] = Integer.valueOf(R.drawable.border_41);
        arrayOfIntegerframe[73] = Integer.valueOf(R.drawable.border_42);
        arrayOfIntegerframe[74] = Integer.valueOf(R.drawable.border_43);
        arrayOfIntegerframe[75] = Integer.valueOf(R.drawable.border_44);
        arrayOfIntegerframe[76] = Integer.valueOf(R.drawable.border_45);
        arrayOfIntegerframe[77] = Integer.valueOf(R.drawable.border_46);
        arrayOfIntegerframe[78] = Integer.valueOf(R.drawable.border_47);
        arrayOfIntegerframe[79] = Integer.valueOf(R.drawable.border_48);
        arrayOfIntegerframe[80] = Integer.valueOf(R.drawable.border_49);
        arrayOfIntegerframe[81] = Integer.valueOf(R.drawable.border_50);
        arrayOfIntegerframe[82] = Integer.valueOf(R.drawable.border_51);
        arrayOfIntegerframe[83] = Integer.valueOf(R.drawable.border_52);
        arrayOfIntegerframe[84] = Integer.valueOf(R.drawable.border_53);
        arrayOfIntegerframe[85] = Integer.valueOf(R.drawable.border_54);
        arrayOfIntegerframe[86] = Integer.valueOf(R.drawable.border_55);

        this.mThumbIdsframe = arrayOfIntegerframe;

        this.mContext = paramContext;
    }

    public int getCount()
    {
        return this.mThumbIdsframe.length;
    }

    public Object getItem(int paramInt) {
        return null;
    }

    public long getItemId(int paramInt) {
        return this.mThumbIdsframe[paramInt].intValue();
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        if (paramView == null) {
            paramView = LayoutInflater.from(this.mContext).inflate(
                    R.layout.act_frame_item, null);
        }
        ImageView localImageView = (ImageView) paramView
                .findViewById(R.id.frame_image);
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inSampleSize = 5;
        Bitmap localBitmap = BitmapFactory.decodeResource(
                this.mContext.getResources(),
                this.mThumbIdsframe[paramInt].intValue(), localOptions);
        int j = 0;
        int i = 0;
        if (localBitmap.getHeight() > localBitmap.getWidth()) {
            j = HEIGHT_SHOW;
            i = HEIGHT_SHOW * localBitmap.getWidth() / localBitmap.getHeight();
        }
        for (;;) {
            i = WIDTH_SHOW;
            j = WIDTH_SHOW * localBitmap.getHeight() / localBitmap.getWidth();
            localImageView.setImageBitmap(Bitmap.createScaledBitmap(
                    localBitmap, i, j, false));
            return paramView;

        }
    }
}

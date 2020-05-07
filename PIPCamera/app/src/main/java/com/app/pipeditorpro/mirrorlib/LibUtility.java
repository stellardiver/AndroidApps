package com.app.pipeditorpro.mirrorlib;


import android.os.Debug;

import com.app.pipeditorpro.R;

public class LibUtility
{
    public static int MODE_MULTIPLY = 1;
    public static int MODE_NONE = -1;
    public static int MODE_OVERLAY = 2;
    public static int MODE_SCREEN = 3;
    private static final String TAG = LibUtility.class.getSimpleName();

    public static int[] borderRes = new int[]
            {
                    MODE_NONE,
                    R.drawable.s_frame_0,
                    R.drawable.s_frame_1,
                    R.drawable.s_frame_2,
                    R.drawable.s_frame_3,
                    R.drawable.s_frame_4,
                    R.drawable.s_frame_5,
                    R.drawable.s_frame_6,
                    R.drawable.s_frame_7,
                    R.drawable.s_frame_8,
                    R.drawable.s_frame_9,
                    R.drawable.s_frame_10,
                    R.drawable.s_frame_11,
                    R.drawable.s_frame_12,
                    R.drawable.s_frame_13,
                    R.drawable.s_frame_14,
                    R.drawable.s_frame_15,
                    R.drawable.s_frame_16,
                    R.drawable.s_frame_17,
                    R.drawable.s_frame_18,
                    R.drawable.s_frame_19,
                    R.drawable.s_frame_20,
                    R.drawable.s_frame_21,
                    R.drawable.s_frame_22,
                    R.drawable.s_frame_23,
                    R.drawable.s_frame_24,
                    R.drawable.s_frame_25,
                    R.drawable.s_frame_26,
                    R.drawable.s_frame_27,
                    R.drawable.s_frame_28,
                    R.drawable.s_frame_29,
                    R.drawable.border_0,
                    R.drawable.border_1,
                    R.drawable.border_2,
                    R.drawable.border_3,
                    R.drawable.border_4,
                    R.drawable.border_5,
                    R.drawable.border_6,
                    R.drawable.border_7,
                    R.drawable.border_8,
                    R.drawable.border_9,
                    R.drawable.border_10,
                    R.drawable.border_11,
                    R.drawable.border_12,
                    R.drawable.border_13,
                    R.drawable.border_14,
                    R.drawable.border_15,
                    R.drawable.border_16,
                    R.drawable.border_17,
                    R.drawable.border_18,
                    R.drawable.border_19,
                    R.drawable.border_20,
                    R.drawable.border_21,
                    R.drawable.border_22,
                    R.drawable.border_23,
                    R.drawable.border_24,
                    R.drawable.border_25,
                    R.drawable.border_26,
                    R.drawable.border_27,
                    R.drawable.border_28,
                    R.drawable.border_29,
                    R.drawable.border_30,
                    R.drawable.border_31,
                    R.drawable.border_32,
                    R.drawable.border_33,
                    R.drawable.border_34,
                    R.drawable.border_35,
                    R.drawable.border_36,
                    R.drawable.border_37,
                    R.drawable.border_38,
                    R.drawable.border_39,
                    R.drawable.border_40,
                    R.drawable.border_41,
                    R.drawable.border_42,
                    R.drawable.border_43,
                    R.drawable.border_44,
                    R.drawable.border_45,
                    R.drawable.border_46,
                    R.drawable.border_47,
                    R.drawable.border_48,
                    R.drawable.border_49,
                    R.drawable.border_50,
                    R.drawable.border_51,
                    R.drawable.border_52,
                    R.drawable.border_53,
                    R.drawable.border_54,
                    R.drawable.border_55,};

    public interface BuyProVersion {
        void proVersionCalled();
    }

    public interface ExcludeTabListener {
        void exclude();
    }

    public interface FooterVisibilityListener {
        void setVisibility();
    }

    public static double getLeftSizeOfMemory() {
        double totalSize = Double.valueOf((double) Runtime.getRuntime().maxMemory()).doubleValue();
        double heapAllocated = Double.valueOf((double) Runtime.getRuntime().totalMemory()).doubleValue();
        return (totalSize - (heapAllocated - Double.valueOf((double) Runtime.getRuntime().freeMemory()).doubleValue())) - Double.valueOf((double) Debug.getNativeHeapAllocatedSize()).doubleValue();
    }

}

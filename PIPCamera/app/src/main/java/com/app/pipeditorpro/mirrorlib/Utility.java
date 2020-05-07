package com.app.pipeditorpro.mirrorlib;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Environment;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

public class Utility {
    public static float DEFAULT_HUE_VAL;

    static {
        DEFAULT_HUE_VAL = 120.0f;
    }

    public static boolean getAmazonMarket(Context context) {
        int AMAZON_MARKET = 0;
        try {
            AMAZON_MARKET = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt("amazon_market");
            if (AMAZON_MARKET < 0) {
                AMAZON_MARKET = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (AMAZON_MARKET == 1) {
            return true;
        }
        return false;
    }

    public static boolean isSDCardAvialable() {
        String state = Environment.getExternalStorageState();
        if ("mounted".equals(state)) {
            boolean mExternalStorageAvailable = true;
            return true;
        } else if ("mounted_ro".equals(state)) {
            return false;
        } else {
            int mExternalStorageAvailable2 = 0;
            return false;
        }
    }

    public static boolean isPackageProEx(Context context) {
        return context.getPackageName().toLowerCase().contains("pro");
    }

    public static long getFreeMemoryEx(Context context) {
        int heapMemory = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass() * AccessibilityNodeInfoCompat.ACTION_DISMISS;
        return (((long) heapMemory) - Debug.getNativeHeapAllocatedSize()) - Runtime.getRuntime().totalMemory();
    }

    public static double getLeftSizeOfMemory() {
        double totalSize = Double.valueOf((double) Runtime.getRuntime().maxMemory()).doubleValue();
        double heapAllocated = Double.valueOf((double) Runtime.getRuntime().totalMemory()).doubleValue();
        double nativeAllocated = Double.valueOf((double) Debug.getNativeHeapAllocatedSize()).doubleValue();
        double usedMemory = heapAllocated - Double.valueOf((double) Runtime.getRuntime().freeMemory()).doubleValue();
        return (totalSize - usedMemory) - nativeAllocated;
    }

    public static void pssFreeMemory() {
        MemoryInfo memoryInfo = new MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        String memMessage = String.format("Memory: Pss=%.2f MB, Private=%.2f MB, Shared=%.2f MB", new Object[]{Double.valueOf(((double) memoryInfo.getTotalPss()) / 1024.0d), Double.valueOf(((double) memoryInfo.getTotalPrivateDirty()) / 1024.0d), Double.valueOf(((double) memoryInfo.getTotalSharedDirty()) / 1024.0d)});
    }

    public static int maxSizeForLoad() {
        int maxSize = (int) Math.sqrt(getLeftSizeOfMemory() / 80.0d);
        if (maxSize > 1080) {
            return 1080;
        }
        return maxSize;
    }

    public static int maxSizeForSave() {
        int maxSize = (int) Math.sqrt(getLeftSizeOfMemory() / 40.0d);

        if (maxSize > 1080) {
            return 1080;
        }
        return maxSize;
    }

    public static void logFreeMemory() {
    }

    public static long getFreeMemoryEx() {
        return Runtime.getRuntime().maxMemory() - Debug.getNativeHeapAllocatedSize();
    }
}

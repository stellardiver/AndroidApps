package com.app.pipeditorpro;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Utils {

    Long fixtime = 86400000L;
    Context mcontext;
    SharedPreferences myPr;
    String pref_name = "myprefadmob";
    Long intervalTIme =1200000L;


    public Utils(Context context) {
        mcontext = context;
        myPr = context.getSharedPreferences(pref_name, MODE_PRIVATE);
    }

    public String fId() {
        if (checkTimeI()) {
            return myPr.getString("Full_Ad_Id",mcontext.getResources().getString(R.string.FullAdId));
        } else {
            return null;
        }
    }

    public String bId() {
        return myPr.getString("Banner_Ad_Id",mcontext.getResources().getString(R.string.BannerAdId));
    }

    public String fbadId() {
        if (checkTimeIf()) {
            return myPr.getString("FBFull_Ad_Id", mcontext.getResources().getString(R.string.FBFullAdId));
        } else {
            return null;
        }
    }

    public String fbbanneradId() {
        return myPr.getString("FBBanner_Ad_Id",mcontext.getResources().getString(R.string.FBBannerAdId));
    }

    //region interstital google
    public boolean checkTimeI() {

        long cget = myPr.getLong("last_clkTime", 0);
        long time = System.currentTimeMillis();

        long diff = (time - cget);
        if (diff == 0 || diff > fixtime) {
            SharedPreferences.Editor editor = myPr.edit();
            editor.putLong("last_clkTime",0);
            editor.apply();
            return true;

        } else
            return false;

    }

    public void setLastTime() {
        long time = System.currentTimeMillis();

        if (myPr.getLong("last_clkTimeFirst", 0) > 0) {
            SharedPreferences.Editor editor1 = myPr.edit();
            editor1.putLong("last_clkTimeSecond", time);
            editor1.commit();

            long cgetF = myPr.getLong("last_clkTimeFirst", 0);

            long cgetS = myPr.getLong("last_clkTimeSecond", 0);

            long intervalTimeDiff = cgetS - cgetF;

            if (intervalTimeDiff < intervalTIme) {
                long timeC = System.currentTimeMillis();

                SharedPreferences.Editor editor = myPr.edit();
                editor.putLong("last_clkTime", timeC);
                editor.putLong("last_clkTimeSecond", 0);
                editor.putLong("last_clkTimeFirst",0);
                editor.apply();
            }
            else{
                SharedPreferences.Editor editor = myPr.edit();
                editor.putLong("last_clkTimeSecond", 0);
                editor.putLong("last_clkTimeFirst", System.currentTimeMillis());
                editor.apply();
            }
        } else {
            SharedPreferences.Editor editor = myPr.edit();
            editor.putLong("last_clkTimeFirst", time);
            editor.apply();
        }

    }
    //endregion

    //region interstital fb
    public boolean checkTimeIf() {

        long cget = myPr.getLong("last_clkTimef", 0);
        long time = System.currentTimeMillis();

        long diff = (time - cget);
        if (diff == 0 || diff > fixtime) {
            SharedPreferences.Editor editor = myPr.edit();
            editor.putLong("last_clkTimef", 0);
            editor.apply();
            return true;

        } else
            return false;

    }

    public void setLastTimef() {
        long time = System.currentTimeMillis();

        if (myPr.getLong("last_clkTimefFirst", 0) > 0) {
            SharedPreferences.Editor editor1 = myPr.edit();
            editor1.putLong("last_clkTimefSecond", time);
            editor1.commit();

            long cgetF = myPr.getLong("last_clkTimefFirst", 0);

            long cgetS = myPr.getLong("last_clkTimefSecond", 0);

            long intervalTimeDiff = cgetS - cgetF;

            if (intervalTimeDiff < intervalTIme) {
                long timeC = System.currentTimeMillis();
                SharedPreferences.Editor editor = myPr.edit();
                editor.putLong("last_clkTimef", timeC);
                editor.putLong("last_clkTimefSecond", 0);
                editor.putLong("last_clkTimefFirst",0);
                editor.apply();
            }
            else{
                SharedPreferences.Editor editor = myPr.edit();
                editor.putLong("last_clkTimefSecond", 0);
                editor.putLong("last_clkTimefFirst", System.currentTimeMillis());
                editor.apply();
            }
        } else {
            SharedPreferences.Editor editor = myPr.edit();
            editor.putLong("last_clkTimefFirst", time);
            editor.apply();
        }

    }
    //endregion

    //region google banner
    public boolean checkTimeB() {

        long cget =myPr.getLong("last_clkTimeB", 0);
        long time = System.currentTimeMillis();
        long diff = (time - cget);
        if (diff == 0 || diff > fixtime) {
            SharedPreferences.Editor editor = myPr.edit();
            editor.putLong("last_clkTimeB", 0);
            editor.apply();
            return true;

        } else {

            return false;
        }
    }

    public void setLastTimeB() {
        long time = System.currentTimeMillis();

        if (myPr.getLong("last_clkTimeBFirst", 0) > 0) {
            SharedPreferences.Editor editor1 = myPr.edit();
            editor1.putLong("last_clkTimeBSecond", time);
            editor1.commit();

            long cgetF = myPr.getLong("last_clkTimeBFirst", 0);

            long cgetS = myPr.getLong("last_clkTimeBSecond", 0);

            long intervalTimeDiff = cgetS - cgetF;

            if (intervalTimeDiff < intervalTIme) {
                long timeC = System.currentTimeMillis();
                SharedPreferences.Editor editor = myPr.edit();
                editor.putLong("last_clkTimeB", timeC);
                editor.putLong("last_clkTimeBSecond", 0);
                editor.putLong("last_clkTimeBFirst",0);
                editor.apply();
            }
            else{
                SharedPreferences.Editor editor = myPr.edit();
                editor.putLong("last_clkTimeBSecond", 0);
                editor.putLong("last_clkTimeBFirst", System.currentTimeMillis());
                editor.apply();
            }
        } else {
            SharedPreferences.Editor editor = myPr.edit();
            editor.putLong("last_clkTimeBFirst", time);
            editor.apply();
        }
    }
    //endregion

    //region Fb banner
    public boolean checkTimeBf() {

        long cget =  myPr.getLong("last_clkTimeBf", 0);
        long time = System.currentTimeMillis();

        long diff = (time - cget);
        if (diff == 0 || diff > fixtime) {
            SharedPreferences.Editor editor = myPr.edit();
            editor.putLong("last_clkTimeBf",0);
            editor.apply();
            return true;

        } else
            return false;

    }

    public void setLastTimeBf() {
        long time = System.currentTimeMillis();

        if (myPr.getLong("last_clkTimeBfFirst", 0) > 0) {
            SharedPreferences.Editor editor1 = myPr.edit();
            editor1.putLong("last_clkTimeBfSecond", time);
            editor1.commit();

            long cgetF = myPr.getLong("last_clkTimeBfFirst", 0);

            long cgetS = myPr.getLong("last_clkTimeBfSecond", 0);

            long intervalTimeDiff = cgetS - cgetF;

            if (intervalTimeDiff < intervalTIme) {
                long timeC = System.currentTimeMillis();
                SharedPreferences.Editor editor = myPr.edit();
                editor.putLong("last_clkTimeBf", timeC);
                editor.putLong("last_clkTimeBfSecond", 0);
                editor.putLong("last_clkTimeBfFirst",0);
                editor.apply();
            }
            else{
                SharedPreferences.Editor editor = myPr.edit();
                editor.putLong("last_clkTimeBfSecond", 0);
                editor.putLong("last_clkTimeBfFirst", System.currentTimeMillis());
                editor.apply();
            }
        } else {
            SharedPreferences.Editor editor = myPr.edit();
            editor.putLong("last_clkTimeBfFirst", time);
            editor.apply();
        }
    }
    //endregion
}

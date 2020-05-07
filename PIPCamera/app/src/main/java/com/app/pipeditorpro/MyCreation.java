package com.app.pipeditorpro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.io.File;
import java.util.ArrayList;

import android.app.Dialog;
import android.view.Window;
import android.widget.RelativeLayout;

import com.app.pipeditorpro.adapter.GridViewAdapter;
import com.app.pipeditorpro.adapter.ImgItem;
import com.app.pipeditorpro.adapter.ViewPagerAdapter;
import com.app.pipeditorpro.transformer.ZoomOutTransformer;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import static com.app.pipeditorpro.adapter.ViewPagerAdapter.filepath;

public class MyCreation extends Activity {
    private ArrayList<ImgItem> FilePathStrings;
    private File[] listFile;
    GridView grid;
    ViewPager pager;
    GridViewAdapter adapter;
    ViewPagerAdapter adapter2;
    File file;
    LinearLayout emptycontent;
    String value;
    ImageView creation_back;
    int currentPage = 0;
    Handler h = new Handler();
    boolean slide_show = false;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "myprefadmob";
    ConnectionDetector connectionDetector;
    RelativeLayout adLAyout;
    LinearLayout adContainer;
    AdView mAdView;
    private InterstitialAd interstitial;
    private LinearLayout bannerContainer;
    private com.facebook.ads.AdView bannerAdView;
    private com.facebook.ads.InterstitialAd interstitialAdFB;
    private ImageView ImageOverlayadview;

    int displayad;    // 1= google, 2=fb ,3=both
    int whichAdFirst;// 1 =google,2=fb
    private static final String TAG = "FBDEMO";

    int whichActivitytoStart = 0;
    boolean isActivityLeft;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_my_creation);

        sharedpreferences = getSharedPreferences(mypreference, MODE_PRIVATE);
        isActivityLeft = false;
        activity = MyCreation.this;

        displayad = sharedpreferences.getInt("displayad", 2);
        whichAdFirst = sharedpreferences.getInt("whichAdFirst", 2);
        adLAyout = (RelativeLayout) findViewById(R.id.adLAyout);
        adContainer = (LinearLayout) findViewById(R.id.adView);
        bannerContainer = (LinearLayout) findViewById(R.id.banner_container);
        ImageOverlayadview = (ImageView) findViewById(R.id.Image_overlayadview);
        ImageOverlayadview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //region banner
        connectionDetector = new ConnectionDetector(getApplicationContext());
        boolean isInternetPresent = connectionDetector.isConnectingToInternet();

        if (isInternetPresent) {
            if (displayad == 1) {
                showHideG();
                if (new Utils(activity).fId() != null) {
                    loadAndDisplayInterstial();
                } else if (new Utils(activity).fbadId() != null) {
                    loadInterstitialFB();
                }
            } else if (displayad == 2) {
                showHideF();
                if (new Utils(activity).fbadId() != null) {
                    loadInterstitialFB();
                } else if (new Utils(activity).fId() != null) {
                    loadAndDisplayInterstial();
                }
            } else {

                showHideG();
                showHideF();
                if (new Utils(activity).fId() != null) {
                    loadAndDisplayInterstial();
                }
                if (new Utils(activity).fbadId() != null) {
                    loadInterstitialFB();
                }
            }
        } else {
            adLAyout.setVisibility(View.GONE);
        }
        //endregion

        FilePathStrings = new ArrayList<ImgItem>();
        value =getResources().getString(R.string.app_name);

        emptycontent = (LinearLayout) findViewById(R.id.emptycontent);
        grid = (GridView) findViewById(R.id.gridview);
        pager = (ViewPager) findViewById(R.id.pager);
        creation_back = (ImageView)findViewById(R.id.creation_back);
        creation_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whichActivitytoStart = 1;
                showInterstitial();
            }
        });
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                showdialog(position);
            }
        });
    }

    private void showdialog(int position) {
        final int pos = position;
        final Dialog dialog = new Dialog(MyCreation.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.act_dialog_griditem);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;

       dialog.getWindow().setAttributes(lp);

        ImageView image_main_classics = (ImageView)dialog.findViewById(R.id.image_main_classics);
        ImageView btn_classic_share = (ImageView)dialog.findViewById(R.id.btn_classic_share);
        ImageView btn_classic_delete = (ImageView)dialog.findViewById(R.id.btn_classic_delete);
        ImageView btn_classic_cancle = (ImageView)dialog.findViewById(R.id.btn_classic_cancle);

        image_main_classics.setImageURI(Uri.parse(listFile[pos].getAbsolutePath()));
        btn_classic_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/png");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                File imageFileToShare = new File(filepath.get(pos).getAb_txt_img());
                Uri apkURI = FileProvider.getUriForFile(MyCreation.this, getApplicationContext()
                        .getPackageName() + ".provider", imageFileToShare);
                shareIntent.putExtra(Intent.EXTRA_STREAM, apkURI);
                startActivity(Intent.createChooser(shareIntent, "Share Image!"));
                dialog.dismiss();
            }
        });
        btn_classic_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();
                try
                {
                    final AlertDialog.Builder localBuilder = new AlertDialog.Builder(MyCreation.this);
                    localBuilder.setTitle("Confirm delete")
                            .setMessage("Are you sure you want to delete this photo?")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface paramAnonymous2DialogInterface,	int paramAnonymous2Int) {
                                    File vval =  new File(filepath.get(pos).getAb_txt_img());
                                    vval.delete();
                                    currentPage=0;
                                    if(file.listFiles().length >= 1){
                                        refreshList();
                                    }else {
                                        whichActivitytoStart = 1;
                                        showInterstitial();
                                    }

                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface paramAnonymous2DialogInterface,	int paramAnonymous2Int)
                                {
                                    paramAnonymous2DialogInterface.cancel();
                                    dialog.dismiss();
                                }
                            });
                    localBuilder.create().show();
                }
                catch (Exception localException)
                {
                    localException.printStackTrace();
                }
            }
        });
        btn_classic_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void refreshList() {
        FilePathStrings.clear();

        file = new File(Environment.getExternalStorageDirectory() + "/"
                + ApplicationProperties.Root_Directory_Name + "/"
                + value);
        if (file.isDirectory()) {

            listFile = file.listFiles();
            for (int i = 0; i < listFile.length; i++) {

                String nm = listFile[i].getAbsolutePath();
                ImgItem country = new ImgItem();

                country.setAb_txt_img(nm);
                FilePathStrings.add(country);
            }

            adapter = new GridViewAdapter(MyCreation.this, FilePathStrings);
                grid.setEmptyView(findViewById(R.id.emptycontent));
                grid.setAdapter(adapter);

                adapter2 = new ViewPagerAdapter(MyCreation.this, FilePathStrings);
                pager.setAdapter(adapter2);
                pager.setPageTransformer(true,new ZoomOutTransformer());
                if(slide_show == false){
                    autoslidepager();
                }
        }
        else
        {
            grid.setEmptyView(findViewById(R.id.emptycontent));

        }
    }

    private void autoslidepager() {
        slide_show = true;
        currentPage = 0;
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentPage == listFile.length)
                {
                    currentPage = 0;
                    if(file.listFiles().length >= 1) {
                        refreshList();
                    }
                }
                pager.setCurrentItem(currentPage++, true);
                h.postDelayed(this, 2000);
            }
        }, 2000);

    }

    @Override
    public void onBackPressed() {
        whichActivitytoStart = 1;
        showInterstitial();
    }

    void showHideG() {
        if (new Utils(activity).bId() != null) {
            if (bannerAdView != null) {
                bannerAdView.destroy();
                bannerAdView = null;
            }

            addBannerLoding();


            bannerContainer.setVisibility(View.GONE);
            adContainer.setVisibility(View.VISIBLE);
            checkDisplayOverlayBannerG();
        } else if (new Utils(activity).fbadId() != null) {
            if (mAdView != null) {
                mAdView.destroy();
                mAdView = null;
            }

            addBannerLodingFB();
            adContainer.setVisibility(View.GONE);
            bannerContainer.setVisibility(View.VISIBLE);
            checkDisplayOverlayBannerFB();
        } else {
            adContainer.setVisibility(View.GONE);
            bannerContainer.setVisibility(View.GONE);
            adLAyout.setVisibility(View.GONE);
        }
    }

    void checkDisplayOverlayBannerG() {
        if (new Utils(activity).checkTimeB())
            ImageOverlayadview.setVisibility(View.GONE);
        else
            ImageOverlayadview.setVisibility(View.VISIBLE);
    }

    void checkDisplayOverlayBannerFB() {
        if (new Utils(activity).checkTimeBf())
            ImageOverlayadview.setVisibility(View.GONE);
        else
            ImageOverlayadview.setVisibility(View.VISIBLE);
    }

    void showHideF() {
        if (new Utils(activity).fbbanneradId() != null) {
            if (mAdView != null) {
                mAdView.destroy();
            }
            checkDisplayOverlayBannerFB();
            addBannerLodingFB();
            adContainer.setVisibility(View.GONE);
            bannerContainer.setVisibility(View.VISIBLE);

        } else if (new Utils(activity).bId() != null) {
            if (bannerAdView != null) {
                bannerAdView.destroy();
                bannerAdView = null;
            }
            checkDisplayOverlayBannerG();
            addBannerLoding();

            bannerContainer.setVisibility(View.GONE);
            adContainer.setVisibility(View.VISIBLE);
        } else {
            adContainer.setVisibility(View.GONE);
            bannerContainer.setVisibility(View.GONE);
            adLAyout.setVisibility(View.GONE);
        }
    }

    private void addBannerLodingFB() {

        if (bannerAdView != null) {
            bannerAdView.destroy();
            bannerAdView = null;
        }
        if (bannerContainer.getChildCount() > 0)
            bannerContainer.removeAllViews();

        bannerAdView = new com.facebook.ads.AdView(activity, new Utils(activity).fbbanneradId(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        AdSettings.addTestDevice("fa3be27b-20b9-4d8d-9bf1-c9724a55c6fb");
        bannerContainer.addView(bannerAdView);


        bannerAdView.setAdListener(new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError error) {
                if (ad == bannerAdView) {
                }

                showHideG();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (ad == bannerAdView) {
                }
            }

            @Override
            public void onAdClicked(Ad ad) {

                ImageOverlayadview.setVisibility(View.VISIBLE);
                new Utils(activity).setLastTimeBf();
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });

        bannerAdView.loadAd();
    }

    public void addBannerLoding() {
        if (adContainer.getChildCount() > 0)
            adContainer.removeAllViews();

        mAdView = new AdView(activity);

        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(new Utils(activity).bId());
        adContainer.addView(mAdView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("6EE36877D3E7CA526E12D0D16E87D51D").build();

        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                showHideF();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                ImageOverlayadview.setVisibility(View.VISIBLE);
                new Utils(activity).setLastTimeB();
            }
        });

    }

    public void showInterstitial() {

        if (whichAdFirst == 1) {
            if (interstitial != null && interstitial.isLoaded() && !isActivityLeft) {
                interstitial.show();
            } else if (interstitialAdFB != null && interstitialAdFB.isAdLoaded() && !isActivityLeft) {
                interstitialAdFB.show();
            } else {
                replaceScreen();
            }
        } else if (whichAdFirst == 2) {
            if (interstitial != null && interstitial.isLoaded() && !isActivityLeft) {
                interstitial.show();
            } else if (interstitialAdFB != null && interstitialAdFB.isAdLoaded() && !isActivityLeft) {
                interstitialAdFB.show();
            } else {
                replaceScreen();
            }
        } else {
            if (interstitialAdFB != null && interstitialAdFB.isAdLoaded() && !isActivityLeft) {
                interstitialAdFB.show();
            } else if (interstitial != null && interstitial.isLoaded() && !isActivityLeft) {
                interstitial.show();
            } else {
                replaceScreen();
            }
        }
    }

    private void loadAndDisplayInterstial() {

        interstitial = new InterstitialAd(activity);
        interstitial.setAdUnitId(new Utils(activity).fId());

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("6EE36877D3E7CA526E12D0D16E87D51D").build();

        interstitial.loadAd(adRequest);

        interstitial.setAdListener(new AdListener() {

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                loadInterstitialFB();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                replaceScreen();
                if (new Utils(activity).fId() != null) {
                    loadAndDisplayInterstial();
                }
            }

            @Override
            public void onAdLeftApplication() {
                new Utils(activity).setLastTime();

                if (interstitial != null) {

                }
            }
        });
    }

    void loadInterstitialFB() {
        interstitialAdFB = new com.facebook.ads.InterstitialAd(activity, new Utils(activity).fbadId());
        AdSettings.addTestDevice("fa3be27b-20b9-4d8d-9bf1-c9724a55c6fb");
        interstitialAdFB.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                replaceScreen();
                if (new Utils(activity).fbadId() != null) {
                    loadInterstitialFB();
                }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                loadInterstitialFB();
            }

            @Override
            public void onAdLoaded(Ad ad) {
            }

            @Override
            public void onAdClicked(Ad ad) {
                new Utils(activity).setLastTimef();
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        });

        interstitialAdFB.loadAd();
    }

    public void onPause() {
        super.onPause();
        this.isActivityLeft = true;
    }

    public void onResume() {
        super.onResume();
        refreshList();
        this.isActivityLeft = false;
    }

    protected void onStop() {
        super.onStop();
        this.isActivityLeft = true;
    }

    protected void onDestroy() {
        super.onDestroy();
        this.isActivityLeft = true;
        if (bannerAdView != null) {
            bannerAdView.destroy();
            bannerAdView = null;
        }
    }

    private void replaceScreen() {

        if (whichActivitytoStart == 1) {
            startActivity(new Intent(MyCreation.this, Act_Home.class));
            finishAffinity();
        }
    }

}
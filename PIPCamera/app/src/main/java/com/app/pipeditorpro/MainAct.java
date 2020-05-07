package com.app.pipeditorpro;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainAct extends Activity implements View.OnClickListener {

    ImageView btn_main_camera;
    ImageView btn_main_gallery;

    private File con_file;
    private Intent intent;
    Global mGlobal;
    private Bitmap m_bitmap1;

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
        setContentView(R.layout.act_main);

        sharedpreferences = getSharedPreferences(mypreference, MODE_PRIVATE);
        isActivityLeft = false;
        activity = MainAct.this;

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
        }else {
            adLAyout.setVisibility(View.GONE);
        }
        //endregion

        btn_main_camera= (ImageView)findViewById(R.id.btn_main_camera);
        btn_main_gallery= (ImageView)findViewById(R.id.btn_main_gallery);
        mGlobal = (Global) getApplication();
        btn_main_camera.setOnClickListener(this);
        btn_main_gallery.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_camera:
                whichActivitytoStart = 1;
                showInterstitial();
                break;

            case R.id.btn_main_gallery:
                whichActivitytoStart = 2;
                showInterstitial();
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1)
        {
            Intent intent2;
            switch (i)
            {
                case 1 :
                    try
                    {
                        InputStream openInputStream = getContentResolver().openInputStream(intent.getData());
                        OutputStream fileOutputStream = new FileOutputStream(con_file);
                        open_filestream(openInputStream, fileOutputStream);
                        fileOutputStream.close();
                        openInputStream.close();
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                         m_bitmap1 = BitmapFactory.decodeFile(con_file.getAbsolutePath(), options);
                        mGlobal.setImage(Bitmap.createScaledBitmap(this.m_bitmap1, 1000, 1000, false));
                        intent2 = new Intent(getBaseContext(), WorkingAct.class);
                        intent2.putExtra("ImageUri", con_file.getAbsolutePath());
                        startActivity(intent2);
                        break;
                    }
                    catch (Throwable e)
                    {
                        break;
                    }
                case 2 :
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    m_bitmap1 = BitmapFactory.decodeFile(con_file.getAbsolutePath(), options);
                    mGlobal.setImage(this.m_bitmap1);
                    intent2 = new Intent(getBaseContext(), WorkingAct.class);
                    intent2.putExtra("ImageUri", con_file.getAbsolutePath());
                    startActivity(intent2);
                    break;
            }
            super.onActivityResult(i, i2, intent);
        }
    }

    private void file_environment() {
        String file = Environment.getExternalStorageDirectory().toString();
        new File(new StringBuilder(String.valueOf(file)).append("/").append(getString(R.string.app_name)).append("/temp").toString()).mkdirs();
        if ("mounted".equals(Environment.getExternalStorageState())) {
            this.con_file = new File(new StringBuilder(String.valueOf(file)).append("/").append(getString(R.string.app_name)).append("/temp/").toString(), "Wedding_dress.jpg");
        } else {
            this.con_file = new File(getFilesDir(), "Wedding_dress.jpg");
        }
    }

    private static void open_filestream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true)
        {
            int read = inputStream.read(bArr);
            if (read != -1)
            {
                outputStream.write(bArr, 0, read);
            }
            else
            {
                return;
            }
        }
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
            if (ContextCompat.checkSelfPermission(MainAct.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(MainAct.this, new String[] {Manifest.permission.CAMERA}, 2);
            } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                return;
            } else {
                file_environment();
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                try
                {
                    Parcelable fromFile;
                    if ("mounted".equals(Environment.getExternalStorageState())) {
                        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                        if (currentapiVersion >= 24) {
                            fromFile = FileProvider.getUriForFile(MainAct.this,
                                    BuildConfig.APPLICATION_ID + ".provider",
                                    con_file);
                        } else {
                            fromFile = Uri.fromFile(this.con_file);
                        }
                    } else {
                        fromFile = InternalStorageContentProvider.content_uri;
                    }
                    intent.putExtra("output", fromFile);
                    intent.putExtra("return-data", true);
                    startActivityForResult(this.intent, 2);
                } catch (Throwable e) {
                }
            }
        } else if (whichActivitytoStart == 2) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                return;
            }
            else {
                file_environment();
                intent = new Intent("android.intent.action.PICK");
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        }
    }
}

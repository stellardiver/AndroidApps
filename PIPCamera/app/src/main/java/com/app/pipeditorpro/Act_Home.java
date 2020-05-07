package com.app.pipeditorpro;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import com.app.pipeditorpro.mirrorlib.MirrorAct;
import com.app.pipeditorpro.mirrorlib.Utility;
import com.app.pipeditorpro.singleImageEdit.EditAct;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Act_Home extends Activity implements View.OnClickListener {

    LinearLayout home_classics;
    LinearLayout home_pro_edit;
    LinearLayout home_mirror;
    LinearLayout home_mycreation;
    LinearLayout share;
    LinearLayout rate;

    ImageView ic_option_menu_main;

    Global app;
    private File temp_file;
    private static final int IMG_FROM_GALLERY = 2;
    private static final int IMG_FROM_GALLERY_Mirror = 3;

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
        setContentView(R.layout.act_home);

        sharedpreferences = getSharedPreferences(mypreference, MODE_PRIVATE);
        isActivityLeft = false;
        activity = Act_Home.this;

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

        app = ((Global) getApplication());
        home_classics = (LinearLayout) findViewById(R.id.home_classics);
        home_pro_edit = (LinearLayout) findViewById(R.id.home_pro_edit);
        home_mirror = (LinearLayout) findViewById(R.id.home_mirror);
        home_mycreation = (LinearLayout) findViewById(R.id.home_mycreation);
        share = (LinearLayout) findViewById(R.id.share);
        rate = (LinearLayout) findViewById(R.id.rate);

        ic_option_menu_main = (ImageView)findViewById(R.id.ic_option_menu_main);

        home_classics.setOnClickListener(this);
        home_pro_edit.setOnClickListener(this);
        home_mirror.setOnClickListener(this);
        home_mycreation.setOnClickListener(this);
        share.setOnClickListener(this);
        rate.setOnClickListener(this);

        ic_option_menu_main.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_classics:
                whichActivitytoStart = 1;
                showInterstitial();
                break;
            case R.id.home_pro_edit:
                whichActivitytoStart = 2;
                showInterstitial();
                break;
            case R.id.home_mirror:
                whichActivitytoStart = 3;
                showInterstitial();
                break;
            case R.id.home_mycreation:
                whichActivitytoStart = 4;
                showInterstitial();
                break;
            case R.id.share:
                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(Act_Home.this);

                alertDialogBuilder1.setTitle("Share");

                alertDialogBuilder1
                        .setMessage("Hi, Take a Minute to Share This Application.")
                        .setCancelable(false)
                        .setPositiveButton("Share Now",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id)
                                    {
                                        Intent share = new Intent(Intent.ACTION_SEND);
                                        share.setType("text_list/plain");
                                        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                                        share.putExtra(Intent.EXTRA_SUBJECT, " ");
                                        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id="+getPackageName());
                                        share.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                                        startActivity(Intent.createChooser(share, "Share link!"));
                                        dialog.dismiss();
                                    }
                                })
                        .setNegativeButton("Remind me later", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog1 = alertDialogBuilder1.create();

                alertDialog1.show();

                break;
            case R.id.rate:
                AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(Act_Home.this);

                alertDialogBuilder2.setTitle("Rate Us");

                alertDialogBuilder2
                        .setMessage("Hi, Take a Minute to Rate This Application and Help to Improve More New Features.")
                        .setCancelable(false)
                        .setPositiveButton("Rate Now",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent in = new Intent(Intent.ACTION_VIEW);
                                        in.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                                        startActivity(in);
                                        dialog.dismiss();
                                    }
                                })
                        .setNegativeButton("Remind me later", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog2 = alertDialogBuilder2.create();

                alertDialog2.show();
                break;
            case  R.id.ic_option_menu_main:
                PopupMenu popup = new PopupMenu(getBaseContext(), view);
                popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.moreapps:
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Act_Home.this);

                                alertDialogBuilder.setTitle(" More Application ");

                                alertDialogBuilder
                                        .setMessage("Hi, Take a Minute to View More Application.")
                                        .setCancelable(false)
                                        .setPositiveButton("Watch Now",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id)
                                                    {
                                                        Uri uri = Uri.parse(getResources().getString(R.string.moreappslink));
                                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                                        startActivity(intent);
                                                        dialog.cancel();
                                                    }
                                                })
                                        .setNegativeButton("Remind me later", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alertDialog = alertDialogBuilder.create();

                                alertDialog.show();
                                break;
                            case R.id.privacy:
                                Uri uri = Uri.parse(getResources().getString(R.string.privacypolicylink));
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
                break;

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

    private void file_environment() {
        String file = Environment.getExternalStorageDirectory().toString();
        new File(new StringBuilder(String.valueOf(file)).append("/").append(getString(R.string.app_name)).append("/temp").toString()).mkdirs();
        if ("mounted".equals(Environment.getExternalStorageState())) {
            temp_file = new File(new StringBuilder(String.valueOf(file)).append("/").append(getString(R.string.app_name)).append("/temp/").toString(), "temp.jpg");
        } else {
            temp_file = new File(getFilesDir(), "temp.jpg");
        }
    }

    @Override
    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        SharedPreferences.Editor editor11 = sharedpreferences.edit();

        if (paramInt2 != -1) {
            return;
        }
        switch (paramInt1) {
            default:
                return;
            case IMG_FROM_GALLERY:
                Uri imgImageCaptureUri2 = paramIntent.getData();
                Intent local = new Intent(getApplicationContext(), EditAct.class);
                editor11.putString("imgImageCaptureUri_gallery", String.valueOf(paramIntent.getData()));
                app.setSelectedImageUri(imgImageCaptureUri2);
                app.setFromCamera(false);
                startActivity(local);
                break;
            case IMG_FROM_GALLERY_Mirror:

                Intent intent2 = new Intent(Act_Home.this, MirrorAct.class);
                int maxSize = Utility.maxSizeForLoad();
                try {
                    InputStream openInputStream = getContentResolver().openInputStream(paramIntent.getData());
                    OutputStream fileOutputStream = null;
                    fileOutputStream = new FileOutputStream(temp_file);
                    open_filestream(openInputStream, fileOutputStream);
                    fileOutputStream.close();
                    openInputStream.close();
                    intent2.putExtra("selectedImagePath", temp_file.getAbsolutePath());
                    intent2.putExtra("MAX_SIZE", maxSize);
                    startActivity(intent2);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        editor11.commit();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finishAffinity();
                    }
                }).create().show();
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
        } else if (whichAdFirst == 3) {
            if (interstitial != null && interstitial.isLoaded() && !isActivityLeft) {
                interstitial.show();
            } else if (interstitialAdFB != null && interstitialAdFB.isAdLoaded() && !isActivityLeft) {
                interstitialAdFB.show();
            } else {
                replaceScreen();
            }
        } else if (whichAdFirst == 4) {
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
            Intent in1 = new Intent(Act_Home.this, MainAct.class);
            startActivity(in1);
        } else if (whichActivitytoStart == 2) {
            if (ActivityCompat.checkSelfPermission(Act_Home.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Act_Home.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                return;
            } else {
                file_environment();
                Intent localIntent1 = new Intent("android.intent.action.PICK");
                localIntent1.setType("image/*");
                startActivityForResult(localIntent1, IMG_FROM_GALLERY);
            }
        } else if (whichActivitytoStart == 3) {
            if (ActivityCompat.checkSelfPermission(Act_Home.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Act_Home.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                return;
            } else {
                file_environment();
                Intent localIntent2 = new Intent("android.intent.action.PICK");
                localIntent2.setType("image/*");
                startActivityForResult(localIntent2, IMG_FROM_GALLERY_Mirror);
            }
        } else if (whichActivitytoStart == 4) {
            if (ActivityCompat.checkSelfPermission(Act_Home.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Act_Home.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                return;
            } else {
                Intent in2 = new Intent(Act_Home.this,MyCreation.class);
                startActivity(in2);
            }
        }
    }
}

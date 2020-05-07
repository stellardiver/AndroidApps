package com.app.pipeditorpro.mirrorlib;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pipeditorpro.Act_Home;
import com.app.pipeditorpro.ApplicationProperties;
import com.app.pipeditorpro.CommonUtils;
import com.app.pipeditorpro.ConnectionDetector;
import com.app.pipeditorpro.Global;
import com.app.pipeditorpro.MyCreation;
import com.app.pipeditorpro.R;
import com.app.pipeditorpro.Utils;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class MirrorShareAct extends Activity implements View.OnClickListener
{
    ImageView editorResultImageView;
    ImageButton imageButtonEditorResultCollage;
    ImageButton imageButtonEditorResultPhotoEditor;
    ImageButton imageButtonEditorResultShare;
    ImageButton imageButtonEditorResultsave;

    String imageUrl;

    Global app;
    Bitmap bitmap;

    String str;
    String albam;
    FrameLayout frm;

    Boolean is_save = false;

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
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.act_fragment_editor_result);

        sharedpreferences = getSharedPreferences(mypreference, MODE_PRIVATE);
        isActivityLeft = false;
        activity = MirrorShareAct.this;

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
        this.editorResultImageView = (ImageView) findViewById(R.id.editorResultImageView);
        imageUrl = getIntent().getStringExtra("IMAGE_DATA");

        frm = (FrameLayout)findViewById(R.id.framePhotoGallery);
        frm.setDrawingCacheEnabled(true);
        frm.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        frm.layout(0, 0, frm.getMeasuredWidth(), frm.getMeasuredHeight());
        frm.buildDrawingCache(true);

        findViewById(R.id.imageButtonEditorResultBack).setOnClickListener(this);
        findViewById(R.id.imageButtonEditorResultCollage).setOnClickListener(this);
        findViewById(R.id.imageButtonEditorResultShare).setOnClickListener(this);

        imageButtonEditorResultsave =(ImageButton)findViewById(R.id.imageButtonEditorResultsave);
        imageButtonEditorResultsave.setVisibility(View.VISIBLE);

        System.gc();
        int i;

        try
        {
            bitmap = BitmapFactory.decodeFile(imageUrl);
            DisplayMetrics localDisplayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
            i = localDisplayMetrics.heightPixels;
            if (bitmap == null)
            {
                Toast.makeText(MirrorShareAct.this, "Failed to load image!", Toast.LENGTH_LONG).show();
                finish();
                return;
            }
        }
        catch (Exception localException)
        {
            Toast.makeText(MirrorShareAct.this, "Failed to load image!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        editorResultImageView.setImageBitmap(bitmap);

        final Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(400);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        imageButtonEditorResultsave.startAnimation(animation);

        imageButtonEditorResultsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(editorResultImageView==null)
                {
                    Toast.makeText(MirrorShareAct.this, "Failed to load image!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    save();
                }

            }
        });
    }

    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imageButtonEditorResultBack:
                if(is_save) {
                    whichActivitytoStart = 2;
                    showInterstitial();
                }
                else {
                    if(editorResultImageView==null)
                    {
                        Toast.makeText(MirrorShareAct.this, "Failed to load image!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        final Dialog dialog = new Dialog(MirrorShareAct.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.act_save_image);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.setCancelable(false);
                        dialog.show();
                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        lp.copyFrom(dialog.getWindow().getAttributes());
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        lp.gravity = Gravity.CENTER;
                        TextView save_yes = (TextView)dialog.findViewById(R.id.save_yes);
                        TextView save_cancle = (TextView)dialog.findViewById(R.id.save_cancle);
                        TextView save_no = (TextView)dialog.findViewById(R.id.save_no);

                        save_yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                save();
                            }
                        });
                        save_cancle.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        save_no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                whichActivitytoStart = 2;
                                showInterstitial();
                            }
                        });

                    }
                }
                break;

            case R.id.imageButtonEditorResultCollage:
                if(is_save) {
                    whichActivitytoStart = 2;
                    showInterstitial();
                }
                else {
                    if(editorResultImageView==null)
                    {
                        Toast.makeText(MirrorShareAct.this, "Failed to load image!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        final Dialog dialog = new Dialog(MirrorShareAct.this,R.style.ActivityDialog);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.act_save_image);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();

                        TextView save_yes = (TextView)dialog.findViewById(R.id.save_yes);
                        TextView save_cancle = (TextView)dialog.findViewById(R.id.save_cancle);
                        TextView save_no = (TextView)dialog.findViewById(R.id.save_no);

                        save_yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                save();
                            }
                        });
                        save_cancle.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        save_no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                whichActivitytoStart = 2;
                                showInterstitial();
                            }
                        });

                    }
                }
                return;

            case R.id.imageButtonEditorResultShare:

                Intent shareIntent = new Intent();
                shareIntent.setAction("android.intent.action.SEND");
                File imageFileToShare = new File(imageUrl);
                Uri apkURI = FileProvider.getUriForFile(MirrorShareAct.this, getApplicationContext()
                        .getPackageName() + ".provider", imageFileToShare);
                shareIntent.putExtra(Intent.EXTRA_STREAM, apkURI);
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent, "Share"));
                return;

            default:
                return;
        }
    }

    @Override
    public void onBackPressed(){
        if(is_save){
            whichActivitytoStart = 2;
            showInterstitial();

        }
        else{
            if(editorResultImageView==null)
            {
                Toast.makeText(MirrorShareAct.this, "Failed to load image!", Toast.LENGTH_LONG).show();
            }
            else
            {
                final Dialog dialog = new Dialog(MirrorShareAct.this,R.style.ActivityDialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.act_save_image);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();


                TextView save_yes = (TextView)dialog.findViewById(R.id.save_yes);
                TextView save_cancle = (TextView)dialog.findViewById(R.id.save_cancle);
                TextView save_no = (TextView)dialog.findViewById(R.id.save_no);

                save_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        save();
                    }
                });
                save_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                save_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        whichActivitytoStart = 2;
                        showInterstitial();

                    }
                });
            }
        }
    }

    public void save() {
        new AsyncTask<Void, Void, Void>()
        {

            ProgressDialog progressDialog = null;

            protected void onPreExecute()
            {
                progressDialog = ProgressDialog.show(MirrorShareAct.this,"Loading...","Saving photo to Gallery...");
            };

            @Override
            protected Void doInBackground(Void... arg0)
            {
                try
                {
                    albam = getString(R.string.app_name);
                    String currentdateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()).toString();
                    String photoName = ApplicationProperties.Album_Photo_Prefix+ currentdateTime;
                    if(albam != null && albam.isEmpty())
                    {
                        str = CommonUtils.saveToGallery(loadBitmapFromView(frm), photoName,
                                getContentResolver(), "png");
                    }
                    else
                    {
                        str = CommonUtils.saveToGallery(loadBitmapFromView(frm), albam, photoName,
                                getContentResolver(), "png");
                    }
                }
                catch (Exception localException)
                {
                    localException.printStackTrace();
                    finish();
                }
                return null;
            }

            protected void onPostExecute(Void result)
            {
                progressDialog.dismiss();
                whichActivitytoStart = 1;
                showInterstitial();
            };
        }.execute();
    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        Canvas canvas = new Canvas(b);
        v.draw(canvas);
        return b;
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
            is_save = true;
            Toast.makeText(MirrorShareAct.this, "Image Save ", Toast.LENGTH_SHORT).show();
            Intent localIntent = new Intent(MirrorShareAct.this, MyCreation.class);
            startActivity(localIntent);
        } else if (whichActivitytoStart == 2) {
            startActivity(new Intent(MirrorShareAct.this, Act_Home.class));
            finish();
        }
    }
}

package com.app.pipeditorpro;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.commit451.nativestackblur.NativeStackBlur;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.myandroid.views.MultiTouchListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class WorkingAct extends AppCompatActivity {
    int currentalpha;
    int currentimg;
    Integer[] frameImages;
    GalleryImageAdapter galImageAdapter;
    Gallery gallery;
    Integer[] iconImages;
    ImageView imageview_id;
    ImageView mFrameIv;
    Global mGlobal;
    ImageView mMovImage;
    Bitmap mask;
    Integer[] maskImages;
    Bitmap original;
    Bitmap result;
    Bitmap bm;
    String str;
    RelativeLayout capturelayout;
    ImageView classics_done;

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
    AppCompatActivity activity;

    class C04271 implements OnItemSelectedListener {
        C04271() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            WorkingAct.this.makeMaskImage(WorkingAct.this.imageview_id, WorkingAct.this.maskImages[i].intValue(), WorkingAct.this.frameImages[i].intValue());
            WorkingAct.this.currentimg = i;
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class GalleryImageAdapter extends BaseAdapter {
        private Activity context;
        private ViewHolder holder;
        int imageBackground;
        private ImageView imageView;

        private class ViewHolder {
            ImageView imageView;

            private ViewHolder() {
            }
        }

        public GalleryImageAdapter(Activity context) {
            this.context = context;
            TypedArray ta = WorkingAct.this.obtainStyledAttributes(R.styleable.Gallery1);
            this.imageBackground = ta.getResourceId(0, 1);
            ta.recycle();
        }

        public int getCount() {
            return WorkingAct.this.iconImages.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                this.holder = new ViewHolder();
                this.imageView = new ImageView(this.context);
                this.imageView.setPadding(3, 3, 3, 3);
                convertView = this.imageView;
                this.holder.imageView = this.imageView;
                convertView.setTag(this.holder);
            } else {
                this.holder = (ViewHolder) convertView.getTag();
            }
            Picasso.with(this.context).load(WorkingAct.this.iconImages[position].intValue()).into(this.holder.imageView);
            this.holder.imageView.setScaleType(ScaleType.FIT_XY);
            this.holder.imageView.setPadding(5,5,5,5);
            this.holder.imageView.setLayoutParams(new LayoutParams(200,200));
            this.holder.imageView.setBackgroundResource(this.imageBackground);
            return this.imageView;
        }
    }

    public WorkingAct() {
        this.iconImages = new Integer[]{Integer.valueOf(R.drawable.frame1),
                Integer.valueOf(R.drawable.frame2), Integer.valueOf(R.drawable.frame3),
                Integer.valueOf(R.drawable.frame4), Integer.valueOf(R.drawable.frame5),
                Integer.valueOf(R.drawable.frame6), Integer.valueOf(R.drawable.frame7),
                Integer.valueOf(R.drawable.frame8), Integer.valueOf(R.drawable.frame9),
                Integer.valueOf(R.drawable.frame10), Integer.valueOf(R.drawable.frame11),
                Integer.valueOf(R.drawable.frame12), Integer.valueOf(R.drawable.frame13),
                Integer.valueOf(R.drawable.frame14), Integer.valueOf(R.drawable.frame15),
                Integer.valueOf(R.drawable.frame16), Integer.valueOf(R.drawable.frame17),
                Integer.valueOf(R.drawable.frame18), Integer.valueOf(R.drawable.frame19),
                Integer.valueOf(R.drawable.frame20), Integer.valueOf(R.drawable.frame21),
                Integer.valueOf(R.drawable.frame22), Integer.valueOf(R.drawable.frame23),
                Integer.valueOf(R.drawable.frame24), Integer.valueOf(R.drawable.frame25),
                Integer.valueOf(R.drawable.frame26), Integer.valueOf(R.drawable.frame27),
                Integer.valueOf(R.drawable.frame28), Integer.valueOf(R.drawable.frame29),
                Integer.valueOf(R.drawable.frame30), Integer.valueOf(R.drawable.frame31),
                Integer.valueOf(R.drawable.frame32), Integer.valueOf(R.drawable.frame33),
                Integer.valueOf(R.drawable.frame34), Integer.valueOf(R.drawable.frame35),
                Integer.valueOf(R.drawable.frame36), Integer.valueOf(R.drawable.frame37),
                Integer.valueOf(R.drawable.frame38), Integer.valueOf(R.drawable.frame39),
                Integer.valueOf(R.drawable.frame40), Integer.valueOf(R.drawable.frame41)};

        this.frameImages = new Integer[]{Integer.valueOf(R.drawable.frameshow1),
                Integer.valueOf(R.drawable.frameshow2), Integer.valueOf(R.drawable.frameshow3),
                Integer.valueOf(R.drawable.frameshow4), Integer.valueOf(R.drawable.frameshow5),
                Integer.valueOf(R.drawable.frameshow6), Integer.valueOf(R.drawable.frameshow7),
                Integer.valueOf(R.drawable.frameshow8), Integer.valueOf(R.drawable.frameshow9),
                Integer.valueOf(R.drawable.frameshow10), Integer.valueOf(R.drawable.frameshow11),
                Integer.valueOf(R.drawable.frameshow12), Integer.valueOf(R.drawable.frameshow13),
                Integer.valueOf(R.drawable.frameshow14), Integer.valueOf(R.drawable.frameshow15),
                Integer.valueOf(R.drawable.frameshow16), Integer.valueOf(R.drawable.frameshow17),
                Integer.valueOf(R.drawable.frameshow18), Integer.valueOf(R.drawable.frameshow19),
                Integer.valueOf(R.drawable.frameshow20), Integer.valueOf(R.drawable.frameshow21),
                Integer.valueOf(R.drawable.frameshow22), Integer.valueOf(R.drawable.frameshow23),
                Integer.valueOf(R.drawable.frameshow24), Integer.valueOf(R.drawable.frameshow25),
                Integer.valueOf(R.drawable.frameshow26), Integer.valueOf(R.drawable.frameshow27),
                Integer.valueOf(R.drawable.frameshow28), Integer.valueOf(R.drawable.frameshow29),
                Integer.valueOf(R.drawable.frameshow30), Integer.valueOf(R.drawable.frameshow31),
                Integer.valueOf(R.drawable.frameshow32), Integer.valueOf(R.drawable.frameshow33),
                Integer.valueOf(R.drawable.frameshow34), Integer.valueOf(R.drawable.frameshow35),
                Integer.valueOf(R.drawable.frameshow36), Integer.valueOf(R.drawable.frameshow37),
                Integer.valueOf(R.drawable.frameshow38), Integer.valueOf(R.drawable.frameshow39),
                Integer.valueOf(R.drawable.frameshow40), Integer.valueOf(R.drawable.frameshow41)};

        this.maskImages = new Integer[]{Integer.valueOf(R.drawable.frameback1),
                Integer.valueOf(R.drawable.frameback2), Integer.valueOf(R.drawable.frameback3),
                Integer.valueOf(R.drawable.frameback4), Integer.valueOf(R.drawable.frameback5),
                Integer.valueOf(R.drawable.frameback6), Integer.valueOf(R.drawable.frameback7),
                Integer.valueOf(R.drawable.frameback8), Integer.valueOf(R.drawable.frameback9),
                Integer.valueOf(R.drawable.frameback10), Integer.valueOf(R.drawable.frameback11),
                Integer.valueOf(R.drawable.frameback12), Integer.valueOf(R.drawable.frameback13),
                Integer.valueOf(R.drawable.frameback14), Integer.valueOf(R.drawable.frameback15),
                Integer.valueOf(R.drawable.frameback16), Integer.valueOf(R.drawable.frameback17),
                Integer.valueOf(R.drawable.frameback18), Integer.valueOf(R.drawable.frameback19),
                Integer.valueOf(R.drawable.frameback20), Integer.valueOf(R.drawable.frameback21),
                Integer.valueOf(R.drawable.frameback22), Integer.valueOf(R.drawable.frameback23),
                Integer.valueOf(R.drawable.frameback24), Integer.valueOf(R.drawable.frameback25),
                Integer.valueOf(R.drawable.frameback26), Integer.valueOf(R.drawable.frameback27),
                Integer.valueOf(R.drawable.frameback28), Integer.valueOf(R.drawable.frameback29),
                Integer.valueOf(R.drawable.frameback30), Integer.valueOf(R.drawable.frameback31),
                Integer.valueOf(R.drawable.frameback32), Integer.valueOf(R.drawable.frameback33),
                Integer.valueOf(R.drawable.frameback34), Integer.valueOf(R.drawable.frameback35),
                Integer.valueOf(R.drawable.frameback36), Integer.valueOf(R.drawable.frameback37),
                Integer.valueOf(R.drawable.frameback38), Integer.valueOf(R.drawable.frameback39),
                Integer.valueOf(R.drawable.frameback40), Integer.valueOf(R.drawable.frameback41)};

        this.currentimg = 0;
        this.currentalpha = 25;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_working);

        sharedpreferences = getSharedPreferences(mypreference, MODE_PRIVATE);
        isActivityLeft = false;
        activity = WorkingAct.this;

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

        this.gallery = (Gallery) findViewById(R.id.gallery);
        capturelayout = (RelativeLayout) findViewById(R.id.rl);
        this.galImageAdapter = new GalleryImageAdapter(this);
        this.gallery.setAdapter(this.galImageAdapter);
        this.imageview_id = (ImageView) findViewById(R.id.imageview_id);
        this.mMovImage = (ImageView) findViewById(R.id.iv_mov);
        this.mFrameIv = (ImageView) findViewById(R.id.mFrameIv);
        this.mGlobal = (Global) getApplication();
        bm = this.mGlobal.getImage();
        this.mMovImage.setImageBitmap(bm);
        makeMaskImage(this.imageview_id, this.maskImages[0].intValue(), this.frameImages[0].intValue());
        this.mMovImage.setOnTouchListener(new MultiTouchListener());
        this.gallery.setOnItemSelectedListener(new C04271());

        classics_done = (ImageView)findViewById(R.id.classics_done);
        classics_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            new AsyncTask<Void, Void, Void>() {
                ProgressDialog progressDialog = null;
                protected void onPreExecute() {
                    progressDialog = ProgressDialog.show(WorkingAct.this,"Loading...","Please Wait...");
                }
                @SuppressLint("WrongThread")
                @Override
                protected Void doInBackground(Void... arg0)
                {
                    capturelayout.buildDrawingCache(true);
                    capturelayout.setDrawingCacheEnabled(true);
                    Bitmap bitmap = Bitmap.createBitmap(capturelayout.getDrawingCache());
                    capturelayout.setDrawingCacheEnabled(false);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    String s = BitMapToString(bitmap);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("image",s);
                    editor.commit();
                    return null;
                }

                protected void onPostExecute(Void result)
                {
                    progressDialog.dismiss();
                    whichActivitytoStart = 1;
                    showInterstitial();
                }
            }.execute();
            }
        });
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public void makeMaskImage(ImageView mImageView, int maskimg, int frame) {
        this.mFrameIv.setBackgroundResource(frame);
        try {
            this.result.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mask = BitmapFactory.decodeResource(getResources(), maskimg);
        this.result = Bitmap.createBitmap(this.mask.getWidth(), this.mask.getHeight(), Config.ARGB_8888);
        this.original = NativeStackBlur.process(getResizedBitmap(this.mGlobal.getImage(), this.mask.getWidth(), this.mask.getHeight()), this.currentalpha);
        Canvas mCanvas = new Canvas(this.result);
        Paint paint = new Paint(1);
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        mCanvas.drawBitmap(this.original, 0.0f, 0.0f, null);
        mCanvas.drawBitmap(this.mask, 0.0f, 0.0f, paint);
        paint.setXfermode(null);
        this.imageview_id.setImageBitmap(this.result);
        mImageView.setScaleType(ScaleType.CENTER_INSIDE);
        try {
            this.mask.recycle();
            this.mask = null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.original.recycle();
            this.original = null;
        } catch (Exception e22) {
            e22.printStackTrace();
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        float scaleWidth;
        float scaleHeight;
        int width = bm.getWidth();
        int height = bm.getHeight();
        if (width < height) {
            scaleWidth = ((float) newWidth) / ((float) width);
            scaleHeight = scaleWidth;
        } else {
            scaleHeight = ((float) newHeight) / ((float) height);
            scaleWidth = scaleHeight;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
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
            if (ActivityCompat.checkSelfPermission(WorkingAct.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(WorkingAct.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);

                return;
            }
            else {
                Intent intent = new Intent(getApplicationContext(), Edit_creation.class);
                startActivity(intent);
                finish();
            }
        }
    }
}

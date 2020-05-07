package com.app.pipeditorpro.singleImageEdit;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pipeditorpro.Act_Home;
import com.app.pipeditorpro.MyCreation;

import com.app.pipeditorpro.R;
import com.app.pipeditorpro.ApplicationProperties;
import com.app.pipeditorpro.Global;
import com.app.pipeditorpro.CommonUtils;
import com.app.pipeditorpro.adapter.ImageAdapter;
import com.app.pipeditorpro.adapter.ImageAdapterFilter;
import com.app.pipeditorpro.graphics.CommandsPreset;
import com.app.pipeditorpro.graphics.ImageProcessor;
import com.app.pipeditorpro.graphics.ImageProcessorListener;
import com.app.pipeditorpro.graphics.commands.ImageProcessingCommand;
import com.app.pipeditorpro.adapter.GalleryAdapter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.app.pipeditorpro.adapter.ImageAdapter.mThumbIds;

public class EditAct extends AppCompatActivity implements ImageProcessorListener, OnTouchListener {

    float[] lastEvent = null;
    float d = 0f;
    float newRot = 0f;

    static final int BLACK_WHITE = 1;
    static final int DRAG = 1;
    static final int HUE = 3;
    static final int MAX_FILE_SIZE = 512;
    static final int NONE = 0;
    static final int NO_EFFECT = 0;
    static final int SEPIA = 2;
    static final int ZOOM = 2;

    private int color;
    String albam;

    Gallery gallery;
    Boolean isGallaryopen = false;
    private ImageProcessor imageProcessor;
    public static final int RESTORE_PREVIEW_BITMAP = 1;

    Context _context;

    ImageView frameImage;
    ImageView selectImage;

    FrameLayout frm;

    public static Bitmap selectedBitmap;

    PointF mid = new PointF();
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    PointF start = new PointF();

    Global app;

    byte[] textId;
    boolean fromCamera;
    boolean isText=false;

    private int heightScreen;
    private int widthScreen;
    int hue = 255;
    int mode = 0;
    float oldDist = 1.0F;
    private Uri selectedImageUri;
    String str;

    int[] frameId_s = new int[]{
            R.drawable.emotion_01,R.drawable.emotion_02,R.drawable.emotion_03,R.drawable.emotion_04,R.drawable.emotion_05,R.drawable.emotion_06,R.drawable.emotion_07,
            R.drawable.emotion_08,R.drawable.emotion_09,R.drawable.emotion_10,R.drawable.emotion_11,R.drawable.emotion_12,R.drawable.emotion_13,
            R.drawable.emotion_14,R.drawable.emotion_15,R.drawable.emotion_16,R.drawable.emotion_17,R.drawable.emotion_18,
            R.drawable.emotion_19,R.drawable.emotion_20,R.drawable.emotion_21,R.drawable.emotion_22,R.drawable.emotion_23,
            R.drawable.emotion_24,R.drawable.emotion_25,

            R.drawable.candy_01,R.drawable.candy_02,R.drawable.candy_03,R.drawable.candy_04,
            R.drawable.candy_05,R.drawable.candy_06,R.drawable.candy_07,R.drawable.candy_08,R.drawable.candy_09,R.drawable.candy_10,
            R.drawable.candy_11,R.drawable.candy_12,R.drawable.candy_13,R.drawable.candy_14,R.drawable.candy_15,R.drawable.candy_16,
            R.drawable.candy_17,R.drawable.candy_18,R.drawable.candy_19,R.drawable.candy_20,

            R.drawable.comic_01,R.drawable.comic_02,
            R.drawable.comic_03,R.drawable.comic_04,R.drawable.comic_05,R.drawable.comic_06,R.drawable.comic_07,R.drawable.comic_08,
            R.drawable.comic_09,R.drawable.comic_10,R.drawable.comic_11,
            R.drawable.comic_12,R.drawable.comic_13,R.drawable.comic_14,R.drawable.comic_15,R.drawable.comic_16,R.drawable.comic_17,
            R.drawable.comic_18,R.drawable.comic_19,R.drawable.comic_20,R.drawable.comic_21,R.drawable.comic_22,R.drawable.comic_23,
            R.drawable.comic_24,R.drawable.comic_25,R.drawable.comic_26,R.drawable.comic_27,R.drawable.comic_28,

            R.drawable.accessory_01,R.drawable.accessory_02,R.drawable.accessory_03,R.drawable.accessory_04,R.drawable.accessory_05,
            R.drawable.accessory_06,R.drawable.accessory_07,R.drawable.accessory_08,R.drawable.accessory_09,R.drawable.accessory_10,R.drawable.accessory_11,
            R.drawable.beard_01,R.drawable.beard_02,R.drawable.beard_03,R.drawable.beard_04,R.drawable.beard_05,R.drawable.beard_06,
            R.drawable.beard_07,R.drawable.beard_08,

            R.drawable.glasses_01,R.drawable.glasses_02,R.drawable.glasses_03,R.drawable.glasses_04,R.drawable.glasses_05,R.drawable.glasses_06,R.drawable.glasses_07,
            R.drawable.glasses_08,R.drawable.glasses_09,R.drawable.glasses_10,R.drawable.glasses_11,R.drawable.glasses_12,R.drawable.glasses_13,
            R.drawable.glasses_14,R.drawable.glasses_15,R.drawable.glasses_16,R.drawable.glasses_17,R.drawable.glasses_18,R.drawable.glasses_19,
            R.drawable.glasses_20,R.drawable.glasses_21,R.drawable.glasses_22,R.drawable.glasses_23,R.drawable.glasses_24,R.drawable.glasses_25,

            R.drawable.hat_01,R.drawable.hat_02,R.drawable.hat_03,R.drawable.hat_04,R.drawable.hat_05,R.drawable.hat_06,R.drawable.hat_07,
            R.drawable.hat_08,R.drawable.hat_09,R.drawable.hat_10,R.drawable.hat_11,

            R.drawable.love_01,R.drawable.love_03,R.drawable.love_04,R.drawable.love_11,R.drawable.love_13,
            R.drawable.love_14,R.drawable.love_15,R.drawable.love_26,R.drawable.love_32,R.drawable.love_35,

            R.drawable.snap_057,R.drawable.snap_058,R.drawable.snap_059,R.drawable.snap_060,R.drawable.snap_061,
            R.drawable.snap_062,R.drawable.snap_063,R.drawable.snap_064,R.drawable.snap_065,R.drawable.snap_067,
            R.drawable.snap_068,R.drawable.snap_069,

            R.drawable.snap_emerald_tiara,R.drawable.snap_eye_left,R.drawable.snap_eye_right,
            R.drawable.snap_flower_crown_0,R.drawable.snap_flower_crown_1,R.drawable.snap_flower_crown_2,R.drawable.snap_flower_crown_3,R.drawable.snap_flower_crown_4,
            R.drawable.snap_flower_crown_5,R.drawable.snap_flower_crown_6,R.drawable.snap_flower_crown_7,R.drawable.snap_flower_crown_8,R.drawable.snap_flower_crown_9,
            R.drawable.snap_flower_crown_10,R.drawable.snap_flower_crown_11,R.drawable.snap_flower_crown_12,R.drawable.snap_flower_crown_13,R.drawable.snap_flower_crown_14,
            R.drawable.snap_flower_crown_15,R.drawable.snap_flower_crown_16,R.drawable.snap_flower_crown_17,R.drawable.snap_flower_crown_18,R.drawable.snap_flower_crown_19,
            R.drawable.snap_flower_crown_20,R.drawable.snap_flower_crown_21,R.drawable.snap_flower_crown_22,R.drawable.snap_flower_tiara,R.drawable.snap_tiara_0,
            R.drawable.snap_tiara_2,};

    ImageView mImageView;
    RecyclerView mRecyclerView;
    GalleryAdapter mGalleryAdapter;

    private com.xiaopo.flying.sticker.StickerView mStickerView;

    Bitmap bitmap2;
    Bitmap localBitmap;

    Gallery frameGallery;

    final int IMG_FROM_CAMERA = 1;
    final int IMG_FROM_GALLERY = 2;

    Uri imgImageCaptureUri;

    int position;

    Button button_square_picture;
    Button button_square_frame;
    Button button_square_effect;
    Button button_mirror_sticker;
    Button button_square_text;

    ImageButton done;
    ImageButton back;
    ImageView imageblur;

    Boolean is_save = false;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "myprefadmob";

    @Override
    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        getWindow().setFormat(1);
        getWindow().addFlags(4096);
        setContentView(R.layout.act_edit);

        sharedpreferences = getSharedPreferences(mypreference, MODE_PRIVATE);

        position = getIntent().getIntExtra("position", 0);

        _context = getApplicationContext();
        app = ((Global) getApplication());

        String temp_uri = sharedpreferences.getString("imgImageCaptureUri_gallery",null);
        selectedImageUri = Uri.parse(temp_uri);
        fromCamera = app.isFromCamera();

        initView();
        initEvent();

        frm = (FrameLayout) findViewById(R.id.framePhotoGallery);

        imageblur=(ImageView)findViewById(R.id.imageblur);
        selectImage = ((ImageView) findViewById(R.id.image));
        frameImage = ((ImageView) findViewById(R.id.frameImage));
        mStickerView = (com.xiaopo.flying.sticker.StickerView) findViewById(R.id.sticker_view);

        button_square_picture=(Button)findViewById(R.id.button_square_picture);
        button_square_frame=(Button)findViewById(R.id.button_square_frame);
        button_square_effect=(Button)findViewById(R.id.button_square_effect);
        button_mirror_sticker=(Button)findViewById(R.id.button_mirror_sticker);
        button_square_text=(Button)findViewById(R.id.button_square_text);

        done=(ImageButton)findViewById(R.id.done) ;
        back=(ImageButton)findViewById(R.id.back);

        back.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (is_save == true) {
                    startActivity(new Intent(EditAct.this, Act_Home.class));
                    finish();
                } else {
                    if(selectImage==null)
                    {
                        Toast.makeText(EditAct.this, "Failed to load image!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        final Dialog dialog = new Dialog(EditAct.this,R.style.ActivityDialog);
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
                                saveimage();
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
                                startActivity(new Intent(EditAct.this, Act_Home.class));
                                finish();
                            }
                        });

                    }
                }
            }
        });

        selectImage.setOnTouchListener(new TouchImageView(this));

        done.setOnClickListener(new OnClickListener()

        {
            @Override
            public void onClick(View view)
            {
                saveimage();
            }
        });

        button_square_picture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mRecyclerView.setVisibility(View.GONE);
                gallery.setVisibility(View.GONE);
                frameGallery.setVisibility(View.GONE);
                isText=false;

                Intent localIntent1 = new Intent("android.intent.action.PICK");
                localIntent1.setType("image/*");
                startActivityForResult(localIntent1,IMG_FROM_GALLERY);
            }
        });

        button_square_frame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view)
            {

                mRecyclerView.setVisibility(View.GONE);
                gallery.setVisibility(View.GONE);

                mStickerView.setLooked(true);
                isText=false;

                if (frameGallery.getVisibility() == View.VISIBLE) {
                    frameGallery.setVisibility(View.INVISIBLE);
                } else {
                    frameGallery.setVisibility(View.VISIBLE);
                }

            }
        });

        button_square_effect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view)
            {

                mRecyclerView.setVisibility(View.GONE);
                frameGallery.setVisibility(View.GONE);
                mStickerView.setLooked(true);

                isGallaryopen = true;
                if (gallery.getVisibility() == View.VISIBLE) {
                    gallery.setVisibility(View.INVISIBLE);
                } else {
                    gallery.setVisibility(View.VISIBLE);
                    readBitmapthis(selectedImageUri);
                }
            }
        });

        button_mirror_sticker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mStickerView.setLooked(false);
                gallery.setVisibility(View.GONE);
                frameGallery.setVisibility(View.GONE);
                selectImage.setOnTouchListener(null);

                if (mRecyclerView.getVisibility() == View.VISIBLE) {
                    mRecyclerView.setVisibility(View.INVISIBLE);
                } else {
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

        button_square_text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view)
            {
                isText=true;
                mRecyclerView.setVisibility(View.GONE);
                gallery.setVisibility(View.GONE);
                frameGallery.setVisibility(View.GONE);
                selectImage.setOnTouchListener(null);
                mStickerView.setLooked(false);

                startActivity(new Intent (EditAct.this, TextAct.class));
            }
        });

        mStickerView.setLooked(true);

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        heightScreen = localDisplayMetrics.heightPixels;
        widthScreen = localDisplayMetrics.widthPixels;

        gallery = (Gallery) findViewById(R.id.filterGallery);
        gallery.setAdapter(new ImageAdapterFilter(this));
        gallery.setOnItemClickListener(listener);
        gallery.setVisibility(View.GONE);

        frameGallery=(Gallery)findViewById(R.id.frameGallery);
        frameGallery.setAdapter(new ImageAdapter(EditAct.this));

        frameGallery.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                if (position==0) {
                    frameImage.setImageResource(0);
                } else {
                    frameImage.setImageResource(mThumbIds[position]);
                }
            }
        });

        this.color = 0;

        frm.setDrawingCacheEnabled(true);
        frm.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        frm.layout(0, 0, frm.getMeasuredWidth(), frm.getMeasuredHeight());
        frm.buildDrawingCache(true);
        readBitmapthis(selectedImageUri);
    }

    private void saveimage() {

        mStickerView.saveclickdone();
        new AsyncTask<Void, Void, Void>() {
            ProgressDialog progressDialog = null;

            protected void onPreExecute()
            {
                progressDialog = ProgressDialog.show(EditAct.this,"Loading...","Saving photo to Gallery...");
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
                is_save = true;
                progressDialog.dismiss();
                Toast.makeText(EditAct.this, "Image Save ", Toast.LENGTH_SHORT).show();
                Intent localIntent = new Intent(EditAct.this, MyCreation.class);
                startActivity(localIntent);
            };
        }.execute();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.image);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mGalleryAdapter = new GalleryAdapter(frameId_s);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mGalleryAdapter);
    }

    private void initEvent(){
        mGalleryAdapter.setOnItemClickListener(new GalleryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int resId) {
                addStickerItem(resId);
            }
        });
    }

    private void addStickerItem(int resId) {

        bitmap2 = BitmapFactory.decodeResource(getResources(), resId);
        mStickerView.addSticker(bitmap2);
        mStickerView.setLooked(false);
    }

    @Override
    public void onProcessStart() {
    }

    @Override
    public void onProcessEnd(Bitmap result){
        selectImage.setImageBitmap(result);
        selectImage.invalidate();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        ImageView view = (ImageView) v;

        float scale;

        switch (event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:

                oldDist = spacing(event);
                if (oldDist > 10f)
                {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;

            case MotionEvent.ACTION_MOVE:

                if (mode == DRAG)
                {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY()- start.y);

                }
                else if (mode == ZOOM && event.getPointerCount() == 2)
                {
                    float newDist = spacing(event);
                    matrix.set(savedMatrix);
                    if (newDist > 10f)
                    {
                        scale = newDist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                    if (lastEvent != null)
                    {
                        newRot = rotation(event);
                        float r = newRot - d;
                        matrix.postRotate(r, view.getMeasuredWidth() / 2,view.getMeasuredHeight() / 2);
                    }
                }
                break;

        }
        view.setImageMatrix(matrix);

        return true;
    }

    public class color {
        int color;

        public color(int color) {
            this.color = color;
        }

        public int getColor() {
            return this.color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }

    private float rotation(MotionEvent event){
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);

        return (float) Math.toDegrees(radians);
    }

    private void midPoint(PointF paramPointF, MotionEvent paramMotionEvent) {
        try {
            float f1 = paramMotionEvent.getX(0) + paramMotionEvent.getX(1);
            float f2 = paramMotionEvent.getY(0) + paramMotionEvent.getY(1);
            paramPointF.set(f1 / 2.0F, f2 / 2.0F);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showTextImage(byte[] textId_) {
        System.gc();

        localBitmap = BitmapFactory.decodeByteArray(textId_, 0, textId_.length);
        mStickerView.addSticker(localBitmap);
    }

    private float spacing(MotionEvent paramMotionEvent) {
        try {
            float f1 = paramMotionEvent.getX(0) - paramMotionEvent.getX(1);
            float f2 = paramMotionEvent.getY(0) - paramMotionEvent.getY(1);
            return (float) Math.sqrt(f1 * f1 + f2 * f2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public String getPath(Uri paramUri){
        Cursor localCursor = managedQuery(paramUri, new String[] { "_data" },null, null, null);
        int i = localCursor.getColumnIndexOrThrow("_data");
        localCursor.moveToFirst();
        return localCursor.getString(i);
    }

    public void readBitmapthis(Uri selectedImageC) {

        Bitmap bm = null;

        AssetFileDescriptor fileDescriptor = null;

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);

        int screenWidth = localDisplayMetrics.widthPixels;
        int screenHeight = localDisplayMetrics.heightPixels;

        BitmapFactory.Options options = null;
        try {

            try {

                fileDescriptor = this.getContentResolver().openAssetFileDescriptor(selectedImageC, "r");

                options = new BitmapFactory.Options();

                options.inJustDecodeBounds = true;

                BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);

                int width = options.outWidth;
                int height = options.outHeight;

                if ((width * height) > (screenWidth * screenHeight))
                {
                    int widthRatio;
                    if (width > height)
                    {
                        widthRatio = Math.round((float) width / (float) screenWidth);
                    }
                    else
                    {
                        widthRatio = Math.round((float) height	/ (float) screenHeight);
                    }
                    options.inSampleSize = widthRatio;
                }

                options.inJustDecodeBounds = false;

                bm = BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);

                selectedBitmap = bm;



                ImageProcessor.getInstance().setBitmap(bm);
                imageProcessor = ImageProcessor.getInstance();

                initializeValues();

                EditAct.this.selectImage.setImageBitmap(bm);
                EditAct.this.imageblur.setImageBitmap(createBlurBitmap(bm, 25));

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (fileDescriptor != null)
                    fileDescriptor.close();
            }
            catch (IOException e)
            {

                e.printStackTrace();
            }
        }
    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        Canvas canvas = new Canvas(b);
        v.draw(canvas);
        return b;
    }

    protected void onResume() {
        super.onResume();
        if (isText) {
            if (app.isText()) {
                app.setText(false);
            } else {
                mStickerView.setLooked(false);
                textId = app.getTextId();
                showTextImage(textId);
                isText = false;
            }
        }
    }


    public void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private OnItemClickListener listener = new OnItemClickListener() {

        public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        {
            runImageProcessor(position);
        }
    };

    private void runImageProcessor(int position) {
        ImageProcessingCommand command = getCommand(position);
        ImageProcessor.getInstance().setProcessListener(this);
        ImageProcessor.getInstance().runCommand(command);
    }

    private ImageProcessingCommand getCommand(int position) {
        return CommandsPreset.Preset.get(position);
    }

    private void initializeValues() {
        final Object data = getLastNonConfigurationInstance();
        if (data == null) {
            selectImage.setImageBitmap(imageProcessor.getBitmap());
        } else {
            restoreSavedValues(data);
        }
    }

    private void restoreSavedValues(Object data) {
        Bundle savedValues = (Bundle) data;

        int bitmapToRead = savedValues.getInt("BITMAP");
        boolean isRunning = savedValues.getBoolean("IS_RUNNING");
        int selectedFilterIdx = savedValues.getInt("SELECTED_FILTER_POSITION");

        if (bitmapToRead == RESTORE_PREVIEW_BITMAP) {
            selectImage.setImageBitmap(imageProcessor.getLastResultBitmap());
        } else {
            selectImage.setImageBitmap(imageProcessor.getBitmap());
        }
        gallery.setSelection(selectedFilterIdx);

        if (isRunning) {
            onProcessStart();
            imageProcessor.setProcessListener(this);
        }
    }

    protected void onRestoreInstanceState(Bundle paramBundle) {
        super.onRestoreInstanceState(paramBundle);
        if (paramBundle.containsKey("cameraImageUri")) {
            this.imgImageCaptureUri = Uri.parse(paramBundle
                    .getString("cameraImageUri"));
        }
    }

    protected void onSaveInstanceState(Bundle paramBundle) {
        super.onSaveInstanceState(paramBundle);
        if (this.imgImageCaptureUri != null) {
            paramBundle.putString("cameraImageUri",
                    this.imgImageCaptureUri.toString());
        }
    }
    @Override
    protected void onActivityResult(int paramInt1, int paramInt2,Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if (paramInt2 != -1) {
            return;
        }
        switch (paramInt1) {
            default:
                return;
            case IMG_FROM_CAMERA:
                selectImage.setImageURI(null);
                imageblur.setImageURI(null);
                readBitmapthis(imgImageCaptureUri);
                break;

            case IMG_FROM_GALLERY:
                imgImageCaptureUri = paramIntent.getData();
                selectedImageUri = imgImageCaptureUri;
                readBitmapthis(imgImageCaptureUri);
                break;
        }
    }

    @Override
    public void onBackPressed() {

     if(is_save == true){
            startActivity(new Intent(EditAct.this, Act_Home.class));
            finish();
        }
        else{
            if(selectImage==null)
            {
                Toast.makeText(EditAct.this, "Failed to load image!", Toast.LENGTH_LONG).show();
            }
            else
            {
                final Dialog dialog = new Dialog(EditAct.this,R.style.ActivityDialog);
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
                        saveimage();
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
                        startActivity(new Intent(EditAct.this, Act_Home.class));
                        finish();
                    }
                });

            }
        }
    }

    private Bitmap createBlurBitmap(Bitmap src, float r) {
        if (r <= 0)
        {
            r = 0.1f;
        }
        else if (r > 25)
        {
            r = 25.0f;
        }
        Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript renderScript = RenderScript.create(this);
        Allocation blurInput = Allocation.createFromBitmap(renderScript, src);
        Allocation blurOutput = Allocation.createFromBitmap(renderScript, bitmap);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        blur.setInput(blurInput);
        blur.setRadius(25);
        blur.forEach(blurOutput);
        blurOutput.copyTo(bitmap);
        renderScript.destroy();
        return bitmap;
    }

    static class TouchImageView implements OnTouchListener {
        final EditAct Touch_EditPhoto;
        Matrix matrix;
        Matrix savedMatrix;

        static int NONE;
        static int DRAG = 1;
        static int ZOOM = 2;
        int mode = NONE;

        PointF start;
        PointF mid;
        float oldDist;

        float[] lastEvent;
        float d;
        float newRot;

        TouchImageView(EditAct editPhoto) {
            this.lastEvent = null;
            this.d = 0f;
            this.newRot = 0f;

            this.Touch_EditPhoto = editPhoto;
            this.matrix = new Matrix();
            this.savedMatrix = new Matrix();
            this.NONE = 0;
            this.start = new PointF();
            this.mid = new PointF();
            this.oldDist = 1.0F;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK)
            {
                case MotionEvent.ACTION_DOWN:
                    savedMatrix.set(matrix);
                    start.set(event.getX(), event.getY());
                    mode = DRAG;
                    lastEvent = null;
                    break;

                case MotionEvent.ACTION_POINTER_DOWN:
                    oldDist = spacing(event);
                    if (oldDist > 10f)
                    {
                        savedMatrix.set(matrix);
                        midPoint(mid, event);
                        mode = ZOOM;
                    }
                    lastEvent = new float[4];
                    lastEvent[0] = event.getX(0);
                    lastEvent[1] = event.getX(1);
                    lastEvent[2] = event.getY(0);
                    lastEvent[3] = event.getY(1);
                    d = rotation(event);
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    mode = NONE;
                    lastEvent = null;
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG)
                    {
                        matrix.set(savedMatrix);
                        matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
                    }
                    else if (mode == ZOOM && event.getPointerCount() == 2)
                    {
                        float newDist = spacing(event);
                        matrix.set(savedMatrix);
                        if (newDist > 10f)
                        {
                            float scale = newDist / oldDist;
                            matrix.postScale(scale, scale, mid.x, mid.y);
                        }
                        if (lastEvent != null)
                        {
                            newRot = rotation(event);
                            float r = newRot - d;
                            matrix.postRotate(r, v.getMeasuredWidth() / 2, v.getMeasuredHeight() / 2);
                        }
                    }
                    break;
            }
            ((ImageView) v).setImageMatrix(matrix);
            return true;
        }
        private float spacing(MotionEvent event) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return (float) Math.sqrt(x * x + y * y);
        }
        private void midPoint(PointF point, MotionEvent event) {
            float x = event.getX(0) + event.getX(1);
            float y = event.getY(0) + event.getY(1);
            point.set(x / 2, y / 2);
        }
        private float rotation(MotionEvent event) {
            double delta_x = (event.getX(0) - event.getX(1));
            double delta_y = (event.getY(0) - event.getY(1));
            double radians = Math.atan2(delta_y, delta_x);
            return (float) Math.toDegrees(radians);
        }
    }
}



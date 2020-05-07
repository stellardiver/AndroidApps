package com.app.pipeditorpro;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.pipeditorpro.adapter.GalleryAdapter;
import com.app.pipeditorpro.adapter.GalleryFrameImageAdapter;
import com.app.pipeditorpro.adapter.GalleryImageAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Edit_creation extends Activity {

    FrameLayout.LayoutParams ll_params = null;
    FrameLayout rl_frm;
    FrameLayout linear_frm;
    LinearLayout linearLayoutStyleShape, linearLayoutStyleFrame, linearLayoutStyleBG, linearLayoutStyleRatio ,linearLayoutStyleStickers;
    FrameLayout frm;
    Bitmap mBitmap,mBitmap1;
    SeekBar seekBarBorder,seekBarBrightness,seekBarOpacity,seekBarRotation;
    String str;
    ImageView image,result_frame;
    ImageView origin,border,brightness,opacity,square_img, round_rect_img, round_img, flip_horizontal, flip_vertical;
    ImageView rotate,move_down,move_up,move_right,move_left;

    int clickcount_square_img = 0;
    int clickcount_round_rect_img = 0;
    int clickcount_round_img = 0;

    ImageButton imageButtonStyleBottomShape, imageButtonStyleBottomFrame, imageButtonStyleBottomRatio;
    ImageButton imageButtonMainBottomText, imageButtonMainBottomSticker, imageButtonMainBottomBG;

    LinearLayout linearLayoutBorder,linearLayoutBrightness,linearLayoutOpacity,linearLayoutRotation;
    boolean isText=false;

    Global app;
    private com.xiaopo.flying.sticker.StickerView mStickerView;
    byte[] textId;
    Bitmap localBitmap;

    RecyclerView mRecyclerView;
    GalleryAdapter mGalleryAdapter;
    Bitmap bitmap2;

    Gallery bgGallery,frameGallery;

    ImageView classics_done,edit_classics_back;
    Boolean is_save = false;

    int[] frameId_s = new int[] {

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
            R.drawable.snap_tiara_2,
    };


    public static final int[] ratioButtonResourceIds = new int[]
            {
                    R.drawable.selector_ratio_1x1, R.drawable.selector_ratio_3x2, R.drawable.selector_ratio_2x3,
                    R.drawable.selector_ratio_4x3, R.drawable.selector_ratio_3x4, R.drawable.selector_ratio_2x1,
                    R.drawable.selector_ratio_1x2};

    SharedPreferences sharedpreferences;
    public static final String mypreference = "myprefadmob";

    protected void onCreate(Bundle paramBundle){
        super.onCreate(paramBundle);
        setContentView(R.layout.act_edit_creation);

        sharedpreferences = getSharedPreferences(mypreference, MODE_PRIVATE);

        app = ((Global) getApplication());
        mStickerView = (com.xiaopo.flying.sticker.StickerView) findViewById(R.id.sticker_view);

        image = ((ImageView) findViewById(R.id.image));
        frm = (FrameLayout) findViewById(R.id.frm);
        result_frame = (ImageView) findViewById(R.id.result_frame);
        linear_frm = (FrameLayout) findViewById(R.id.linear_frm);
        rl_frm  = (FrameLayout) findViewById(R.id.rl_frm);

        classics_done = (ImageView)findViewById(R.id.edit_classics_done);
        edit_classics_back = (ImageView)findViewById(R.id.edit_classics_back);

        origin = (ImageView) findViewById(R.id.origin);
        square_img = (ImageView) findViewById(R.id.square_img);
        round_rect_img = (ImageView) findViewById(R.id.round_rect_img);
        round_img = (ImageView) findViewById(R.id.round_img);
        flip_horizontal = (ImageView) findViewById(R.id.flip_horizontal);
        flip_vertical = (ImageView) findViewById(R.id.flip_vertical);

        move_down = (ImageView)findViewById(R.id.move_down);
        move_up = (ImageView)findViewById(R.id.move_up);
        move_right = (ImageView)findViewById(R.id.move_right);
        move_left = (ImageView)findViewById(R.id.move_left);

        border = (ImageView)findViewById(R.id.border);
        brightness = (ImageView) findViewById(R.id.brightness);
        opacity = (ImageView) findViewById(R.id.opacity);
        rotate = (ImageView)findViewById(R.id.rotate);

        linearLayoutBorder = (LinearLayout)findViewById(R.id.linearLayoutBorder);
        linearLayoutBrightness = (LinearLayout)findViewById(R.id.linearLayoutBrightness);
        linearLayoutOpacity = (LinearLayout)findViewById(R.id.linearLayoutOpacity);
        linearLayoutRotation = (LinearLayout)findViewById(R.id.linearLayoutRotation);

        seekBarBorder = (SeekBar) findViewById(R.id.seekBarBorder);
        seekBarBrightness = (SeekBar) findViewById(R.id.seekBarBrightness);
        seekBarOpacity = (SeekBar)findViewById(R.id.seekBarOpacity);
        seekBarRotation = (SeekBar)findViewById(R.id.seekBarRotation);

        String bitmapstring = sharedpreferences.getString("image", null);
        image.setImageBitmap(StringToBitMap(bitmapstring));

        bgGallery = (Gallery)findViewById(R.id.bgGallery);
        final GalleryImageAdapter galleryImageAdapter= new GalleryImageAdapter(this);
        bgGallery.setAdapter((SpinnerAdapter) galleryImageAdapter);

        frameGallery = (Gallery)findViewById(R.id.frameGallery);
        final GalleryFrameImageAdapter galleryImageAdapter2= new GalleryFrameImageAdapter(this);
        frameGallery.setAdapter((SpinnerAdapter) galleryImageAdapter2);

        initView();
        initEvent();

        bgGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    linear_frm.setBackgroundResource(0);
                }else {
                    linear_frm.setBackgroundResource(GalleryImageAdapter.GalleryImagesList[i]);
                }
            }
        });

        frameGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    result_frame.setBackgroundResource(0);
                }else {
                    result_frame.setBackgroundResource(GalleryFrameImageAdapter.GalleryImagesList2[i]);
                }
            }
        });

        mBitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        mBitmap1 = ((BitmapDrawable) image.getDrawable()).getBitmap();

        origin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageBitmap(mBitmap1);
                clickcount_round_rect_img = 0;
                clickcount_round_img = 0;
                clickcount_square_img = 0;
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutBrightness.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);

            }
        });

        border.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutBorder.setVisibility(View.VISIBLE);
                linearLayoutBrightness.setVisibility(View.GONE);
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);
            }
        });
        brightness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutBrightness.setVisibility(View.VISIBLE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);
            }
        });
        opacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutOpacity.setVisibility(View.VISIBLE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutBrightness.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);
            }
        });
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutRotation.setVisibility(View.VISIBLE);
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutBrightness.setVisibility(View.GONE);

            }
        });

        seekBarBorder.setProgress(100);
        seekBarBorder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float scale = ((i / 100.0f));
                frm.setScaleX(scale);
                frm.setScaleY(scale);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBarBrightness.setProgress(125);
        seekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                image.setColorFilter(setBrightness(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekBarOpacity.setProgress(255);
        seekBarOpacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                image.setAlpha((float)progress/255);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekBarRotation.setProgress(360);
        seekBarRotation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               frm.setRotation((float)progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        edit_classics_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(is_save == true){
                    startActivity(new Intent(Edit_creation.this, Act_Home.class));
                    finish();
                }
                else{
                    if(image==null)
                    {
                        Toast.makeText(Edit_creation.this, "Failed to load image!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        final Dialog dialog = new Dialog(Edit_creation.this,R.style.ActivityDialog);
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
                                saveImage();
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
                                startActivity(new Intent(Edit_creation.this, Act_Home.class));
                                finish();
                            }
                        });

                    }
                }
            }
        });

        square_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutBrightness.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);
                Bitmap bm1_rect = Shape_Method.addBorderToBitmap(mBitmap1, 20, Color.WHITE);
                image.setImageBitmap(bm1_rect);

            }
        });

        round_rect_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutBrightness.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);
                Bitmap bm2_round_rect = Shape_Method.getRoundedCornerBitmap2(mBitmap1, Edit_creation.this);
                image.setImageBitmap(bm2_round_rect);

            }
        });


        round_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutBrightness.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);
                Bitmap bm3_round = Shape_Method.getCircleBitmap(mBitmap1);
                image.setImageBitmap(bm3_round);

            }
        });

        flip_horizontal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutBrightness.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);
                mBitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                Matrix matrix = new Matrix();
                matrix.preScale(-1.0f, 1.0f);
                Bitmap bm4_horizontal_flip = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
                image.setImageBitmap(bm4_horizontal_flip);
                mBitmap=bm4_horizontal_flip;

            }
        });

        flip_vertical.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutBrightness.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);
                mBitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                Matrix matrix = new Matrix();
                matrix.preScale(1.0f, -1.0f);
                Bitmap bm5_vertical_flip = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
                image.setImageBitmap(bm5_vertical_flip);
                mBitmap=bm5_vertical_flip;

            }
        });

        move_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutBrightness.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);
                FrameLayout.LayoutParams param_down = (FrameLayout.LayoutParams)frm.getLayoutParams();
                param_down.topMargin += 10;
                param_down.bottomMargin -= 10;
                frm.setLayoutParams(param_down);
            }
        });
        move_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutBrightness.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);
                FrameLayout.LayoutParams param_up = (FrameLayout.LayoutParams)frm.getLayoutParams();
                param_up.topMargin -= 10;
                param_up.bottomMargin += 10;
                frm.setLayoutParams(param_up);
            }
        });
        move_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutBrightness.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);
                FrameLayout.LayoutParams param_right = (FrameLayout.LayoutParams)frm.getLayoutParams();
                param_right.leftMargin += 10;
                param_right.rightMargin -= 10;
                frm.setLayoutParams(param_right);
            }
        });
        move_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutOpacity.setVisibility(View.GONE);
                linearLayoutBorder.setVisibility(View.GONE);
                linearLayoutBrightness.setVisibility(View.GONE);
                linearLayoutRotation.setVisibility(View.GONE);
                FrameLayout.LayoutParams param_left = (FrameLayout.LayoutParams)frm.getLayoutParams();
                param_left.leftMargin -= 10;
                param_left.rightMargin += 10;
                frm.setLayoutParams(param_left);
            }
        });

        imageButtonStyleBottomShape = (ImageButton) findViewById(R.id.imageButtonStyleBottomShape);
        linearLayoutStyleShape = (LinearLayout) findViewById(R.id.linearLayoutStyleShape);

        imageButtonStyleBottomShape.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                imageButtonStyleBottomShape.setBackgroundColor(getResources().getColor(R.color.clr_pri));
                imageButtonStyleBottomRatio.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonStyleBottomFrame.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomBG.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomText.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomSticker.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                linearLayoutStyleShape.setVisibility(View.VISIBLE);
                linearLayoutStyleFrame.setVisibility(View.GONE);
                linearLayoutStyleStickers.setVisibility(View.GONE);
                linearLayoutStyleRatio.setVisibility(View.GONE);
                linearLayoutStyleBG.setVisibility(View.GONE);
            }
        });


        imageButtonStyleBottomFrame = (ImageButton) findViewById(R.id.imageButtonStyleBottomFrame);
        linearLayoutStyleFrame = (LinearLayout) findViewById(R.id.linearLayoutStyleFrame);
        imageButtonStyleBottomFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonStyleBottomShape.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonStyleBottomRatio.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonStyleBottomFrame.setBackgroundColor(getResources().getColor(R.color.clr_pri));
                imageButtonMainBottomBG.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomText.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomSticker.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                linearLayoutStyleFrame.setVisibility(View.VISIBLE);
                linearLayoutStyleShape.setVisibility(View.GONE);
                linearLayoutStyleStickers.setVisibility(View.GONE);
                linearLayoutStyleRatio.setVisibility(View.GONE);
                linearLayoutStyleBG.setVisibility(View.GONE);
            }
        });

        imageButtonMainBottomBG = (ImageButton) findViewById(R.id.imageButtonMainBottomBG);
        linearLayoutStyleBG = (LinearLayout) findViewById(R.id.linearLayoutStyleBG);
        imageButtonMainBottomBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonStyleBottomShape.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonStyleBottomRatio.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonStyleBottomFrame.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomBG.setBackgroundColor(getResources().getColor(R.color.clr_pri));
                imageButtonMainBottomText.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomSticker.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                linearLayoutStyleBG.setVisibility(View.VISIBLE);
                linearLayoutStyleRatio.setVisibility(View.GONE);
                linearLayoutStyleShape.setVisibility(View.GONE);
                linearLayoutStyleStickers.setVisibility(View.GONE);
                linearLayoutStyleFrame.setVisibility(View.GONE);
            }
        });

        imageButtonStyleBottomRatio = (ImageButton) findViewById(R.id.imageButtonStyleBottomRatio);
        linearLayoutStyleRatio = (LinearLayout) findViewById(R.id.linearLayoutStyleRatio);
        imageButtonStyleBottomRatio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonStyleBottomShape.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonStyleBottomRatio.setBackgroundColor(getResources().getColor(R.color.clr_pri));
                imageButtonStyleBottomFrame.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomBG.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomText.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomSticker.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                linearLayoutStyleRatio.setVisibility(View.VISIBLE);
                linearLayoutStyleShape.setVisibility(View.GONE);
                linearLayoutStyleStickers.setVisibility(View.GONE);
                linearLayoutStyleFrame.setVisibility(View.GONE);
                linearLayoutStyleBG.setVisibility(View.GONE);

                Drawable background = linear_frm.getBackground();
                if (background != null){
                    linear_frm.setBackground(null);
                }
            }
        });

        final LinearLayout linearLayoutStyleRatioButtons = (LinearLayout) findViewById(R.id.linearLayoutStyleRatioButtons);
        linearLayoutStyleRatioButtons.setPadding(50, 0, 50, 0);

        for (int i = 0; i < ratioButtonResourceIds.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(ratioButtonResourceIds[i]);
            iv.setPadding(80, 50, 80, 50);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            linearLayoutStyleRatioButtons.addView(iv);
            final int finalI = i;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isText=true;
                    mStickerView.setLooked(false);
                    switch (finalI) {

                        case 0:
                            ll_params = new FrameLayout.LayoutParams(linear_frm.getWidth(), linear_frm.getHeight());
                            break;
                        case 1:
                            ll_params = new FrameLayout.LayoutParams(linear_frm.getWidth(), (int) ((linear_frm.getHeight() * 2.0f) / 3.0f));
                            break;
                        case 2:
                            ll_params = new FrameLayout.LayoutParams((int) ((linear_frm.getWidth() * 2.0f) / 3.0f), linear_frm.getHeight());
                            break;
                        case 3:
                            ll_params = new FrameLayout.LayoutParams(linear_frm.getWidth(), (int) ((linear_frm.getHeight() * 3.0f) / 4.0f));
                            break;
                        case 4:
                            ll_params = new FrameLayout.LayoutParams((int) ((linear_frm.getWidth() * 3.0f) / 4.0f), rl_frm.getHeight());
                            break;
                        case 5:
                            ll_params = new FrameLayout.LayoutParams(linear_frm.getWidth(), (int) (linear_frm.getHeight() / 2.0f));
                            break;
                        case 6:
                            ll_params = new FrameLayout.LayoutParams((int) (linear_frm.getWidth() / 2.0f), linear_frm.getHeight());
                            break;
                        default:
                            ll_params = new FrameLayout.LayoutParams(linear_frm.getWidth(), linear_frm.getHeight());
                            break;
                    }
                    ll_params.gravity= Gravity.CENTER;
                    frm.setLayoutParams(ll_params);

                }

            });
        }

        imageButtonMainBottomText = (ImageButton) findViewById(R.id.imageButtonMainBottomText);
        imageButtonMainBottomText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonStyleBottomShape.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonStyleBottomRatio.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonStyleBottomFrame.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomBG.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomText.setBackgroundColor(getResources().getColor(R.color.clr_pri));
                imageButtonMainBottomSticker.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                isText=true;
                mStickerView.setLooked(false);

                startActivity(new Intent (Edit_creation.this, TextAct.class));
            }
        });

        imageButtonMainBottomSticker = (ImageButton) findViewById(R.id.imageButtonMainBottomSticker);
        linearLayoutStyleStickers = (LinearLayout) findViewById(R.id.linearLayoutStyleStickers);
        imageButtonMainBottomSticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonStyleBottomShape.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonStyleBottomRatio.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonStyleBottomFrame.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomBG.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomText.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                imageButtonMainBottomSticker.setBackgroundColor(getResources().getColor(R.color.clr_pri));

                linearLayoutStyleStickers.setVisibility(View.VISIBLE);
                linearLayoutStyleShape.setVisibility(View.GONE);
                linearLayoutStyleRatio.setVisibility(View.GONE);
                linearLayoutStyleFrame.setVisibility(View.GONE);
                linearLayoutStyleBG.setVisibility(View.GONE);

                mStickerView.setLooked(false);
            }
        });

        classics_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage();
            }
        });

    }

    private void initView() {

        image = (ImageView) findViewById(R.id.image);
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

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    private void addStickerItem(int resId) {

        bitmap2 = BitmapFactory.decodeResource(getResources(), resId);
        mStickerView.addSticker(bitmap2);
        mStickerView.setLooked(false);

    }

    private void showTextImage(byte[] textId_) {
        System.gc();
        localBitmap = BitmapFactory.decodeByteArray(textId_, 0, textId_.length);
        mStickerView.addSticker(localBitmap);
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

    @Override
    protected void onResume() {
        super.onResume();

        if(isText)
        {
            if( app.isText())
            {
                app.setText(false);
            }
            else
            {
                mStickerView.setLooked(false);
                textId = app.getTextId();
                showTextImage(textId);
                isText=false;
            }
        }
    }

    private void saveImage() {

        mStickerView.saveclickdone();
        rl_frm.setDrawingCacheEnabled(true);
        rl_frm.layout(0, 0, rl_frm.getMeasuredWidth(), rl_frm.getMeasuredHeight());
        new AsyncTask<Void, Void, Void>() {
            ProgressDialog progressDialog = null;
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(Edit_creation.this,"Loading...","Saving photo to Gallery...");
            }
            @Override
            protected Void doInBackground(Void... arg0)
            {
                try
                {
                    String albam = getResources().getString(R.string.app_name);
                    String currentdateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()).toString();
                    String photoName = ApplicationProperties.Album_Photo_Prefix + currentdateTime;
                    if(albam != null && albam.isEmpty())
                    {
                        str = CommonUtils.saveToGallery(loadBitmapFromView(rl_frm), photoName,
                                getContentResolver(), "png");
                    }
                    else
                    {
                        str = CommonUtils.saveToGallery(loadBitmapFromView(rl_frm), albam, photoName,
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
                Toast.makeText(Edit_creation.this, "Image Save ", Toast.LENGTH_SHORT).show();
                Intent localIntent = new Intent(Edit_creation.this, MyCreation.class);
                startActivity(localIntent);
            };
        }.execute();
    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }

    public static PorterDuffColorFilter setBrightness(int progress) {
        if (progress >=    100)
        {
            int value = (int) (progress-100) * 255 / 100;

            return new PorterDuffColorFilter(Color.argb(value, 255, 255, 255), PorterDuff.Mode.SRC_OVER);

        }
        else
        {
            int value = (int) (100-progress) * 255 / 100;
            return new PorterDuffColorFilter(Color.argb(value, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);


        }
    }

    @Override
    public void onBackPressed() {
        if(is_save){
            startActivity(new Intent(Edit_creation.this, Act_Home.class));
            finish();
        }
        else{
            if(image==null)
            {
                Toast.makeText(Edit_creation.this, "Failed to load image!", Toast.LENGTH_LONG).show();
            }
            else
            {
                final Dialog dialog = new Dialog(Edit_creation.this,R.style.ActivityDialog);
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
                        saveImage();
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
                        startActivity(new Intent(Edit_creation.this, Act_Home.class));
                        finish();
                    }
                });

            }
        }
    }
}

package com.app.pipeditorpro.mirrorlib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import androidx.fragment.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.app.pipeditorpro.Act_Home;
import com.app.pipeditorpro.R;
import com.app.pipeditorpro.adapter.FarmeImageAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MirrorAct extends Activity
{
    public static final int INDEX_MIRROR = 0;
    public static final int INDEX_MIRROR_3D = 1;
    public static final int INDEX_MIRROR_ADJUSTMENT = 5;
    public static final int INDEX_MIRROR_EFFECT = 3;
    public static final int INDEX_MIRROR_FRAME = 4;
    public static final int INDEX_MIRROR_INVISIBLE_VIEW = 7;
    public static final int INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX = 4;
    public static final int INDEX_MIRROR_RATIO = 2;
    static final int SAVE_IMAGE_ID = 1348;
    public static final int TAB_SIZE = 6;
    private static final String TAG;
    int D3_BUTTON_SIZE;
    int MIRROR_BUTTON_SIZE;
    int RATIO_BUTTON_SIZE;
    Activity activity;
    FragmentActivity activityFragment;
    Context context;
    int currentSelectedTabIndex;
    Button[] d3ButtonArray;
    private int[] d3resList;

    Bitmap filterBitmap;
    int initialYPos;
    RelativeLayout mainLayout;
    Matrix matrixMirror1;
    Matrix matrixMirror2;
    Matrix matrixMirror3;
    Matrix matrixMirror4;
    Button[] mirrorButtonArray;
    MirrorView mirrorView;
    int mode;
    float mulX;
    float mulY;
    Button[] ratioButtonArray;
    AlertDialog saveImageAlert;
    int screenHeightPixels;
    int screenWidthPixels;
    private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;
    public static Bitmap sourceBitmap;
    int stickerFragemntContinerId;

    View[] tabButtonList;
    FrameLayout textAndStickerViewContainer;
    int textFragemntContinerId;

    float topOffset;
    float totalOffset;
    ViewFlipper viewFlipper;
    Gallery frameGallery2;

    String resultPath;

    class MirrorView extends View {
        final Matrix f629I;
        int currentModeIndex;
        Bitmap d3Bitmap;
        boolean d3Mode;
        int defaultColor;
        RectF destRect1;
        RectF destRect1X;
        RectF destRect1Y;
        RectF destRect2;
        RectF destRect2X;
        RectF destRect2Y;
        RectF destRect3;
        RectF destRect4;
        float distance;
        boolean drawSavedImage;
        RectF dstRectPaper1;
        RectF dstRectPaper2;
        RectF dstRectPaper3;
        RectF dstRectPaper4;
        Bitmap frameBitmap;
        Paint framePaint;
        RectF frameSolo;
        int height;
        int heightPixels;
        final int initialMode;
        boolean isTouchStartedLeft;
        boolean isTouchStartedTop;
        boolean isVerticle;
        Matrix m1;
        Matrix m2;
        Matrix m3;
        Matrix matrixSolo;
        MirrorMode[] mirrorModeList;
        MirrorMode modeX;
        MirrorMode modeX10;
        MirrorMode modeX11;
        MirrorMode modeX12;
        MirrorMode modeX13;
        MirrorMode modeX14;
        MirrorMode modeX15;
        MirrorMode modeX16;
        MirrorMode modeX17;
        MirrorMode modeX18;
        MirrorMode modeX19;
        MirrorMode modeX2;
        MirrorMode modeX20;
        MirrorMode modeX3;
        MirrorMode modeX4;
        MirrorMode modeX5;
        MirrorMode modeX6;
        MirrorMode modeX7;
        MirrorMode modeX8;
        MirrorMode modeX9;
        float oldX;
        float oldY;
        RectF srcRect1;
        RectF srcRect2;
        RectF srcRect3;
        RectF srcRectPaper;
        int tMode1;
        int tMode2;
        int tMode3;
        Paint textRectPaint;
        RectF totalArea1;
        RectF totalArea2;
        RectF totalArea3;
        int width;
        int widthPixels;

        public MirrorView(Context context, int screenWidth, int screenHeight)
        {
            super(context);
            this.f629I = new Matrix();
            this.framePaint = new Paint();
            this.isVerticle = false;
            this.defaultColor = -2236963;
            this.mirrorModeList = new MirrorMode[21];
            this.initialMode = MirrorAct.INDEX_MIRROR_3D;
            this.currentModeIndex = MirrorAct.INDEX_MIRROR_3D;
            this.drawSavedImage = false;
            this.d3Mode = false;
            this.textRectPaint = new Paint(MirrorAct.INDEX_MIRROR_3D);
            this.m1 = new Matrix();
            this.m2 = new Matrix();
            this.m3 = new Matrix();
            this.frameSolo = new RectF();
            this.width = MirrorAct.this.sourceBitmap.getWidth();
            this.height = MirrorAct.this.sourceBitmap.getHeight();
            this.widthPixels = screenWidth;
            this.heightPixels = screenHeight;
            createMatrix(this.widthPixels, this.heightPixels);
            createRectX(this.widthPixels, this.heightPixels);
            createRectY(this.widthPixels, this.heightPixels);
            createRectXY(this.widthPixels, this.heightPixels);
            createModes();
            this.framePaint.setAntiAlias(true);
            this.framePaint.setFilterBitmap(true);
            this.framePaint.setDither(true);
            this.textRectPaint.setColor(this.defaultColor);
            float viewWidth = (float) this.widthPixels;
            float viewHeight = ((float) this.heightPixels) - MirrorAct.this.totalOffset;
            float scaleSolo = Math.min(viewWidth / ((float) this.width), viewHeight / ((float) this.height));
            if (this.matrixSolo == null) {
                this.matrixSolo = new Matrix();
            }
            this.matrixSolo.reset();
            this.matrixSolo.postScale(scaleSolo, scaleSolo);
            this.matrixSolo.postTranslate((viewWidth - (((float) this.width) * scaleSolo)) / 2.0f, MirrorAct.this.topOffset + ((viewHeight - (((float) this.height) * scaleSolo)) / 2.0f));
        }

        private void reset(int widthPixels, int heightPixels, boolean invalidate) {
            createMatrix(widthPixels, heightPixels);
            createRectX(widthPixels, heightPixels);
            createRectY(widthPixels, heightPixels);
            createRectXY(widthPixels, heightPixels);
            createModes();
            if (invalidate) {
                postInvalidate();
            }
        }

        @SuppressLint("WrongThread")
        private String saveBitmap(boolean saveToFile, int widthPixel, int heightPixel)
        {
            float offX;
            float offY;
            float btmScale;
            Bitmap savedBitmap;
            Canvas bitmapCanvas;
            Matrix matrixS = new Matrix();

            MirrorMode saveMode;
            if (this.currentModeIndex == 0) {
                float w2 = (float) this.width;
                float h2 = (float) this.height;
                int viewWidth = this.widthPixels;
                int viewHeight = (int) (((float) this.heightPixels) - MirrorAct.this.totalOffset);
                float scaleSolo = Math.min(((float) viewWidth) / w2, ((float) viewHeight) / h2);
                offX = (((float) viewWidth) - (w2 * scaleSolo)) / 2.0f;
                offY = MirrorAct.this.topOffset + ((((float) viewHeight) - (h2 * scaleSolo)) / 2.0f);
                viewWidth = (int) (w2 * scaleSolo);
                viewHeight = (int) (h2 * scaleSolo);
                btmScale = ((float) Utility.maxSizeForSave()) / ((float) Math.max(viewHeight, viewWidth));

                int newBtmWidth = (int) (((float) viewWidth) * btmScale);
                int newBtmHeight = (int) (((float) viewHeight) * btmScale);
                if (newBtmWidth <= 0) {
                    newBtmWidth = viewWidth;
                }
                if (newBtmHeight <= 0) {
                    newBtmHeight = viewHeight;
                }
                savedBitmap = Bitmap.createBitmap(newBtmWidth, newBtmHeight, Config.ARGB_8888);
                bitmapCanvas = new Canvas(savedBitmap);
                matrixS.reset();
                matrixS.postTranslate(-offX, -offY);
                matrixS.postScale(btmScale, btmScale);
                bitmapCanvas.setMatrix(matrixS);
                saveMode = this.mirrorModeList[this.currentModeIndex];
                if (MirrorAct.this.filterBitmap == null || MirrorAct.this.filterBitmap.isRecycled()) {
                    drawMode(bitmapCanvas, MirrorAct.this.sourceBitmap, saveMode, matrixS);
                } else {
                    drawMode(bitmapCanvas, MirrorAct.this.filterBitmap, saveMode, matrixS);
                }
            } else {
                btmScale = ((float) Utility.maxSizeForSave()) / ((float) Math.min(widthPixel, heightPixel));
                if (MirrorAct.this.mulY > MirrorAct.this.mulX) {
                    btmScale = (MirrorAct.this.mulX * btmScale) / MirrorAct.this.mulY;
                }
                if (btmScale <= 0.0f) {
                    btmScale = 1.0f;
                }
                int wP = Math.round(((float) widthPixel) * btmScale);
                int wH = Math.round(((float) heightPixel) * btmScale);
                RectF srcRect = this.mirrorModeList[this.currentModeIndex].getSrcRect();
                reset(wP, wH, false);
                int btmWidth = Math.round(MirrorAct.this.mirrorView.getCurrentMirrorMode().rectTotalArea.width());
                int btmHeight = Math.round(MirrorAct.this.mirrorView.getCurrentMirrorMode().rectTotalArea.height());
                if (btmWidth % MirrorAct.INDEX_MIRROR_RATIO == MirrorAct.INDEX_MIRROR_3D) {
                    btmWidth--;
                }
                if (btmHeight % MirrorAct.INDEX_MIRROR_RATIO == MirrorAct.INDEX_MIRROR_3D) {
                    btmHeight--;
                }
                savedBitmap = Bitmap.createBitmap(btmWidth, btmHeight, Config.ARGB_8888);
                bitmapCanvas = new Canvas(savedBitmap);
                matrixS.reset();
                matrixS.postTranslate(((float) (-(wP - btmWidth))) / 2.0f, ((float) (-(wH - btmHeight))) / 2.0f);
                saveMode = this.mirrorModeList[this.currentModeIndex];
                saveMode.setSrcRect(srcRect);
                if (MirrorAct.this.filterBitmap == null) {
                    drawMode(bitmapCanvas, MirrorAct.this.sourceBitmap, saveMode, matrixS);
                } else {
                    drawMode(bitmapCanvas, MirrorAct.this.filterBitmap, saveMode, matrixS);
                }
                if (!(!this.d3Mode || this.d3Bitmap == null || this.d3Bitmap.isRecycled())) {
                    bitmapCanvas.setMatrix(matrixS);
                    bitmapCanvas.drawBitmap(this.d3Bitmap, null, this.mirrorModeList[this.currentModeIndex].rectTotalArea, this.framePaint);
                }
                offX = ((float) (wP - btmWidth)) / (2.0f * btmScale);
                offY = ((float) (wH - btmHeight)) / (2.0f * btmScale);
            }
            if (!(this.frameBitmap == null || this.frameBitmap.isRecycled())) {
                RectF area;
                if (this.currentModeIndex == 0) {
                    area = new RectF();
                    area.set(0.0f, 0.0f, (float) this.width, (float) this.height);
                    this.matrixSolo.mapRect(area);
                    bitmapCanvas.setMatrix(matrixS);
                    bitmapCanvas.drawBitmap(this.frameBitmap, null, area, this.framePaint);
                } else {
                    area = new RectF();
                    area.set(this.mirrorModeList[this.currentModeIndex].rectTotalArea);
                    bitmapCanvas.setMatrix(matrixS);
                    bitmapCanvas.drawBitmap(this.frameBitmap, null, area, this.framePaint);
                }
            }
            String resultPath = null;
            if (saveToFile)
            {
                resultPath = Environment.getExternalStorageDirectory().toString() + MirrorAct.this.getString(R.string.directory) + String.valueOf(System.currentTimeMillis()) + ".jpg";
                new File(resultPath).getParentFile().mkdirs();
                try
                {
                    OutputStream out = new FileOutputStream(resultPath);
                    savedBitmap.compress(CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                } catch (IOException e2)
                {
                    e2.printStackTrace();
                }
            }
            savedBitmap.recycle();
            reset(widthPixel, heightPixel, false);
            this.mirrorModeList[this.currentModeIndex].setSrcRect(this.mirrorModeList[this.currentModeIndex].getSrcRect());
            return resultPath;
        }

        private void setCurrentMode(int index) {
            this.currentModeIndex = index;
        }

        public MirrorMode getCurrentMirrorMode() {
            return this.mirrorModeList[this.currentModeIndex];
        }

        private void createModes() {
            this.modeX = new MirrorMode(MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX, this.srcRect3, this.destRect1, this.destRect1, this.destRect3, this.destRect3, MirrorAct.this.matrixMirror1, this.f629I, MirrorAct.this.matrixMirror1, this.tMode3, this.totalArea3);
            this.modeX2 = new MirrorMode(MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX, this.srcRect3, this.destRect1, this.destRect4, this.destRect1, this.destRect4, MirrorAct.this.matrixMirror1, MirrorAct.this.matrixMirror1, this.f629I, this.tMode3, this.totalArea3);
            this.modeX3 = new MirrorMode(MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX, this.srcRect3, this.destRect3, this.destRect2, this.destRect3, this.destRect2, MirrorAct.this.matrixMirror1, MirrorAct.this.matrixMirror1, this.f629I, this.tMode3, this.totalArea3);
            this.modeX8 = new MirrorMode(MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX, this.srcRect3, this.destRect1, this.destRect1, this.destRect1, this.destRect1, MirrorAct.this.matrixMirror1, MirrorAct.this.matrixMirror2, MirrorAct.this.matrixMirror3, this.tMode3, this.totalArea3);
            int m9TouchMode = MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX;
            if (this.tMode3 == 0) {
                m9TouchMode = MirrorAct.INDEX_MIRROR;
            }
            this.modeX9 = new MirrorMode(MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX, this.srcRect3, this.destRect2, this.destRect2, this.destRect2, this.destRect2, MirrorAct.this.matrixMirror1, MirrorAct.this.matrixMirror2, MirrorAct.this.matrixMirror3, m9TouchMode, this.totalArea3);
            int m10TouchMode = MirrorAct.INDEX_MIRROR_EFFECT;
            if (this.tMode3 == MirrorAct.INDEX_MIRROR_3D) {
                m10TouchMode = MirrorAct.INDEX_MIRROR_3D;
            }
            this.modeX10 = new MirrorMode(MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX, this.srcRect3, this.destRect3, this.destRect3, this.destRect3, this.destRect3, MirrorAct.this.matrixMirror1, MirrorAct.this.matrixMirror2, MirrorAct.this.matrixMirror3, m10TouchMode, this.totalArea3);
            int m11TouchMode = MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX;
            if (this.tMode3 == 0) {
                m11TouchMode = MirrorAct.INDEX_MIRROR_EFFECT;
            }
            this.modeX11 = new MirrorMode(MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX, this.srcRect3, this.destRect4, this.destRect4, this.destRect4, this.destRect4, MirrorAct.this.matrixMirror1, MirrorAct.this.matrixMirror2, MirrorAct.this.matrixMirror3, m11TouchMode, this.totalArea3);
            this.modeX4 = new MirrorMode(MirrorAct.INDEX_MIRROR_RATIO, this.srcRect1, this.destRect1X, this.destRect1X, MirrorAct.this.matrixMirror1, this.tMode1, this.totalArea1);
            int m5TouchMode = MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX;
            if (this.tMode1 == 0) {
                m5TouchMode = MirrorAct.INDEX_MIRROR;
            } else if (this.tMode1 == MirrorAct.INDEX_MIRROR_ADJUSTMENT) {
                m5TouchMode = MirrorAct.INDEX_MIRROR_ADJUSTMENT;
            }
            this.modeX5 = new MirrorMode(MirrorAct.INDEX_MIRROR_RATIO, this.srcRect1, this.destRect2X, this.destRect2X, MirrorAct.this.matrixMirror1, m5TouchMode, this.totalArea1);
            this.modeX6 = new MirrorMode(MirrorAct.INDEX_MIRROR_RATIO, this.srcRect2, this.destRect1Y, this.destRect1Y, MirrorAct.this.matrixMirror2, this.tMode2, this.totalArea2);
            int m7TouchMode = MirrorAct.INDEX_MIRROR_EFFECT;
            if (this.tMode2 == MirrorAct.INDEX_MIRROR_3D) {
                m7TouchMode = MirrorAct.INDEX_MIRROR_3D;
            } else if (this.tMode2 == MirrorAct.TAB_SIZE) {
                m7TouchMode = MirrorAct.TAB_SIZE;
            }
            this.modeX7 = new MirrorMode(MirrorAct.INDEX_MIRROR_RATIO, this.srcRect2, this.destRect2Y, this.destRect2Y, MirrorAct.this.matrixMirror2, m7TouchMode, this.totalArea2);
            this.modeX12 = new MirrorMode(MirrorAct.INDEX_MIRROR_RATIO, this.srcRect1, this.destRect1X, this.destRect2X, MirrorAct.this.matrixMirror4, this.tMode1, this.totalArea1);
            this.modeX13 = new MirrorMode(MirrorAct.INDEX_MIRROR_RATIO, this.srcRect2, this.destRect1Y, this.destRect2Y, MirrorAct.this.matrixMirror4, this.tMode2, this.totalArea2);
            this.modeX14 = new MirrorMode(MirrorAct.INDEX_MIRROR_RATIO, this.srcRect1, this.destRect1X, this.destRect1X, MirrorAct.this.matrixMirror3, this.tMode1, this.totalArea1);
            this.modeX15 = new MirrorMode(MirrorAct.INDEX_MIRROR_RATIO, this.srcRect2, this.destRect1Y, this.destRect1Y, MirrorAct.this.matrixMirror3, this.tMode2, this.totalArea2);
            this.modeX16 = new MirrorMode(MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX, this.srcRectPaper, this.dstRectPaper1, this.dstRectPaper2, this.dstRectPaper3, this.dstRectPaper4, MirrorAct.this.matrixMirror1, MirrorAct.this.matrixMirror1, this.f629I, this.tMode1, this.totalArea1);
            this.modeX17 = new MirrorMode(MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX, this.srcRectPaper, this.dstRectPaper1, this.dstRectPaper3, this.dstRectPaper3, this.dstRectPaper1, this.f629I, MirrorAct.this.matrixMirror1, MirrorAct.this.matrixMirror1, this.tMode1, this.totalArea1);
            this.modeX18 = new MirrorMode(MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX, this.srcRectPaper, this.dstRectPaper2, this.dstRectPaper4, this.dstRectPaper2, this.dstRectPaper4, this.f629I, MirrorAct.this.matrixMirror1, MirrorAct.this.matrixMirror1, this.tMode1, this.totalArea1);
            this.modeX19 = new MirrorMode(MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX, this.srcRectPaper, this.dstRectPaper1, this.dstRectPaper2, this.dstRectPaper2, this.dstRectPaper1, this.f629I, MirrorAct.this.matrixMirror1, MirrorAct.this.matrixMirror1, this.tMode1, this.totalArea1);
            this.modeX20 = new MirrorMode(MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX, this.srcRectPaper, this.dstRectPaper4, this.dstRectPaper3, this.dstRectPaper3, this.dstRectPaper4, this.f629I, MirrorAct.this.matrixMirror1, MirrorAct.this.matrixMirror1, this.tMode1, this.totalArea1);
            this.mirrorModeList[MirrorAct.INDEX_MIRROR] = this.modeX4;
            this.mirrorModeList[MirrorAct.INDEX_MIRROR_3D] = this.modeX4;
            this.mirrorModeList[MirrorAct.INDEX_MIRROR_RATIO] = this.modeX5;
            this.mirrorModeList[MirrorAct.INDEX_MIRROR_EFFECT] = this.modeX6;
            this.mirrorModeList[MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX] = this.modeX7;
            this.mirrorModeList[MirrorAct.INDEX_MIRROR_ADJUSTMENT] = this.modeX8;
            this.mirrorModeList[MirrorAct.TAB_SIZE] = this.modeX9;
            this.mirrorModeList[MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW] = this.modeX10;
            this.mirrorModeList[8] = this.modeX11;
            this.mirrorModeList[9] = this.modeX12;
            this.mirrorModeList[10] = this.modeX13;
            this.mirrorModeList[11] = this.modeX14;
            this.mirrorModeList[12] = this.modeX15;
            this.mirrorModeList[13] = this.modeX;
            this.mirrorModeList[14] = this.modeX2;
            this.mirrorModeList[15] = this.modeX3;
            this.mirrorModeList[16] = this.modeX7;
            this.mirrorModeList[17] = this.modeX17;
            this.mirrorModeList[18] = this.modeX18;
            this.mirrorModeList[19] = this.modeX19;
            this.mirrorModeList[20] = this.modeX20;
        }

        public Bitmap getBitmap() {
            setDrawingCacheEnabled(true);
            buildDrawingCache();
            Bitmap bmp = Bitmap.createBitmap(getDrawingCache());
            setDrawingCacheEnabled(false);
            return bmp;
        }

        public void setFrame(int index)
        {
            if (!(this.frameBitmap == null || this.frameBitmap.isRecycled()))
            {
                this.frameBitmap.recycle();
                this.frameBitmap = null;
            }
            if (index == 0)
            {
                postInvalidate();
                return;
            }
            this.frameBitmap = BitmapFactory.decodeResource(getResources(), LibUtility.borderRes[index]);
            postInvalidate();
        }

        private void createMatrix(int widthPixels, int heightPixels) {
            this.f629I.reset();
            MirrorAct.this.matrixMirror1.reset();
            MirrorAct.this.matrixMirror1.postScale(-1.0f, 1.0f);
            MirrorAct.this.matrixMirror1.postTranslate((float) widthPixels, 0.0f);
            MirrorAct.this.matrixMirror2.reset();
            MirrorAct.this.matrixMirror2.postScale(1.0f, -1.0f);
            MirrorAct.this.matrixMirror2.postTranslate(0.0f, (float) heightPixels);
            MirrorAct.this.matrixMirror3.reset();
            MirrorAct.this.matrixMirror3.postScale(-1.0f, -1.0f);
            MirrorAct.this.matrixMirror3.postTranslate((float) widthPixels, (float) heightPixels);
        }

        private void createRectX(int widthPixels, int heightPixels) {
            float destH = ((float) widthPixels) * (MirrorAct.this.mulY / MirrorAct.this.mulX);
            float destW = ((float) widthPixels) / 2.0f;
            float destX = 0.0f;
            float destY = (float) MirrorAct.this.initialYPos;
            if (destH > ((float) heightPixels)) {
                destH = (float) heightPixels;
                destW = ((MirrorAct.this.mulX / MirrorAct.this.mulY) * destH) / 2.0f;
                destX = (((float) widthPixels) / 2.0f) - destW;
            }
            destY = ((float) MirrorAct.this.initialYPos) + ((((float) heightPixels) - destH) / 2.0f);
            float srcX = 0.0f;
            float srcY = 0.0f;
            float srcX2 = (float) this.width;
            float srcY2 = (float) this.height;
            this.destRect1X = new RectF(destX, destY, destW + destX, destH + destY);
            float destXX = destX + destW;
            this.destRect2X = new RectF(destXX, destY, destW + destXX, destH + destY);
            this.totalArea1 = new RectF(destX, destY, destW + destXX, destH + destY);
            this.tMode1 = MirrorAct.INDEX_MIRROR_3D;
            if (MirrorAct.this.mulX * ((float) this.height) <= (MirrorAct.this.mulY * 2.0f) * ((float) this.width)) {
                srcX = (((float) this.width) - (((MirrorAct.this.mulX / MirrorAct.this.mulY) * ((float) this.height)) / 2.0f)) / 2.0f;
                srcX2 = srcX + (((MirrorAct.this.mulX / MirrorAct.this.mulY) * ((float) this.height)) / 2.0f);
            } else {
                srcY = (((float) this.height) - (((float) (this.width * MirrorAct.INDEX_MIRROR_RATIO)) * (MirrorAct.this.mulY / MirrorAct.this.mulX))) / 2.0f;
                srcY2 = srcY + (((float) (this.width * MirrorAct.INDEX_MIRROR_RATIO)) * (MirrorAct.this.mulY / MirrorAct.this.mulX));
                this.tMode1 = MirrorAct.INDEX_MIRROR_ADJUSTMENT;
            }
            this.srcRect1 = new RectF(srcX, srcY, srcX2, srcY2);
            this.srcRectPaper = new RectF(srcX, srcY, ((srcX2 - srcX) / 2.0f) + srcX, srcY2);
            float destWPapar = destW / 2.0f;
            this.dstRectPaper1 = new RectF(destX, destY, destWPapar + destX, destH + destY);
            float dextXP = destX + destWPapar;
            this.dstRectPaper2 = new RectF(dextXP, destY, destWPapar + dextXP, destH + destY);
            dextXP += destWPapar;
            this.dstRectPaper3 = new RectF(dextXP, destY, destWPapar + dextXP, destH + destY);
            dextXP += destWPapar;
            this.dstRectPaper4 = new RectF(dextXP, destY, destWPapar + dextXP, destH + destY);
        }

        private void createRectY(int widthPixels, int heightPixels) {
            float destH = (((float) widthPixels) * (MirrorAct.this.mulY / MirrorAct.this.mulX)) / 2.0f;
            float destW = (float) widthPixels;
            float destX = 0.0f;
            float destY = (float) MirrorAct.this.initialYPos;
            if (destH > ((float) heightPixels)) {
                destH = (float) heightPixels;
                destW = ((MirrorAct.this.mulX / MirrorAct.this.mulY) * destH) / 2.0f;
                destX = (((float) widthPixels) / 2.0f) - destW;
            }
            destY = ((float) MirrorAct.this.initialYPos) + ((((float) heightPixels) - (2.0f * destH)) / 2.0f);
            this.destRect1Y = new RectF(destX, destY, destW + destX, destH + destY);
            float destYY = destY + destH;
            this.destRect2Y = new RectF(destX, destYY, destW + destX, destH + destYY);
            this.totalArea2 = new RectF(destX, destY, destW + destX, destH + destYY);
            float srcX = 0.0f;
            float srcY = 0.0f;
            float srcX2 = (float) this.width;
            float srcY2 = (float) this.height;
            this.tMode2 = MirrorAct.INDEX_MIRROR;
            if ((MirrorAct.this.mulX * 2.0f) * ((float) this.height) > MirrorAct.this.mulY * ((float) this.width)) {
                srcY = (((float) this.height) - (((MirrorAct.this.mulY / MirrorAct.this.mulX) * ((float) this.width)) / 2.0f)) / 2.0f;
                srcY2 = srcY + (((MirrorAct.this.mulY / MirrorAct.this.mulX) * ((float) this.width)) / 2.0f);
            } else {
                srcX = (((float) this.width) - (((float) (this.height * MirrorAct.INDEX_MIRROR_RATIO)) * (MirrorAct.this.mulX / MirrorAct.this.mulY))) / 2.0f;
                srcX2 = srcX + (((float) (this.height * MirrorAct.INDEX_MIRROR_RATIO)) * (MirrorAct.this.mulX / MirrorAct.this.mulY));
                this.tMode2 = MirrorAct.TAB_SIZE;
            }
            this.srcRect2 = new RectF(srcX, srcY, srcX2, srcY2);
        }

        private void createRectXY(int widthPixels, int heightPixels) {
            float destH = (((float) widthPixels) * (MirrorAct.this.mulY / MirrorAct.this.mulX)) / 2.0f;
            float destW = ((float) widthPixels) / 2.0f;
            float destX = 0.0f;
            float destY = (float) MirrorAct.this.initialYPos;
            if (destH > ((float) heightPixels)) {
                destH = (float) heightPixels;
                destW = ((MirrorAct.this.mulX / MirrorAct.this.mulY) * destH) / 2.0f;
                destX = (((float) widthPixels) / 2.0f) - destW;
            }
            destY = ((float) MirrorAct.this.initialYPos) + ((((float) heightPixels) - (2.0f * destH)) / 2.0f);
            float srcX = 0.0f;
            float srcY = 0.0f;
            float srcX2 = (float) this.width;
            float srcY2 = (float) this.height;
            this.destRect1 = new RectF(destX, destY, destW + destX, destH + destY);
            float destX2 = destX + destW;
            this.destRect2 = new RectF(destX2, destY, destW + destX2, destH + destY);
            float destY2 = destY + destH;
            this.destRect3 = new RectF(destX, destY2, destW + destX, destH + destY2);
            this.destRect4 = new RectF(destX2, destY2, destW + destX2, destH + destY2);
            this.totalArea3 = new RectF(destX, destY, destW + destX2, destH + destY2);
            if (MirrorAct.this.mulX * ((float) this.height) <= MirrorAct.this.mulY * ((float) this.width)) {
                srcX = (((float) this.width) - ((MirrorAct.this.mulX / MirrorAct.this.mulY) * ((float) this.height))) / 2.0f;
                srcX2 = srcX + ((MirrorAct.this.mulX / MirrorAct.this.mulY) * ((float) this.height));
                this.tMode3 = MirrorAct.INDEX_MIRROR_3D;
            } else {
                srcY = (((float) this.height) - (((float) this.width) * (MirrorAct.this.mulY / MirrorAct.this.mulX))) / 2.0f;
                srcY2 = srcY + (((float) this.width) * (MirrorAct.this.mulY / MirrorAct.this.mulX));
                this.tMode3 = MirrorAct.INDEX_MIRROR;
            }
            this.srcRect3 = new RectF(srcX, srcY, srcX2, srcY2);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawColor(this.defaultColor);
            if (MirrorAct.this.filterBitmap == null) {
                drawMode(canvas, MirrorAct.this.sourceBitmap, this.mirrorModeList[this.currentModeIndex], this.f629I);
            } else {
                drawMode(canvas, MirrorAct.this.filterBitmap, this.mirrorModeList[this.currentModeIndex], this.f629I);
            }
            if (!(!this.d3Mode || this.d3Bitmap == null || this.d3Bitmap.isRecycled())) {
                canvas.setMatrix(this.f629I);
                canvas.drawBitmap(this.d3Bitmap, null, this.mirrorModeList[this.currentModeIndex].rectTotalArea, this.framePaint);
            }
            if (!(this.frameBitmap == null || this.frameBitmap.isRecycled())) {
                if (this.currentModeIndex == 0) {
                    this.frameSolo.set(0.0f, 0.0f, (float) this.width, (float) this.height);
                    this.matrixSolo.mapRect(this.frameSolo);
                    canvas.setMatrix(this.f629I);
                    canvas.drawBitmap(this.frameBitmap, null, this.frameSolo, this.framePaint);
                } else {
                    canvas.setMatrix(this.f629I);
                    canvas.drawBitmap(this.frameBitmap, null, this.mirrorModeList[this.currentModeIndex].rectTotalArea, this.framePaint);
                }
            }
            super.onDraw(canvas);
        }

        private void drawMode(Canvas canvas, Bitmap bitmap, MirrorMode mirrorMode, Matrix matrix) {
            canvas.setMatrix(matrix);
            if (this.currentModeIndex != 0) {
                if (!(bitmap == null || bitmap.isRecycled())) {
                    canvas.drawBitmap(bitmap, mirrorMode.getDrawBitmapSrc(), mirrorMode.rect1, this.framePaint);
                }
                this.m1.set(mirrorMode.matrix1);
                this.m1.postConcat(matrix);
                canvas.setMatrix(this.m1);
                if (!(bitmap == null || bitmap.isRecycled())) {
                    canvas.drawBitmap(bitmap, mirrorMode.getDrawBitmapSrc(), mirrorMode.rect2, this.framePaint);
                }
                if (mirrorMode.count == MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX) {
                    this.m2.set(mirrorMode.matrix2);
                    this.m2.postConcat(matrix);
                    canvas.setMatrix(this.m2);
                    if (!(bitmap == null || bitmap.isRecycled())) {
                        canvas.drawBitmap(bitmap, mirrorMode.getDrawBitmapSrc(), mirrorMode.rect3, this.framePaint);
                    }
                    this.m3.set(mirrorMode.matrix3);
                    this.m3.postConcat(matrix);
                    canvas.setMatrix(this.m3);
                    if (bitmap != null && !bitmap.isRecycled()) {
                        canvas.drawBitmap(bitmap, mirrorMode.getDrawBitmapSrc(), mirrorMode.rect4, this.framePaint);
                    }
                }
            } else if (bitmap != null && !bitmap.isRecycled()) {
                canvas.drawBitmap(bitmap, this.matrixSolo, this.framePaint);
            }
        }

        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()) {
                case MirrorAct.INDEX_MIRROR /*0*/:
                    if (x < ((float) (MirrorAct.this.screenWidthPixels / MirrorAct.INDEX_MIRROR_RATIO))) {
                        this.isTouchStartedLeft = true;
                    } else {
                        this.isTouchStartedLeft = false;
                    }
                    if (y < ((float) (MirrorAct.this.screenHeightPixels / MirrorAct.INDEX_MIRROR_RATIO))) {
                        this.isTouchStartedTop = true;
                    } else {
                        this.isTouchStartedTop = false;
                    }
                    this.oldX = x;
                    this.oldY = y;
                    break;
                case MirrorAct.INDEX_MIRROR_RATIO /*2*/:
                    moveGrid(this.mirrorModeList[this.currentModeIndex].getSrcRect(), x - this.oldX, y - this.oldY);
                    this.mirrorModeList[this.currentModeIndex].updateBitmapSrc();
                    this.oldX = x;
                    this.oldY = y;
                    break;
            }
            postInvalidate();
            return true;
        }

        void moveGrid(RectF srcRect, float x, float y) {
            if (this.mirrorModeList[this.currentModeIndex].touchMode == MirrorAct.INDEX_MIRROR_3D || this.mirrorModeList[this.currentModeIndex].touchMode == MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX || this.mirrorModeList[this.currentModeIndex].touchMode == MirrorAct.TAB_SIZE) {
                if (this.mirrorModeList[this.currentModeIndex].touchMode == MirrorAct.INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX) {
                    x *= -1.0f;
                }
                if (this.isTouchStartedLeft && this.mirrorModeList[this.currentModeIndex].touchMode != MirrorAct.TAB_SIZE) {
                    x *= -1.0f;
                }
                if (srcRect.left + x < 0.0f) {
                    x = -srcRect.left;
                }
                if (srcRect.right + x >= ((float) this.width)) {
                    x = ((float) this.width) - srcRect.right;
                }
                srcRect.left += x;
                srcRect.right += x;
            } else if (this.mirrorModeList[this.currentModeIndex].touchMode == 0 || this.mirrorModeList[this.currentModeIndex].touchMode == MirrorAct.INDEX_MIRROR_EFFECT || this.mirrorModeList[this.currentModeIndex].touchMode == MirrorAct.INDEX_MIRROR_ADJUSTMENT) {
                if (this.mirrorModeList[this.currentModeIndex].touchMode == MirrorAct.INDEX_MIRROR_EFFECT) {
                    y *= -1.0f;
                }
                if (this.isTouchStartedTop && this.mirrorModeList[this.currentModeIndex].touchMode != MirrorAct.INDEX_MIRROR_ADJUSTMENT) {
                    y *= -1.0f;
                }
                if (srcRect.top + y < 0.0f) {
                    y = -srcRect.top;
                }
                if (srcRect.bottom + y >= ((float) this.height)) {
                    y = ((float) this.height) - srcRect.bottom;
                }
                srcRect.top += y;
                srcRect.bottom += y;
            }
        }
    }

    public MirrorAct() {
        this.context = this;
        this.matrixMirror1 = new Matrix();
        this.matrixMirror2 = new Matrix();
        this.matrixMirror3 = new Matrix();
        this.matrixMirror4 = new Matrix();
        this.activity = this;
        this.initialYPos = INDEX_MIRROR;
        this.textFragemntContinerId = R.id.text_view_fragment_container;
        this.d3resList = new int[]{R.drawable.mirror_3d_14, R.drawable.mirror_3d_14, R.drawable.mirror_3d_10, R.drawable.mirror_3d_10, R.drawable.mirror_3d_11, R.drawable.mirror_3d_11, R.drawable.mirror_3d_4, R.drawable.mirror_3d_4, R.drawable.mirror_3d_3, R.drawable.mirror_3d_3, R.drawable.mirror_3d_1, R.drawable.mirror_3d_1, R.drawable.mirror_3d_6, R.drawable.mirror_3d_6, R.drawable.mirror_3d_13, R.drawable.mirror_3d_13, R.drawable.mirror_3d_15, R.drawable.mirror_3d_15, R.drawable.mirror_3d_15, R.drawable.mirror_3d_15, R.drawable.mirror_3d_16, R.drawable.mirror_3d_16, R.drawable.mirror_3d_16, R.drawable.mirror_3d_16};
        this.D3_BUTTON_SIZE = 24;
        this.MIRROR_BUTTON_SIZE = 16;
        this.RATIO_BUTTON_SIZE = 11;
        this.currentSelectedTabIndex = -1;
        this.mode = INDEX_MIRROR_ADJUSTMENT;
        this.mulX = 16.0f;
        this.mulY = 16.0f;
    }

    static {
        TAG = MirrorAct.class.getSimpleName();
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(INDEX_MIRROR_3D);

        Bundle extras = getIntent().getExtras();
        this.sourceBitmap = BitmapResizer.decodeBitmapFromFile(extras.getString("selectedImagePath"), extras.getInt("MAX_SIZE"));
        if (this.sourceBitmap == null) {
            Toast msg = Toast.makeText(this.context, getString(R.string.save_image_lib_loading_error_message), Toast.LENGTH_LONG);
            msg.setGravity(17, msg.getXOffset() / INDEX_MIRROR_RATIO, msg.getYOffset() / INDEX_MIRROR_RATIO);
            msg.show();
            startActivity(new Intent(MirrorAct.this, Act_Home.class));
            finish();
            return;
        }

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        this.screenHeightPixels = metrics.heightPixels;
        this.screenWidthPixels = metrics.widthPixels;

        this.mirrorView = new MirrorView(this.context, this.screenWidthPixels, this.screenHeightPixels);
        setContentView(R.layout.act_mirror);
        this.mainLayout = (RelativeLayout) findViewById(R.id.text_view_fragment_container);
        this.mainLayout.addView(this.mirrorView);
        this.viewFlipper = (ViewFlipper) findViewById(R.id.mirror_view_flipper);
        this.slideLeftIn = AnimationUtils.loadAnimation(this.activity, R.anim.slide_in_left);
        this.slideLeftOut = AnimationUtils.loadAnimation(this.activity, R.anim.slide_out_left);
        this.slideRightIn = AnimationUtils.loadAnimation(this.activity, R.anim.slide_in_right);
        this.slideRightOut = AnimationUtils.loadAnimation(this.activity, R.anim.slide_out_right);

        Utility.logFreeMemory();
        setSelectedTab(INDEX_MIRROR);
        findViewById(R.id.mirror_header).bringToFront();
        this.viewFlipper.bringToFront();
        ((ViewGroup) findViewById(R.id.mirror_footer)).bringToFront();

        frameGallery2 = (Gallery) findViewById(R.id.frameGallery2);
        frameGallery2.setAdapter(new FarmeImageAdapter(MirrorAct.this));

        frameGallery2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                MirrorAct.this.mirrorView.setFrame(position);
            }
        });

        mirrorView.setDrawingCacheEnabled(true);
        mirrorView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        mirrorView.layout(0, 0, mirrorView.getMeasuredWidth(), mirrorView.getMeasuredHeight()-20);
        mirrorView.buildDrawingCache(true);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SAVE_IMAGE_ID) {
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.sourceBitmap != null) {
            this.sourceBitmap.recycle();
        }
        if (this.filterBitmap != null) {
            this.filterBitmap.recycle();
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (this.textAndStickerViewContainer == null) {
            this.textAndStickerViewContainer.bringToFront();
            View mirrorHeader = findViewById(R.id.mirror_header);
            if (mirrorHeader != null) {
                mirrorHeader.bringToFront();
            }
            if (this.viewFlipper == null) {
                this.viewFlipper = (ViewFlipper) findViewById(R.id.mirror_view_flipper);
            }
            if (this.viewFlipper != null) {
                this.viewFlipper.bringToFront();
            }
            ViewGroup mirrorFooter = (ViewGroup) findViewById(R.id.mirror_footer);
            if (mirrorFooter != null) {
                mirrorFooter.bringToFront();
            }
        }
    }

    public void myClickHandler(View view) {
        int id = view.getId();
        this.mirrorView.drawSavedImage = false;

        if (id == R.id.button_save_mirror_image)
        {

            new AsyncTask<Void, Void, Void>()
            {
                ProgressDialog progressDialog = null;

                protected void onPreExecute()
                {
                    progressDialog = ProgressDialog.show(MirrorAct.this, "Loading...", "Please Wait...");
                }

                @SuppressLint("WrongThread")
                @Override
                protected Void doInBackground(Void... arg0) {
                    try {
                        resultPath = MirrorAct.this.mirrorView.saveBitmap(true,
                                MirrorAct.this.screenWidthPixels, MirrorAct.this.screenHeightPixels);
                    } catch (Exception localException)
                    {
                        localException.printStackTrace();
                        finish();
                    }
                    return null;
                }

                protected void onPostExecute(Void result)
                {
                    progressDialog.dismiss();
                    Intent localIntent = new Intent(MirrorAct.this, MirrorShareAct.class);
                    localIntent.putExtra("IMAGE_DATA", resultPath);
                    startActivity(localIntent);
                    finish();
                }
            }.execute();
        }
        else if (id == R.id.button_cancel_mirror_image)
        {
            startActivity(new Intent(MirrorAct.this, Act_Home.class));
            finish();
        }
        else if (id == R.id.button_mirror)
        {
            setSelectedTab(INDEX_MIRROR);
            frameGallery2.setVisibility(View.INVISIBLE);

        } else if (id == R.id.button_mirror_frame)
        {
            setSelectedTab(INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX);
            if (frameGallery2.getVisibility() == View.VISIBLE)
            {
                frameGallery2.setVisibility(View.INVISIBLE);

            } else
                {
                frameGallery2.setVisibility(View.VISIBLE);
            }
        } else if (id == R.id.button_mirror_ratio)
        {
            setSelectedTab(INDEX_MIRROR_RATIO);
            frameGallery2.setVisibility(View.INVISIBLE);

        }
        else if (id == R.id.button_mirror_effect)
        {
            setSelectedTab(INDEX_MIRROR_EFFECT);
            frameGallery2.setVisibility(View.INVISIBLE);

        } else if (id == R.id.button_mirror_adj)
        {
            setSelectedTab(INDEX_MIRROR_ADJUSTMENT);
            frameGallery2.setVisibility(View.INVISIBLE);
        } else if (id == R.id.button_mirror_3d) {
            setSelectedTab(INDEX_MIRROR_3D);
            frameGallery2.setVisibility(View.INVISIBLE);
        } else if (id == R.id.button_3d_1) {
            set3dMode(INDEX_MIRROR);
        } else if (id == R.id.button_3d_2) {
            set3dMode(INDEX_MIRROR_3D);

        } else if (id == R.id.button_3d_3) {
            set3dMode(INDEX_MIRROR_RATIO);

        } else if (id == R.id.button_3d_4) {
            set3dMode(INDEX_MIRROR_EFFECT);

        } else if (id == R.id.button_3d_5) {
            set3dMode(INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX);
        } else if (id == R.id.button_3d_6) {
            set3dMode(INDEX_MIRROR_ADJUSTMENT);
        } else if (id == R.id.button_3d_7) {
            set3dMode(TAB_SIZE);
        } else if (id == R.id.button_3d_8) {
            set3dMode(INDEX_MIRROR_INVISIBLE_VIEW);
        } else if (id == R.id.button_3d_9) {
            set3dMode(8);
        } else if (id == R.id.button_3d_10) {
            set3dMode(9);
        } else if (id == R.id.button_3d_11) {
            set3dMode(10);
        } else if (id == R.id.button_3d_12) {
            set3dMode(11);
        } else if (id == R.id.button_3d_13) {
            set3dMode(12);
        } else if (id == R.id.button_3d_14) {
            set3dMode(13);
        } else if (id == R.id.button_3d_15) {
            set3dMode(14);
        } else if (id == R.id.button_3d_16) {
            set3dMode(15);
        } else if (id == R.id.button_3d_17) {
            set3dMode(16);
        } else if (id == R.id.button_3d_18) {
            set3dMode(17);
        } else if (id == R.id.button_3d_19) {
            set3dMode(18);
        } else if (id == R.id.button_3d_20) {
            set3dMode(19);
        } else if (id == R.id.button_3d_21) {
            set3dMode(20);
        } else if (id == R.id.button_3d_22) {
            set3dMode(21);
        } else if (id == R.id.button_3d_23) {
            set3dMode(22);
        } else if (id == R.id.button_3d_24) {
            set3dMode(23);
        } else if (id == R.id.button11) {
            setRatio(INDEX_MIRROR);
        } else if (id == R.id.button21) {
            setRatio(INDEX_MIRROR_3D);
        } else if (id == R.id.button12) {
            setRatio(INDEX_MIRROR_RATIO);
        } else if (id == R.id.button32) {
            setRatio(INDEX_MIRROR_EFFECT);
        } else if (id == R.id.button23) {
            setRatio(INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX);
        } else if (id == R.id.button43) {
            setRatio(INDEX_MIRROR_ADJUSTMENT);
        } else if (id == R.id.button34) {
            setRatio(TAB_SIZE);
        } else if (id == R.id.button45) {
            setRatio(INDEX_MIRROR_INVISIBLE_VIEW);
        } else if (id == R.id.button57) {
            setRatio(8);
        } else if (id == R.id.button169) {
            setRatio(9);
        } else if (id == R.id.button916) {
            setRatio(10);
        } else if (id == R.id.button_m0) {
            setMirrorMode(INDEX_MIRROR);
        } else if (id == R.id.button_m1) {
            setMirrorMode(INDEX_MIRROR_3D);
        } else if (id == R.id.button_m2) {
            setMirrorMode(INDEX_MIRROR_RATIO);
        } else if (id == R.id.button_m3) {
            setMirrorMode(INDEX_MIRROR_EFFECT);
        } else if (id == R.id.button_m4) {
            setMirrorMode(INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX);
        } else if (id == R.id.button_m5) {
            setMirrorMode(INDEX_MIRROR_ADJUSTMENT);
        } else if (id == R.id.button_m6) {
            setMirrorMode(TAB_SIZE);
        } else if (id == R.id.button_m7) {
            setMirrorMode(INDEX_MIRROR_INVISIBLE_VIEW);
        } else if (id == R.id.button_m8) {
            setMirrorMode(8);
        } else if (id == R.id.button_m9) {
            setMirrorMode(9);
        } else if (id == R.id.button_m10) {
            setMirrorMode(10);
        } else if (id == R.id.button_m11) {
            setMirrorMode(11);
        } else if (id == R.id.button_m12) {
            setMirrorMode(12);
        } else if (id == R.id.button_m13) {
            setMirrorMode(13);
        } else if (id == R.id.button_m14) {
            setMirrorMode(14);
        } else if (id == R.id.button_m15) {
            setMirrorMode(15);

        } else if (id == R.id.button_mirror_text) {

            clearViewFlipper();
            frameGallery2.setVisibility(View.INVISIBLE);

        } else if (id == R.id.button_mirror_sticker) {

            clearViewFlipper();
            frameGallery2.setVisibility(View.INVISIBLE);
        }
    }

    private void setMirrorMode(int index) {
        this.mirrorView.setCurrentMode(index);
        this.mirrorView.d3Mode = false;
        this.mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels-20, true);
        setMirrorButtonBg(index);
        setD3ButtonBg(-1);
    }

    private void setRatio(int index) {
        if (index == 0) {
            this.mulX = 1.0f;
            this.mulY = 1.0f;
        } else if (index == INDEX_MIRROR_3D) {
            this.mulX = 2.0f;
            this.mulY = 1.0f;
        } else if (index == INDEX_MIRROR_RATIO) {
            this.mulX = 1.0f;
            this.mulY = 2.0f;
        } else if (index == INDEX_MIRROR_EFFECT) {
            this.mulX = 3.0f;
            this.mulY = 2.0f;
        } else if (index == INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX) {
            this.mulX = 2.0f;
            this.mulY = 3.0f;
        } else if (index == INDEX_MIRROR_ADJUSTMENT) {
            this.mulX = 4.0f;
            this.mulY = 3.0f;
        } else if (index == TAB_SIZE) {
            this.mulX = 3.0f;
            this.mulY = 4.0f;
        } else if (index == INDEX_MIRROR_INVISIBLE_VIEW) {
            this.mulX = 4.0f;
            this.mulY = 5.0f;
        } else if (index == 8) {
            this.mulX = 5.0f;
            this.mulY = 7.0f;
        } else if (index == 9) {
            this.mulX = 16.0f;
            this.mulY = 9.0f;
        } else if (index == 10) {
            this.mulX = 9.0f;
            this.mulY = 16.0f;
        }
        if (this.mirrorView.currentModeIndex == 0) {
            this.mirrorView.setCurrentMode(INDEX_MIRROR_3D);
            setMirrorButtonBg(INDEX_MIRROR_3D);
        }
        this.mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels-20, true);
        setRatioButtonBg(index);
    }

    private void set3dMode(int index) {
        setMirrorButtonBg(-1);
        this.mirrorView.d3Mode = true;
        if (index > 15 && index < 20) {
            this.mirrorView.setCurrentMode(index);
        } else if (index > 19) {
            this.mirrorView.setCurrentMode(index - 4);
        } else if (index % INDEX_MIRROR_RATIO == 0) {
            this.mirrorView.setCurrentMode(INDEX_MIRROR_3D);
        } else {
            this.mirrorView.setCurrentMode(INDEX_MIRROR_RATIO);
        }
        this.mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels-20, false);
        if (VERSION.SDK_INT < 11) {
            if (!(this.mirrorView.d3Bitmap == null || this.mirrorView.d3Bitmap.isRecycled())) {
                this.mirrorView.d3Bitmap.recycle();
            }
            this.mirrorView.d3Bitmap = BitmapFactory.decodeResource(getResources(), this.d3resList[index]);
        } else {
            loadInBitmap(this.d3resList[index]);
        }
        this.mirrorView.postInvalidate();
        setD3ButtonBg(index);
    }

    @SuppressLint({"NewApi"})
    private void loadInBitmap(int resId) {
        Utility.logFreeMemory();
        Options options = new Options();
        if (this.mirrorView.d3Bitmap == null || this.mirrorView.d3Bitmap.isRecycled()) {
            options.inJustDecodeBounds = true;
            options.inMutable = true;
            BitmapFactory.decodeResource(getResources(), resId, options);
            this.mirrorView.d3Bitmap = Bitmap.createBitmap(options.outWidth, options.outHeight, Config.ARGB_8888);
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = INDEX_MIRROR_3D;
        options.inBitmap = this.mirrorView.d3Bitmap;
        try {
            this.mirrorView.d3Bitmap = BitmapFactory.decodeResource(getResources(), resId, options);
        } catch (Exception e) {
            if (!(this.mirrorView.d3Bitmap == null || this.mirrorView.d3Bitmap.isRecycled())) {
                this.mirrorView.d3Bitmap.recycle();
            }
            this.mirrorView.d3Bitmap = BitmapFactory.decodeResource(getResources(), resId);
        }
    }

    private void setD3ButtonBg(int index) {
        if (this.d3ButtonArray == null) {
            this.d3ButtonArray = new Button[this.D3_BUTTON_SIZE];
            this.d3ButtonArray[INDEX_MIRROR] = (Button) findViewById(R.id.button_3d_1);
            this.d3ButtonArray[INDEX_MIRROR_3D] = (Button) findViewById(R.id.button_3d_2);
            this.d3ButtonArray[INDEX_MIRROR_RATIO] = (Button) findViewById(R.id.button_3d_3);
            this.d3ButtonArray[INDEX_MIRROR_EFFECT] = (Button) findViewById(R.id.button_3d_4);
            this.d3ButtonArray[INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX] = (Button) findViewById(R.id.button_3d_5);
            this.d3ButtonArray[INDEX_MIRROR_ADJUSTMENT] = (Button) findViewById(R.id.button_3d_6);
            this.d3ButtonArray[TAB_SIZE] = (Button) findViewById(R.id.button_3d_7);
            this.d3ButtonArray[INDEX_MIRROR_INVISIBLE_VIEW] = (Button) findViewById(R.id.button_3d_8);
            this.d3ButtonArray[8] = (Button) findViewById(R.id.button_3d_9);
            this.d3ButtonArray[9] = (Button) findViewById(R.id.button_3d_10);
            this.d3ButtonArray[10] = (Button) findViewById(R.id.button_3d_11);
            this.d3ButtonArray[11] = (Button) findViewById(R.id.button_3d_12);
            this.d3ButtonArray[12] = (Button) findViewById(R.id.button_3d_13);
            this.d3ButtonArray[13] = (Button) findViewById(R.id.button_3d_14);
            this.d3ButtonArray[14] = (Button) findViewById(R.id.button_3d_15);
            this.d3ButtonArray[15] = (Button) findViewById(R.id.button_3d_16);
            this.d3ButtonArray[16] = (Button) findViewById(R.id.button_3d_17);
            this.d3ButtonArray[17] = (Button) findViewById(R.id.button_3d_18);
            this.d3ButtonArray[18] = (Button) findViewById(R.id.button_3d_19);
            this.d3ButtonArray[19] = (Button) findViewById(R.id.button_3d_20);
            this.d3ButtonArray[20] = (Button) findViewById(R.id.button_3d_21);
            this.d3ButtonArray[21] = (Button) findViewById(R.id.button_3d_22);
            this.d3ButtonArray[22] = (Button) findViewById(R.id.button_3d_23);
            this.d3ButtonArray[23] = (Button) findViewById(R.id.button_3d_24);
        }
        for (int i = INDEX_MIRROR; i < this.D3_BUTTON_SIZE; i += INDEX_MIRROR_3D) {
            this.d3ButtonArray[i].setBackgroundResource(R.drawable.gallery_strip);
        }
        if (index >= 0 && index < this.D3_BUTTON_SIZE) {
            this.d3ButtonArray[index].setBackgroundResource(R.drawable.border);
        }
    }

    private void setMirrorButtonBg(int index) {
        if (this.mirrorButtonArray == null) {
            this.mirrorButtonArray = new Button[this.MIRROR_BUTTON_SIZE];
            this.mirrorButtonArray[INDEX_MIRROR] = (Button) findViewById(R.id.button_m0);
            this.mirrorButtonArray[INDEX_MIRROR_3D] = (Button) findViewById(R.id.button_m1);
            this.mirrorButtonArray[INDEX_MIRROR_RATIO] = (Button) findViewById(R.id.button_m2);
            this.mirrorButtonArray[INDEX_MIRROR_EFFECT] = (Button) findViewById(R.id.button_m3);
            this.mirrorButtonArray[INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX] = (Button) findViewById(R.id.button_m4);
            this.mirrorButtonArray[INDEX_MIRROR_ADJUSTMENT] = (Button) findViewById(R.id.button_m5);
            this.mirrorButtonArray[TAB_SIZE] = (Button) findViewById(R.id.button_m6);
            this.mirrorButtonArray[INDEX_MIRROR_INVISIBLE_VIEW] = (Button) findViewById(R.id.button_m7);
            this.mirrorButtonArray[8] = (Button) findViewById(R.id.button_m8);
            this.mirrorButtonArray[9] = (Button) findViewById(R.id.button_m9);
            this.mirrorButtonArray[10] = (Button) findViewById(R.id.button_m10);
            this.mirrorButtonArray[11] = (Button) findViewById(R.id.button_m11);
            this.mirrorButtonArray[12] = (Button) findViewById(R.id.button_m12);
            this.mirrorButtonArray[13] = (Button) findViewById(R.id.button_m13);
            this.mirrorButtonArray[14] = (Button) findViewById(R.id.button_m14);
            this.mirrorButtonArray[15] = (Button) findViewById(R.id.button_m15);
        }
        for (int i = INDEX_MIRROR; i < this.MIRROR_BUTTON_SIZE; i += INDEX_MIRROR_3D) {
            this.mirrorButtonArray[i].setBackgroundResource(R.color.lib_adjustment_background_color);
        }
        if (index >= 0 && index < this.MIRROR_BUTTON_SIZE) {
            this.mirrorButtonArray[index].setBackgroundResource(R.color.colorPrimaryDark);
        }
    }

    private void setRatioButtonBg(int index) {
        if (this.ratioButtonArray == null) {
            this.ratioButtonArray = new Button[this.RATIO_BUTTON_SIZE];
            this.ratioButtonArray[INDEX_MIRROR] = (Button) findViewById(R.id.button11);
            this.ratioButtonArray[INDEX_MIRROR_3D] = (Button) findViewById(R.id.button21);
            this.ratioButtonArray[INDEX_MIRROR_RATIO] = (Button) findViewById(R.id.button12);
            this.ratioButtonArray[INDEX_MIRROR_EFFECT] = (Button) findViewById(R.id.button32);
            this.ratioButtonArray[INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX] = (Button) findViewById(R.id.button23);
            this.ratioButtonArray[INDEX_MIRROR_ADJUSTMENT] = (Button) findViewById(R.id.button43);
            this.ratioButtonArray[TAB_SIZE] = (Button) findViewById(R.id.button34);
            this.ratioButtonArray[INDEX_MIRROR_INVISIBLE_VIEW] = (Button) findViewById(R.id.button45);
            this.ratioButtonArray[8] = (Button) findViewById(R.id.button57);
            this.ratioButtonArray[9] = (Button) findViewById(R.id.button169);
            this.ratioButtonArray[10] = (Button) findViewById(R.id.button916);
        }
        for (int i = INDEX_MIRROR; i < this.RATIO_BUTTON_SIZE; i += INDEX_MIRROR_3D) {
            this.ratioButtonArray[i].setBackgroundResource(R.drawable.selector_mirror_ratio_button);
        }
        this.ratioButtonArray[index].setBackgroundResource(R.drawable.ratio_bg_pressed);
    }

    void setSelectedTab(int index) {
        setTabBg(INDEX_MIRROR);
        int displayedChild = this.viewFlipper.getDisplayedChild();
        if (index == 0) {
            if (displayedChild != 0) {
                this.viewFlipper.setInAnimation(this.slideLeftIn);
                this.viewFlipper.setOutAnimation(this.slideRightOut);
                this.viewFlipper.setDisplayedChild(INDEX_MIRROR);
            } else {
                return;
            }
        }
        if (index == INDEX_MIRROR_3D) {
            setTabBg(INDEX_MIRROR_3D);
            if (displayedChild != INDEX_MIRROR_3D) {
                if (displayedChild == 0) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                }
                this.viewFlipper.setDisplayedChild(INDEX_MIRROR_3D);
            } else {
                return;
            }
        }
        if (index == INDEX_MIRROR_RATIO) {
            setTabBg(INDEX_MIRROR_RATIO);
            if (displayedChild != INDEX_MIRROR_RATIO) {
                if (displayedChild == 0) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                }
                this.viewFlipper.setDisplayedChild(INDEX_MIRROR_RATIO);
            } else {
                return;
            }
        }
        if (index == INDEX_MIRROR_EFFECT) {
            setTabBg(INDEX_MIRROR_EFFECT);

            if (displayedChild != INDEX_MIRROR_EFFECT) {
                if (displayedChild == 0 || displayedChild == INDEX_MIRROR_RATIO) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                }
                this.viewFlipper.setDisplayedChild(INDEX_MIRROR_EFFECT);
            } else {
                return;
            }
        }
        if (index == INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX) {
            setTabBg(INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX);

            if (displayedChild != INDEX_MIRROR_EFFECT) {
                if (displayedChild == INDEX_MIRROR_ADJUSTMENT) {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                }
                this.viewFlipper.setDisplayedChild(INDEX_MIRROR_EFFECT);
            } else {
                return;
            }
        }
        if (index == INDEX_MIRROR_ADJUSTMENT) {
            setTabBg(INDEX_MIRROR_ADJUSTMENT);

            if (displayedChild != INDEX_MIRROR_EFFECT) {
                this.viewFlipper.setInAnimation(this.slideRightIn);
                this.viewFlipper.setOutAnimation(this.slideLeftOut);
                this.viewFlipper.setDisplayedChild(INDEX_MIRROR_EFFECT);
            } else {
                return;
            }
        }
        if (index == INDEX_MIRROR_INVISIBLE_VIEW) {
            setTabBg(-1);
            if (displayedChild != INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX) {
                this.viewFlipper.setInAnimation(this.slideRightIn);
                this.viewFlipper.setOutAnimation(this.slideLeftOut);
                this.viewFlipper.setDisplayedChild(INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX);
            }
        }
    }

    private void setTabBg(int index) {
        this.currentSelectedTabIndex = index;
        if (this.tabButtonList == null) {
            this.tabButtonList = new View[TAB_SIZE];
            this.tabButtonList[INDEX_MIRROR] = findViewById(R.id.button_mirror);
            this.tabButtonList[INDEX_MIRROR_3D] = findViewById(R.id.button_mirror_3d);
            this.tabButtonList[INDEX_MIRROR_EFFECT] = findViewById(R.id.button_mirror_effect);
            this.tabButtonList[INDEX_MIRROR_RATIO] = findViewById(R.id.button_mirror_ratio);
            this.tabButtonList[INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX] = findViewById(R.id.button_mirror_frame);
            this.tabButtonList[INDEX_MIRROR_ADJUSTMENT] = findViewById(R.id.button_mirror_adj);
        }
        for (int i = INDEX_MIRROR; i < this.tabButtonList.length; i += INDEX_MIRROR_3D) {
            this.tabButtonList[i].setBackgroundResource(R.drawable.mirror_footer_button);
        }
        if (index >= 0) {
            this.tabButtonList[index].setBackgroundResource(R.color.colorPrimaryDark);
        }
    }

    void clearViewFlipper() {
        this.viewFlipper.setInAnimation(null);
        this.viewFlipper.setOutAnimation(null);
        this.viewFlipper.setDisplayedChild(INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX);
        setTabBg(-1);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MirrorAct.this, Act_Home.class));
        finish();
    }

    protected void onResume() {
        Utility.logFreeMemory();
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

}

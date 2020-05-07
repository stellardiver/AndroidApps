package com.app.pipeditorpro.singleImageEdit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.app.pipeditorpro.Global;
import com.app.pipeditorpro.R;
import com.app.pipeditorpro.adapter.ColorAdapter;
import com.app.pipeditorpro.adapter.FontAdapter;
import com.app.pipeditorpro.adapter.ImageAdapter_Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static com.app.pipeditorpro.R.id.editText;

public class TextAct extends Activity implements OnClickListener, OnSeekBarChangeListener
{
    public File f46a;
    OnItemClickListener f48c;
    OnItemClickListener f49d;
    private Dialog f50e;
    private EditText f51f;
    private TextView f52g;
    private Typeface f53h;
    private String[] f54i;
    private ColorAdapter f55j;
    private RelativeLayout f56k;
    private int f57l;
    private int f58m;
    private int f59n;
    private int f60o;
    private boolean f61p;
    private boolean f62q;
    private boolean f63r;
    private String f64s;
    private String f65t;
    private String f66u;
    private String f67v;
    private String f68w;
    private Integer[] f69x;
    private int f70y;

    Global app;

    LinearLayout l_edit,l_font,l_fcolor,l_fshadow,l_frame;
    RelativeLayout allLayout;

    class C02151 implements OnItemClickListener
    {
        final TextAct f42a;

        C02151(TextAct textAct) {
            f42a = textAct;
        }

        public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j)
        {
            if (f42a.f57l == 1)
            {
                f42a.f53h = Typeface.createFromAsset(f42a.getAssets(), f42a.f54i[i]);
                f42a.f52g.setTypeface(f42a.f53h);

            }
            else if (f42a.f57l == 2)
            {

                if (f42a.f61p)
                {
                    f42a.f64s = f42a.f55j.f71a[i];
                }
                else
                {
                    f42a.f65t = f42a.f55j.f71a[i];
                }
                if (f42a.f62q)
                {
                    TextAct.m27a(f42a, f42a.f64s, f42a.f64s);
                }
                else
                {
                    TextAct.m27a(f42a, f42a.f64s, f42a.f65t);
                }
            }
            else if (f42a.f57l == 4)
            {
                //For text bg
                if (f42a.f61p)
                {
                    f42a.f66u = f42a.f55j.f71a[i];
                }
                else
                {
                    f42a.f67v = f42a.f55j.f71a[i];
                }
                if (f42a.f63r)
                {
                    TextAct.m31b(f42a, f42a.f66u, f42a.f66u);
                }
                else
                {
                    TextAct.m31b(f42a, f42a.f66u, f42a.f67v);
                }
            }
        }
    }

    class C02162 implements OnItemClickListener
    {
        final TextAct f43a;

        C02162(TextAct textAct) {
            f43a = textAct;
        }

        public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (f43a.f57l == 3) {
                f43a.f68w = f43a.f55j.f71a[i];
                f43a.f52g.getPaint().setShader(null);
                f43a.f52g.setShadowLayer((float) f43a.f60o, (float) f43a.f59n, (float) f43a.f59n, Color.parseColor(f43a.f68w));
                f43a.f52g.setTextColor(Color.parseColor(f43a.f64s));
                f43a.f52g.invalidate();
                return;
            }
            f43a.f56k.setBackgroundResource(f43a.f69x[i].intValue());
        }
    }

    class C02173 implements OnClickListener
    {
        final TextAct f44a;

        C02173(TextAct textAct) {
            f44a = textAct;
        }

        public final void onClick(View view) {
            if (((CheckBox) view).isChecked()) {
                f44a.f62q = false;
                TextAct.m27a(f44a, f44a.f64s, f44a.f65t);
                return;
            }
            f44a.f62q = true;
            TextAct.m27a(f44a, f44a.f64s, f44a.f64s);
        }
    }

    class C02184 implements OnClickListener
    {
        final TextAct f45a;

        C02184(TextAct textAct) {
            f45a = textAct;
        }

        public final void onClick(View view) {
            if (((CheckBox) view).isChecked()) {
                f45a.f63r = false;
                TextAct.m31b(f45a, f45a.f66u, f45a.f67v);
                return;
            }
            f45a.f63r = true;
            TextAct.m31b(f45a, f45a.f66u, f45a.f66u);
        }
    }

    public TextAct() {
        f51f = null;
        f52g = null;
        f46a = null;
        f57l = 1;
        f58m = 30;
        f59n = 0;
        f60o = 0;
        f61p = true;
        f62q = true;
        f63r = true;
        f69x = new Integer[]{Integer.valueOf(R.drawable.bg_pattern_01),
                Integer.valueOf(R.drawable.bg_pattern_02),
                Integer.valueOf(R.drawable.bg_pattern_03),
                Integer.valueOf(R.drawable.bg_pattern_04),
                Integer.valueOf(R.drawable.bg_pattern_05), Integer.valueOf(R.drawable.bg_pattern_06), Integer.valueOf(R.drawable.bg_pattern_07), Integer.valueOf(R.drawable.bg_pattern_08), Integer.valueOf(R.drawable.bg_pattern_09), Integer.valueOf(R.drawable.bg_pattern_010), Integer.valueOf(R.drawable.bg_pattern_011), Integer.valueOf(R.drawable.bg_pattern_012), Integer.valueOf(R.drawable.bg_pattern_013), Integer.valueOf(R.drawable.bg_pattern_014), Integer.valueOf(R.drawable.bg_pattern_015), Integer.valueOf(R.drawable.bg_pattern_016)};
        f70y = 1;
        f48c = new C02151(this);
        f49d = new C02162(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_text);
        app = ((Global) getApplication());

        allLayout=(RelativeLayout) findViewById(R.id.allLayout);
         l_edit=(LinearLayout)findViewById(R.id.l_edit);
         l_font=(LinearLayout)findViewById(R.id.l_font);
         l_fcolor=(LinearLayout)findViewById(R.id.l_fcolor);
         l_fshadow=(LinearLayout)findViewById(R.id.l_fshadow);
         l_frame=(LinearLayout)findViewById(R.id.l_frame);

        findViewById(R.id.inputKet).setOnClickListener(this);
        findViewById(R.id.btn_font).setOnClickListener(this);
        findViewById(R.id.btn_normalfont).setOnClickListener(this);
        findViewById(R.id.btn_boldfont).setOnClickListener(this);
        findViewById(R.id.btn_italicfont).setOnClickListener(this);
        findViewById(R.id.btn_bolditalicfont).setOnClickListener(this);
        findViewById(R.id.btn_textColor).setOnClickListener(this);
        findViewById(R.id.btn_singleColor).setOnClickListener(this);
        findViewById(R.id.btn_multiColor).setOnClickListener(this);
        findViewById(R.id.btn_textStyle).setOnClickListener(this);
        findViewById(R.id.btn_textBg).setOnClickListener(this);
        findViewById(R.id.btn_BgsingleColor).setOnClickListener(this);
        findViewById(R.id.btn_BgmultiColor).setOnClickListener(this);
        findViewById(R.id.btn_done).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);

        f70y = getIntent().getIntExtra("activityCode", 1);
        f56k = (RelativeLayout) findViewById(R.id.textviewLayout);
        f52g = (TextView) findViewById(R.id.textView);


        f55j = new ColorAdapter(this);

        f64s = f55j.f71a[35];
        f65t = f55j.f71a[35];
        f66u = f55j.f71a[6];
        f67v = f55j.f71a[6];
        f68w = f55j.f71a[20];

        Gallery gallery = (Gallery) findViewById(R.id.fontGallery);
        gallery.setAdapter(new FontAdapter(this));
        gallery.setOnItemClickListener(f48c);

        gallery = (Gallery) findViewById(R.id.colorGallery);
        gallery.setAdapter(f55j);
        gallery.setOnItemClickListener(f48c);

        gallery = (Gallery) findViewById(R.id.colorGalleryBg);
        gallery.setAdapter(f55j);
        gallery.setOnItemClickListener(f48c);

        gallery = (Gallery) findViewById(R.id.shadowcolorGallery);
        gallery.setAdapter(f55j);
        gallery.setOnItemClickListener(f49d);

        gallery = (Gallery) findViewById(R.id.patternGallery);
        gallery.setAdapter(new ImageAdapter_Text(this));
        gallery.setOnItemClickListener(f49d);

        ((SeekBar) findViewById(R.id.seekBar)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.textOpacitySeekBar)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.paddingSeekBar)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.shadwoXYSeekBar)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.shadowRadiosSeekBar)).setOnSeekBarChangeListener(this);

        String file = Environment.getExternalStorageDirectory().toString();
        new File(new StringBuilder(String.valueOf(file)).append("/").append(getString(R.string.app_name)).append("/temp").toString()).mkdirs();

        if ("mounted".equals(Environment.getExternalStorageState()))
        {
            f46a = new File(new StringBuilder(String.valueOf(file)).append("/").append(getString(R.string.app_name)).append("/temp/").toString(), "Nature_1.jpg");
            try
            {
                FileOutputStream fileOutputStream = new FileOutputStream(f46a);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else
        {
            f46a = new File(getFilesDir(), "Nature_1.jpg");
        }
        findViewById(R.id.colorCheckBox).setOnClickListener(new C02173(this));
        findViewById(R.id.BgcolorCheckBox).setOnClickListener(new C02184(this));
        f54i = getResources().getStringArray(R.array.FontFamily);

        f52g.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                f50e = new Dialog(TextAct.this,R.style.Theme_AppCompat_Translucent);
                f50e.requestWindowFeature(1);
                f50e.setContentView(R.layout.act_edit_text_dialog);
                f50e.findViewById(R.id.btn_edittext_done).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        f50e.dismiss();
                        f52g.setText(f51f.getText().toString());
                    }
                });
                f50e.findViewById(R.id.btn_edittext_cancel).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        f50e.dismiss();
                    }
                });
                f51f = (EditText) f50e.findViewById(editText);
                f51f.setText(f52g.getText().toString().trim());
                f50e.show();
            }
        });
    }

    public void onClick(View view) {

        switch (view.getId())
        {

            case R.id.btn_cancel:
                app.setText(true);
                finish();
                break;
            case R.id.btn_done:
                m29b();

                View v1=findViewById(R.id.textviewLayout);
                v1.buildDrawingCache(true);
                v1.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
                v1.setDrawingCacheEnabled(false);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                app.setTextId(byteArray);

                finish();
                break;

            case R.id.inputKet:

                l_edit.setBackgroundColor(getResources().getColor(R.color.clr_pri));
                l_font.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_fcolor.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_fshadow.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_frame.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                allLayout.setVisibility(View.GONE);
                f50e = new Dialog(this,R.style.Theme_AppCompat_Translucent);
                f50e.requestWindowFeature(1);
                f50e.setContentView(R.layout.act_edit_text_dialog);
                f50e.findViewById(R.id.btn_edittext_done).setOnClickListener(this);
                f50e.findViewById(R.id.btn_edittext_cancel).setOnClickListener(this);
                f51f = (EditText) f50e.findViewById(editText);
                f51f.setText(f52g.getText().toString().trim());
                f50e.show();
                break;

            case R.id.btn_font:
                allLayout.setVisibility(View.VISIBLE);
                f57l = 1;
                m24a();
                l_edit.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_font.setBackgroundColor(getResources().getColor(R.color.clr_pri));
                l_fcolor.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_fshadow.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_frame.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                findViewById(R.id.fontLayout).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_font).setBackgroundResource(R.drawable.btn_text_style_hover);
                break;

            case R.id.btn_textColor:

                allLayout.setVisibility(View.VISIBLE);
                f57l = 2;
                m24a();
                l_edit.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_font.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_fcolor.setBackgroundColor(getResources().getColor(R.color.clr_pri));
                l_fshadow.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_frame.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                findViewById(R.id.colorLayout).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_textColor).setBackgroundResource(R.drawable.btn_text_color_hover);
                break;

            case R.id.btn_textStyle:

                allLayout.setVisibility(View.VISIBLE);
                f57l = 3;
                m24a();
                l_edit.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_font.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_fcolor.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_fshadow.setBackgroundColor(getResources().getColor(R.color.clr_pri));
                l_frame.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                findViewById(R.id.textStyleLayout).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_textStyle).setBackgroundResource(R.drawable.btn_text_glow_hover);
                break;

            case R.id.btn_textBg:

                allLayout.setVisibility(View.VISIBLE);
                f57l = 4;
                m24a();
                l_edit.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_font.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_fcolor.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_fshadow.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_frame.setBackgroundColor(getResources().getColor(R.color.clr_pri));
                findViewById(R.id.TextbackgroundLayout).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_textBg).setBackgroundResource(R.drawable.btn_text_bg_hover);
                break;

            case R.id.btn_normalfont:

                f52g.setTypeface(f52g.getTypeface(), Typeface.NORMAL);
                f52g.invalidate();
                findViewById(R.id.btn_normalfont).setBackgroundResource(R.drawable.btn_normal_hover);
                findViewById(R.id.btn_boldfont).setBackgroundResource(R.drawable.btn_bold);
                findViewById(R.id.btn_italicfont).setBackgroundResource(R.drawable.btn_italics);
                findViewById(R.id.btn_bolditalicfont).setBackgroundResource(R.drawable.btn_bold_italics);
                break;

            case R.id.btn_boldfont:

                f52g.setTypeface(f52g.getTypeface(), Typeface.BOLD);
                f52g.invalidate();
                findViewById(R.id.btn_normalfont).setBackgroundResource(R.drawable.btn_normal);
                findViewById(R.id.btn_boldfont).setBackgroundResource(R.drawable.btn_bold_hover);
                findViewById(R.id.btn_italicfont).setBackgroundResource(R.drawable.btn_italics);
                findViewById(R.id.btn_bolditalicfont).setBackgroundResource(R.drawable.btn_bold_italics);
                break;

            case R.id.btn_italicfont:

                f52g.setTypeface(f52g.getTypeface(), Typeface.ITALIC);
                f52g.invalidate();
                findViewById(R.id.btn_normalfont).setBackgroundResource(R.drawable.btn_normal);
                findViewById(R.id.btn_boldfont).setBackgroundResource(R.drawable.btn_bold);
                findViewById(R.id.btn_italicfont).setBackgroundResource(R.drawable.btn_italics_hover);
                findViewById(R.id.btn_bolditalicfont).setBackgroundResource(R.drawable.btn_bold_italics);
                break;

            case R.id.btn_bolditalicfont:

                f52g.setTypeface(f52g.getTypeface(), Typeface.BOLD_ITALIC);
                f52g.invalidate();
                findViewById(R.id.btn_normalfont).setBackgroundResource(R.drawable.btn_normal);
                findViewById(R.id.btn_boldfont).setBackgroundResource(R.drawable.btn_bold);
                findViewById(R.id.btn_italicfont).setBackgroundResource(R.drawable.btn_italics);
                findViewById(R.id.btn_bolditalicfont).setBackgroundResource(R.drawable.btn_bold_italics_hover);
                break;

            case R.id.btn_singleColor:

                f61p = true;
                findViewById(R.id.btn_BgsingleColor).setBackgroundResource(R.drawable.btn_color1_hover);
                findViewById(R.id.btn_BgmultiColor).setBackgroundResource(R.drawable.btn_color2);
                findViewById(R.id.btn_singleColor).setBackgroundResource(R.drawable.btn_color1_hover);
                findViewById(R.id.btn_multiColor).setBackgroundResource(R.drawable.btn_color2);
                break;

            case R.id.btn_multiColor:

                f61p = false;
                findViewById(R.id.btn_BgsingleColor).setBackgroundResource(R.drawable.btn_color1);
                findViewById(R.id.btn_BgmultiColor).setBackgroundResource(R.drawable.btn_color2_hover);
                findViewById(R.id.btn_singleColor).setBackgroundResource(R.drawable.btn_color1);
                findViewById(R.id.btn_multiColor).setBackgroundResource(R.drawable.btn_color2_hover);
                break;

            case R.id.btn_BgsingleColor:

                f61p = true;
                findViewById(R.id.btn_BgsingleColor).setBackgroundResource(R.drawable.btn_color1_hover);
                findViewById(R.id.btn_BgmultiColor).setBackgroundResource(R.drawable.btn_color2);
                findViewById(R.id.btn_singleColor).setBackgroundResource(R.drawable.btn_color1_hover);
                findViewById(R.id.btn_multiColor).setBackgroundResource(R.drawable.btn_color2);
                break;

            case R.id.btn_BgmultiColor:

                f61p = false;
                findViewById(R.id.btn_BgsingleColor).setBackgroundResource(R.drawable.btn_color1);
                findViewById(R.id.btn_BgmultiColor).setBackgroundResource(R.drawable.btn_color2_hover);
                findViewById(R.id.btn_singleColor).setBackgroundResource(R.drawable.btn_color1);
                findViewById(R.id.btn_multiColor).setBackgroundResource(R.drawable.btn_color2_hover);
                break;

            case R.id.btn_edittext_done:

                f50e.dismiss();
                f52g.setText(f51f.getText().toString());
                break;

            case R.id.btn_edittext_cancel:
                allLayout.setVisibility(View.GONE);
                l_edit.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_font.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_fcolor.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_fshadow.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                l_frame.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                f50e.dismiss();
                break;

            default:
                break;
        }
    }

    static void m27a(TextAct textAct, String str, String str2) {
        float f = 0.0f;
        textAct.f52g.getPaint().setShader(new LinearGradient(0.0f, (float) (textAct.f58m * 1), f, (float) (textAct.f58m * 2), new int[]{Color.parseColor(str), Color.parseColor(str2)}, new float[]{0.0f, 1.0F}, TileMode.CLAMP));
        textAct.f52g.getPaint().setStrokeWidth(5.0f);
        textAct.f52g.invalidate();
    }

    static void m31b(TextAct textAct, String str, String str2) {
        try {
            ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
            shapeDrawable.getPaint().setShader(new LinearGradient(0.0f, 0.0f, 0.0f, (float) (textAct.f58m * 3), Color.parseColor(str), Color.parseColor(str2), TileMode.REPEAT));
            textAct.findViewById(R.id.textviewLayout).setBackgroundDrawable(shapeDrawable);
            textAct.findViewById(R.id.textviewLayout).invalidate();
        } catch (Exception e) {
        }
    }

    private void m24a() {
        findViewById(R.id.colorLayout).setVisibility(View.GONE);
        findViewById(R.id.fontLayout).setVisibility(View.GONE);
        findViewById(R.id.TextbackgroundLayout).setVisibility(View.GONE);
        findViewById(R.id.textStyleLayout).setVisibility(View.GONE);
        findViewById(R.id.btn_font).setBackgroundResource(R.drawable.btn_text_style);
        findViewById(R.id.btn_textColor).setBackgroundResource(R.drawable.btn_text_color);
        findViewById(R.id.btn_textStyle).setBackgroundResource(R.drawable.btn_text_glow);
        findViewById(R.id.btn_textBg).setBackgroundResource(R.drawable.btn_text_bg);
    }

    private Bitmap m29b() {
        f56k.setDrawingCacheEnabled(true);
        f56k.layout(0, 0, f56k.getMeasuredWidth(), f56k.getMeasuredHeight());
        Bitmap drawingCache = f56k.getDrawingCache(true);

        try
        {
            drawingCache.compress(CompressFormat.PNG, 90, new FileOutputStream(f46a));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return drawingCache;
    }

    @SuppressLint({"NewApi"})
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        switch (seekBar.getId())
        {
            case R.id.seekBar:
                f58m = i;
                f52g.setTextSize((float) f58m);
                break;

            case R.id.textOpacitySeekBar:
                try {
                    f52g.setAlpha(((float) i) / 100.0f);
                } catch (Exception e) {
                }
                break;

            case R.id.shadwoXYSeekBar:
                f59n = (i / 5) - 10;
                f52g.setShadowLayer((float) f60o, (float) f59n, (float) f59n, Color.parseColor(f68w));
                f52g.invalidate();
                break;

            case R.id.shadowRadiosSeekBar:
                f60o = i / 5;
                f52g.setShadowLayer((float) f60o, (float) f59n, (float) f59n, Color.parseColor(f68w));
                f52g.invalidate();
                break;

            case R.id.paddingSeekBar:
                f58m = i;
                f56k.setPadding(i, i, i, i);
                break;

            default:
                break;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    protected void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();

    }

    protected void onDestroy() {
        if (f46a.exists()) {
            f46a.delete();
        }
        super.onDestroy();

    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    public void onBackPressed() {
        super.onBackPressed();
        app.setText(true);
    }

}

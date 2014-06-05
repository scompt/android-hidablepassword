package com.scompt.hidablepassword.sample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.scompt.hidablepassword.HidablePasswordEditText;

public class SampleActivity extends Activity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sample_activity);

        final HidablePasswordEditText hidable1 =
                (HidablePasswordEditText) findViewById(R.id.hidable1);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(22 * metrics.scaledDensity);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setShadowLayer(6f, 0, 0, Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);

        hidable1.setPaint(paint);
    }
}
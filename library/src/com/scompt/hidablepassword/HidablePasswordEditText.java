package com.scompt.hidablepassword;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public class HidablePasswordEditText extends EditText implements View.OnTouchListener {

    private static final int LEFT   = 0;
    private static final int TOP    = 1;
    private static final int RIGHT  = 2;
    private static final int BOTTOM = 3;
    private static final int COMPOUND_DRAWABLE_COUNT = 4;

    private TextDrawable mHideDrawable;
    private TextDrawable mShowDrawable;
    private TextDrawable mActiveDrawable;

    @SuppressWarnings ("UnusedDeclaration")
    public HidablePasswordEditText(final Context inContext) {
        super(inContext);
        init(inContext);
    }

    @SuppressWarnings ("UnusedDeclaration")
    public HidablePasswordEditText(final Context inContext, final AttributeSet inAttrs) {
        super(inContext, inAttrs);
        init(inContext);
    }

    @SuppressWarnings ("UnusedDeclaration")
    public HidablePasswordEditText(final Context inContext, final AttributeSet inAttrs,
                                   final int inDefStyle) {

        super(inContext, inAttrs, inDefStyle);
        init(inContext);
    }

    @SuppressLint ("ClickableViewAccessibility")
    @Override
    public boolean onTouch(final View inView, final MotionEvent inEvent) {
        final Drawable[] drawables = getCompoundDrawables();
        if (drawables != null && drawables[RIGHT] != null) {
            final int xLeft = getWidth() - getPaddingRight() - mActiveDrawable.getIntrinsicWidth();
            boolean tappedX = inEvent.getX() > xLeft;
            if (tappedX) {
                if (inEvent.getAction() == MotionEvent.ACTION_UP) {
                    int inputType = getInputType();
                    if (isPasswordHidden()) {
                        inputType = inputType & ~EditorInfo.TYPE_TEXT_VARIATION_PASSWORD
                                              | EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                    } else {
                        inputType = inputType & ~EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                                            | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD;
                    }
                    setInputType(inputType);
                    updateUpdateShowHideDrawable();
                }
                return true;
            }
        }
        return false;
    }

    protected void configurePasswordTogglePaint(@SuppressWarnings ("UnusedParameters")
                                                final Paint inPaint) {
    }

    private void init(final Context inContext) {
        setOnTouchListener(this);

        mHideDrawable = new TextDrawable(inContext.getString(R.string.hp__hidePassword));
        mShowDrawable = new TextDrawable(inContext.getString(R.string.hp__showPassword));

        configurePasswordTogglePaint(mHideDrawable.mPaint);
        configurePasswordTogglePaint(mShowDrawable.mPaint);

        updateUpdateShowHideDrawable();
    }

    private void updateUpdateShowHideDrawable() {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (compoundDrawables == null || compoundDrawables.length < COMPOUND_DRAWABLE_COUNT) {
            compoundDrawables = new Drawable[COMPOUND_DRAWABLE_COUNT];
        }

        if (isPasswordHidden()) {
            mActiveDrawable = mShowDrawable;

        } else {
            mActiveDrawable = mHideDrawable;
        }

        setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[LEFT], compoundDrawables[TOP],
                                                mActiveDrawable, compoundDrawables[BOTTOM]);
    }

    private boolean isPasswordHidden() {
        final int inputType = getInputType();
        return (inputType & EditorInfo.TYPE_TEXT_VARIATION_PASSWORD)
                       == EditorInfo.TYPE_TEXT_VARIATION_PASSWORD
                       && (inputType & EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                                  != EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
    }

    private static class TextDrawable extends Drawable {

        private final String mText;
        private final Paint  mPaint;

        public TextDrawable(final String inText) {

            mText = inText;

            mPaint = new Paint();
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(22f);
            mPaint.setAntiAlias(true);
            mPaint.setFakeBoldText(true);
            mPaint.setShadowLayer(6f, 0, 0, Color.BLACK);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setTextAlign(Paint.Align.LEFT);
        }

        @Override
        public void draw(final Canvas inCanvas) {
            inCanvas.drawText(mText, 0, 0, mPaint);
        }

        @Override
        public void setAlpha(final int inAlpha) {
            mPaint.setAlpha(inAlpha);
        }

        @Override
        public void setColorFilter(final ColorFilter inColorFilter) {
            mPaint.setColorFilter(inColorFilter);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }

        @Override public int getIntrinsicWidth() {
            return (int) this.mPaint.measureText(mText);
        }
    }
}

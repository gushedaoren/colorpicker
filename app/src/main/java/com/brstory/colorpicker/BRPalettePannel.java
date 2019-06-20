package com.brstory.colorpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BRPalettePannel extends View {

    String[] colors = {"#FFFFF0","#FF7F24","#FF1493","#EEE9E9","#EE7600","#EE0000","#A8A8A8","#97FFFF","#8B2500"};

    Context context;
    public BRPalettePannel(Context context) {
        super(context);
        this.context=context;
    }

    public BRPalettePannel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public BRPalettePannel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    @SuppressLint("NewApi")
    public BRPalettePannel(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

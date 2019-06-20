package com.brstory.colorpicker;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import androidx.annotation.Nullable;

public class BRPalettePannel extends View {

    public int degree = 0;
    Context context;

    // 属性变量
    private float translationX; // 移动X
    private float translationY; // 移动Y
    private float scale = 1; // 伸缩比例
    private float rotation; // 旋转角度

    private float MIN_MOVE_SIZE=30f;

    // 移动过程中临时变量
    private float actionX;
    private float actionY;
    private float spacing;

    private int moveType; // 0=未选择，1=拖动，2=缩放

    String[] colors = {"#FFFFF0","#FF7F24","#FF1493","#EEE9E9","#EE7600","#EE0000","#A8A8A8","#97FFFF","#8B2500"};

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
        int canvasWidth=canvas.getWidth();
        Paint paint = new Paint();

        if(translationX>canvasWidth){
            translationX=translationX-canvasWidth;
        }
        if(translationX<-canvasWidth){
            translationX=translationX+canvasWidth;
        }

        //去锯齿

        paint.setAntiAlias(true);


        paint.setStyle(Paint.Style.FILL);

        paint.setStrokeWidth(3);

        int paletteWidth=canvasWidth/colors.length;

        int canvasHeight= canvas.getHeight();

        for(int i=0;i<colors.length;i++){

            paint.setColor(  Color.parseColor(colors[i]));

            int left= (int) (i*paletteWidth+translationX);
            int right= (int) ((i+1)*paletteWidth+translationX);

            RectF rectF = new RectF(left,0,right,canvasHeight);
            RectF rectLeft = new RectF(left-canvasWidth,0,right-canvasWidth,canvasHeight);
            RectF rectRight = new RectF(left+canvasWidth,0,right+canvasWidth,canvasHeight);

            canvas.drawRect(rectF,paint);
            canvas.drawRect(rectLeft,paint);
            canvas.drawRect(rectRight,paint);



        }

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                moveType = 1;
                actionX = event.getX();

                return true;


            case MotionEvent.ACTION_POINTER_DOWN:
                moveType = 2;
                actionX = event.getX();
//                spacing = getSpacing(event);
//                degree = getDegree(event);
                return true;


            case MotionEvent.ACTION_MOVE:
                if (moveType == 1) {

                    int currentX= (int) event.getX();
                    int currentY= (int) event.getY();
                    int dX= (int) (currentX-actionX);
                    int dY= (int) (currentY-actionY);
                    if(Math.abs(dX)>Math.abs(dY)&&Math.abs(dX)>MIN_MOVE_SIZE){//左右滑动
                        translationX = translationX + dX*2;



//                    setTranslationY(translationY);

                        Log.e(getClass().toString(),"translationX:"+translationX+" actionX:"+actionX+" currentX:"+currentX);
                        actionX = event.getX();
                        actionY = event.getY();
                    }else {//上下滑动

                    }

                    return true;


                } else if (moveType == 2) {
//                    scale = scale * getSpacing(event) / spacing;
//                    setScaleX(scale);
//                    setScaleY(scale);
//                    rotation = rotation + getDegree(event) - degree;
//                    if (rotation > 360) {
//                        rotation = rotation - 360;
//                    }
//                    if (rotation < -360) {
//                        rotation = rotation + 360;
//                    }
//                    setRotation(rotation);

                    return true;
                }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                moveType = 0;

                startTrans();
                return true;
        }

        return super.dispatchTouchEvent(event);
    }

    private void startTrans(){

        Animation ani=new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {
                // TODO Auto-generated method stub
                setDegree((int)(interpolatedTime*300f));
            }
        };
        ani.setDuration(3000);
        startAnimation(ani);

    }

    public void setDegree(int degree) {
        this.degree = degree;
        postInvalidate();
    }
}

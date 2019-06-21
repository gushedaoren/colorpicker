package com.brstory.colorpicker;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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
    Point center;

    int canvasWidth;
    int canvasHeight;
    Canvas canvas;
    Paint paint;

    int paletteWidth;

    int outer_radius;
    int inner_radius;

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

    int paletteHeight;

    int topPalettePosition=0;
    private int moveType; // 0=未选择，1=拖动，2=缩放

    String[] colors = {"#FFFFF0","#FF7F24","#FF1493","#EEE9E9","#EE7600","#EE0000","#A8A8A8","#97FFFF","#8B2500"};


    Bitmap orignalBitmap;
    private void initPalette(){


    }

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
        this.canvas=canvas;
        canvasWidth=canvas.getWidth();
        canvasHeight=canvas.getHeight();
        paint = new Paint();

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

        paletteWidth=canvasWidth/colors.length;



        paletteHeight= canvasHeight*2/3;

        outer_radius = canvasWidth*4/5;
        center = new Point(canvas.getWidth()/2, outer_radius);
        inner_radius = outer_radius-canvasWidth/6;

        topPalettePosition=outer_radius-inner_radius;

        drawPalette();

        drawCircle();




    }

    private void drawPalette(){
        for(int i=0;i<colors.length;i++){

            paint.setColor(  Color.parseColor(colors[i]));

            int left= (int) (i*paletteWidth+translationX);
            int right= (int) ((i+1)*paletteWidth+translationX);

            RectF rectF = new RectF(left,topPalettePosition,right,canvasHeight);
            RectF rectLeft = new RectF(left-canvasWidth,topPalettePosition,right-canvasWidth,canvasHeight);
            RectF rectRight = new RectF(left+canvasWidth,topPalettePosition,right+canvasWidth,canvasHeight);

            canvas.drawRect(rectF,paint);
            canvas.drawRect(rectLeft,paint);
            canvas.drawRect(rectRight,paint);



        }


    }

    private void drawCircle(){



        int arc_sweep = 180;
        int arc_ofset = 180;

        RectF outer_rect = new RectF(center.x-outer_radius, center.y-outer_radius, center.x+outer_radius, center.y+outer_radius);
        RectF inner_rect = new RectF(center.x-inner_radius, center.y-inner_radius, center.x+inner_radius, center.y+inner_radius);

        Path path = new Path();
        path.arcTo(outer_rect, arc_ofset, arc_sweep);
        path.arcTo(inner_rect, arc_ofset + arc_sweep, -arc_sweep);
        path.close();

        Paint fill = new Paint();
        fill.setColor(Color.GREEN);
        canvas.drawPath(path, fill);

        Paint border = new Paint();
        border.setStyle(Paint.Style.STROKE);
        border.setStrokeWidth(2);
        canvas.drawPath(path, border);



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

    /**
     * 将bitmap中的某种颜色值替换成新的颜色
     * @param bitmap
     * @param oldColor
     * @param newColor
     * @return
     */
    public static Bitmap replaceBitmapColor(Bitmap oldBitmap, int oldColor, int newColor)
    {
        //相关说明可参考 http://xys289187120.blog.51cto.com/3361352/657590/
        Bitmap mBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true);
        //循环获得bitmap所有像素点
        int mBitmapWidth = mBitmap.getWidth();
        int mBitmapHeight = mBitmap.getHeight();
        int mArrayColorLengh = mBitmapWidth * mBitmapHeight;
        int[] mArrayColor = new int[mArrayColorLengh];
        int count = 0;
        for (int i = 0; i < mBitmapHeight; i++) {
            for (int j = 0; j < mBitmapWidth; j++) {
                //获得Bitmap 图片中每一个点的color颜色值
                //将需要填充的颜色值如果不是
                //在这说明一下 如果color 是全透明 或者全黑 返回值为 0
                //getPixel()不带透明通道 getPixel32()才带透明部分 所以全透明是0x00000000
                //而不透明黑色是0xFF000000 如果不计算透明部分就都是0了
                int color = mBitmap.getPixel(j, i);
                //将颜色值存在一个数组中 方便后面修改
                if (color == oldColor) {
                    mBitmap.setPixel(j, i, newColor);  //将白色替换成透明色
                }

            }
        }
        return mBitmap;
    }
}

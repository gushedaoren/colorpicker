package com.brstory.colorpicker;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;

public class BRColorPickerView extends RelativeLayout {

    ViewPager viewPager;

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
    private float degree;
    private int moveType; // 0=未选择，1=拖动，2=缩放



    public BRColorPickerView(Context context) {
        super(context);
        this.context=context;
        initView();
    }

    private  void initView(){


        initViewPager();


        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });


    }

    private void initViewPager(){
        viewPager=new ViewPager(context);

    }






    public BRColorPickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initView();
    }

    public BRColorPickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BRColorPickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context=context;
        initView();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


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
                        translationX = translationX + dX*3;



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


    // 触碰两点间距离
    private float getSpacing(MotionEvent event) {
        //通过三角函数得到两点间的距离
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    // 取旋转角度
    private float getDegree(MotionEvent event) {
        //得到两个手指间的旋转角度
        double delta_x = event.getX(0) - event.getX(1);
        double delta_y = event.getY(0) - event.getY(1);
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }


    private void startTrans(){

        if(translationX>5000){
            translationX=0;
        }else if(translationX<-5000){
            translationX=0;
        }
        int transLeft=0;int transRight=0;
        if(translationX>0){
            transRight= (int) -translationX;
            transLeft=0;
        }else{
            transLeft= (int) translationX;
            transRight=0;
        }


        ObjectAnimator transAnim = ObjectAnimator.ofFloat(this, "translationX", translationX);
        transAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        transAnim.setDuration(500);
        transAnim.start();;

    }
}

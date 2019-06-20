package com.brstory.colorpicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PaintActivity extends AppCompatActivity {


    ViewPager viewPager;

    private List<View> viewList;//view数组

    private View view1, view2, view3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);


//        viewPager=findViewById(R.id.viewPager);
//
//        LayoutInflater inflater=getLayoutInflater();
//        view1 =  inflater.inflate(R.layout.layout_palette_pannel, null);
//
//        view1.setBackgroundColor(Color.BLACK);
//
//        view2 = inflater.inflate(R.layout.layout_palette_pannel, null);
//        view2.setBackgroundColor(Color.YELLOW);
//
//        view3 = inflater.inflate(R.layout.layout_palette_pannel, null);;
//        view3.setBackgroundColor(Color.GRAY);
//
//
//        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
//        viewList.add(view1);
//        viewList.add(view2);
//        viewList.add(view3);
//
//        viewPager.setAdapter(pagerAdapter);



    }

    PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return viewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            // TODO Auto-generated method stub
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            container.addView(viewList.get(position));


            return viewList.get(position);
        }
    };

}

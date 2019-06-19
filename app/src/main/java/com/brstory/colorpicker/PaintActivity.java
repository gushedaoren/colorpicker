package com.brstory.colorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PaintActivity extends AppCompatActivity {

    BRColorPickerView brColorPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        brColorPickerView=findViewById(R.id.color_picker_view);
    }
}

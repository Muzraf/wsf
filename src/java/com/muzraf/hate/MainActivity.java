package com.muzraf.hate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;


public class MainActivity extends Activity {
    private TextView t1;
    private TextView t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.text_time);
        t2 = findViewById(R.id.text_time_value);
    }
}

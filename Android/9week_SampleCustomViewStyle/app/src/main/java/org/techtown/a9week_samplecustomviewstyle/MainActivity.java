package org.techtown.a9week_samplecustomviewstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomViewStyle view = new CustomViewStyle(this);
        setContentView(view);
    }
}
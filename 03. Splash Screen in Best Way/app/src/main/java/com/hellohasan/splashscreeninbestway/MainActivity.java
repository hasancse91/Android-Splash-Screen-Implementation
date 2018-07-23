package com.hellohasan.splashscreeninbestway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set your theme before super.onCreate() to replace your previous theme of Manifest
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: add your MainActivity code here
    }
}
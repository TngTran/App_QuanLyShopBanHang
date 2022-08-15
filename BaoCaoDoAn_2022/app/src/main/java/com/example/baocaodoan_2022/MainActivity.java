package com.example.baocaodoan_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Snackbar.make(findViewById(R.id.layout_main), "Đăng nhập thành công vào thế giới của tớ <3", Snackbar.LENGTH_LONG)
                .setAction("Action", null).setTextColor(Color.rgb(252, 252, 252)).setBackgroundTint(Color.rgb(255, 154, 45)).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
    }
}
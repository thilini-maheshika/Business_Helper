package com.example.businesshelper.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.businesshelper.R;

public class Landing extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Landing.this,Start_menu.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}
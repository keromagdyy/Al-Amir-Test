package com.example.alamirgrouptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Animation logoAnim,logoCenterAnim;

    ImageView imgLogo,imgCenterLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, homeActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);


        logoAnim = AnimationUtils.loadAnimation(this,R.anim.logo_anim);
        logoCenterAnim = AnimationUtils.loadAnimation(this,R.anim.logo_center_anim);

        imgLogo = findViewById(R.id.img_logo);
        imgCenterLogo = findViewById(R.id.img_center_logo);


        imgCenterLogo.setAnimation(logoCenterAnim);
        imgLogo.setAnimation(logoAnim);

    }
}
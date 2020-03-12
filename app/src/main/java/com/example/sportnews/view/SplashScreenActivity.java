package com.example.sportnews.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportnews.R;

public class SplashScreenActivity extends AppCompatActivity {

    LinearLayout layoutlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        layoutlogo = findViewById(R.id.line);

        Animation splashing = AnimationUtils.loadAnimation(this,R.anim.splash_logo);
        layoutlogo.startAnimation(splashing);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToMain = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(goToMain);
                finish();
            }
        },5000);
    }
}

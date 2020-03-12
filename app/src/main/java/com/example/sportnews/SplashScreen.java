package com.example.sportnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import java.util.Objects;

public class SplashScreen extends AppCompatActivity {

    LinearLayout layoutlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();

        layoutlogo = findViewById(R.id.line);

        Animation splashing = AnimationUtils.loadAnimation(this, R.anim.splash_logo);
        layoutlogo.startAnimation(splashing);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToMain = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(goToMain);
                finish();
            }
        },5000);
    }
}

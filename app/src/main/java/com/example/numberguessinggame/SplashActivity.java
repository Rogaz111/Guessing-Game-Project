package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    Animation animationImage,animationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//find component Ids
        imageView = findViewById(R.id.imageButton2);
        textView = findViewById(R.id.textView);
//Create animation objects
        animationImage = AnimationUtils.loadAnimation(this,R.anim.image_animation);
        animationText = AnimationUtils.loadAnimation(this,R.anim.text_animation);
// Initialize animation objects
        imageView.setAnimation(animationImage);
        imageView.setAnimation(animationText);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }.start();
    }
}
package com.jalilov.mathtime.activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.jalilov.mathtime.R;
import com.jalilov.mathtime.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    Animation topAnim, botAnim;
    ActivitySplashBinding binding;
     static int SPLASH_SCREEN = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivitySplashBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        binding.imageView2.setAnimation(topAnim);
        binding.textview.setAnimation(botAnim);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN);
    }


}
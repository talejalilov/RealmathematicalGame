package com.jalilov.mathtime.activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.jalilov.mathtime.R;
import com.jalilov.mathtime.activities.additionalClasses.AdditionalActivity;
import com.jalilov.mathtime.activities.dividedClasses.DividedActivity;
import com.jalilov.mathtime.activities.initialize.InitializationAnim;
import com.jalilov.mathtime.activities.multiplyClasses.MultiplyActivity;
import com.jalilov.mathtime.activities.subtractionClasses.SubtractionActivity;
import com.jalilov.mathtime.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private Intent intent;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MobileAds.initialize(MainActivity.this, initializationStatus -> {

        });

        AdRequest adRequest  =new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);

        @SuppressLint("NonConstantResourceId")
        View.OnTouchListener onTouchListener = (v, event) -> {
            switch (v.getId()) {
                case R.id.multiplyBtn:
                    intent = new Intent(MainActivity.this, MultiplyActivity.class);

                    break;
                case R.id.dividedBtn:
                    intent = new Intent(MainActivity.this, DividedActivity.class);
                    break;
                case R.id.subtractionBtn:
                    intent = new Intent(MainActivity.this, SubtractionActivity.class);
                    break;
                default:
                    intent = new Intent(MainActivity.this, AdditionalActivity.class);
                    break;
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            InitializationAnim.initialize(MainActivity.this, v, event);
            return false;
        };

        binding.addBtn.setOnTouchListener(onTouchListener);
        binding.dividedBtn.setOnTouchListener(onTouchListener);
        binding.multiplyBtn.setOnTouchListener(onTouchListener);
        binding.subtractionBtn.setOnTouchListener(onTouchListener);


    }
}
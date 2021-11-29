package com.jalilov.mathtime.activities.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.jalilov.mathtime.activities.initialize.InitializationAnim;
import com.jalilov.mathtime.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    ActivityResultBinding binding;
    int newScore;
    SharedPreferences setting;
    int recordScore;
    private InterstitialAd mInterstitialAd;
    private RewardedAd mRewardedAd;

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        newScore = intent.getIntExtra("userScore", 0);
        String userScore = String.valueOf(newScore);
        binding.textViewResult.setText("Your Score: " + userScore);

        setting = getSharedPreferences("Game_data", MODE_PRIVATE);
        recordScore = setting.getInt("HighScore", 0);

        if (newScore >= recordScore) {
            binding.textViewRecord.setText("High Score: " + newScore);
            //Save
            SharedPreferences.Editor editor = setting.edit();
            editor.putInt("HighScore", newScore);
            editor.apply();
        } else {
            binding.textViewRecord.setText("High Score: " + recordScore);
        }
//        /**
//         * bu method bir defe ishleyecek her basildiginda yox ilk defe basildiginda ishe dushecek
//         ama her basildiginda ishe dushmek ucun buttonun icindede cagirirq*/
        awardWinningAd();
        addTransitionUnit();

        binding.btnAgain.setOnTouchListener((v, event) -> {
            binding.btnAgain.setOnClickListener(v1 -> {
                if (mRewardedAd != null) {

                    mRewardedAd.show(ResultActivity.this, rewardItem -> {
                        //eger reklami izleyibse oyuncuya can veririkse burdan vermeliyik
                        Intent intent23 = new Intent(ResultActivity.this, MainActivity.class);
                        intent23.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent23);
                        finish();

                    });
                } else {
                    Intent intent23 = new Intent(ResultActivity.this, MainActivity.class);
                    intent23.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent23);
                    finish();
                }
            });

            InitializationAnim.initialize(getApplicationContext(), v, event);
            return false;
        });

        binding.btnExit.setOnClickListener(v -> {


            AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
            builder.setTitle("Exit");
            builder.setMessage("Are you sure?");
            builder.setNegativeButton("No", null);
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(ResultActivity.this);
                            addTransitionUnit();
                            finish();
                        }
                        finish();
                    }
            );
            builder.show();
        });
    }


    public void addTransitionUnit() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, "ca-app-pub-5112439562846419/1803736028", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                //reklam olusdurdugu zaman yukarda belirtdiiymiz ivine topladik
                mInterstitialAd = interstitialAd;

            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                mInterstitialAd = null;
                //    Toast.makeText(ResultActivity.this, "Dont showed unit", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void awardWinningAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-5112439562846419/4480680883", adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                super.onAdLoaded(rewardedAd);
                //reklam gelerse ne yapsin
                mRewardedAd = rewardedAd;
                mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        //reklam bitdikten sonra tekrar tikladiginda ne yapsin
                        super.onAdDismissedFullScreenContent();
                        awardWinningAd();
                    }
                });

            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                //reklam gosterilmezse ne yapsin
                mRewardedAd = null;
                //  Toast.makeText(ResultActivity.this, "Dont showed unit", Toast.LENGTH_LONG).show();


            }
        });

    }
}
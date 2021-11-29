package com.jalilov.mathtime.activities.subtractionClasses;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jalilov.mathtime.R;
import com.jalilov.mathtime.activities.initialize.InitializationAnim;
import com.jalilov.mathtime.databinding.ActivitySubtractionBinding;

public class SubtractionActivity extends AppCompatActivity {

    private ActivitySubtractionBinding binding;

    SubtractionGame game = new SubtractionGame();
    boolean timerRunning;
    int currentLife;

    int secondsRemaining = 40;
    private CountDownTimer timer;

    // Animation scaleUp, scaleDown;
    MediaPlayer wrongAnswer, correctAnswer, tryAgain;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubtractionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        correctAnswer = MediaPlayer.create(this, R.raw.correct_answer_sound);
        tryAgain = MediaPlayer.create(this, R.raw.tryagain);
        wrongAnswer = MediaPlayer.create(this, R.raw.wrong_answer_sound);
//        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
//        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        binding.setQuestion.setText("");
        binding.setScore.setText("0");
        binding.progressBarSetTime.setProgress(0);
        nextTurn(binding.getRoot());
//        startTimer();

        @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
        View.OnTouchListener answerButtonListener = (v, event) -> {
            TextView buttonClicked = (TextView) v;
            int realAnswer = game.getCurrentQuestion().getAnswer();
            int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
            if (realAnswer == answerSelected) {
                correctAnswer.start();
                game.checkAnswer(answerSelected);
                binding.setScore.setText(Integer.toString(game.getScore()));
                v.setBackgroundResource(R.drawable.nextback);
                nextTurn(v);

                for (int i = 10; i < 10000; i += 10) {
                    if (game.getScore() == (i) * 10) {
                        pauseTimer();
                        resetTimer();
                        startTimer();
                        resetLife();
                    }
                }
            } else {
                wrongAnswer.start();
                game.checkLife(timer, SubtractionActivity.this);
                currentLife = game.getLife();
                currentLife--;
                game.setLife(currentLife);
                binding.setLife.setText(Integer.toString(currentLife));
                v.setBackgroundResource(R.drawable.extback);
            }

            InitializationAnim.initialize(this, v , event);
            return false;
        };
        binding.option1.setOnTouchListener(answerButtonListener);
        binding.option2.setOnTouchListener(answerButtonListener);
        binding.option3.setOnTouchListener(answerButtonListener);
        binding.option4.setOnTouchListener(answerButtonListener);

    }

    @Override
    protected void onStart() {
        Log.d("TAG", "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("TAG", "onResume: ");
        startTimer();

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("TAG", "onPause: ");

        super.onPause();
    }

    @Override
    protected void onStop() {
        pauseTimer();
        Log.d("TAG", "onStop: ");
        super.onStop();
    }



    @Override
    protected void onDestroy() {
        timer.cancel();
        finish();
        Log.d("TAG", "onDestroy: ");
        super.onDestroy();
    }


    @SuppressLint("SetTextI18n")
    public void nextTurn(View v) {
        //create new question
        //set text on answer buttons
        //enable answer buttons
        //start the timer

        game.makeQuestion();
        int[] answer = game.getCurrentQuestion().getAnswerArray();

        binding.option1.setText(Integer.toString(answer[0]));
        binding.option2.setText(Integer.toString(answer[1]));
        binding.option3.setText(Integer.toString(answer[2]));
        binding.option4.setText(Integer.toString(answer[3]));

        binding.option1.setBackgroundResource(R.drawable.multuply_back_btn);
        binding.option2.setBackgroundResource(R.drawable.multuply_back_btn);
        binding.option3.setBackgroundResource(R.drawable.multuply_back_btn);
        binding.option4.setBackgroundResource(R.drawable.multuply_back_btn);

        binding.option1.setEnabled(true);
        binding.option2.setEnabled(true);
        binding.option3.setEnabled(true);
        binding.option4.setEnabled(true);

        binding.setQuestion.setText(game.getCurrentQuestion().getQuestionPhrase());

    }

    private void updateText() {
        secondsRemaining--;
        binding.progressBarSetTime.setProgress(40 - secondsRemaining);
    }

    public void startTimer() {
        timer = new CountDownTimer(40000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateText();

            }

            @Override
            public void onFinish() {
                binding.option1.setEnabled(false);
                binding.option2.setEnabled(false);
                binding.option3.setEnabled(false);
                binding.option4.setEnabled(false);
                game.sendScore(SubtractionActivity.this);
            }
        }.start();

    }

    private void pauseTimer() {

        timer.cancel();
        timerRunning = false;
    }

    public void resetTimer() {

        secondsRemaining = 40;
        updateText();

    }


    @SuppressLint("SetTextI18n")
    public void resetLife() {

        currentLife = 3;
        binding.setLife.setText(Integer.toString(currentLife));
    }

}
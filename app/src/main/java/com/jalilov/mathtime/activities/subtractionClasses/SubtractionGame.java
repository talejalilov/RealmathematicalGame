package com.jalilov.mathtime.activities.subtractionClasses;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import com.jalilov.mathtime.activities.activities.ResultActivity;
import java.util.ArrayList;
import java.util.List;

public class SubtractionGame {
      List<SubtractionQuestion> questionList;

    private int numberCorrect;
    private int numberICorrect;
    private int totalQuestions = 0;



    private int score;
    private int life;

    private SubtractionQuestion currentQuestion;


    public SubtractionGame() {
        currentQuestion = new SubtractionQuestion(10);
        numberCorrect = 0;
        numberICorrect = 0;
        score = 0;
        life = 3;
        questionList = new ArrayList<>();

    }

    public void makeQuestion(){
        currentQuestion = new SubtractionQuestion(totalQuestions * 2 + 5);
        totalQuestions += 5;
        questionList.add(currentQuestion);

    }

    public void sendScore(Activity activity){

        Intent intent = new Intent(activity, ResultActivity.class);
        intent.putExtra("userScore", getScore());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();

    }



    public void checkAnswer(int submittedAnswer) {
        if (currentQuestion.getAnswer() == submittedAnswer) {
            numberCorrect++;
        } else {
            numberICorrect++;
        }
        score = numberCorrect * 10 - numberICorrect * 20;
    }

    public void checkLife(CountDownTimer timer, Activity activity) {
        if (life == 1) {
            timer.cancel();
            sendScore(activity);
        }

    }






    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }


    public SubtractionQuestion getCurrentQuestion() {
        return currentQuestion;
    }


    }

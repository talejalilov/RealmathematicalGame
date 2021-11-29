package com.jalilov.mathtime.activities.dividedClasses;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;

import com.jalilov.mathtime.activities.activities.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class DividedGame {
    List <DividedQuestion> questionList;


    private int numberCorrect;
    private int numberICorrect;
    private int score;
    private int life;

    private DividedQuestion dividedQuestion;


    public DividedGame() {
        dividedQuestion = new DividedQuestion();
        numberCorrect = 0;
        numberICorrect = 0;
        score = 0;
        life = 3;
        questionList = new ArrayList<>();

    }

    public void makeQuestion(){
        dividedQuestion = new DividedQuestion();
        questionList.add(dividedQuestion);

    }

    public void sendScore(Activity activity){

        Intent intent = new Intent(activity, ResultActivity.class);
        intent.putExtra("userScore", getScore());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();

    }





    public void checkAnswer(int submittedAnswer) {
        if (dividedQuestion.getAnswer() == submittedAnswer) {
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

    public  DividedQuestion getDividedQuestion() {
        return dividedQuestion;
    }


}

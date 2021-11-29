package com.jalilov.mathtime.activities.multiplyClasses;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import com.jalilov.mathtime.activities.activities.ResultActivity;
import java.util.ArrayList;
import java.util.List;

public class MultiplyGame {

      List<MultiplyQuestion> questionList;



    private int numberCorrect;
    private int numberICorrect;
    private int totalQuestions = 0;
    private int score;
    private int life;

    private MultiplyQuestion currentQuestion;


    public MultiplyGame() {
        currentQuestion = new MultiplyQuestion(5);
        numberCorrect = 0;
        numberICorrect = 0;
        score = 0;
        life = 3;
        questionList = new ArrayList<>();

    }

    public void makeQuestion(){
        currentQuestion = new MultiplyQuestion(totalQuestions * 2 + 1);
        totalQuestions += 1;
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

    public MultiplyQuestion getCurrentQuestion() {
        return currentQuestion;
    }

}

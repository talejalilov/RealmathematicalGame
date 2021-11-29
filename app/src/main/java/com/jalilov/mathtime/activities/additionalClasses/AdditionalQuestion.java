package com.jalilov.mathtime.activities.additionalClasses;

import java.util.Random;

public class AdditionalQuestion {
      int firstNumber;
      int secondNumber;
      int answer;

    //there are four possible choices dor the user to pick from
      int[] answerArray;

    //which of the four positions is correct , 0, 1, 2, 3
      int answerPosition;

    //the maximum value of firstNumber ot SecondNumber

    //string output of the problem e.g. "4 + 9 ="
      String questionPhrase;


//    // generate a new Random question


    public AdditionalQuestion(int upperLimit) {

        Random random = new Random();
//
        this.firstNumber = random.nextInt(upperLimit);
        this.secondNumber = random.nextInt(upperLimit);
        this.answer = this.firstNumber + this.secondNumber;
        this.questionPhrase = this.firstNumber + " + " + this.secondNumber;

        //cavab 4unden birindedir
        this.answerPosition = random.nextInt(4);
        this.answerArray = new int[] {0,1,2,3};

        this.answerArray[0] = this.answer + 1;
        this.answerArray[1] = this.answer + 5;
        this.answerArray[2] = this.answer - 10;
        this.answerArray[3] = this.answer - 3;

        shufflerArray(this.answerArray);
        //ve o array icinde answerPosition-da esas cavabdir
        answerArray[answerPosition] = this.answer;

    }
    private void shufflerArray(int[] array) {
        int index, temp;

        Random randomNumberGenerator = new Random();

        for (int i = array.length - 1; i > 0; i--)
        {
            index = randomNumberGenerator.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }



    public int getAnswer() {
        return answer;
    }

    public int[] getAnswerArray() {
        return answerArray;
    }

    public String getQuestionPhrase() {
        return questionPhrase;
    }

}


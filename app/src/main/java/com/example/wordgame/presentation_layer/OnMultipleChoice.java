package com.example.wordgame.presentation_layer;

import android.view.View;
import android.widget.Button;

import java.util.List;

public interface OnMultipleChoice {
    /**
     * handles choice buttons in multiple choice questions
     * @param view clicked choice
     * @param pos position of the clicked view
     */
    public void choices(View view, int pos);

    /**
     * initialise buttons of multiple choice
     * @param button1 first button
     * @param button2 second button
     * @param button3 third button
     * @param button4 fourth button
     */
    public void onMultipleChoice(Button button1, Button button2, Button button3, Button button4);


    /**
     * set number of questions
     * @param number of questions
     */
    public  void numberOfQuestions(int number);

    /**
     * set color
     * @param value color being set
     */
    public  void setColor(int value);

    /**
     * get color
     * @return color
     */
    public  int getColor();

    /**
     * store answers in the given array list
     * @param answers list to store answers
     */
    public void StoreAnswer(List<String > answers);
}

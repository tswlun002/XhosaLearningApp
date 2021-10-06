package com.example.wordgame.presentation_layer;
import android.view.View;

import java.util.List;

public interface OnTrueFalseQuestion {
    /***
     * handle true button event
     * @param view  of true button
     * @param position of the true button
     */
    public void trueButton(View view, int position);
    /***
     * handle false button event
     * @param view  of false button
     * @param position of the true button
     */
    public void falseButton(View view, int position);

    /**
     * @param number of true false game
     */
    public  void numberOfQuestions(int number);

    /**
     * @param value set color in a clicked button
     */
    public  void setColor(int value);

    /**
     * @return get color of the clicked buttom
     */
    public  int getColor();
    public void StoreAnswer(List<View > answers);

}

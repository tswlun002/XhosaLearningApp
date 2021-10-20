package com.example.wordgame.presentation_layer;
import android.view.View;

import java.util.List;

/**
 * @Class HandleTrueFalse is used to handle button clicks of TrueFalse game
 * Uses abstract class CreateKeys to store user answer and
 * implement OnTrueFalseQuestion to handle button clicks on TrueFalse game
 */
public class HandleTrueFalse extends CreateKeys implements OnTrueFalseQuestion {
    private int color ;
    public HandleTrueFalse(){
        super();
    }
    /**
     * handle true button on True False game
     * @param view clicked view
     * @param position clicked position
     */
    @Override
    public void trueButton(View view, int position) {
        setColor(addView(view,position));
    }
    /**
     * handle false button on True False game
     * @param view clicked view
     * @param position clicked position
     */
    @Override
    public void falseButton(View view, int position) {
        setColor(addView(view,position));
    }

    /**
     * set number of questions
     * @param number number of questions
     */
    @Override
    public void numberOfQuestions(int number) {
          setNumberOfQuestions(number);
    }

    /**
     * set color in the clicked button
     * @param value of the color
     */
    @Override
    public void setColor(int value){
        color =value;
    }

    /**
     * get value of the color
     * @return value of the color
     */
    @Override
    public int getColor(){
        return color;
    }

    /**
     * Store all user answer for multiple choice
     * @param answers user answer for multiple choice
     */
    @Override
    public void StoreAnswer(List<View > answers) {
        storeAnswer(answers);
    }
}

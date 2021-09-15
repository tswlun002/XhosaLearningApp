package com.example.wordgame.presentation_layer;
import android.view.View;

/**
 * @Class HandleTrueFalse is used to handle button clicks of TrueFalse game
 * Uses abstract class CreateKeys to store user answer and
 * implement OnTrueFalseQuestion to handle button clicks on TrueFalse game
 */
public class HandleTrueFalse extends CreateKeys implements OnTrueFalseQuestion {
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
        addView(view,position);
    }
    /**
     * handle false button on True False game
     * @param view clicked view
     * @param position clicked position
     */
    @Override
    public void falseButton(View view, int position) {
        addView(view,position);
    }
}

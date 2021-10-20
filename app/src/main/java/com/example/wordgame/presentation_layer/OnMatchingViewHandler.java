package com.example.wordgame.presentation_layer;

import android.view.View;

public interface OnMatchingViewHandler {
    /**
     * @param view set view that touched , dragged or clicked
     */
    public  void viewClicked(View view);

    /**
     * number of questions for matching game
     * @param number number of questions
     */
    public  void numberOfQuestions(int number);
}

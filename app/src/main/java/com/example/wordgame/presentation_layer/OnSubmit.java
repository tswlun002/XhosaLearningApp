package com.example.wordgame.presentation_layer;

import android.view.LayoutInflater;
import android.view.View;

public interface OnSubmit {
    /**
     * submit user answers for each game
     * @param view is the clicked
     * @param inflater inflate the window
     */
    public  void onSubmit(View view, LayoutInflater inflater);

    /**
     * get score of after submission
     * @return score of the user after submission
     */
    public double getScore();
}

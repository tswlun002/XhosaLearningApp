package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * @Class  Handles hint requests and update number of hints
 */
public class HintHandler extends HintAdapter implements OnHints {

    /**
     * @serialField  position of the hint button a layout
     */
    int position;
    private final int[] totalHints ={10,10,10,10};

    /**
     * Request hint
     * If number hints are still greater than zero, hint is requested
     * Else disable the hint button and not hint request are made
     * @param inflater inflater for hint dialog
     * @param view is the hint button view
     * @param position of the hint button
     */
    @Override
    public void onRequestHint(LayoutInflater inflater,View view, int position) {
        int numberOfHints;
        this.position=position;
        numberOfHints = totalHints[position];
        numberOfHints--;
        if(numberOfHints <0)
            view.setEnabled(false);
        else {
            totalHints[position] =numberOfHints;
            getHint(inflater);
        }
    }

    /**
     * Update hint number label
     * @param view label we update
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void updateNumberHints(TextView view) {
        view.setText("No of hints: " + totalHints[position]);
    }


}

package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * @Class  Handles hint requests and update number of hints
 */
public class HintHandler extends HintAdapter implements OnHints {

    /**
     * @serialField  position of the hint button a layout
     */
    private  int position;
    private int[] totalHints ;
    private int numberOfHints;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNumberOfHints() {
        return numberOfHints;
    }

    public void setNumberOfHints(int numberOfHints) {
        this.numberOfHints = numberOfHints;
    }


    /**
     * Request hint
     * If number hints are still greater than zero, hint is requested
     * Else disable the hint button and not hint request are made
     * @param inflater inflater for hint dialog
     * @param view is the hint button view
     * @param position of the hint button
     */
    @Override
    public void onRequestHint(LayoutInflater inflater,View view,int numberHints,int position,String [] data) {

        setPosition(position);
        setNumberOfHints(numberHints);
        if(numberOfHints <=0)
            view.setEnabled(false);
        else {
            getHint(inflater,data[3-getNumberOfHints()]);
            setNumberOfHints(getNumberOfHints()-1);
        }
    }
    @Override
    public void generateTotalHints(int range){
         totalHints = new int[range];
        for(int i=0; i<range;i++){
            totalHints[i]=3;
        }
    }

    /**
     * Update hint number value
     * @param hintData list we update
     */
    @Override
    public void updateNumberHints(List<Integer> hintData,int position) {
            hintData.set(position,getNumberOfHints());
    }


}

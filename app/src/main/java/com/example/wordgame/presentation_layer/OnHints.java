package com.example.wordgame.presentation_layer;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public interface OnHints {

    /**
     * @param range number hint user can get
     */
     public void generateTotalHints(int range);

    /**
     * Handle hint requests from user
     * @param inflater inflater of hint window
     * @param view of the hint window
     * @param numberHints hint user have
     * @param position  question number for the hint
     * @param data hint data
     */
    public  void  onRequestHint(LayoutInflater inflater,View view,int numberHints,int position,String [] data);
    public  void updateNumberHints(List<Integer> hintData,int position);
}

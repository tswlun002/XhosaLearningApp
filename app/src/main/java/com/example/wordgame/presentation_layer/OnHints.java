package com.example.wordgame.presentation_layer;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public interface OnHints {
     public void generateTotalHints(int range);
    public  void  onRequestHint(LayoutInflater inflater,View v,int numberHints,int position,String [] data);
    public  void updateNumberHints(List<Integer> hintData,int position);
}

package com.example.wordgame.presentation_layer;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public interface OnHints {
     public void generateTotalHints(int range);
    public  void  onRequestHint(LayoutInflater inflater,View v,int position,String [] data);
    public  void updateNumberHints(TextView numberOfHints);
}

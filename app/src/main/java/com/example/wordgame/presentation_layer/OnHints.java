package com.example.wordgame.presentation_layer;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public interface OnHints {
    public  void  onRequestHint(LayoutInflater inflater,View v,int position);
    public  void updateNumberHints(TextView numberOfHints);
}

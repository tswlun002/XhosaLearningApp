package com.example.wordgame.presentation_layer;

import android.view.LayoutInflater;
import android.view.View;

public interface OnSubmit {

    public  void onSubmit(View view, LayoutInflater inflater);
    public double getScore();
}

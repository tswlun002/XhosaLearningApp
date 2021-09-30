package com.example.wordgame.presentation_layer;
import android.view.View;

import java.util.List;

public interface OnTrueFalseQuestion {
    public void trueButton(View view, int position);
    public void falseButton(View view, int position);
    public  void numberOfQuestions(int number);
    public  void setColor(int value);
    public  int getColor();
    public void StoreAnswer(List<String > answers);

}

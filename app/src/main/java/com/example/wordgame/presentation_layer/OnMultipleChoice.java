package com.example.wordgame.presentation_layer;

import android.view.View;
import android.widget.Button;

import java.util.List;

public interface OnMultipleChoice {

    public void choices(View view, int pos);
    public void onMultipleChoice(Button button1, Button button2, Button button3, Button button4);
    public void scrollDown(int pos);
    public  void numberOfQuestions(int number);
    public  void setColor(int value);
    public  int getColor();
    public void StoreAnswer(List<String > answers);
}

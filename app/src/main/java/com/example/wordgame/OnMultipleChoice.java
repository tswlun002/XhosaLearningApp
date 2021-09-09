package com.example.wordgame;

import android.view.View;
import android.widget.Button;

public interface OnMultipleChoice {

    public void choices(View view, int pos);
    public void onMultipleChoice(Button button1, Button button2, Button button3, Button button4);
    public void scrollDown(int pos);
}

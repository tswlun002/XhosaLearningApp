package com.example.wordgame;
import android.view.View;

import java.util.HashMap;


public class HandleTrueFalse extends CreateKeys implements  OnTrueFalseQuestion{

    HandleTrueFalse(){
        super(new String[]{"a", "b", "c", "d", "e", "f"},new HashMap<String,View>());
    }
    /**
     * handle true button on True False game
     * @param view clicked view
     * @param position clicked position
     */
    @Override
    public void trueButton(View view, int position) {
        addView(view,position);
    }
    /**
     * handle false button on True False game
     * @param view clicked view
     * @param position clicked position
     */
    @Override
    public void falseButton(View view, int position) {
        addView(view,position);
    }
}

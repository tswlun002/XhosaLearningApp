package com.example.wordgame;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.List;

/**
 * @Class HandleMQCButtons is used to handle button clicks of MQC game
 * Uses abstract class CreateKeys to store user answer and
 * implement OnMultipleChoice to handle button clicks on MQC game
 */
public class HandleMQCButtons extends CreateKeys implements  OnMultipleChoice{
    private Button btn1,btn2,btn3,btn4;
    HandleMQCButtons(){
       super();
    }

    /**
     * add the view to list
     * @param view is the clicked view
     */
    @Override
    public void choices(View view,int pos) { addView(view,pos);
    }

    /**
     * multiple choice interface method to initialise buttons
     * @param button1 button one
     * @param button2 button two
     * @param button3 button three
     * @param button4 button four
     */
    @Override
    public void onMultipleChoice(Button button1, Button button2, Button button3, Button button4) {
        this.btn1=button1;
        this.btn2=button2;
        this.btn3=button3;
        this.btn4=button4;
    }

    /**
     * scroll recycle view to the next position
     * @param position current position , scroll from
     */
    @Override
    public void scrollDown(int position) {
        /*recycleView.scrollToPosition(position+1)*/;
    }

}

package com.example.wordgame.presentation_layer;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @Class HandleMQCButtons is used to handle button clicks of MQC game
 * Uses abstract class CreateKeys to store user answer and
 * implement OnMultipleChoice to handle button clicks on MQC game
 */
public class HandleMQCButtons extends CreateKeys implements OnMultipleChoice ,OnScroll{
    private int color,numberOfQuestions;
    public HandleMQCButtons(){
       super();
    }

    /**
     * add the view to list
     * @param view is the clicked view
     */
    @Override
    public void choices(View view,int pos) {
       setColor(addView(view,pos));
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
    }

    /**
     * scroll recycle view to the next position
     * @param position current position , scroll from
     */
     @Override
    public void scrollDown(int position, RecyclerView recyclerView) {
         if (position + 1 < getNumberQuestions())
             recyclerView.scrollToPosition(position+1);

    }
    /**
     * set number of questions
     * @param number number of questions
     */
    @Override
    public void numberOfQuestions(int number) {
        numberOfQuestions=number;
        setNumberOfQuestions(number);
    }

    /**
     * @param value color number
     */
    @Override
    public void setColor(int value) {
       color=value;
    }

    /**
     * @return color number
     */
    @Override
    public int getColor() {
        return color;
    }

    /**
     * store all answers
     * @param answers list of answers
     */
    @Override
    public void StoreAnswer(List<String> answers) {
         //storeAnswer(answers);
    }

    /**
     * Get number of questions of  multiple choice
     * @return number of questions for multiple choice
     */
    public int getNumberQuestions(){
        return numberOfQuestions;
    }

}

package com.example.wordgame.presentation_layer;
import android.view.LayoutInflater;
import android.view.View;


public class SubmitHandler extends ActivityResults implements OnSubmit,View.OnClickListener{

    /**
     * @serialField  inflater to inflate user score submitted
     */
    private final LayoutInflater inflater;

    /**
     * initialise   super class and inflater
     * @param inflater to inflate user score submitted
     * @param Id navigation to score board
     * @param view current view or fragment of the score
     */
    public SubmitHandler(LayoutInflater inflater, int Id, View view) {
        super(inflater,Id,view);
        this.inflater=inflater;
    }

    /**
     * submit user score
     * @param view current view or fragment of the score
     * @param inflater  of the  the score window
     */
    @Override
    public void onSubmit(View view, LayoutInflater inflater) {
        gradesActity(0,0);
    }
    /**
     * handle button event
     * Show grades for user
     * @param v is clicked view
     */
    @Override
    public void onClick(View v) {
       onSubmit(v,inflater);
    }
}

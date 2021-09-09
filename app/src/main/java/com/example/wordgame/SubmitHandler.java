package com.example.wordgame;
import android.view.LayoutInflater;
import android.view.View;



public class SubmitHandler implements  Submit {

    @Override
    public void onSubmit(View view, LayoutInflater inflater) {
        ActiviyResults activiyResults = new ActiviyResults(inflater, inflater.getContext(),R.id.action_matchingFragment_to_play,view);
        activiyResults.gradesActity(0,0);
    }

}

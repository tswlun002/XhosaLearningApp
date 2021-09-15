package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.example.wordgame.R;

public abstract  class ActivityResults {

    private final LayoutInflater inflater;
    private final Context context;
    static int actionId;
    private final View fragmentView;

    public ActivityResults(LayoutInflater inflater, int Id, View fragment1){
        this.inflater=inflater;
        this.context=inflater.getContext();
        actionId = Id;
        fragmentView = fragment1;
    }


    /**
     * This method display user score, the answers they had wrong with corrections.
     * @param userScore stores the user score for each of the exercises
     * @param totalScore stores the user average score of each of the levels.
     */
    @SuppressLint("SetTextI18n")
    //introduced new parameters userScore and total Score
    public void gradesActity(int userScore, int totalScore){
        final View view = inflater.inflate(R.layout.fragment_results__current_activity, null);
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("                           Score");


        TextView grades  = view.findViewById(R.id.gradesActivity);
        TextView answers = view.findViewById(R.id.CorrectionsID);
        Button tryAgainButton = view.findViewById(R.id.ExitBitton);
        Button nextButton = view.findViewById(R.id.NextButtonId);

        grades.setText("1.True\n2.false\n3.true\n4.false");
        answers.setText("Your Score and Answers:".toUpperCase()+userScore+"/"+totalScore);
        alertDialog.setView(view);
        alertDialog.show();

        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
               Navigation.findNavController(fragmentView).navigate(actionId);
            }
        });
    }

}

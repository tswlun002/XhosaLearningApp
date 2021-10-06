package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.example.wordgame.R;

import java.util.HashMap;
import java.util.List;

public abstract  class ActivityResults {

    private final LayoutInflater inflater;
    private final Context context;
    private  int actionId;
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
    public void gradesActity(double userScore, int totalScore, List<String> correctAnswers){
        final View view = inflater.inflate(R.layout.fragment_results__current_activity, null);
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("     Score");


        String CorrectAnswers = buildAnswers(correctAnswers);
        TextView grades  = view.findViewById(R.id.gradesActivity);
        TextView answers = view.findViewById(R.id.CorrectionsID);
        Button tryAgainButton = view.findViewById(R.id.ExitBitton);
        Button nextButton = view.findViewById(R.id.NextButtonId);

        grades.setText(CorrectAnswers);
        answers.setText("Your Score and Answers:".toUpperCase()+userScore+"/"+totalScore);
        alertDialog.setView(view);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController((MainActivity) inflater.getContext(),
                        R.id.nav_host_fragment_content_main).
                        navigate(actionId);

                alertDialog.dismiss();

            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Navigation.findNavController((MainActivity) inflater.getContext(),
                        R.id.nav_host_fragment_content_main).
                        navigate(R.id.action_results_CurrentActivity_to_play);
            }
        });
    }
    String  buildAnswers( List<String> correctAnswers){
        StringBuilder answers = new StringBuilder();
        int count =0;
        for (Object key:correctAnswers) {
            answers.append(count).append(".").append(key).append("\n");
            count++;
        }
        return answers.toString();
    }

}
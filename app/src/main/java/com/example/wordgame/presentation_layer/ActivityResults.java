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

/**
 * @Class  ActivityResults displays the  the results of the user after submission
 * Allow user to redo same game by pressing try again button or
 * play next game by pressing next button
 */
public abstract  class ActivityResults {
    /**
     * @serialField  inflater used to inflates the window of the results
     * @serialField  context is the context at which the window will be displayed on
     * @serialField  actionId is used for navigation from the window results to game(s)
     */
    private final LayoutInflater inflater;
    private final Context context;
    private final int actionId;

    /**
     * initialise all serial fields of this class Activity results
     * @param inflater inflater of the window
     * @param Id for navigation to game(s)
     */
    public ActivityResults(LayoutInflater inflater, int Id, View fragment1){
        this.inflater=inflater;
        this.context=inflater.getContext();
        actionId = Id;
    }


    /**
     * This method display user score and correct answers.
     * Next button to play next game
     * try again button to replay same game
     * @param userScore stores the user score for each of the game
     * @param totalScore stores the user average score of each of the levels.
     * @param correctAnswers  list of user scores
     */
    @SuppressLint("SetTextI18n")
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

    /**
     * Helper method to build the string answer to fit the
     * results window
     * @param correctAnswers is the list of correct answers in string type
     * @return appropriate string format that fits in the window
     */
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
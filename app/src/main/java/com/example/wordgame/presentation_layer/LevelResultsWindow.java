package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.example.wordgame.R;

import java.util.List;

public  class LevelResultsWindow {

    private final LayoutInflater inflater;
    private final Context context;

    public LevelResultsWindow(LayoutInflater inflater){
        this.inflater=inflater;
        this.context=inflater.getContext();
    }


    /**
     * This method display user score of each level.
     * Dispaly user level ,score , game type and total score
     * @param correctAnswers  is the user scores for by level
     */
    @SuppressLint("SetTextI18n")
    public void displayScore(List<String> correctAnswers){
        if(correctAnswers==null || correctAnswers.size()==0)
            correctAnswers.add("You still need to play this level");
        final View view = inflater.inflate(R.layout.fragment_level_one_results, null);
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        TextView grades  = view.findViewById(R.id.gradesActivity);
        TextView answers = view.findViewById(R.id.CorrectionsID);
        Button nextButton = view.findViewById(R.id.NextButtonId);
         String answer  = buildAnswers(correctAnswers);
        grades.setText(answer);
        answers.setText("Your Results");
        alertDialog.setView(view);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    /**
     * @param correctAnswers build answer to fit window
     * @return string that fit the window
     */
    String  buildAnswers( List<String> correctAnswers){
        StringBuilder answers = new StringBuilder();
        for (Object key:correctAnswers) {
            answers.append(key).append("\n");
        }
        return answers.toString();
    }

}

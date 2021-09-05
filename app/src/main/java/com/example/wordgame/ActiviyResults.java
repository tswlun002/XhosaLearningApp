package com.example.wordgame;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.zip.Inflater;

public class ActiviyResults {

    private final LayoutInflater inflater;
    private final Context context;

    public  ActiviyResults (LayoutInflater inflater,Context context){
        this.inflater=inflater;
        this.context=context;
    }


    /**
     * This method desplay's the user score, the answers they had wrong with corrections.
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
        Button button = view.findViewById(R.id.ExitBitton);
        grades.setText("1.True\n2.false\n3.true\n4.false");
        answers.setText("Your Score and Answers:".toUpperCase()+userScore+"/"+totalScore);
        alertDialog.setView(view);
        alertDialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();
            }
        });
    }

}

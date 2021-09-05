package com.example.wordgame;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.zip.Inflater;

public class ActiviyResults {

    private LayoutInflater inflater;
    private Context context;

    public  ActiviyResults (LayoutInflater inflater,Context context){
        this.inflater=inflater;
        this.context=context;
    }


    /**
     * Show user marks for current activity on dialog
     * Click close to dismiss dialog
     */



    @SuppressLint("SetTextI18n")
    //introduced new parameters userScore and total Score
    public void gradesActity(int userScore, int totalScore){
        final View view = inflater.inflate(R.layout.fragment_results__current_activity, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Your Score");


        TextView grades  = view.findViewById(R.id.gradesActivity);
        grades.setText(userScore+"/"+totalScore);
        alertDialog.setMessage(userScore+"/"+totalScore);

        alertDialog.setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final  AlertDialog alert = alertDialog.create();
        alert.show();
        alert.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
    }

}

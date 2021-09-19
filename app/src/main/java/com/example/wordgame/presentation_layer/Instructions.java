package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;

public class Instructions {
    private  final Context context;
    private final LayoutInflater inflater;
    Instructions(Context context, LayoutInflater inflater){
        this.context = context;
        this.inflater = inflater;
    }
    @SuppressLint("SetTextI18n")
    void instructions(){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(
                "1. Start from level for a beginner\n" +
                "2. LearnDB all the material provided from the selected level\n" +
                "3. Select PlayFragment to chose games provided\n" +
                "4. Select game from each level\n" +
                "5. OnSubmit to get feedback\n" +
                "6. To see all your grade for each level," +
                "select progress button from the bottom navigation\n");
        alertDialog.setTitle("Instructions");

        alertDialog.setButton(alertDialog.BUTTON_NEGATIVE, "Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        handleButton(alertDialog);
    }

    void handleButton(AlertDialog alertDialog){
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 alertDialog.dismiss();
            }
        });
    }

}

package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;

/**
 * @Class  Instructions displays instructions of the WordGame
 */
public class Instructions {

    /**
     * @serialField  context  is the context at which the instruction window is displayed on
     * @serialField  inflater is the inflater of the instruction window
     */
    private  final Context context;
    private final LayoutInflater inflater;

    /**
     * initialise the  the fields context and inflater
     * @param context is the context at which the window will be displayed on
     * @param inflater is the inflater of the window
     */
    Instructions(Context context, LayoutInflater inflater){
        this.context = context;
        this.inflater = inflater;
    }

    /**
     * Inflate instruction window
     * Instructions for WordGame
     * Close button to close the window
     */
    @SuppressLint("SetTextI18n")
    void instructions(){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(
                "1.Beginners start at level one\n" +
                "2.Select level one button to start level one\n" +
                "3.On main window, select progress button to see your scores\n" +
                "4.On learn window, you may search topic you want\n" +
                "5.Scroll to access all topic covered in the level\n" +
                "6.Select play tab or swipe left" +
                "7.Select game of choice \n" +
                        "8.Use progress button to see your scores\n" +
                        "9.On game selected, press submit to submit your answers\n" +
                        "10.On submit, correction and score  will be displayed\n" +
                        "11.Select try again to redo same game\n " +
                        "12 Or next button to choose another game type\n" +
                        "13 On a game, you may view your scores\n" +
                        "14.On report window ,over all score each level will be displayed\n " +
                        "15.Use menu drawer and select level to view scores of all submissions\n" +
                        "14.After getting 65% of each level , you be able to access next level\n" +""
        );
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

    /**
     * handle Close button of instruction window
     * Window is dismissed after close button clicked
     * @param alertDialog is the dialog window of instruction
     */
    void handleButton(AlertDialog alertDialog){
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 alertDialog.dismiss();
            }
        });
    }

}

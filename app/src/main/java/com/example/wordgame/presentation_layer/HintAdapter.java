package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.wordgame.R;

/**
 * @Class  HintAdapter handle hints for for translation game
 */
public abstract  class HintAdapter {


    /**
     * Inflate window with the appropriate hint for user
     * Window has close button to close window dialog
     * @param inflater of the hint window dialog
     * @param data is the hint data
     */

    @SuppressLint("SetTextI18n")
    public void getHint(LayoutInflater inflater,String data){
        final View view = inflater.inflate(R.layout.hint, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(inflater.getContext());
        alertDialog.setTitle("Hints !!");

        TextView hint  = view.findViewById(R.id.hintDialogID);
        hint.setText(data);
        alertDialog.setMessage(data);

        HandleButton handleButton = new HandleButton();
        alertDialog.setNeutralButton("Close",handleButton);
        final  AlertDialog alert = alertDialog.create();
        alert.show();
        handleButton.handleButton(alert);


    }

    /**
     * @Class  Handles click of close button in the hint dialog window
     * When close button clicked , window is shutting down
     */
    private static  class HandleButton implements  DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
        void handleButton(AlertDialog alert){
            alert.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert.dismiss();
                }
            });
        }
    }

}

package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.wordgame.R;

public abstract  class HintAdapter {

    /**
     * Hint user with answer
     * Click close to dismiss dialog
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

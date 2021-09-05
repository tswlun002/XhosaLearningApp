package com.example.wordgame;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class HintController {
    LayoutInflater inflater;
    Context context;
    public HintController(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
        this.context = context;
    }
    /**
     * Hint user with answer
     * Click close to dismiss dialog
     */

    @SuppressLint("SetTextI18n")
    public void getHint(){
        final View view = inflater.inflate(R.layout.hint, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Hints !!");

        TextView hint  = view.findViewById(R.id.hintDialogID);
        hint.setText("greeting your Sister");
        alertDialog.setMessage("greeting your Sister");
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

package com.example.wordgame;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wordgame.databinding.FragmentMatchingBinding;

public class DragandDrop {

    @SuppressLint("ClickableViewAccessibility")
    void handleDragDrop(View view){

        view.setOnTouchListener(new TouchChoices());
        view.setOnDragListener(new choice());

    }

    private static class  choice implements  View.OnDragListener{

        @SuppressLint({"UseCompatLoadingForDrawables", "UseCompatTextViewDrawableApis", "SetTextI18n"})
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch(event.getAction()) {

                case DragEvent.ACTION_DRAG_ENTERED:
                    int x_cord = (int) event.getX();
                    int y_cord = (int) event.getY();
                    break;

                case DragEvent.ACTION_DROP:
                    TextView dropped = (TextView) event.getLocalState();
                    if(v.getId()==R.id.xhosaEditText1 ||v.getId()==R.id.xhosaEditText2
                            ||v.getId()==R.id.xhosaEditText3 ||v.getId()==R.id.xhosaEditText4 )
                    ((EditText)v).setText(dropped.getText());
                    dropped.setBackgroundColor(Color.CYAN);

                    break;
                default: break;
            }
            return true;
        }

    }

    private static class TouchChoices implements  View.OnTouchListener{


        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN )  {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(data, shadowBuilder, v, 0);

                return true;
            } else {

                return false;
            }

        }
    }

}

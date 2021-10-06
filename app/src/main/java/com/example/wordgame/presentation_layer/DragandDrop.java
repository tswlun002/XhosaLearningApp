package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wordgame.R;

/**
 * @Class Drag and Drop set drag and drop of the answer of matching game
 * Drag and drop are allowed from textview (answers) to EditText
 */
public abstract class DragandDrop {

    /**
     * Initiate drag and drops  matching view
     * Set  setOnTouchListener of the view @Class TouchChoices
     * Set  setOnDragListener of the view  @Class Choice
     * @param view is the view being drag and dropped (matching game views)
     */
    @SuppressLint("ClickableViewAccessibility")
    public void handleDragDrop(View view){

        view.setOnTouchListener(new TouchChoices());
        view.setOnDragListener(new Choice());


    }

    /**
     * @Class Choice allow user to drag answers( text views) and drop them at editText view
     */

    private static class  Choice implements  View.OnDragListener{

        /**
         * Allow dragging and dropping of views
         * Only drop at the EditText and TextEdit cannot be be drag and dropped
         * @param view  view being dragged and dropped
         * @param event of the view
         * @return true if the drag was successful else false
         */
        @SuppressLint({"UseCompatLoadingForDrawables", "UseCompatTextViewDrawableApis", "SetTextI18n"})
        @Override
        public boolean onDrag(View view, DragEvent event) {
            switch(event.getAction()) {

                case DragEvent.ACTION_DRAG_ENTERED:
                    int x_cord = (int) event.getX();
                    int y_cord = (int) event.getY();
                    break;

                case DragEvent.ACTION_DROP:
                    TextView dropped = (TextView) event.getLocalState();
                    if (view != null){
                        if ((view.getId() == R.id.xhosaEditText1 || view.getId() == R.id.xhosaEditText2
                                || view.getId() == R.id.xhosaEditText3 || view.getId() == R.id.xhosaEditText4)
                                &
                                !(dropped.getId() == R.id.xhosaEditText1 || dropped.getId() == R.id.xhosaEditText2
                                        || dropped.getId() == R.id.xhosaEditText3 || dropped.getId() == R.id.xhosaEditText4)

                        ) {
                            ((EditText) view).setText(dropped.getText());
                            ((EditText) view).setBackgroundResource(R.drawable.answerbackground);
                        }
                    }

                    break;
                default: break;
            }
            return true;
        }

    }

    /***
     * @Class   TouchChoices triggered when view is being touched for dragging
     * Store  information about the view dragged
     */
    private static class TouchChoices implements  View.OnTouchListener{

        /**
         *
         * @param view touched view
         * @param event of the touched view
         * @return  true if motion event is down else false
         */
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN )  {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDragAndDrop(data, shadowBuilder, view, 0);

                return true;
            } else {

                return false;
            }

        }
    }

}

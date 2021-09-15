package com.example.wordgame.presentation_layer;
import android.view.View;

/**
 * @Class MatchingViewHandler handle click view( buttons) and
 * Call parent class  Drag andDrop to handle drag and drop events
 */
public class MatchingViewHandler extends DragandDrop implements OnMatchingViewHandler {

    /**
     * handle view clicks events
     * @param view being clicked
     */
    @Override
    public void viewClicked(View view) {
        handleViewDrag(view);
    }
    /**
     * Call drag to in a super class handles drag events
     * @param view being dragged
     */
    void handleViewDrag(View view){
        super.handleDragDrop(view);
    }
}

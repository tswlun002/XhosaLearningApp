package com.example.wordgame.presentation_layer;

import androidx.recyclerview.widget.RecyclerView;

public interface OnScroll {
    /**
     * handle  scroll down button
     * @param pos is the position of the scroll button
     */
    public void scrollDown(int pos, RecyclerView recyclerView);

}

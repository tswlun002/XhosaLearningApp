package com.example.wordgame.presentation_layer;

import java.util.HashMap;

public interface OnExtractResults {
    public HashMap<String, String> getUserAnswers();
    public HashMap<String, String> getGameAnswers();
    public String getGameInformation();

}

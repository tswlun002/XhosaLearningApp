package com.example.wordgame.presentation_layer;

import java.util.HashMap;

public interface OnExtractResults {
    /**
     * @return user answers
     */
    public HashMap<String, String> getUserAnswers();

    /**
     * @return current played game answer
     */
    public HashMap<String, String> getGameAnswers();

    /**
     * @return information the game such gameId , game level and total score
     */
    public String getGameInformation();

}

package com.example.wordgame.data_layer;

import android.content.Context;
import android.os.AsyncTask;
import java.io.IOException;
class PopulateLevelResultsDB extends AsyncTask<Context,Void,Void> {
    private final MatchingDao matchingDao ;
    protected PopulateLevelResultsDB(WordGameDB wordGameDB){
        matchingDao=wordGameDB.matchingDao();

    }


    @Override
    protected Void doInBackground(Context... contexts) {

        return null;
    }

    private void getData(Context context) throws IOException {

    }
}
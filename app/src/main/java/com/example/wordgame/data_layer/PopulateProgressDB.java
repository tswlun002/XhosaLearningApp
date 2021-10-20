package com.example.wordgame.data_layer;

import android.content.Context;
import android.os.AsyncTask;

import com.example.wordgame.model_layer.ProgressReport;

import java.io.IOException;

/**
 * @Class used to populate the progress of the user to the database
 */
class PopulateProgressDB extends AsyncTask<Void,Void,Void> {
    /**
     * @serialField object field to have access to progress class
     */
    private final ProgressReportDao progressReportDao ;

    /**
     * constructor to prove access to the database for progress class
     * @param wordGameDB to prove access to the database table for progress of the user to update and insert from and to
     */
    protected PopulateProgressDB(WordGameDB wordGameDB){
        progressReportDao=wordGameDB.progressReportDao();

    }

    /**
     * this function initialises the progress table for the user in the database
     * @param voids
     * @return the progress grades of the user for every level and the average of all the levels
     */
    @Override
    protected Void doInBackground(Void... voids) {

       progressReportDao.insert(new ProgressReport(0,
               0,0,0,
               0," On start"));
        return null;
    }

}
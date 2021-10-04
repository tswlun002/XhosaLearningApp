package com.example.wordgame.data_layer;

import android.content.Context;
import android.os.AsyncTask;

import com.example.wordgame.model_layer.ProgressReport;

import java.io.IOException;
class PopulateProgressDB extends AsyncTask<Void,Void,Void> {
    private final ProgressReportDao progressReportDao ;
    protected PopulateProgressDB(WordGameDB wordGameDB){
        progressReportDao=wordGameDB.progressReportDao();

    }


    @Override
    protected Void doInBackground(Void... voids) {

       progressReportDao.insert(new ProgressReport(0,
               0,0,0,
               0," On start"));
        return null;
    }

}
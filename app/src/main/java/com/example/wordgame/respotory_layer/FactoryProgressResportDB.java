package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wordgame.data_layer.LevelResultsDao;
import com.example.wordgame.data_layer.WordGameDB;
import com.example.wordgame.model_layer.LevelResults;

import java.util.List;

public class FactoryProgressResportDB {
    private final ProgressReportDao progressReportDao;
    private final LiveData<List<PorgressReport>> results;

    public FactoryProgressResportDB(Application application) {
        WordGameDB progressReportDB = WordGameDB.getInstanceWordGameDb(application);

        progressReportDao = progressReportDB.progressReportDao();
        results = progressReportDao.getAll();

    }
    public LiveData<List<PorgressReport>> getResults() {
        return results;
    }

    public void insert(PorgressReport porgressReport) {
        new FactoryProgressResportDB.insertHandler(levelResultsDao).execute(porgressReport);
    }

    public void update(PorgressReport porgressReport) {
        new FactoryProgressResportDB.UpdateHandler(levelResultsDao).execute(porgressReport);
    }


    private static class insertHandler extends AsyncTask<PorgressReport, Void, Void> {
        private final ProgressReportDao progressReportDao;

        private insertHandler(ProgressReportDao progressReportDao) {
            this.progressReportDao= progressReportDao;
        }

        @Override
        protected Void doInBackground(PorgressReport... porgressReports) {
            levelResultsDao.insert(levelResults[0]);
            return null;
        }
    }
    private static class UpdateHandler extends AsyncTask<PorgressReport, Void, Void> {
        private final ProgressReportDao progressReportDao;

        private UpdateHandler(ProgressReportDao progressReportDao) {
            this.progressReportDao = progressReportDao;
        }

        @Override
        protected Void doInBackground(PorgressReport... porgressReports) {
            progressReportDao.update(porgressReports[0]);
            return null;
        }


    }

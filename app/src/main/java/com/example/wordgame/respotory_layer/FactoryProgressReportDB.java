package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wordgame.data_layer.ProgressReportDao;
import com.example.wordgame.data_layer.WordGameDB;
import com.example.wordgame.model_layer.ProgressReport;

import java.util.List;

public class FactoryProgressReportDB {
    private final ProgressReportDao progressReportDao;
    private final LiveData<List<ProgressReport>> results;

    public FactoryProgressReportDB(Application application) {
        WordGameDB progressReportDB = WordGameDB.getInstanceWordGameDb(application);

        progressReportDao = progressReportDB.progressReportDao();
        results = progressReportDao.getAll();

    }

    public LiveData<List<ProgressReport>> getResults() {
        return results;
    }

    public void insert(ProgressReport porgressReport) {
        new FactoryProgressReportDB.insertHandler(progressReportDao).execute(porgressReport);
    }

    public void update(ProgressReport porgressReport) {
        new FactoryProgressReportDB.UpdateHandler(progressReportDao).execute(porgressReport);
    }


    private static class insertHandler extends AsyncTask<ProgressReport, Void, Void> {
        private final ProgressReportDao progressReportDao;

        private insertHandler(ProgressReportDao progressReportDao) {
            this.progressReportDao = progressReportDao;
        }

        @Override
        protected Void doInBackground(ProgressReport... porgressReports) {
            progressReportDao.insert(porgressReports[0]);
            return null;
        }
    }

    private static class UpdateHandler extends AsyncTask<ProgressReport, Void, Void> {
        private final ProgressReportDao progressReportDao;

        private UpdateHandler(ProgressReportDao progressReportDao) {
            this.progressReportDao = progressReportDao;
        }

        @Override
        protected Void doInBackground(ProgressReport... porgressReports) {
            progressReportDao.update(porgressReports[0]);
            return null;
        }


    }
}

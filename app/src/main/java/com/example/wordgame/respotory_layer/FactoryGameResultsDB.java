package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.wordgame.data_layer.WordGameDB;

import java.util.List;

public class FactoryGameResultsDB {
    private final LevelResultsDao levelResultsDao;
    private final LiveData<List<LevelResults>> results;

    public FactoryGameResultsDB(Application application) {
        WordGameDB levelResultlsDB = WordGameDB.getInstanceWordGameDb(application);

        levelResultsDao = levelResultlsDB.levelResultsDao();
        results = levelResultsDao.getAll();

    }
    public LiveData<List<LevelResults>> getResults() {
        return results;
    }

    public void insert(LevelResults levelResults) {
        new FactoryGameResultsDB.insertHandler(levelResultsDao).execute(levelResults);
    }

    public void update(LevelResults translationGame) {
        new FactoryGameResultsDB.UpdateHandler(levelResultsDao).execute(levelResults);
    }


    private static class insertHandler extends AsyncTask<LevelResults, Void, Void> {
        private final LevelResultsDao levelResultsDao;

        private insertHandler(LevelResultsDao levelResultsDao) {
            this.levelResultsDao= levelResultsDao;
        }

        @Override
        protected Void doInBackground(LevelResults... levelResults) {
            levelResultsDao.insert(levelResults[0]);
            return null;
        }
    }
    private static class UpdateHandler extends AsyncTask<LevelResults, Void, Void> {
        private final LevelResultsDao levelResultsDao;

        private UpdateHandler(LevelResultsDao levelResultsDao) {
            this.levelResultsDao = levelResultsDao;
        }

        @Override
        protected Void doInBackground(LevelResults... levelResults) {
            translationDao.update(levelResults[0]);
            return null;
        }


    }
}

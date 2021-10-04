package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.wordgame.data_layer.LevelResultsDao;
import com.example.wordgame.data_layer.WordGameDB;
import com.example.wordgame.model_layer.LevelResults;

import java.util.List;

public class FactoryLevelResultsDB {
    private final LevelResultsDao levelResultsDao;
    private final LiveData<List<LevelResults>> results;
    private final LiveData<List<LevelResults>> resultsLevelOne;

    public FactoryLevelResultsDB(Application application) {
        WordGameDB levelResultlsDB = WordGameDB.getInstanceWordGameDb(application);

        levelResultsDao = levelResultlsDB.levelResultsDao();
        results = levelResultsDao.getAll();
        resultsLevelOne = levelResultsDao.level1();


    }
    public LiveData<List<LevelResults>> getResults() {
        return results;
    }
    /**
     * get all results and then average them
     *
     * @return  the results you have returned
     */
    public LiveData<List<LevelResults>> getAllGradesLevelOne() {
        return resultsLevelOne;
    }
    public void insert(LevelResults levelResults) {
        new FactoryLevelResultsDB.insertHandler(levelResultsDao).execute(levelResults);
    }

    public void update(LevelResults levelResults) {
        new FactoryLevelResultsDB.UpdateHandler(levelResultsDao).execute(levelResults);
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
            levelResultsDao.update(levelResults[0]);
            return null;
        }


    }
}

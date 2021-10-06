package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.wordgame.data_layer.LevelResultsDao;
import com.example.wordgame.data_layer.WordGameDB;
import com.example.wordgame.model_layer.LevelResults;

import java.util.List;
/**
 * @Class   FactoryLevelResultsDB class for results
 */
public class FactoryLevelResultsDB {

    /**
     * @serialField object levelResultsDao to access database
     * @serialField  results to store Results
     * @serialField resultsLevelOne for level 1 Results
     * @serialField  resultsLevelTwo for level 2 Results
     * @serialField  resultsLevelThree for level 3 Results
     */
    private final LevelResultsDao levelResultsDao;
    private final LiveData<List<LevelResults>> results;
    private final LiveData<List<LevelResults>> resultsLevelOne,resultsLevelTwo,resultsLevelThree;



    /**
     * get level1 results from the database
     * get all level 2 results from the database
     *  get all level 3 results from the database
     * @param application to prove access to the database
     */
    public FactoryLevelResultsDB(Application application) {
        WordGameDB levelResultlsDB = WordGameDB.getInstanceWordGameDb(application);

        levelResultsDao = levelResultlsDB.levelResultsDao();
       results = levelResultsDao.getAll();
        resultsLevelOne = levelResultsDao.level1();
        resultsLevelTwo = levelResultsDao.level2();
        resultsLevelThree = levelResultsDao.level3();


    }
    /**
     * get all Results from the database for every level
     * @return the notes for level 2
     */
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

    /**
     * get all the Results for the database from level 2
     * @return the results for level 2 from the database
     *
     */
    public LiveData<List<LevelResults>> getAllGradesLevelTwo() {
        return resultsLevelTwo;
    }
    /**
     * get all the Results for the database from level 3
     * @return the results for level 3 from the database
     *
     */
    public LiveData<List<LevelResults>> getAllGradesLevelThree() {
        return resultsLevelThree;
    }

    /**
     * inserts the content to the database of the application
     * @param levelResults inserts to all the columns on the database
     */
    public void insert(LevelResults levelResults) {
        new FactoryLevelResultsDB.insertHandler(levelResultsDao).execute(levelResults);
    }

    /**
     * updates all the information from learning
     * @param levelResults updates the information from the user
     */
    public void update(LevelResults levelResults) {
        new FactoryLevelResultsDB.UpdateHandler(levelResultsDao).execute(levelResults);
    }


    private static class insertHandler extends AsyncTask<LevelResults, Void, Void> {
        private final LevelResultsDao levelResultsDao;

        private insertHandler(LevelResultsDao levelResultsDao) {
            this.levelResultsDao= levelResultsDao;
        }
        /**]
         * insert into the database all the learning material
         * @param levelResults insert into the database of the application
         * @return
         */
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
        /**
         * update the information  from the database of the application
         * @param levelResults updates information into the database, from learn table
         * @return
         */
        @Override
        protected Void doInBackground(LevelResults... levelResults) {
            levelResultsDao.update(levelResults[0]);
            return null;
        }


    }
}

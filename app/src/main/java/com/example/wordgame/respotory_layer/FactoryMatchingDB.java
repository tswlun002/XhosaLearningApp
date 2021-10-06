package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wordgame.data_layer.MatchingDao;
import com.example.wordgame.data_layer.WordGameDB;
import com.example.wordgame.model_layer.Matching;

import java.util.List;

public class FactoryMatchingDB {

    /**
     * @serialField object matchingDao to access database
     * @serialField  questions to store Results
     * @serialField questionsLevelOne for level 1 Results
     * @serialField  questionsLevelTwo for level 2 Results
     * @serialField  questionsLevelThree for level 3 Results
     */
    private final MatchingDao matchingDao;
    private final LiveData<List<Matching>>  questions;
    private final LiveData<List<Matching>>  questionsLevelOne;
    private final LiveData<List<Matching>>  questionsLevelTwo;
    private final LiveData<List<Matching>>  questionsLevelThree;
    public FactoryMatchingDB(Application application){
        WordGameDB matchingDB = WordGameDB.getInstanceWordGameDb(application);

        matchingDao = matchingDB.matchingDao();
        questions = matchingDao.getAll();
        questionsLevelOne = matchingDao.loadLevelOne();
        questionsLevelTwo = matchingDao.loadLevelTwo();
        questionsLevelThree = matchingDao.loadLevelThree();


    }

    /**
     * initial
     * @return
     */
    public LiveData<List<Matching>> getQuestions() {
        return questions;
    }

    /**
     * get all the notes for level 1 from the database
     * @return the notes for level 1
     */
    public LiveData<List<Matching>> getQuestionsLevelOne() {
        return questionsLevelOne;
    }
    /**
     * get all the notes for level 2 from the database
     * @return the notes for level 2
     */
    public LiveData<List<Matching>> getQuestionsLevelTwo(){
        return questionsLevelTwo;
    }
    /**
     * get all the notes for level 3 from the database
     * @return the notes for level 3
     */
    public LiveData<List<Matching>> getQuestionsLevelThree() {
        return questionsLevelThree;
    }
    public  void insert(Matching matching){
        new insertHandler(matchingDao).execute(matching);
    }

    public  void update(Matching matching){
        new UpdateHandler(matchingDao).execute(matching);
    }


    private  static class insertHandler extends AsyncTask<Matching,Void,Void>{
        private final MatchingDao matchingDao;

        private  insertHandler(MatchingDao matchingDao){
            this.matchingDao =matchingDao;
        }
        /**]
         * insert into the database all the learning material
         * @param learns insert into the database of the application
         * @return
         */
        @Override
        protected Void doInBackground(Matching... matchings) {
            matchingDao.insert(matchings[0]);
            return null;
        }
    }
    private  static class UpdateHandler extends AsyncTask<Matching,Void,Void>{
        private final MatchingDao matchingDao;

        private  UpdateHandler(MatchingDao matchingDao){
            this.matchingDao=matchingDao;
        }

        @Override
        protected Void doInBackground(Matching... matchings) {
            matchingDao.update(matchings[0]);


            return null;
        }
    }
}

package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wordgame.data_layer.MatchingDao;
import com.example.wordgame.data_layer.WordGameDB;
import com.example.wordgame.model_layer.Matching;

import java.util.List;

public class FactoryMatchingDB {
    private final MatchingDao matchingDao;
    private final LiveData<List<Matching>>  questions;
    public FactoryMatchingDB(Application application){
        WordGameDB matchingDB = WordGameDB.getInstanceWordGameDb(application);

        matchingDao = matchingDB.matchingDao();
        questions = matchingDao.getAll();

    }

    public LiveData<List<Matching>> getQuestions() {
        return questions;
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

package com.example.wordgame.data_layer;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wordgame.model_layer.Learn;

import java.util.List;

public class FactoryDB {
    private final LearnDao learnDao;
    private final LiveData<List<Learn>> allNotes;

    public FactoryDB(Application application){
        LearnDB learnDB = LearnDB.getInstanceLearnDb(application);

        learnDao = learnDB.learnDao();
        allNotes = learnDao.getAll();

    }

    public LiveData<List<Learn>> getAllNotes() {
        return allNotes;
    }

    public  void insert(Learn learn){
          new insertHandler(learnDao).execute(learn);
    }

    public  void update(Learn learn){
           new UpdateHandler(learnDao).execute(learn);
    }

    public LiveData<List<Learn>> getVowels(){
      return learnDao.getVowels();
    }

    public LiveData<List<Learn>> getConsonants(){
        return learnDao.getConsonants();
    }

    public LiveData<List<Learn>> getNumbers(){
           return learnDao.getNumbers();
    }

    public LiveData<List<Learn>> getClicks(){
        return learnDao.getClicks();
    }
    private  static class insertHandler extends AsyncTask<Learn,Void,Void>{
        private final LearnDao learnDao;

        private  insertHandler(LearnDao learnDao){
            this.learnDao=learnDao;
        }

        @Override
        protected Void doInBackground(Learn... learns) {
            learnDao.insert(learns[0]);
            return null;
        }
    }
    private  static class UpdateHandler extends AsyncTask<Learn,Void,Void>{
        private final LearnDao learnDao;

        private  UpdateHandler(LearnDao learnDao){
            this.learnDao=learnDao;
        }

        @Override
        protected Void doInBackground(Learn... learns) {
            learnDao.update(learns[0]);
            return null;
        }
    }
}

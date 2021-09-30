package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wordgame.data_layer.WordGameDB;
import com.example.wordgame.data_layer.LearnDao;
import com.example.wordgame.model_layer.Learn;

import java.util.List;

public class FactoryLearnDB {
    private final LearnDao learnDao;
    private final LiveData<List<Learn>> allNotes;

    public FactoryLearnDB(Application application){
        WordGameDB wordGameDB = WordGameDB.getInstanceWordGameDb(application);

        learnDao = wordGameDB.learnDao();
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

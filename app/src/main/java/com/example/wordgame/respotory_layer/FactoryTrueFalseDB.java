package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wordgame.data_layer.TrueFalseDao;
import com.example.wordgame.data_layer.WordGameDB;
import com.example.wordgame.model_layer.TrueFalseGame;

import java.util.List;

public class FactoryTrueFalseDB {
    private final TrueFalseDao trueFalseDao;
    private final LiveData<List<TrueFalseGame>> questions;
    public FactoryTrueFalseDB(Application application){
        WordGameDB trueFalseDB = WordGameDB.getInstanceWordGameDb(application);

        trueFalseDao = trueFalseDB.trueFalseDao();
        questions = trueFalseDao.getAll();

    }

    public LiveData<List<TrueFalseGame>> getQuestions() {
        return questions;
    }

    public  void insert(TrueFalseGame trueFalse){
        new FactoryTrueFalseDB.insertHandler(trueFalseDao).execute(trueFalse);
    }

    public  void update(TrueFalseGame trueFalse){
        new FactoryTrueFalseDB.UpdateHandler(trueFalseDao).execute(trueFalse);
    }


    private  static class insertHandler extends AsyncTask<TrueFalseGame,Void,Void> {
        private final TrueFalseDao trueFalseDao;

        private  insertHandler(TrueFalseDao trueFalseDao){
            this.trueFalseDao =trueFalseDao;
        }

        @Override
        protected Void doInBackground(TrueFalseGame... trueFalse) {
            trueFalseDao.insert(trueFalse[0]);
            return null;
        }


    }
    private  static class UpdateHandler extends AsyncTask<TrueFalseGame,Void,Void>{
        private final TrueFalseDao trueFalseDao;

        private  UpdateHandler(TrueFalseDao trueFalseDao){
            this.trueFalseDao=trueFalseDao;
        }

        @Override
        protected Void doInBackground(TrueFalseGame... trueFalse) {
            trueFalseDao.update(trueFalse[0]);


            return null;
        }


    }
}

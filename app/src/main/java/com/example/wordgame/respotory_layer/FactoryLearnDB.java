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
    private final LiveData<List<Learn>> allNotesLevel1, allNotesLevel2,allNotesLevel3;
   // private final LiveData<List<Learn>> allNotesLeve2;



    public FactoryLearnDB(Application application){
        WordGameDB wordGameDB = WordGameDB.getInstanceWordGameDb(application);

        learnDao = wordGameDB.learnDao();
        allNotes = learnDao.getAll();
        allNotesLevel1 = learnDao.level1();
        allNotesLevel2 = learnDao.level2();
        allNotesLevel3 = learnDao.level3();

    }

    public LiveData<List<Learn>> getAllNotes() {
        return allNotes;
    }
    public LiveData<List<Learn>> getAllNotesLevel1() {
        return allNotesLevel1;
    }
    public LiveData<List<Learn>> getAllNotesLevel2() {
        return allNotesLevel2;
    }
    public LiveData<List<Learn>> getAllNotesLevel3() {
        return allNotesLevel3;
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

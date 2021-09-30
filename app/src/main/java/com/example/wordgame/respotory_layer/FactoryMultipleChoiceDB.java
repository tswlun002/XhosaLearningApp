package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.wordgame.data_layer.MultipleChoiceDao;
import com.example.wordgame.data_layer.WordGameDB;
import com.example.wordgame.model_layer.MultipleChoice;
import java.util.List;


public class FactoryMultipleChoiceDB {
    private final MultipleChoiceDao multipleChoiceDao;
    private final LiveData<List<MultipleChoice>> questions;
    public FactoryMultipleChoiceDB(Application application){
        WordGameDB MQCDB = WordGameDB.getInstanceWordGameDb(application);

        multipleChoiceDao = MQCDB.multipleChoiceDao();
        questions = multipleChoiceDao.getAll();

    }

    public LiveData<List<MultipleChoice>> getQuestions() {
        return questions;
    }

    public  void insert(MultipleChoice multipleChoice){
        new insertHandler(multipleChoiceDao).execute(multipleChoice);
    }

    public  void update(MultipleChoice multipleChoice){
        new UpdateHandler(multipleChoiceDao).execute(multipleChoice);
    }


    private  static class insertHandler extends AsyncTask<MultipleChoice,Void,Void> {
        private final MultipleChoiceDao multipleChoiceDao;

        private  insertHandler(MultipleChoiceDao multipleChoiceDao){
            this.multipleChoiceDao =multipleChoiceDao;
        }

        @Override
        protected Void doInBackground(MultipleChoice... multipleChoices) {
            multipleChoiceDao.insert(multipleChoices[0]);
            return null;
        }


    }
    private  static class UpdateHandler extends AsyncTask<MultipleChoice,Void,Void>{
        private final MultipleChoiceDao multipleChoiceDao;

        private  UpdateHandler(MultipleChoiceDao multipleChoice){
            this.multipleChoiceDao=multipleChoice;
        }

        @Override
        protected Void doInBackground(MultipleChoice... multipleChoices) {
            multipleChoiceDao.update(multipleChoices[0]);


            return null;
        }


    }
}

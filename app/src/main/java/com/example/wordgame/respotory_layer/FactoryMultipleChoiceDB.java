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
    private final LiveData<List<MultipleChoice>> questionsLevelOne,questionsLevelTwo
            ,questionsLevelThree;
    public FactoryMultipleChoiceDB(Application application){
        WordGameDB MQCDB = WordGameDB.getInstanceWordGameDb(application);

        multipleChoiceDao = MQCDB.multipleChoiceDao();
        questionsLevelOne = multipleChoiceDao.loadLevelOne();
        questionsLevelThree= multipleChoiceDao.loadLevelThree();
        questionsLevelTwo= multipleChoiceDao.loadLevelTwo();

    }

    public LiveData<List<MultipleChoice>> getQuestionsLevelOne() {
        return questionsLevelOne;
    }
    public LiveData<List<MultipleChoice>> getQuestionsLevelTwo() {
        return questionsLevelTwo;
    }
    public LiveData<List<MultipleChoice>> getQuestionsLevelThree() {
        return questionsLevelThree;
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

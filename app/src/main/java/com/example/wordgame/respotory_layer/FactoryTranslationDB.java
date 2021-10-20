package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wordgame.data_layer.TranslationDao;
import com.example.wordgame.data_layer.WordGameDB;
import com.example.wordgame.model_layer.TranslationGame;
import com.example.wordgame.presentation_layer.TranslationAdapter;

import java.util.List;

public class FactoryTranslationDB {
    private final TranslationDao translationDao;
    private final LiveData<List<TranslationGame>> questions,questionsLevelOne
            ,questionsLevelTwo,questionsLevelThree;

    public FactoryTranslationDB(Application application) {
        WordGameDB translationDB = WordGameDB.getInstanceWordGameDb(application);

        translationDao = translationDB.translationDao();
        questions = translationDao.getAll();
        questionsLevelOne = translationDao.level1();
        questionsLevelTwo = translationDao.level2();
        questionsLevelThree=translationDao.level3();

    }
    public LiveData<List<TranslationGame>> getQuestions() {
        return questions;
    }
    public LiveData<List<TranslationGame>> getQuestionsLevelOne() {
        return questionsLevelOne;
    }
    public LiveData<List<TranslationGame>> getQuestionsLevelTwo() {
        return questionsLevelTwo;
    }
    public LiveData<List<TranslationGame>> getQuestionsLevelThree() {
        return questionsLevelThree;
    }



    public void insert(TranslationGame translationGame) {
        new FactoryTranslationDB.insertHandler(translationDao).execute(translationGame);
    }

    public void update(TranslationGame translationGame) {
        new FactoryTranslationDB.UpdateHandler(translationDao).execute(translationGame);
    }


    private static class insertHandler extends AsyncTask<TranslationGame, Void, Void> {
        private final TranslationDao translationDao;

        private insertHandler(TranslationDao translationDao) {
            this.translationDao= translationDao;
        }

        @Override
        protected Void doInBackground(TranslationGame... translationGame) {
            translationDao.insert(translationGame[0]);
            return null;
        }


    }

    private static class UpdateHandler extends AsyncTask<TranslationGame, Void, Void> {
        private final TranslationDao translationDao;

        private UpdateHandler(TranslationDao translationDao) {
            this.translationDao = translationDao;
        }

        @Override
        protected Void doInBackground(TranslationGame... translationGames) {
            translationDao.update(translationGames[0]);
            return null;
        }


    }
}

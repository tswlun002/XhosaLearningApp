package com.example.wordgame.model_layer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wordgame.presentation_layer.TranslationAdapter;
import com.example.wordgame.respotory_layer.FactoryMatchingDB;
import com.example.wordgame.respotory_layer.FactoryTranslationDB;

import java.util.List;

public class TranslationViewModel extends AndroidViewModel {
    private final FactoryTranslationDB factoryTranslationDB;
    private final LiveData<List<TranslationGame>> allMaterial,questionsLevelOne
            ,questionsLevelTwo,questionsLevelThree;

    /**
     * construct to initialise serial fields
     *
     * @param application is the context of matching3.txt
     */
    public TranslationViewModel(@NonNull Application application) {
        super(application);
        factoryTranslationDB = new FactoryTranslationDB(application);
        allMaterial = factoryTranslationDB.getQuestions();
        questionsLevelOne= factoryTranslationDB.getQuestionsLevelOne();
        questionsLevelTwo= factoryTranslationDB.getQuestionsLevelTwo();
        questionsLevelThree= factoryTranslationDB.getQuestionsLevelThree();
    }

    /**
     * get all material for matching3.txt game
     *
     * @return matching3.txt material
     */
    public LiveData<List<TranslationGame>> getGameMaterial() {
        return allMaterial;
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
    /**
     * insert data of matching3.txt
     *
     * @param translation is a data being inserted
     */
    public void insert(TranslationGame translation) {
        factoryTranslationDB.insert(translation);
    }

    /**
     * update data of matching3.txt game
     *
     * @param translation is the data being updated
     */
    public void update(TranslationGame translation) {
        factoryTranslationDB.update(translation);
    }
}

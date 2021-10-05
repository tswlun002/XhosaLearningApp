package com.example.wordgame.model_layer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wordgame.respotory_layer.FactoryMatchingDB;


import java.util.List;

public class MatchingViewModel extends AndroidViewModel {
    private final FactoryMatchingDB factoryMatchingDB;
    private final LiveData<List<Matching>> allMaterial,questionsLevelOne,
            questionsLevelTwo,questionsLevelThree;

    /**
     * construct to initialise serial fields
     * @param application is the context of matching
     */
    public MatchingViewModel(@NonNull Application application) {
        super(application);
       factoryMatchingDB = new FactoryMatchingDB(application);
        allMaterial = factoryMatchingDB.getQuestions();
        questionsLevelOne = factoryMatchingDB.getQuestionsLevelOne();
        questionsLevelTwo = factoryMatchingDB.getQuestionsLevelTwo();
        questionsLevelThree = factoryMatchingDB.getQuestionsLevelThree();
    }

    /**
     * get all material for matching game
     * @return matching material
     */
    public LiveData<List<Matching>> getGameMaterial() {
        return allMaterial;
    }
    public LiveData<List<Matching>> getQuestionsLevelOne() {
        return questionsLevelOne;
    }

    public LiveData<List<Matching>> getQuestionsLevelTwo(){
        return questionsLevelTwo;
    }
    public LiveData<List<Matching>> getQuestionsLevelThree() {
        return questionsLevelThree;
    }
    /**
     * insert data of matching
     * @param match is a data being inserted
     */
    public  void insert(Matching match){
        factoryMatchingDB.insert(match);
    }

    /**
     * update data of matching game
     * @param match is the data being updated
     */
    public  void update(Matching match){
        factoryMatchingDB.update(match);
    }
}

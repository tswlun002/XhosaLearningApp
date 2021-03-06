package com.example.wordgame.model_layer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wordgame.data_layer.PopulateTrueFalseDB;
import com.example.wordgame.data_layer.TrueFalseDao;
import com.example.wordgame.respotory_layer.FactoryMatchingDB;
import com.example.wordgame.respotory_layer.FactoryTrueFalseDB;

import java.util.List;

public class TrueFalseViewModel extends AndroidViewModel {
    private final FactoryTrueFalseDB factoryTrueFalseDB;
    private final LiveData<List<TrueFalseGame>> allMaterial,
            questionsLevelOne,questionsLevelTwo,questionsLevelThree;
    public TrueFalseViewModel(@NonNull Application application) {
        super(application);
        factoryTrueFalseDB = new FactoryTrueFalseDB(application);
        allMaterial = factoryTrueFalseDB.getQuestions();
        questionsLevelOne= factoryTrueFalseDB.getQuestionsLevelOne();
        questionsLevelTwo= factoryTrueFalseDB.getQuestionsLevelTwo();
        questionsLevelThree= factoryTrueFalseDB.getQuestionsLevelThree();
    }
    /**
     * get all material for matching3.txt game
     * @return matching3.txt material
     */
    public LiveData<List<TrueFalseGame>> getGameMaterial() {
        return allMaterial;
    }
    public LiveData<List<TrueFalseGame>> getQuestionsLevelOne() {
        return questionsLevelOne;
    }
    public LiveData<List<TrueFalseGame>> getQuestionsLevelTwo() {
        return questionsLevelTwo;
    }
    public LiveData<List<TrueFalseGame>> getQuestionsLevelThree() {
        return questionsLevelThree;
    }
    /**
     * insert data of matching3.txt
     * @param trueFalseGame is a data being inserted
     */
    public  void insert(TrueFalseGame trueFalseGame){
        factoryTrueFalseDB.insert(trueFalseGame);
    }

    /**
     * update data of matching3.txt game
     * @param trueFalseGame is the data being updated
     */
    public  void update(TrueFalseGame trueFalseGame){
        factoryTrueFalseDB.update(trueFalseGame);
    }
}

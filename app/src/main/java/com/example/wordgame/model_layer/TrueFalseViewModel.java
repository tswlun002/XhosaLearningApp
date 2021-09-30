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
    private final LiveData<List<TrueFalseGame>> allMaterial;
    public TrueFalseViewModel(@NonNull Application application) {
        super(application);
        factoryTrueFalseDB = new FactoryTrueFalseDB(application);
        allMaterial = factoryTrueFalseDB.getQuestions();
    }
    /**
     * get all material for matching game
     * @return matching material
     */
    public LiveData<List<TrueFalseGame>> getGameMaterial() {
        return allMaterial;
    }

    /**
     * insert data of matching
     * @param trueFalseGame is a data being inserted
     */
    public  void insert(TrueFalseGame trueFalseGame){
        factoryTrueFalseDB.insert(trueFalseGame);
    }

    /**
     * update data of matching game
     * @param trueFalseGame is the data being updated
     */
    public  void update(TrueFalseGame trueFalseGame){
        factoryTrueFalseDB.update(trueFalseGame);
    }
}

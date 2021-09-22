package com.example.wordgame.model_layer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wordgame.respotory_layer.FactoryMatchingDB;


import java.util.List;

public class MatchingViewModel extends AndroidViewModel {
    private  FactoryMatchingDB factoryMatchingDB;
    private  LiveData<List<Matching>> allMaterial;

    public MatchingViewModel(@NonNull Application application) {
        super(application);
       factoryMatchingDB = new FactoryMatchingDB(application);
        allMaterial = factoryMatchingDB.getQuestions();
    }
    public LiveData<List<Matching>> getGameMaterial() {
        return allMaterial;
    }
    public  void insert(Matching match){
        factoryMatchingDB.insert(match);
    }
    public  void update(Matching match){
        factoryMatchingDB.update(match);
    }
}

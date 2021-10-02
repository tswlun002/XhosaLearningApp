package com.example.wordgame.model_layer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wordgame.respotory_layer.FactoryLearnDB;

import java.util.List;

public class LearnViewModel extends AndroidViewModel {
    private final FactoryLearnDB factoryDB;
    private final LiveData<List<Learn>> allMaterial;
    private final LiveData<List<Learn>> allNotesLevel1;

    public LearnViewModel(@NonNull Application application) {
        super(application);
        factoryDB = new FactoryLearnDB(application);
        allMaterial = factoryDB.getAllNotes();
        allNotesLevel1= factoryDB.getAllNotesLevel1();
    }
    public LiveData<List<Learn>> getAllMaterial() {
        return allMaterial;

    }
    public LiveData<List<Learn>> getAllNotesLevel1() {
        return allNotesLevel1;
    }
    public  void insert(Learn learn){
        factoryDB.insert(learn);
    }
    public  void update(Learn learn){
        factoryDB.update(learn);
    }




}

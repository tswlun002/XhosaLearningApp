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
    private final LiveData<List<Learn>> allNotesLevel1,allNotesLevel2,allNotesLevel3;

    public LearnViewModel(@NonNull Application application) {
        super(application);
        factoryDB = new FactoryLearnDB(application);
        allMaterial = factoryDB.getAllNotes();
        allNotesLevel1= factoryDB.getAllNotesLevel1();
        allNotesLevel2= factoryDB.getAllNotesLevel2();
        allNotesLevel3= factoryDB.getAllNotesLevel3();
    }
    public LiveData<List<Learn>> getAllMaterial() {
        return allMaterial;

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
        factoryDB.insert(learn);
    }
    public  void update(Learn learn){
        factoryDB.update(learn);
    }




}

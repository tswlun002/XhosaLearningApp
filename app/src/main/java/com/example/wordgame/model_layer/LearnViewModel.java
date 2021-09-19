package com.example.wordgame.model_layer;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.example.wordgame.data_layer.FactoryDB;
import com.example.wordgame.model_layer.Learn;

import java.util.List;

public class LearnViewModel extends AndroidViewModel {
    private final FactoryDB factoryDB;
    private final LiveData<List<Learn>> allMaterial;
    private  LiveData<List<Learn>> selectedMaterial;
    public LearnViewModel(@NonNull Application application) {
        super(application);
        factoryDB = new FactoryDB(application);
        allMaterial = factoryDB.getAllNotes();
    }
    public LiveData<List<Learn>> getAllMaterial() {
        return allMaterial;

    }
    public  void insert(Learn learn){
        factoryDB.insert(learn);
    }
    public  void update(Learn learn){
        factoryDB.update(learn);
    }
    public LiveData<List<Learn>> getVowels(){
        return factoryDB.getVowels();
    }

    public LiveData<List<Learn>> getConsonants(){
        return factoryDB.getConsonants();
    }

    public LiveData<List<Learn>> getNumbers(){
        return factoryDB.getNumbers();
    }

    public LiveData<List<Learn>> getClicks(){
        return factoryDB.getClicks();
    }


}

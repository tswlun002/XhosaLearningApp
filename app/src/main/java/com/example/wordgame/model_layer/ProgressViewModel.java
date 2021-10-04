package com.example.wordgame.model_layer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wordgame.respotory_layer.FactoryLevelResultsDB;
import java.util.List;

public class ProgressViewModel extends AndroidViewModel {
    private final FactoryLevelResultsDB factoryLevelResultsDB;
    private final LiveData<List<PorgressReport>> allMaterial;

    /**
     * construct to initialise serial fields
     *
     * @param application is the context of prgressResults
     */
    public ProgressViewModel(@NonNull Application application) {
        super(application);
        factoryTranslationDB = new FactoryLevelResultsDB(application);
        allMaterial = factoryTranslationDB.getQuestions();
    }

    /**
     * get all material for matching game
     *
     * @return matching material
     */
    public LiveData<List<PorgressReport>> getGameMaterial() {
        return allMaterial;
    }

    /**
     * insert data of matching
     *
     * @param porgressReport is a data being inserted
     */
    public void insert(PorgressReport porgressReport) {
        FactoryLevelResultsDB.insert(porgressReport);
    }

    /**
     * update data of prgressResults
     *
     * @param porgressReport is the data being updated
     */
    public void update(PorgressReport porgressReport) {
        FactoryLevelResultsDB.update(porgressReport);
    }
}

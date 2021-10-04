package com.example.wordgame.model_layer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wordgame.respotory_layer.FactoryLevelResultsDB;
import com.example.wordgame.respotory_layer.FactoryProgressReportDB;

import java.util.List;

public class ProgressViewModel extends AndroidViewModel {
    private final FactoryProgressReportDB factoryProgressReportDB;
    private final LiveData<List<ProgressReport>> allMaterial;

    /**
     * construct to initialise serial fields
     *
     * @param application is the context of prgressResults
     */
    public ProgressViewModel(@NonNull Application application) {
        super(application);
        factoryProgressReportDB = new FactoryProgressReportDB(application);
        allMaterial = factoryProgressReportDB.getResults();
    }

    /**
     * get all material for matching game
     *
     * @return matching material
     */
    public LiveData<List<ProgressReport>> getGameMaterial() {
        return allMaterial;
    }

    /**
     * insert data of matching
     *
     * @param porgressReport is a data being inserted
     */
    public void insert(ProgressReport porgressReport) {
        factoryProgressReportDB.insert(porgressReport);
    }

    /**
     * update data of prgressResults
     *
     * @param porgressReport is the data being updated
     */
    public void update(ProgressReport porgressReport) {
        factoryProgressReportDB.update(porgressReport);
    }
}

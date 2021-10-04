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
     * @param application is the context of progressResults
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
     * @param progressReport is a data being inserted
     */
    public void insert(ProgressReport progressReport) {
        factoryProgressReportDB.insert(progressReport);
    }

    /**
     * update data of prgressResults
     *
     * @param progressReport is the data being updated
     */
    public void update(ProgressReport progressReport) {
        factoryProgressReportDB.update(progressReport);
    }
}

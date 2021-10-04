package com.example.wordgame.model_layer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wordgame.respotory_layer.FactoryLevelResultsDB;
import com.example.wordgame.respotory_layer.FactoryTranslationDB;

import java.util.List;

public class LevelResultsViewModel extends AndroidViewModel {
        private final FactoryLevelResultsDB factoryGameResultsDB;
        private final LiveData<List<LevelResults>> allGrades;
    //private final LiveData<List<LevelResults>> allGradesLevelone;

        /**
         * construct to initialise serial fields
         *
         * @param application is the context of LevelResultsFragment
         */
        public LevelResultsViewModel(@NonNull Application application) {
            super(application);
            factoryGameResultsDB = new FactoryLevelResultsDB(application);
            allGrades = factoryGameResultsDB.getResults();
        }

        /**
         * get all results and then average them
         *
         * @return return the results you have returned
         */
        public LiveData<List<LevelResults>> getAllGradesLevelone() {
            return factoryGameResultsDB.getAllGradesLevelOne();
        }

    /**
     * get all results and then average them
     *
     * @return return the results you have returned
     */
    public LiveData<List<LevelResults>> getAverage() {
        return allGrades;
    }

    /**
         * insert the results fo each game into the database
         *
         * @param levelResults grade of the level inserted
         */
        public void insert(LevelResults levelResults) {
            factoryGameResultsDB.insert(levelResults);
        }

        /**
         * update data of the grades
         *
         * @param levelResults the graded being inserted
         */
        public void update(LevelResults levelResults) {
            factoryGameResultsDB.update(levelResults);
        }

}

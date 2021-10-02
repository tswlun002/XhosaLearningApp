package com.example.wordgame.model_layer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wordgame.respotory_layer.FactoryGameResultsDB;
import com.example.wordgame.respotory_layer.FactoryTranslationDB;

import java.util.List;

public class GameResultsViewModel extends AndroidViewModel {
        private final FactoryGameResultsDB factoryGameResultsDB;
        private final LiveData<List<LevelResults>> allGrades;

        /**
         * construct to initialise serial fields
         *
         * @param application is the context of LevelResults
         */
        public TranslationViewModel(@NonNull Application application) {
            super(application);
            factoryGameResultsDB = new FactoryTranslationDB(application);
            allGrades = factoryGameResultsDB.getResults();
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
         * @param the grade of the level inserted
         */
        public void insert(LevelResults levelResults) {
            factoryGameResultsDB.insert(levelResults);
        }

        /**
         * update data of the grades
         *
         * @param updating the graded being inserted
         */
        public void update(LevelResults levelResults) {
            factoryGameResultsDB.update(levelResults);
        }
    }
}

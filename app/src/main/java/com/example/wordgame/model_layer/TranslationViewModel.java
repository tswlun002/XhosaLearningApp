package com.example.wordgame.model_layer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wordgame.presentation_layer.TranslationAdapter;
import com.example.wordgame.respotory_layer.FactoryMatchingDB;
import com.example.wordgame.respotory_layer.FactoryTranslationDB;

import java.util.List;

public class TranslationViewModel extends AndroidViewModel {
    private final FactoryTranslationDB factoryTranslationDB;
    private final LiveData<List<TranslationGame>> allMaterial;

    /**
     * construct to initialise serial fields
     *
     * @param application is the context of matching
     */
    public TranslationViewModel(@NonNull Application application) {
        super(application);
        factoryTranslationDB = new FactoryTranslationDB(application);
        allMaterial = factoryTranslationDB.getQuestions();
    }

    /**
     * get all material for matching game
     *
     * @return matching material
     */
    public LiveData<List<TranslationGame>> getGameMaterial() {
        return allMaterial;
    }

    /**
     * insert data of matching
     *
     * @param translation is a data being inserted
     */
    public void insert(TranslationGame translation) {
        factoryTranslationDB.insert(translation);
    }

    /**
     * update data of matching game
     *
     * @param translation is the data being updated
     */
    public void update(TranslationGame translation) {
        factoryTranslationDB.update(translation);
    }
}

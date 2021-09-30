package com.example.wordgame.model_layer;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.wordgame.respotory_layer.FactoryMultipleChoiceDB;

import java.util.List;
public class MultipleChoiceViewModel extends AndroidViewModel {
    private final FactoryMultipleChoiceDB factoryMultipleChoiceDB;
    private final LiveData<List<MultipleChoice>> allMaterial;
    public MultipleChoiceViewModel(@NonNull Application application) {
        super(application);
        factoryMultipleChoiceDB = new FactoryMultipleChoiceDB(application);
        allMaterial = factoryMultipleChoiceDB.getQuestions();
    }
    /**
     * get all material for matching game
     * @return matching material
     */
    public LiveData<List<MultipleChoice>> getGameMaterial() {
        return allMaterial;
    }

    /**
     * insert data of matching
     * @param multipleChoice is a data being inserted
     */
    public  void insert(MultipleChoice multipleChoice){
        factoryMultipleChoiceDB.insert(multipleChoice);
    }

    /**
     * update data of matching game
     * @param multipleChoice is the data being updated
     */
    public  void update(MultipleChoice multipleChoice){
        factoryMultipleChoiceDB.update(multipleChoice);
    }
}


package com.example.wordgame.model_layer;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.wordgame.respotory_layer.FactoryMultipleChoiceDB;

import java.util.List;
public class MultipleChoiceViewModel extends AndroidViewModel {
    private final FactoryMultipleChoiceDB factoryMultipleChoiceDB;
    private final LiveData<List<MultipleChoice>> questionsLevelOne,questionsLevelTwo,
    questionsLevelThree;
    public MultipleChoiceViewModel(@NonNull Application application) {
        super(application);
        factoryMultipleChoiceDB = new FactoryMultipleChoiceDB(application);
        questionsLevelOne = factoryMultipleChoiceDB.getQuestionsLevelOne();
        questionsLevelTwo= factoryMultipleChoiceDB.getQuestionsLevelTwo();
        questionsLevelThree= factoryMultipleChoiceDB.getQuestionsLevelThree();

    }
    public LiveData<List<MultipleChoice>> getQuestionsLevelOne() {
        return questionsLevelOne;
    }
    public LiveData<List<MultipleChoice>> getQuestionsLevelTwo() {
        return questionsLevelTwo;
    }
    public LiveData<List<MultipleChoice>> getQuestionsLevelThree() {
        return questionsLevelThree;
    }

        /**
         * insert data of matching3.txt
         * @param multipleChoice is a data being inserted
         */
    public  void insert(MultipleChoice multipleChoice){
        factoryMultipleChoiceDB.insert(multipleChoice);
    }

    /**
     * update data of matching3.txt game
     * @param multipleChoice is the data being updated
     */
    public  void update(MultipleChoice multipleChoice){
        factoryMultipleChoiceDB.update(multipleChoice);
    }
}


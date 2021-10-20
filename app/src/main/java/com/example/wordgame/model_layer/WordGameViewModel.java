package com.example.wordgame.model_layer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class WordGameViewModel extends ViewModel {
    private final MutableLiveData<LevelResults> state;
    private final MutableLiveData<List<LevelResults>> state1;
    private final MutableLiveData<Integer> userLevel;

    public WordGameViewModel() {
        state=new MutableLiveData<>();
        userLevel = new MutableLiveData<>();
        state1 = new MutableLiveData<>();

    }

    public void setResults(LevelResults stringValue) {
        state.setValue(stringValue);
    }

    public LiveData<LevelResults> getResults() {
        return state;
    }

    public void setResults(List<LevelResults> stringValue) {
        state1.setValue(stringValue);
    }

    public LiveData<List<LevelResults>> getAllResults() {
        return state1;
    }


    public void setUserLevel(int value) {
        userLevel.setValue(value);
    }

    public LiveData<Integer> getUserLevel() {
        return userLevel;
    }
}

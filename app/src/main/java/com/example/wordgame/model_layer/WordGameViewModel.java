package com.example.wordgame.model_layer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class WordGameViewModel extends ViewModel {
    private final MutableLiveData<LevelResults> state;

    public WordGameViewModel() {
        state=new MutableLiveData<>();

    }

    public void setValue(LevelResults stringValue) {
        state.setValue(stringValue);
    }

    public LiveData<LevelResults> getState() {
        return state;
    }
}

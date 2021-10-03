package com.example.wordgame.model_layer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class WordGameViewModel extends ViewModel {
    private final MutableLiveData<List<String>> state;

    public WordGameViewModel() {
        state=new MutableLiveData<>();

    }

    public void setValue(List<String> stringValue) {
        state.setValue(stringValue);
    }

    public LiveData<List<String>> getState() {
        return state;
    }
}

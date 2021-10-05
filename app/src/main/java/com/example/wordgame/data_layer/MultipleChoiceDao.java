package com.example.wordgame.data_layer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wordgame.model_layer.MultipleChoice;
import com.example.wordgame.model_layer.TrueFalseGame;

import java.util.List;
@Dao
public interface MultipleChoiceDao {
    @Query("SELECT * FROM MultipleChoice")
    LiveData<List<MultipleChoice>> getAll();
    @Query("SELECT * FROM MultipleChoice WHERE level =1")
    LiveData<List<MultipleChoice>> loadLevelOne();
    @Query("SELECT * FROM MultipleChoice WHERE level =2")
    LiveData<List<MultipleChoice>> loadLevelTwo();
    @Query("SELECT * FROM MultipleChoice WHERE level =3")
    LiveData<List<MultipleChoice>> loadLevelThree();
    @Update
    void update(MultipleChoice activity);
    @Insert
    void insert(MultipleChoice activity);
}

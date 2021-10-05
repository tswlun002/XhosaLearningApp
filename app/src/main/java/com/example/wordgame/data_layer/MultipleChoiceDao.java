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
    @Query("SELECT * FROM Matching WHERE level =1")
    LiveData<List<MultipleChoice>> loadLevelOne(int[] userIds);
    @Query("SELECT * FROM Matching WHERE level =2")
    LiveData<List<MultipleChoice>> loadLevelTwo(int[] userIds);
    @Query("SELECT * FROM Matching WHERE level =3")
    LiveData<List<MultipleChoice>> loadLevelThree(int[] userIds);
    @Update
    void update(MultipleChoice activity);
    @Insert
    void insert(MultipleChoice activity);
}

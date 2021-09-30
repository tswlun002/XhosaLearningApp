package com.example.wordgame.data_layer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wordgame.model_layer.Matching;
import com.example.wordgame.model_layer.TrueFalseGame;

import java.util.List;
@Dao
public interface TrueFalseDao {
    @Query("SELECT * FROM TrueOfFalse")
    LiveData<List<TrueFalseGame>> getAll();
    /*@Query("SELECT * FROM Matching WHERE level =1")
    LiveData<List<Matching>> loadLevelOne(int[] userIds);
    @Query("SELECT * FROM Matching WHERE level =2")
    LiveData<List<Matching>> loadLevelTwo(int[] userIds);
    @Query("SELECT * FROM Matching WHERE level =3")
    LiveData<List<Matching>> loadLevelThree(int[] userIds);*/
    @Update
    void update(TrueFalseGame activity);
    @Insert
    void insert(TrueFalseGame activity);
}

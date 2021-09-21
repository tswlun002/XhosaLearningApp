package com.example.wordgame.data_layer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wordgame.model_layer.Learn;
import com.example.wordgame.model_layer.Matching;

import java.util.List;
@Dao
public interface MatchingDao {

    @Query("SELECT * FROM MATCHING")
    LiveData<List<Matching>> getAll();
    @Query("SELECT * FROM Matching WHERE level =1")
    LiveData<List<Matching>> loadLevelOne(int[] userIds);
    @Query("SELECT * FROM Matching WHERE level =2")
    LiveData<List<Matching>> loadLevelTwo(int[] userIds);
    @Query("SELECT * FROM Matching WHERE level =3")
    LiveData<List<Matching>> loadLevelThree(int[] userIds);
    @Update
    void update(Matching activity);
    @Insert
    void insert(Matching activity);
}

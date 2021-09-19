package com.example.wordgame.data_layer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wordgame.model_layer.Learn;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface LearnDao {

        @Query("SELECT * FROM Learn")
        LiveData<List<Learn>> getAll();
        @Query("SELECT * FROM LEARN WHERE section= 'Vowels' AND level ==1")
        LiveData<List<Learn>> getVowels();
        @Query("SELECT * FROM LEARN WHERE section= 'Consonants Or Amaqabane' AND level ==1")
        LiveData<List<Learn>> getConsonants();
        @Query("SELECT * FROM LEARN WHERE section= 'Numbers Or Izibalo' AND level ==1")
        LiveData<List<Learn>> getNumbers();
        @Query("SELECT * FROM LEARN WHERE section= 'Clicks Or Izandi' AND level ==1")
        LiveData<List<Learn>> getClicks();
        @Query("SELECT * FROM Learn WHERE learnId IN (:userIds)")
        List<Learn> loadAllByIds(int[] userIds);

        @Query("SELECT * FROM Learn WHERE level LIKE :first AND " +
                "section LIKE :last LIMIT 1")
        Learn findByName(String first, String last);
         @Update
         void update(Learn LearningMaterial );
        @Insert
        void insert(Learn learningMaterial);



}

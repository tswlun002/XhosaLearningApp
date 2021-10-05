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
        @Query("SELECT * FROM LEARN WHERE  level ==1")
        LiveData<List<Learn>> level1();
        @Query("SELECT * FROM LEARN WHERE level ==1 or level==2 ")
        LiveData<List<Learn>> level2();
        @Query("SELECT * FROM LEARN WHERE level ==1 or level ==2 or level ==3")
        LiveData<List<Learn>> level3();
        @Query("SELECT * FROM Learn WHERE level LIKE :first AND " +
                "section LIKE :last LIMIT 1")
        Learn findByName(String first, String last);
         @Update
         void update(Learn LearningMaterial );
        @Insert
        void insert(Learn learningMaterial);



}

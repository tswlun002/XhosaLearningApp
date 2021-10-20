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
        /**
         * this function selects all the data the user will use to learn from the database
         * @return the material from the database
         */
        @Query("SELECT * FROM Learn")
        LiveData<List<Learn>> getAll();

        /**
         * selects learning material from level one database
         * @return the learning material from database
         */
        @Query("SELECT * FROM LEARN WHERE  level ==1")
        LiveData<List<Learn>> level1();

        /**
         * selects learning material from level 2
         * @return the material for level 2
         */
        @Query("SELECT * FROM LEARN WHERE level ==1 or level==2 ")
        LiveData<List<Learn>> level2();

        /**
         * fecth learning material from the database
         * @return the learning material from level3
         */
        @Query("SELECT * FROM LEARN WHERE level ==1 or level ==2 or level ==3")
        LiveData<List<Learn>> level3();
        @Query("SELECT * FROM Learn WHERE level LIKE :first AND " +
                "section LIKE :last LIMIT 1")
        Learn findByName(String first, String last);

        /**
         * this function updates the new lorded learning material in the database
         * @param LearningMaterial updates the material to the database
         */
         @Update
         void update(Learn LearningMaterial );

        /**
         * initialises the database of the system
         * @param learningMaterial inserts the learning material to the database
         */
        @Insert
        void insert(Learn learningMaterial);



}

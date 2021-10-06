package com.example.wordgame.data_layer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wordgame.model_layer.Matching;
import com.example.wordgame.model_layer.TrueFalseGame;

import java.util.List;
/**
 * @Class  selects data for TrueFalseDao for the user
 */
@Dao
public interface TrueFalseDao {
    /**
     * gets all the information for true or false
     * @return get true or false questions and answers and marks for those questions
     */
    @Query("SELECT * FROM TrueOfFalse")
    LiveData<List<TrueFalseGame>> getAll();


    /**
     * gets all the information for true or false for level 1
     * @return get true or false questions and answers and marks for those questions for level 1
     */
    @Query("SELECT * FROM TrueOfFalse WHERE level =1")
    LiveData<List<TrueFalseGame>> loadLevelOne();
    /**
     * gets all the information for true or false for level 2
     * @return get true or false questions and answers and marks for those questions for level 2
     */
    @Query("SELECT * FROM TrueOfFalse WHERE level =2")
    LiveData<List<TrueFalseGame>> loadLevelTwo();
    /**
     * gets all the information for true or false for level 3
     * @return get true or false questions and answers and marks for those questions for level 3
     */
    @Query("SELECT * FROM TrueOfFalse WHERE level =3")
    LiveData<List<TrueFalseGame>> loadLevelThree();

    /**
     * updates the information
     * @param activity update all the information from the table
     */
    @Update
    void update(TrueFalseGame activity);
    /**
     * creates and insert new information to table in the database
     * @param activity insert all the columns in the database
     */
    @Insert
    void insert(TrueFalseGame activity);
}

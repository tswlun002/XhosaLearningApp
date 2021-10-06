package com.example.wordgame.data_layer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.wordgame.model_layer.TranslationGame;

import java.util.List;

/**
 * @Class  selects data for translation for the user
 */
@Dao
public interface TranslationDao {
    /**
     * gets all the information for translation
     * @return get translations questions and answers and marks for those questions
     */
    @Query("SELECT * FROM TranslationGame")
    LiveData<List<TranslationGame>> getAll();

    /**
     * gets all the information for translation for level 1
     * @return get translations questions and answers and marks for those questions for level 1
     */
    @Query("SELECT * FROM TranslationGame WHERE  level ==1")
    LiveData<List<TranslationGame>> level1();


    /**
     * gets all the information for translation for level 2
     * @return get translations questions and answers and marks for those questions for level 2
     */
    @Query("SELECT * FROM TranslationGame WHERE level ==2")
    LiveData<List<TranslationGame>> level2();


    /**
     * gets all the information for translation for level 3
     * @return get translations questions and answers and marks for those questions for level 3
     */
    @Query("SELECT * FROM TranslationGame WHERE level ==3")
    LiveData<List<TranslationGame>> level3();

    /**
     * updates the information
     * @param activity update all the information from the table
     */
    @Update
    void update(TranslationGame activity);

    /**
     * creates and insert new information to table in the database
     * @param activity insert all the columns in the database
     */
    @Insert
    void insert(TranslationGame activity);
}

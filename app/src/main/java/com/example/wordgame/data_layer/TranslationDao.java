package com.example.wordgame.data_layer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.wordgame.model_layer.TranslationGame;

import java.util.List;
@Dao
public interface TranslationDao {

    @Query("SELECT * FROM TranslationGame")
    LiveData<List<TranslationGame>> getAll();
    @Query("SELECT * FROM TranslationGame WHERE  level ==1")
    LiveData<List<TranslationGame>> level1();
    @Query("SELECT * FROM TranslationGame WHERE level ==2")
    LiveData<List<TranslationGame>> level2();
    @Query("SELECT * FROM TranslationGame WHERE level ==3")
    LiveData<List<TranslationGame>> level3();
    @Update
    void update(TranslationGame activity);
    @Insert
    void insert(TranslationGame activity);
}

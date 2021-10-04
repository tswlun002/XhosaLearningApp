package com.example.wordgame.data_layer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wordgame.model_layer.Learn;
import com.example.wordgame.model_layer.ProgressReport;
import com.example.wordgame.model_layer.TranslationGame;

import java.util.List;
@Dao
public interface ProgressReportDao {
    @Query("SELECT * FROM ProgressReport")
    LiveData<List<ProgressReport>> getAll();
    @Query("SELECT * FROM ProgressReport WHERE  levelOneScore ==1")
    LiveData<List<ProgressReport>> level1();
    @Query("SELECT * FROM ProgressReport WHERE levelTwoScore ==2")
    LiveData<List<ProgressReport>> level2();
    @Query("SELECT * FROM ProgressReport WHERE levelTwoScore ==3")
    LiveData<List<ProgressReport>> level3();
    @Query("SELECT * FROM ProgressReport WHERE userId LIKE :first AND " +
            "levelTwoScore LIKE :last LIMIT 1")
    ProgressReport findByName(String first, String last);
    @Update
    void update(ProgressReport progressReport );
    @Insert
    void insert(ProgressReport progressReport);


}

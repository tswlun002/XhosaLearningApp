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
    /**
     *  selects matching material from the database to use to play
     * @return all the questions and answers for matching game type for level 1
     */
    @Query("SELECT * FROM MATCHING where level=1")
    LiveData<List<Matching>> getAll();

    /**
     *  selects matching material from the database to use to play
     * @return all the questions and answers for matching game type for level 1
     */
    @Query("SELECT * FROM Matching WHERE level =1")
    LiveData<List<Matching>> loadLevelOne();

    /**
     *  selects matching material from the database to use to play level 2
     * @return all the questions and answers for matching game type for level 2
     */
    @Query("SELECT * FROM Matching WHERE level =2")
    LiveData<List<Matching>> loadLevelTwo();

    /**
     *  selects matching material from the database to use to play level3
     * @return all the questions and answers for matching game type for level 3
     */
    @Query("SELECT * FROM Matching WHERE level =3")
    LiveData<List<Matching>> loadLevelThree();

    /**
     * used to update the databases matching material
     * @param activity used to update all the questions and answers for the levels for matching
     */
    @Update
    void update(Matching activity);

    /**
     * initialises the the grades for all the levels for the matching
     * @param activity inserts all the questions and answers  for all the levels for matching
     */
    @Insert
    void insert(Matching activity);
}

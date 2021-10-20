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

/**
 * @Class  to get grades of the user to see how much they have progressed
 */
@Dao
public interface ProgressReportDao {

    /**
     * @// FIXME: 2021/10/06
     * @return
     */

    /**
     * this functions gets all the user grades for each level
     * @return gameId, userID gametype level 1 grades, level 2 grades, level 3 grades and the average of all of them
     */
    @Query("SELECT * FROM ProgressReport")
    LiveData<List<ProgressReport>> getAll();
    @Query("SELECT * FROM ProgressReport WHERE  levelOneScore ==1")
    LiveData<List<ProgressReport>> level1();

    /**
     * gets results for level 2 and the total Marks for each of the game
     * @return level 2 results, game type userid and the total user Marks
     */
    @Query("SELECT * FROM ProgressReport WHERE levelTwoScore ==2")
    LiveData<List<ProgressReport>> level2();

    /**
     * gets results for level 2 and the total Marks for each of the game
     * @return level 3 results, game type userid and the total user Marks
     */
    @Query("SELECT * FROM ProgressReport WHERE levelTwoScore ==3")
    LiveData<List<ProgressReport>> level3();

    /**
     * gets results for level 2 and the total Marks for each of the game
     * @return level 2 results, game type userid and the total user Marks
     */
    @Query("SELECT * FROM ProgressReport WHERE userId LIKE :first AND " +
            "levelTwoScore LIKE :last LIMIT 1")
    ProgressReport findByName(String first, String last);

    /**
     * used to update the progress of the user
     * @param progressReport update the all the columns
     */
    @Update
    void update(ProgressReport progressReport);

    /**
     * used to create and insert grades for each levels including the game type
     * @param progressReport used to insert the all the grades for all the levels for the user
     */
    @Insert
    void insert(ProgressReport progressReport);


}

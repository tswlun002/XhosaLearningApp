package com.example.wordgame.data_layer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wordgame.model_layer.Learn;
import com.example.wordgame.model_layer.LevelResults;
import java.util.List;
@Dao
public interface LevelResultsDao {


    /**
     * this functions all the Playing material from the database for level 1
     * @return all the playing material for each game type (translation, multiple choice, true or false and matching)
     */
    @Query("SELECT *From LevelResults")
    LiveData<List<LevelResults>> getAll();
    @Query("SELECT  * FROM LevelResults  WHERE  userMarks= " +
            "( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'matching' and level ==1)" +
            "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'multiple choice'and level ==1) \n" +
            "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'translation'and level ==1)\n" +
            "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'true false'and level ==1) " +
            "GROUP BY gameType ")
    LiveData<List<LevelResults>> level1();


    /**
     * this functions all the Playing material from the database for level 2
     * @return all the playing material for each game type (translation, multiple choice, true or false and matching)
     */
    @Query("SELECT  * FROM LevelResults  WHERE  userMarks= " +
            "( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'matching' and level ==2)" +
            "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'multiple choice'and level ==2) \n" +
            "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'translation'and level ==2)\n" +
            "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'true false'and level ==2) " +
            "GROUP BY gameType ")

    LiveData<List<LevelResults>> level2();

    /**
     * this functions all the Playing material from the database for level 3
     * @return all the playing material for each game type (translation, multiple choice, true or false and matching)
     */
    @Query("SELECT  * FROM LevelResults  WHERE  userMarks= " +
            "( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'matching' and level ==3)" +
            "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'multiple choice'and level ==3) \n" +
            "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'translation'and level ==3)\n" +
            "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'true false'and level ==3) " +
            "GROUP BY gameType ")
    LiveData<List<LevelResults>> level3();

    /**
     * this function updates the current results from the database
     * @param levelResults used to update the level Results for every gametype
     */
    @Update
    void update(LevelResults levelResults );

    /**
     * this funcction is used to initialise the grades for every level to the database
     * @param levelResults used to insert the level results to the database
     */
    @Insert
    void insert(LevelResults levelResults);
}

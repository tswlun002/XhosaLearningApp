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
    @Query("SELECT  DISTINCT level_resultsId,gameId,userId,level,gameType,userMarks,totalMarks FROM LevelResults")
    LiveData<List<LevelResults>> getAll();
    @Query("SELECT  * FROM LevelResults  WHERE  userMarks= " +
            "( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'matching' and level ==1)" +
            "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'multiple choice'and level ==1) \n" +
            "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'translation'and level ==1)\n" +
            "or userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'true false'and level ==1) " +
            "GROUP BY gameType ")
    LiveData<List<LevelResults>> level1();
    @Query("SELECT * FROM LevelResults WHERE level ==2")
    LiveData<List<LevelResults>> level2();
    @Query("SELECT * FROM LevelResults WHERE level ==3")
    LiveData<List<LevelResults>> level3();
    @Query("SELECT * FROM LevelResults WHERE level LIKE :first AND " +
            "userId LIKE :last LIMIT 1")
    LevelResults findByName(String first, String last);
    @Update
    void update(LevelResults levelResults );
    @Insert
    void insert(LevelResults levelResults);

    /**
     *SELECT * FROM LevelResults  WHERE
     * userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'matching'and level ==1)
     *  or
     * userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'multiple choice'and level ==1)
     * or
     *  userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'translation'and level ==1)
     *  or
     *  userMarks= ( SELECT MAX(userMarks) FROM LevelResults WHERE gameType == 'true false'and level ==1)
     */
}

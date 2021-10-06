package com.example.wordgame.data_layer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.wordgame.model_layer.User;

import java.util.List;
/**
 * @Class  selects data for User for the user
 */
@Dao
public interface UserDao {
    /**
     * gets all the information for User table
     * @return get User questions and answers and marks for those questions
     */
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAll();

    /**
     * used to update the user table from the database
     * @param user updates all the field from the table in the database
     */
    @Update
    void update(User user);

    /**
     * this function insert the new table into the database of the user
     * @param user  initiates all the fields from the user table
     */
    @Insert
    void insert(User user);
}

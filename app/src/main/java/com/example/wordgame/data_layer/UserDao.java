package com.example.wordgame.data_layer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.wordgame.model_layer.User;

import java.util.List;
@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAll();
    @Update
    void update(User user);
    @Insert
    void insert(User user);
}

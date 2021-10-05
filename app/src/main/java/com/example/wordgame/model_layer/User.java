package com.example.wordgame.model_layer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "User")
public class User {

    @PrimaryKey
    private  int userId;
    private  int currentLevel;

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public String toString() {
        return  userId + ", " + currentLevel;
    }

    public User(){}

    public User(int userId, int currentLevel) {
        this.userId = userId;
        this.currentLevel=currentLevel;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

package com.example.wordgame.model_layer;

public class User {

    private  int userId;

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

    private  int currentLevel;

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

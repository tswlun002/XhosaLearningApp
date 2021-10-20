package com.example.wordgame.model_layer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LevelResults")
public class LevelResults {
    @PrimaryKey(autoGenerate = true)
    public   int level_resultsId=0;
    private int gameId;

    public LevelResults(int gameId, int userId, int level, String gameType, double userMarks, int totalMarks) {
        this.gameId = gameId;
        this.userId = userId;
        this.level = level;
        this.gameType = gameType;
        this.userMarks = userMarks;
        this.totalMarks = totalMarks;
    }

    private  int userId;
    private int level;
    private  String gameType;
    private double userMarks;
    private  int totalMarks;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public double getUserMarks() {
        return userMarks;
    }

    public void setUserMarks(double userMarks) {
        this.userMarks = userMarks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    @Override
    public String toString() {
        return
                "" + level_resultsId +
                ", " + gameId +
                ", " + userId +
                ", " + level +
                ", " + gameType + '\'' +
                "," + userMarks +
                ", " + totalMarks;
    }
    public  String getInformation(){
        return "Level  : Game Type  : User Mark : Total Mark\n"+
                "     "+level +"         "+ gameType +"              " +
                userMarks + "           " +
                totalMarks;
    }

}

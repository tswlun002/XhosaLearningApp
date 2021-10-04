package com.example.wordgame.model_layer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "ProgressReport")
public class ProgressReport {

    @PrimaryKey(autoGenerate = true)
    public  int progressId=0;
    private int userId;
    private int levelOneScore;
    private int levelTwoScore;
    private int levelThreeScore;
    private  int AverageScore;
    private String status;
    public  ProgressReport(){}
    public ProgressReport(int userId, int levelOneScore, int levelTwoScore, int levelThreeScore, int averageScore, String status) {
        this.userId = userId;
        this.levelOneScore = levelOneScore;
        this.levelTwoScore = levelTwoScore;
        this.levelThreeScore = levelThreeScore;
        this.AverageScore = averageScore;
        this.status = status;
    }

    public int getProgressId() {
        return progressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLevelOneScore() {
        return levelOneScore;
    }

    public void setLevelOneScore(int levelOneScore) {
        this.levelOneScore = levelOneScore;
    }

    public int getLevelTwoScore() {
        return levelTwoScore;
    }

    public void setLevelTwoScore(int levelTwoScore) {
        this.levelTwoScore = levelTwoScore;
    }

    public int getLevelThreeScore() {
        return levelThreeScore;
    }

    public void setLevelThreeScore(int levelThreeScore) {
        this.levelThreeScore = levelThreeScore;
    }

    public int getAverageScore() {
        return AverageScore;
    }

    public void setAverageScore(int averageScore) {
        AverageScore = averageScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}

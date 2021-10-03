package com.example.wordgame.model_layer;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Matching")
public class Matching {
    @PrimaryKey(autoGenerate = true)
    public int matchingId=0;
    private  int level;
    private String tittle;
    private String instructions;
    private  String questions;
    private String answers;

    @Ignore
    public Matching(){}
    @Ignore
    public Matching(int matchingId) {
        this.matchingId = matchingId;
    }

    public Matching(int level, String questions, String answers, String tittle, String instructions, int totalMarks) {
        this.level = level;
        this.questions = questions;
        this.answers = answers;
        this.tittle = tittle;
        this.instructions = instructions;
        this.totalMarks = totalMarks;
    }

    private int totalMarks;

    public int getMatchingId() {
        return matchingId;
    }

    public void setMatchingId(int matchingId) {
        this.matchingId = matchingId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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
                 matchingId +
                ", " + level +
                ", " + totalMarks ;
    }
}

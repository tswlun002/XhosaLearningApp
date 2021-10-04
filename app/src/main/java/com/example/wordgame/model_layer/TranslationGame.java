package com.example.wordgame.model_layer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "TranslationGame")
public class TranslationGame {

    @PrimaryKey(autoGenerate = true)
    public int translationId=0;
    private String hints;
    private int hintCounter;
    private String instructions;
    private  int totalMarks;
    private  int level;
    private String question;
    private String answers;


    @Override
    public String toString() {
        return
                translationId +
                "," + level +
                "," + totalMarks ;
    }


    public TranslationGame(int level, String question, String answers,
                           String hints, int hintCounter, String instructions, int totalMarks) {
        this.level = level;
        this.question = question;
        this.answers = answers;
        this.hints = hints;
        this.hintCounter = hintCounter;
        this.instructions = instructions;
        this.totalMarks = totalMarks;
    }

    public int getTranslationId() {
        return translationId;
    }

    public void setTranslationId(int translationId) {
        this.translationId = translationId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getHints() {
        return hints;
    }

    public void setHints(String hints) {
        this.hints = hints;
    }

    public int getHintCounter() {
        return hintCounter;
    }

    public void setHintCounter(int hintCounter) {
        this.hintCounter = hintCounter;
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


}

package com.example.wordgame.model_layer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "TrueOfFalse")
public class TrueFalseGame{

        @PrimaryKey(autoGenerate = true)
        public int trueFalseId=0;
        private  int level;
        private String question;
        private  String figures;
        private String answers;
        private String instructions;
        private  int totalMarks;

    public TrueFalseGame(int level, String question,  String figures, String answers,String instructions,int totalMarks) {
        this.level = level;
        this.question = question;
        this.instructions = instructions;
        this.figures = figures;
        this.answers=answers;
        this.totalMarks=totalMarks;
    }


    public int getTrueFalseId() {
        return trueFalseId;
    }

    public void setTrueFalseId(int trueFalseId) {
        this.trueFalseId = trueFalseId;
    }
    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getFigures() {
        return figures;
    }

    public void setFigures(String figures) {
        this.figures = figures;
    }

    @Override
    public String toString() {
        return trueFalseId +
                ", " + level +
                ", " + totalMarks ;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }



}

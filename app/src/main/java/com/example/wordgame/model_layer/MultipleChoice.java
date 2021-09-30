package com.example.wordgame.model_layer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "MultipleChoice")
public class MultipleChoice {
    @PrimaryKey(autoGenerate = true)
    public int multipleId=0;
    private  int level;
    private String question;
    private String choiceOne;
    private String choiceTwo;
    private String choiceThree;
    private String choiceFour;
    private String answer;
    private String instructions;
    private  int totalMarks;


    public MultipleChoice(int level, String question, String choiceOne, String choiceTwo,
                          String choiceThree, String choiceFour, String instructions, String answer, int totalMarks) {
        this.level = level;
        this.question = question;
        this.choiceOne = choiceOne;
        this.choiceTwo = choiceTwo;
        this.choiceThree = choiceThree;
        this.choiceFour = choiceFour;
        this.instructions = instructions;
        this.totalMarks = totalMarks;
        this.answer=answer;

    }

    public int getMultipleId() {
        return multipleId;
    }

    public void setMultipleId(int multipleId) {
        this.multipleId = multipleId;
    }

    @Override
    public String toString() {
        return "MultipleChoice{" +
                "multipleId=" + multipleId +
                ", level=" + level +
                ", question='" + question + '\'' +
                ", choiceOne='" + choiceOne + '\'' +
                ", choiceTwo='" + choiceTwo + '\'' +
                ", choiceThree='" + choiceThree + '\'' +
                ", choiceFour='" + choiceFour + '\'' +
                ", answer='" + answer + '\'' +
                ", instructions='" + instructions + '\'' +
                ", totalMarks=" + totalMarks +
                '}';
    }
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public String getChoiceOne() {
        return choiceOne;
    }

    public void setChoiceOne(String choiceOne) {
        this.choiceOne = choiceOne;
    }

    public String getChoiceTwo() {
        return choiceTwo;
    }

    public void setChoiceTwo(String choiceTwo) {
        this.choiceTwo = choiceTwo;
    }

    public String getChoiceThree() {
        return choiceThree;
    }

    public void setChoiceThree(String choiceThree) {
        this.choiceThree = choiceThree;
    }

    public String getChoiceFour() {
        return choiceFour;
    }

    public void setChoiceFour(String choiceFour) {
        this.choiceFour = choiceFour;
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

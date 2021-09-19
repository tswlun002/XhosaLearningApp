package com.example.wordgame.model_layer;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Class  Learn is the class for learning material
 */
@Entity(tableName = "Learn")
public class Learn  {
    /**
     * @serialField levelId is the id for each learning material we store
     * @serialField level is the level of learning
     * @serialField  section is the sections in of learning in the particular level
     * @serialField  instructions is the instructions of learning
     */
   @PrimaryKey(autoGenerate = true)
   public   int learnId=0 ;
   private  int level;
   private  String section;
   private  String content;
   private String instruction;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * constructor that initialise serial fields
     * @param level is given level
     * @param instruction given instructions
     * @param section given instructions
     */
    public Learn( int level, String section, String content,String instruction) {
        this.level = level;
        this.instruction = instruction;
        this.section = section;
        this.content =content;

    }

    /**
     * get section of learning
     * @return section of learning
     */
    public String getSection() {
        return section;
    }

    /**
     * set section
     * @param section is given section
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     * get learning id for learning
     * @return learning id
     */
    public int getLearnId() {
        return learnId;
    }

    /**
     * Get level of learning
     * @return level of learning
     */
    public int getLevel() {
        return level;
    }

    /**
     * set level of learning
     * @param level given level of learning
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /***
     * get instructions of learning
     * @return instructions of learning
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * set instructions of learning
     * @param instruction given instructions
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
    /**
     * Get all learning properties in string type
     * @return all learning material
     */
    @NonNull
    @Override
    public String toString() {
        return "LearnDB{" +
                "learnId=" + learnId +
                ", level=" + level +
                ", section="+section+
                ", content="+content+
        ", instruction='" + instruction + '\'' +
                '}';
    }



}

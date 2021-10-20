package com.example.wordgame.data_layer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wordgame.model_layer.MultipleChoice;
import com.example.wordgame.model_layer.TrueFalseGame;

import java.util.List;
@Dao
public interface MultipleChoiceDao {
    /**
     * function used to select all the question and answers
     * @return all the questions and answers for the game type
     */
    @Query("SELECT * FROM MultipleChoice")
    LiveData<List<MultipleChoice>> getAll();

    /**
     * function used to select all the question and answers for level 1
     * @return all the questions and answers for the game type in level 1
     */
    @Query("SELECT * FROM MultipleChoice WHERE level =1")
    LiveData<List<MultipleChoice>> loadLevelOne();

    /**
     * function used to select all the question and answers for level 2
     * @return all the questions and answers for the game type in level 2
     */
    @Query("SELECT * FROM MultipleChoice WHERE level =2")
    LiveData<List<MultipleChoice>> loadLevelTwo();


    /**
     * function used to select all the question and answers for level 3
     * @return all the questions and answers for the game type in level 3
     */
    @Query("SELECT * FROM MultipleChoice WHERE level =3")
    LiveData<List<MultipleChoice>> loadLevelThree();

    /**
     * used to update the material for all the levels of the game type (multiple choice)
     * @param activity used to update the playing material
     */
    @Update
    void update(MultipleChoice activity);

    /**
     * this function inserts all the questions and answers for multiple choices
     * @param activity used to insert the  questions and answers from multiple choice questions
     */
    @Insert
    void insert(MultipleChoice activity);
}

package com.example.wordgame.presentation_layer;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.User;
import com.example.wordgame.model_layer.UserViewModel;
import com.example.wordgame.model_layer.WordGameViewModel;

import java.util.HashMap;
import java.util.List;

public class LevelResultsHandler {

    /**
     * @serialField userViewModel is the user view model instance
     * @serialField  context of level results fragment
     * @serialField wordGameViewModel is the instance of  WordGameViewModel
     * @serialField viewLifecycleOwner of the level results view model
     */
    private final UserViewModel userViewModel;
    private final Context context;
    private final WordGameViewModel wordGameViewModel;
    private final LifecycleOwner viewLifecycleOwner;

    /**
     * initialises the serial field of LevelResultsHandler
     * @param context initiate context
     * @param userViewModel initiate  userViewModel
     * @param wordGameViewModel initiate wordGameViewModel
     * @param viewLifecycleOwner initialise  viewLifecycleOwner
     */
    public LevelResultsHandler(Context context,UserViewModel userViewModel,
                               WordGameViewModel  wordGameViewModel,  LifecycleOwner viewLifecycleOwner){
        this.context=context;
        this.wordGameViewModel=wordGameViewModel;
        this.userViewModel=userViewModel;
        this.viewLifecycleOwner =viewLifecycleOwner;

    }

    /**
     * Share level results data using share view model wordGameViewModel so that all other fragment have
     * access on them
     * Level results are created from user answers after each game
     * @param submit is the interface that is used to record score after submit
     * @param onExtractResults store game questions and correct answers of the each game
     * @param userAnswers user answers of the game
     * @param gameType type of the game user played
     */
    void  shareData(OnSubmit submit, OnExtractResults onExtractResults, HashMap<String,String> userAnswers,String gameType){
        double score = submit.getScore();

        int userId = UserId();
        String[] information  = onExtractResults.getGameInformation().split(",");
        int gameId=0;int level=0; int totalMarks=0;

        try {
            gameId= Integer.parseInt(information[0].trim());
            level= Integer.parseInt(information[1].trim());
            totalMarks= userAnswers.size();
        }catch (Exception e){
            Toast.makeText(context,"Error LevelResultsHandler\n "+e.toString(),Toast.LENGTH_SHORT).show();
        }

        LevelResults levelResults = new LevelResults(
                gameId,userId,level,gameType,
                score,totalMarks
        );
        wordGameViewModel.setResults(levelResults);
    }

    /**
     * Get user id using userViewModel from database
     * @return the user id
     */
    private  int UserId(){
        final User[] user = new User[1];
        userViewModel.getGameMaterial().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if(users!=null)
                    user[0] = users.get(0);
            }
        });
        return user[0].getUserId();
    }

    /**
     * Life cycle of fragment level results
     * @return fragment level results
     */
    public LifecycleOwner getViewLifecycleOwner() {

        return viewLifecycleOwner;
    }


}

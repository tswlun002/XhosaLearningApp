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
    private final UserViewModel userViewModel;
    private final Context context;
    private final WordGameViewModel wordGameViewModel;
    private final LifecycleOwner viewLifecycleOwner;
    public LevelResultsHandler(Context context,UserViewModel userViewModel,
                               WordGameViewModel  wordGameViewModel,  LifecycleOwner viewLifecycleOwner){
        this.context=context;
        this.wordGameViewModel=wordGameViewModel;
        this.userViewModel=userViewModel;
        this.viewLifecycleOwner =viewLifecycleOwner;

    }

    void  shareData(OnSubmit submit, OnExtractResults onExtractResults, HashMap<String,String> userAnswers,String gameType){
        double score = submit.getScore();

        int userId = UserId();
        String[] information  = onExtractResults.getGameInformation().split(",");
        int gameId=0;int level=0; int totalMarks=0;
        try {
            gameId= Integer.parseInt(information[0].trim());
            level= Integer.parseInt(information[1].trim());
            totalMarks= userAnswers.size()/*Integer.parseInt(information[2].trim())*/;
        }catch (Exception e){
            Toast.makeText(context,"Error LevelResultsHandler\n "+e.toString(),Toast.LENGTH_SHORT).show();
        }

        LevelResults levelResults = new LevelResults(
                gameId,userId,level,gameType,
                score,totalMarks
        );
        wordGameViewModel.setValue(levelResults);
    }

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

    public LifecycleOwner getViewLifecycleOwner() {

        return viewLifecycleOwner;
    }


}

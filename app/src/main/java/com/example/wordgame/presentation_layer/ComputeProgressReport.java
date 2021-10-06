package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.ProgressReport;
import com.example.wordgame.model_layer.ProgressViewModel;
import com.example.wordgame.model_layer.User;
import com.example.wordgame.model_layer.UserViewModel;
import com.example.wordgame.model_layer.WordGameViewModel;

import java.util.HashMap;
import java.util.List;

public class ComputeProgressReport {
    private HashMap<String,Double> gameScoresLevelOne;
    private final HashMap<String,Double> gameScoresLevelTwo;
    private final HashMap<String,Double> gameScoresLevelThree ;
    private List<LevelResults> levelResultsList;
    private final Application context;
    private ProgressViewModel progressViewModel;


    public ComputeProgressReport(Application context) {
        this.context = context;
        gameScoresLevelOne = new HashMap<>();
        gameScoresLevelTwo= new HashMap<>();
        gameScoresLevelThree= new HashMap<>();

    }

    /**
     * @param levelResultsList all latest scores of the user
     */
    public void setLevelResultsList(List<LevelResults> levelResultsList) {
        this.levelResultsList = levelResultsList;
    }
    /**
     * compute score for each level
     */
      void computeScores(){

          for(LevelResults levelResults:levelResultsList){
            int level = levelResults.getLevel();
            String gameType  = levelResults.getGameType().trim().toLowerCase();
            double marks  = levelResults.getUserMarks();
            double totalMarks  = levelResults.getTotalMarks();
            double overAllMarks  = marks/totalMarks;


            if(level ==1) {
                if (gameScoresLevelOne.get(gameType) != null) {
                    double value = gameScoresLevelOne.get(gameType) + overAllMarks;
                    gameScoresLevelOne.put(gameType, value);
                } else {

                    gameScoresLevelOne.put(gameType, overAllMarks);
                }
            }
            else if(level ==2){

                if (gameScoresLevelTwo.get(gameType) != null) {
                    double value = gameScoresLevelTwo.get(gameType) + overAllMarks;
                    gameScoresLevelTwo.put(gameType, value);
                } else {

                    gameScoresLevelTwo.put(gameType, overAllMarks);
                }
            }
            else if (level==3){
                if (gameScoresLevelThree.get(gameType) != null) {
                    double value = gameScoresLevelThree.get(gameType) + overAllMarks;
                    gameScoresLevelThree.put(gameType, value);
                } else {

                    gameScoresLevelThree.put(gameType, overAllMarks);
                }
            }
        }

    }

    /**
     * compute score for level one
     * @return  average score for level one
     */
    @SuppressLint("DefaultLocale")
    public int getLevelOneAverage() {
        double value =0;
        Object[] keys=gameScoresLevelOne.keySet().toArray();
        try {
            for (Object key : keys) {
                value += gameScoresLevelOne.get(key + "");
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        double value3 =  (value/4)*100;

        return Integer.parseInt(String.format("%.0f",value3));
    }



    @SuppressLint("DefaultLocale")
    public int getLevelTwoAverage() {
        double value =0;
        Object[] keys=gameScoresLevelTwo.keySet().toArray();
        try {
            for (Object key : keys) {
                value += gameScoresLevelTwo.get(key + "");
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        double value3 =  (value/4)*100;

        return Integer.parseInt(String.format("%.0f",value3));
    }



    @SuppressLint("DefaultLocale")
    public int getLevelThreeAverage() {
        double value =0;

        Object[] keys=gameScoresLevelThree.keySet().toArray();
        try {
            for (Object key : keys) {
                value += gameScoresLevelThree.get(key + "");
            }
        }catch (NullPointerException  e){
            e.printStackTrace();
        }
        double value3 =  (value/4)*100;

        return Integer.parseInt(String.format("%.0f",value3));
    }

    /**
     * Insert the averages scores for levels into Progress report
     *
     */
     void  insert( LifecycleOwner viewLifecycleOwner){
         progressViewModel = new ProgressViewModel((Application) context);
        int levelOneAverage  =getLevelOneAverage();
        int levelTwoAverage  =getLevelTwoAverage();
        int levelThreeAverage =getLevelThreeAverage();
        int average  = (levelOneAverage+levelTwoAverage+levelThreeAverage)/3;

         final boolean[] flag = {false};
        progressViewModel.getGameMaterial().observe(viewLifecycleOwner, new Observer<List<ProgressReport>>() {
            @Override
            public void onChanged(List<ProgressReport> progressReports) {
                //Toast.makeText(context, progressReports.get(0).toString()+" \n\napha joe",Toast.LENGTH_SHORT).show();
                if(!flag[0]) {

                    int level1  =progressReports.get(0).getLevelOneScore();
                    int level2  = progressReports.get(0).getLevelTwoScore();
                    int level3  =progressReports.get(0).getLevelThreeScore();
                    int average1  =progressReports.get(0).getAverageScore();
                    Toast.makeText(context,levelOneAverage+" | "+average,Toast.LENGTH_SHORT).show();
                    if(level1<levelOneAverage)
                        level1=levelOneAverage;
                    if(level2<levelTwoAverage)
                        level2=levelTwoAverage;
                    if(level3<levelThreeAverage)
                        level3=levelThreeAverage;
                    if(average1<average)
                        average1=average;
                    String status = "";
                    if(average1>65){
                        status="In good progress";
                    }
                    else if(average1 ==100)
                        status ="Passed very well";
                    else
                        status ="In progress";


                    ProgressReport progressReport = new ProgressReport(0, level1,
                            level2, level3, average1, status);
                    progressViewModel.update(progressReport);
                    flag[0]=true;
                }
            }
        });
        userLevelCheck(viewLifecycleOwner);

    }

    private  void userLevelCheck(LifecycleOwner viewLifecycleOwner){
         boolean changed =false;
         progressViewModel.getGameMaterial().observe(viewLifecycleOwner, new Observer<List<ProgressReport>>() {
             @Override
             public void onChanged(List<ProgressReport> progressReports) {
                 final int[] level = {1};
                 int level1 = progressReports.get(0).getLevelOneScore();
                 int level2  = progressReports.get(0).getLevelTwoScore();
                 if(level1>=65 & level2<65)
                     level[0] =2;
                 if(level2>=65)
                     level[0]=3;
                // Toast.makeText(context,level2+" level",Toast.LENGTH_SHORT).show();
                 UserViewModel userViewModel = MainActivity.userViewModel;
                 userViewModel.update(new User(0, level[0]));
                /*
                 userViewModel.getGameMaterial().observe(viewLifecycleOwner, new Observer<List<User>>() {
                     @Override
                     public void onChanged(List<User> users) {
                         int currentLevel = users.get(0).getCurrentLevel();
                         if(level[0] >currentLevel) {


                             popUp(level[0]);
                             /*Intent mStartActivity = new Intent(context, MainActivity.class);
                             int mPendingIntentId = 123456;
                             PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                             AlarmManager mgr = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
                             mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                             System.exit(0);
                         }

                     }
                 });*/
             }
         });


    }

    private void popUp(int level ){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Note");
        alertDialog.setMessage(
                "Congratulation!!, you qualify to go to next level." +
                        "To access material for next level, go back home and click level "+level+1+""
        );
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Continue current level", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}

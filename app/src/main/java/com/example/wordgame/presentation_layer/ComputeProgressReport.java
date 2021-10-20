package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.ProgressReport;
import com.example.wordgame.model_layer.ProgressViewModel;
import com.example.wordgame.model_layer.User;
import com.example.wordgame.model_layer.UserViewModel;

import java.util.HashMap;
import java.util.List;


/**
 * @Class  ComputeProgressReport  responsible for computing all
 * the average of each game of each level user plays
 */
public class ComputeProgressReport {

    /**
     * @serialField gameScoresLevelOne is the hashMap used to store average score
     * of each game for level one
     * @serialField gameScoresLevelTwo is the hashMap used to store average score
     * of each game for level o two
     * @serialField gameScoresLevelThree is the hashMap used to store average score
     * of each game for level o three
     * @serialField levelResultsList is the list of maximum scores of each game user has played
     * @serialField context is the context to initialise progress view model
     * @serialField progressViewModel is the progress view model used to read data base of progress
     */
    private HashMap<String, Double> gameScoresLevelOne;
    private final HashMap<String, Double> gameScoresLevelTwo;
    private final HashMap<String, Double> gameScoresLevelThree;
    private List<LevelResults> levelResultsList;
    private final Application context;
    private ProgressViewModel progressViewModel;


    /**
     * Initialise all the serial fields gameScoresLevelOne,gameScoresLevelTwo,gameScoresLevelThree and
     * context
     *
     * @param context set context
     */
    public ComputeProgressReport(Application context) {
        this.context = context;
        gameScoresLevelOne = new HashMap<>();
        gameScoresLevelTwo = new HashMap<>();
        gameScoresLevelThree = new HashMap<>();

    }

    /**
     * set list of levelResultsList
     *
     * @param levelResultsList all latest scores of the user
     */
    public void setLevelResultsList(List<LevelResults> levelResultsList) {
        this.levelResultsList = levelResultsList;
    }

    /**
     * Calculate overall( all games played by user) marks  of the user playing each game
     * Current level is checked before calculate the overall marks
     * compute score for each level
     */
    void computeScores() {

        for (LevelResults levelResults : levelResultsList) {
            int level = levelResults.getLevel();
            String gameType = levelResults.getGameType().trim().toLowerCase();
            double marks = levelResults.getUserMarks();
            double totalMarks = levelResults.getTotalMarks();
            double overAllMarks = marks / totalMarks;


            if (level == 1) {
                if (gameScoresLevelOne.get(gameType) != null) {
                    double value = gameScoresLevelOne.get(gameType) + overAllMarks;
                    gameScoresLevelOne.put(gameType, value);
                } else {

                    gameScoresLevelOne.put(gameType, overAllMarks);
                }
            } else if (level == 2) {

                if (gameScoresLevelTwo.get(gameType) != null) {
                    double value = gameScoresLevelTwo.get(gameType) + overAllMarks;
                    gameScoresLevelTwo.put(gameType, value);
                } else {

                    gameScoresLevelTwo.put(gameType, overAllMarks);
                }
            } else if (level == 3) {
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
     * compute over all score for level
     * keys are type of game such as matching , multiple choice, true false and translation
     * Total scores are divided by four because we have four games for each level
     *
     * @return average score for level one
     */
    @SuppressLint("DefaultLocale")
    public int getLevelOneAverage() {
        double value = 0;
        Object[] keys = gameScoresLevelOne.keySet().toArray();
        try {
            for (Object key : keys) {
                value += gameScoresLevelOne.get(key + "");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        double value3 = (value / 4) * 100;

        return (int) Math.round(value3);
    }


    /**
     * compute over all score for level
     * keys are type of game such as matching , multiple choice, true false and translation
     * Total scores are divided by four because we have four games each game
     *
     * @return average score for level two
     */
    @SuppressLint("DefaultLocale")
    public int getLevelTwoAverage() {
        double value = 0;
        Object[] keys = gameScoresLevelTwo.keySet().toArray();
        try {
            for (Object key : keys) {
                value += gameScoresLevelTwo.get(key + "");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        double value3 = (value / 4) * 100;

        return (int) Math.round(value3);
    }


    /**
     * compute over all score for level
     * keys are type of game such as matching , multiple choice, true false and translation
     * Total scores are divided by four because we have four games each game
     *
     * @return average score for level three
     */
    @SuppressLint("DefaultLocale")
    public int getLevelThreeAverage() {
        double value = 0;

        Object[] keys = gameScoresLevelThree.keySet().toArray();
        try {
            for (Object key : keys) {
                value += gameScoresLevelThree.get(key + "");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        double value3 = (value / 4) * 100;

        return (int) Math.round(value3);
    }

    /**
     * Get current played games of each level
     * Get progress score current stored in the database
     * Compare the score of each level
     * If score of level improved , update the level using new score
     * Else level is updated with same score
     * Also, if user has overall average of all level above 65 ,
     * progress status is updates to In good progress
     * else if 100% will be regarded as passed wer well be able to access next level
     * User level will be  updated
     * else user status will be marked as in progress
     * Insert the averages scores for levels into Progress report
     */
    void insert(LifecycleOwner viewLifecycleOwner) {
        progressViewModel = new ProgressViewModel((Application) context);
        int levelOneAverage = getLevelOneAverage();
        int levelTwoAverage = getLevelTwoAverage();
        int levelThreeAverage = getLevelThreeAverage();
        final boolean[] flag = {false};
        progressViewModel.getGameMaterial().observe(viewLifecycleOwner, new Observer<List<ProgressReport>>() {
            @Override
            public void onChanged(List<ProgressReport> progressReports) {
                if (!flag[0]) {

                    int level1 = progressReports.get(0).getLevelOneScore();
                    int level2 = progressReports.get(0).getLevelTwoScore();
                    int level3 = progressReports.get(0).getLevelThreeScore();
                    int average1 = progressReports.get(0).getAverageScore();

                    if (level1 < levelOneAverage)
                        level1 = levelOneAverage;
                    if (level2 < levelTwoAverage)
                        level2 = levelTwoAverage;
                    if (level3 < levelThreeAverage)
                        level3 = levelThreeAverage;

                    int average = (level1 + level2 + level3) / 3;
                    average = Math.round(average);
                    if (average1 < average)
                        average1 = average;
                    String status = "";
                    if (average1 > 65) {
                        status = "In good progress";
                    } else if (average1 == 100)
                        status = "Passed very well";
                    else
                        status = "In progress";
                    ProgressReport progressReport = new ProgressReport(0, level1,
                            level2, level3, average1, status);
                    progressViewModel.update(progressReport);
                    flag[0] = true;
                }
            }
        });
        updateUserLevel(viewLifecycleOwner);


    }

    /**
     * Update user level to level 2 if user achieve overall 65 level one but level 2 is still less 65
     * User level updated to level 3 if user achieve overall of 65 of level 2
     * User level is then updated on the database
     * user will be toasted
     * @param viewLifecycleOwner is the view own that enable to get live data on the data base
     */
    private void updateUserLevel(LifecycleOwner viewLifecycleOwner) {
        progressViewModel.getGameMaterial().observe(viewLifecycleOwner, new Observer<List<ProgressReport>>() {
            @Override
            public void onChanged(List<ProgressReport> progressReports) {
                final int[] level = {1};
                int level1Score = progressReports.get(0).getLevelOneScore();
                int level2Score = progressReports.get(0).getLevelTwoScore();
                if (level1Score >= 65 & level2Score < 65) {
                    level[0] = 2;
                    Toast.makeText(context,"You qualify for level 2",Toast.LENGTH_SHORT).show();
                }
                if (level2Score >= 65) {
                    level[0] = 3;
                    Toast.makeText(context,"You qualify for level 3",Toast.LENGTH_SHORT).show();

                }
                UserViewModel userViewModel = MainActivity.userViewModel;
                userViewModel.update(new User(0, level[0]));

            }
        });


    }

}

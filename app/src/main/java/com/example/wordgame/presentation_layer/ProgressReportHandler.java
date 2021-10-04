package com.example.wordgame.presentation_layer;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.ProgressReport;
import com.example.wordgame.model_layer.ProgressViewModel;

import java.util.HashMap;
import java.util.List;

public class ProgressReportHandler {
    private  int levelOneAverage;
    private  int levelTwoAverage;
    private  int levelThreeAverage;
    private  int overAllScore;
    private  double multipleChoiceScore;
    private double translationScore;
    private double matchingScore;
    private double trueFalseScore;
    private int  totalScore ;
    private HashMap<String,Double> gameScoresLevelOne = new HashMap<>();
    private List<LevelResults> levelResultsList;
    private Application context;
    private ProgressViewModel progressViewModel;
    public ProgressReportHandler(Application context) {
        this.context=context;
        progressViewModel = new ProgressViewModel((Application) context);
    }
    public ProgressReportHandler(double multipleChoiceScore, double translationScore,
                                 double matchingScore, double trueFalseScore) {
        this.multipleChoiceScore = multipleChoiceScore;
        this.translationScore = translationScore;
        this.matchingScore = matchingScore;
        this.trueFalseScore = trueFalseScore;
    }
    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public HashMap<String, Double> getGameScoresLevelOne() {
        return gameScoresLevelOne;
    }

    public void setGameScoresLevelOne(HashMap<String, Double> gameScoresLevelOne) {
        this.gameScoresLevelOne = gameScoresLevelOne;
    }

    public List<LevelResults> getLevelResultsList() {
        return levelResultsList;
    }
    public void setLevelResultsList(List<LevelResults> levelResultsList) {

        this.levelResultsList = levelResultsList;
    }

    /**
     * compute score for each level
     */
      void computeScores(){
        int count=1;
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
                    count++;
                } else {

                    gameScoresLevelOne.put(gameType, overAllMarks);
                    count++;
                }
            }
        }

    }

    /**
     * compute score for level one
     * @return  average score for level one
     */
    public int getLevelOneAverage() {
        double value =0;
        double size  = gameScoresLevelOne.size();
       Object[] value1=gameScoresLevelOne.keySet().toArray();
       for(Object value2 :value1){
            value+=gameScoresLevelOne.get(value2+"");
        }
        double value3 = (int) (value/size)*100;
        //int value2 = Integer.parseInt(String.format("%.0f",value1));
       // setLevelOneAverage(value1);

        return Integer.parseInt(String.format("%.0f",value3));
    }

    /**
     * set average score level one
     * @param levelOneAverage is average for level one being set
     */
    public void setLevelOneAverage(int levelOneAverage) {
        setOverAllScore(levelOneAverage);
        this.levelOneAverage = levelOneAverage;
    }

    public int getLevelTwoAverage() {
        return levelTwoAverage;
    }

    public void setLevelTwoAverage(int levelTwoAverage) {
        this.levelTwoAverage = levelTwoAverage;
    }

    public int getLevelThreeAverage() {
        return levelThreeAverage;
    }

    public void setLevelThreeAverage(int levelThreeAverage) {
        this.levelThreeAverage = levelThreeAverage;
    }

    /**
     * @return over all score for all three  level
     */
    public int getOverAllScore() {
        return overAllScore;
    }

    /**
     * over all score for all three levels
     * @param overAllScore is the over all score for all level
     */
    public void setOverAllScore(int overAllScore) {
        this.overAllScore = overAllScore;
    }

    /**
     * Insert the averages scores for levels into Progress report
     *
     */
     void  insert(){
        int avScore  =getLevelOneAverage();
        String status = "";
        if(avScore>50){
            status="In good progress";
        }
        else
            status ="In progress";
         Toast.makeText(context.getApplicationContext(),
                 gameScoresLevelOne+" \n\nsize in progress\n ", Toast.LENGTH_SHORT).show();
        progressViewModel.insert(
                new ProgressReport(0,avScore,0,0,avScore,status));
    }


    public double getMultipleChoiceScore() {
        return multipleChoiceScore;
    }

    public void setMultipleChoiceScore(double multipleChoiceScore) {
        this.multipleChoiceScore = multipleChoiceScore;
    }

    public double getTranslationScore() {
        return translationScore;
    }

    public void setTranslationScore(double translationScore) {
        this.translationScore = translationScore;
    }

    public double getMatchingScore() {
        return matchingScore;
    }

    public void setMatchingScore(double matchingScore) {
        this.matchingScore = matchingScore;
    }

    public double getTrueFalseScore() {
        return trueFalseScore;
    }

    public void setTrueFalseScore(double trueFalseScore) {
        this.trueFalseScore = trueFalseScore;
    }



    private void computeLevelOne(){}

    private void computeLevelTwo(){}

    private void computeLevelThree(){}

    private void computeAverage(){}


}

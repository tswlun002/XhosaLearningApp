package com.example.wordgame.presentation_layer;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.ProgressReport;
import com.example.wordgame.model_layer.ProgressViewModel;

import java.util.HashMap;
import java.util.List;

public class ComputeProgressReport {
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
    private HashMap<String,Double> distinctResults = new HashMap<>();
    private List<LevelResults> levelResultsList;
    private Application context;
    private ProgressViewModel progressViewModel;
    public ComputeProgressReport(Application context) {
        this.context=context;
        gameScoresLevelOne = new HashMap<>();

    }
    public ComputeProgressReport(double multipleChoiceScore, double translationScore,
                                 double matchingScore, double trueFalseScore) {
        this.multipleChoiceScore = multipleChoiceScore;
        this.translationScore = translationScore;
        this.matchingScore = matchingScore;
        this.trueFalseScore = trueFalseScore;
        gameScoresLevelOne = new HashMap<>();
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


    /**
     *
     * @return latest scores  of the user
     */
    public List<LevelResults> getLevelResultsList() {
        return levelResultsList;
    }

    /**
     *
     * @param levelResultsList all latest scores of the user
     */
    public void setLevelResultsList(List<LevelResults> levelResultsList) {

        this.levelResultsList = levelResultsList;
    }
     void orderResults(){
         for(LevelResults levelResults:levelResultsList) {
             distinctResults.put(levelResults.getGameType().trim().toLowerCase(),levelResults.getUserMarks());
         }

     }
    /**
     * compute score for each level
     */
      void computeScores(){
          orderResults();
        int count=1;
        for(LevelResults levelResults:levelResultsList){

            int level = levelResults.getLevel();
            String gameType  = levelResults.getGameType().trim().toLowerCase();
            double marks  = distinctResults.get(gameType);
            double totalMarks  = levelResults.getTotalMarks();
            double overAllMarks  = marks/(double) totalMarks;

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

        Object[] keys=gameScoresLevelOne.keySet().toArray();

        for(Object key :keys){
            value+=gameScoresLevelOne.get(key+"");
        }
        double value3 =  (value/4)*100;
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
         progressViewModel = new ProgressViewModel((Application) context);
        int avScore  =getLevelOneAverage();

        String status = "";
        if(avScore>50){
            status="In good progress";
        }
        else
            status ="In progress";
         //Toast.makeText(context.getApplicationContext(),
         //        gameScoresLevelOne+" \n\nsize in progress\n ", Toast.LENGTH_SHORT).show();
         ProgressReport  progressReport =new ProgressReport(0,avScore,
                 0,0,avScore/3,status);
         progressReport.setProgressId(0);
        progressViewModel.update(progressReport);
        gameScoresLevelOne.clear();
        setLevelOneAverage(0);
         setGameScoresLevelOne(gameScoresLevelOne);
         Toast.makeText(context, gameScoresLevelOne.size()+" here wena", Toast.LENGTH_SHORT).show();
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

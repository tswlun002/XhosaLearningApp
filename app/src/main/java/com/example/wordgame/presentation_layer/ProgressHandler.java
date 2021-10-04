package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wordgame.R;
import com.example.wordgame.model_layer.ProgressReport;

import java.util.List;

public class ProgressHandler {

    private final View progressView;
    private List<ProgressReport> progressReportList;
    private  ProgressBar levelOneBar,levelTwoBar,levelThreeBar,averageBar;
    private TextView textViewLevelOne,textViewLevelTwo,textViewLevelThree,textViewAverage;

    public  ProgressHandler(View view){
        this.progressView=view;
    }

    public void setData(List<ProgressReport> progressReportList){
        this.progressReportList=progressReportList;
        initViews();
        setDataIntoInterface();
    }

    private void initViews (){
         levelOneBar = progressView.findViewById(R.id.determinateBar1);
         levelTwoBar = progressView.findViewById(R.id.determinateBar2);
         levelThreeBar = progressView.findViewById(R.id.determinateBar3);
         averageBar = progressView.findViewById(R.id.determinateBar4);
         textViewLevelOne = progressView.findViewById(R.id.leveloneProgressBar);
         textViewLevelTwo = progressView.findViewById(R.id.levelTwoProgressBar);
         textViewLevelThree = progressView.findViewById(R.id.levelThreeProgressBar);
         textViewAverage = progressView.findViewById(R.id.AverProgressBar);
    }

    @SuppressLint("SetTextI18n")
    private  void setDataIntoInterface(){
        ProgressReport progressReport = progressReportList.get(0);
        int levelOneScore =progressReport.getLevelOneScore();
        int levelTwoScore = progressReport.getLevelTwoScore();
        int levelThreeScore = progressReport.getLevelThreeScore();
        int averageScore  = progressReport.getAverageScore();
        levelOneBar.setProgress(levelOneScore);
        levelTwoBar.setProgress(levelTwoScore);
        levelThreeBar.setProgress(levelThreeScore);
        averageBar.setProgress(averageScore);
        textViewLevelOne.setText(levelOneScore+"%");
        textViewLevelTwo.setText(levelTwoScore+"%");
        textViewLevelThree.setText(levelThreeScore+"%");
        textViewAverage.setText(averageScore+"%");

    }
    
}

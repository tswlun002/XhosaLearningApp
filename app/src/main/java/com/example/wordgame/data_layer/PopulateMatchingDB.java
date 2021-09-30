package com.example.wordgame.data_layer;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.example.wordgame.model_layer.Matching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class PopulateMatchingDB extends AsyncTask<Context,Void,Void> {
    private final MatchingDao matchingDao ;
    protected PopulateMatchingDB(WordGameDB wordGameDB){
        matchingDao=wordGameDB.matchingDao();

    }


    @Override
    protected Void doInBackground(Context... contexts) {
        List<String> questions  = new ArrayList<>();
        List<String> answers  = new ArrayList<>();
        List<String> instructions  = new ArrayList<>();

        String data ="";
        try {
            data = getData(questions,answers,instructions,contexts[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }


       String tittle = data.substring(0,data.indexOf(";"));

        int totalmarks  = Integer.parseInt(data.substring(data.indexOf(";")+1).trim());


        String instruction =  instructions.get(0).substring(instructions.get(0).indexOf(";")+1);


        int level =  Integer.parseInt(instructions.get(0).substring(0,instructions.get(0)
               .indexOf(";")).trim());


        for(int i =0; i< questions.size(); i++) {
            matchingDao.insert(new Matching(level, questions.get(i), answers.get(i), tittle, instruction, totalmarks));
        }
        return null;
    }

    private String getData(List<String> questions,List<String> answers,List<String> instructions,
                           Context context) throws IOException {


        String tittleAndMarks = "";
        String lineData = "";
        try {


            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader( context.getResources().getAssets().open("days.txt"),
                    StandardCharsets.UTF_8));
            int count =0;
             while ( (lineData =bufferedReader.readLine() )!=null){
                 if(lineData.trim().length() != 0){
                     if(count ==0) {
                         tittleAndMarks = lineData;
                         count++;
                     }
                     else if(count ==1){
                         instructions.add(lineData);
                         count++;
                     }
                     else {
                         count++;
                         questions.add(lineData.substring(0,lineData.indexOf(";")));
                         answers.add(lineData.substring(lineData.indexOf(";")+1));
                     }
                 }
             }
            bufferedReader.close();

        }catch (Exception e){
            e.printStackTrace();

        }

        return  tittleAndMarks;
    }
}
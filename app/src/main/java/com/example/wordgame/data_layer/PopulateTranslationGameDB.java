package com.example.wordgame.data_layer;

import android.content.Context;
import android.os.AsyncTask;

import com.example.wordgame.model_layer.TranslationGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PopulateTranslationGameDB extends AsyncTask<Context, Void, Void> {
    private final TranslationDao translationDao;

    protected PopulateTranslationGameDB(WordGameDB wordGameDB){
        translationDao =wordGameDB.translationDao();

    }


    @Override
    protected Void doInBackground(Context... contexts) {
        List<String> hints  = new ArrayList<>();
        List<Integer> hintsCounter  = new ArrayList<>();
        List<String> questions  = new ArrayList<>();
        List<String> answers  = new ArrayList<>();
        List<String> instructions  = new ArrayList<>();

        String data ="";
        try {
            data = getData(hints,answers,questions,instructions,contexts[0]);

        } catch (IOException e) {

            e.printStackTrace();
        }

        int totalmarks  = Integer.parseInt(data.trim());


        String instruction =  instructions.get(0).substring(instructions.get(0).indexOf(";")+1);


        int level =  Integer.parseInt(instructions.get(0).substring(0,instructions.get(0)
                .indexOf(";")).trim());


        for(int i =0; i< hints.size(); i++) {
            translationDao.insert(new TranslationGame(level, questions.get(i),
                    answers.get(i),hints.get(i),hintsCounter.get(i),instruction,totalmarks));
        }
        return null;
    }

    private String getData(List<String> figures,List<String> answers,List<String>questions,List<String> instructions,Context context) throws IOException {


        String Marks = "";

        String lineData;
        try {

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(context.getResources().getAssets().open("translationGame.txt"),
                            StandardCharsets.UTF_8));
            int count = 0;
            while ((lineData = bufferedReader.readLine()) != null) {
                if (count == 0) {
                    Marks = lineData;
                    count++;
                } else if (count == 1) {
                    instructions.add(lineData);
                    count++;
                } else {
                    count++;
                    figures.add(lineData.substring(0, lineData.indexOf(";")));
                    questions.add(lineData.substring(lineData.indexOf(";")+1,lineData.indexOf("?")));
                    answers.add(lineData.substring(lineData.indexOf("?") + 1));
                }
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();

        }


        return Marks;
    }



}

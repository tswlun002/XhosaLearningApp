package com.example.wordgame.data_layer;

import android.content.Context;
import android.os.AsyncTask;

import com.example.wordgame.model_layer.TranslationGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PopulateTranslationGameDB extends AsyncTask<Context, Void, Void> {
    private final TranslationDao translationDao;

    protected PopulateTranslationGameDB(WordGameDB wordGameDB){
        translationDao =wordGameDB.translationDao();

    }


    @Override
    protected Void doInBackground(Context... contexts) {
        String[] filenames ={"translation1.txt","translation2.txt","translation3.txt"};
        try {
            for(String filename: filenames)
                getData(contexts[0],filename);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getData(Context context,String filename) throws IOException {


        String Marks = "";

        String lineData;
        try {

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(context.getResources().getAssets().open(filename),
                            StandardCharsets.UTF_8));
            int count = 0;
            int level=1;
            String instruction="";

            while ((lineData = bufferedReader.readLine()) != null) {
                if (count == 0) {
                    Marks = lineData;
                    count++;
                } else if (count == 1) {
                    level =  Integer.parseInt((lineData.substring(0,lineData.indexOf(";"))));
                    instruction =  lineData.substring(lineData.indexOf(";")+1);
                    count++;
                } else {


                    int totalmarks  = Integer.parseInt(Marks.trim());
                    String ans = lineData.substring(lineData.indexOf(";")+1,lineData.indexOf("$"));

                    String hint  = lineData.substring(lineData.indexOf("$")+1);
                     String Q  =   lineData.substring(0, lineData.indexOf(";"));

                    translationDao.insert(new TranslationGame(level, Q,
                            ans,hint,3,instruction,totalmarks));

                    count++;
                }
            }
            bufferedReader.close();

        } catch (Exception e) {
           e.printStackTrace();

        }
        return Marks;
    }



}

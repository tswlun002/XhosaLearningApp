package com.example.wordgame.data_layer;

import android.content.Context;
import android.os.AsyncTask;

import com.example.wordgame.model_layer.TranslationGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @Class populate the translation table from the database
 */
public class PopulateTranslationGameDB extends AsyncTask<Context, Void, Void> {
    /**
     * @serialField  Translation object to prove access from the database
     */
    private final TranslationDao translationDao;


    /**
     * constructor to creat an object for treanslationDao to proved access to the dsatabase with
     * @param wordGameDB used to access translation talbe from the table with.
     */
    protected PopulateTranslationGameDB(WordGameDB wordGameDB){
        translationDao =wordGameDB.translationDao();

    }

    /**
     * used to insert and translation table to the database for all the levels to translations
     * @param contexts to prove context for the translation table
     * @return
     */

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

    /**
     * this function get data from the database for translations play for all the levels of the game
     * @param context  proves context for the database
     * @param filename for the text information for each level of the game
     * @return
     * @throws IOException handles the file not found exceptions for if the function does not find the file
     */
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

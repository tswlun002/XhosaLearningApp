package com.example.wordgame.data_layer;

import android.content.Context;
import android.os.AsyncTask;

import com.example.wordgame.model_layer.TrueFalseGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Class  populate true or false table
 */
public class PopulateTrueFalseDB extends AsyncTask<Context, Void, Void> {
    /**
     * @serialField  for true or false class
     */
    private final TrueFalseDao trueFalseDao ;

    /**
     * constructor to create an object for the true or false class to prove access to the database with
     * @param wordGameDB
     */

    protected PopulateTrueFalseDB(WordGameDB wordGameDB){
        trueFalseDao=wordGameDB.trueFalseDao();

    }

    /**
     * to create and populate the true or false table from the database
     * @param contexts proves context of the database
     * @return
     */
    @Override
    protected Void doInBackground(Context... contexts) {

        String[] filenames ={"truefalse1.txt","truefalse2.txt","truefalse3.txt"};
        for(String filename :filenames) {
            List<String> pictures  = new ArrayList<>();
            List<String> questions  = new ArrayList<>();
            List<String> answers  = new ArrayList<>();
            List<String> instructions  = new ArrayList<>();
            String data = "";
            try {
                data = getData(pictures, answers, questions, instructions, contexts[0], filename);

            } catch (IOException e) {

                e.printStackTrace();
            }

            int totalmarks = Integer.parseInt(data.trim());


            String instruction = instructions.get(0).substring(instructions.get(0).indexOf(";") + 1);


            int level = Integer.parseInt(instructions.get(0).substring(0, instructions.get(0)
                    .indexOf(";")).trim());


            for (int i = 0; i < pictures.size(); i++) {
                trueFalseDao.insert(new TrueFalseGame(level, questions.get(i), pictures.get(i), answers.get(i), instruction, totalmarks));
            }

        }
        return null;
    }

    /**
     *  gets the data from true or false table for user to play the game
     * @param figures array to store figures that will be displayed for the user during the game
     * @param answers array for answers when we want to chess if the users answers are correct
     * @param questions array for questions to access the user
     * @param instructions  for how true or false game is plyaed
     * @param context proved context of the database
     * @param filename  to read the information to store to the database
     * @return the user data and marks for the section of game
     * @throws IOException
     */
    private String getData(List<String> figures,List<String> answers,List<String>questions,
                           List<String> instructions,Context context, String filename) throws IOException {

        String Marks = "";
        String lineData;
        try {

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(context.getResources().getAssets().open(filename),
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
                        questions.add(lineData.substring(lineData.indexOf(";") + 1, lineData.indexOf("?")));
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

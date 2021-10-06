package com.example.wordgame.data_layer;
import android.content.Context;
import android.os.AsyncTask;

import com.example.wordgame.model_layer.Matching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Class  populates the matchingDao on the database table
 */
class PopulateMatchingDB extends AsyncTask<Context,Void,Void> {
    /**
     * @serialField object used to access MatchingDao class
     */
    private final MatchingDao matchingDao ;


    /**
     * this constructor is used to create an object for MathcingDao
     * @param wordGameDB reference from the database to ensure that matchingDao can insert data to the database
     */
    protected PopulateMatchingDB(WordGameDB wordGameDB){
        matchingDao=wordGameDB.matchingDao();

    }

    /**
     * this function populates the database matching questions and answers for all the levels
     * @param contexts proves context to use to access the data from
     * @return null reference
     */
    @Override
    protected Void doInBackground(Context... contexts) {

        String[] filenames ={"matching1.txt","matching2.txt","matching3.txt"};

        for(String filename:filenames) {
            List<String> questions  = new ArrayList<>();
            List<String> answers  = new ArrayList<>();
            List<String> instructions  = new ArrayList<>();
            String data = "";
            try {

                data = getData(questions, answers, instructions, contexts[0],filename);

            } catch (IOException e) {
                e.printStackTrace();
            }


            String tittle = data.substring(0, data.indexOf(";"));

            int totalmarks = Integer.parseInt(data.substring(data.indexOf(";") + 1).trim());


            String instruction = instructions.get(0).substring(instructions.get(0).indexOf(";") + 1);


            int level = Integer.parseInt(instructions.get(0).substring(0, instructions.get(0)
                    .indexOf(";")).trim());


            for (int i = 0; i < questions.size(); i++) {
                matchingDao.insert(new Matching(level, questions.get(i), answers.get(i), tittle, instruction, totalmarks));
            }
        }
        return null;
    }

    /**
     * this function gets the data from the database
     * @param questions array used to store the  questions from the database
     * @param answers array used to store answers from the database
     * @param instructions array used to store instructions on to play each level
     * @param context proves context to access the database
     * @param filename contains the file name that contains all the questions and answers
     * @return returns marks for each question and the tittle for the sections
     * @throws IOException handles a file error from the file name
     */
    private String getData(List<String> questions,List<String> answers,List<String> instructions,
                           Context context,String filename) throws IOException {


        String tittleAndMarks = "";
        String lineData = "";
        try {


            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader( context.getResources().getAssets().open(filename),
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
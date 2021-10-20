package com.example.wordgame.data_layer;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.example.wordgame.model_layer.Learn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @Class  Populates learnDoa
 */
class PopulateLearnDB extends AsyncTask<Context ,Void,Void> {
    /**
     * @serialField  private object to access the learnDao class
     */
    private final LearnDao learnDao ;

    /**
     *  the constructor for the class PopulateLearnBD used to get the material for learnDao
     * @param wordGameDB used to initialise learnDao
     */
    protected PopulateLearnDB(WordGameDB wordGameDB){
        learnDao= wordGameDB.learnDao();

    }
    /**
     * this function is used to read the data from a text file for the learning material to store on the database
     * @param myData  provided  with the array for data from the text file to store from
     * @param fileName the file name of the text file to read from
     * @param context the context of to which you use to store the information to
     */
    private void readFile(ArrayList<String> myData, String fileName,Context context){

        try {
            String currentLine = "";
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(context.getResources().getAssets().open(fileName),
                            StandardCharsets.UTF_8
                    ));

            while ((currentLine = bufferedReader.readLine()) != null) {
                if(currentLine.trim().length()!=0){
                    myData.add(currentLine);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * this function is used to insert the material into the database of the system  for learning
     * @param myData the array that contains the information that will be store to the system
     * @param levelFiles the text files that contains the material for every level for that game type
     * @param level integer for the level the information will be used from from
     * @param section the array of topics for the Content on each of the files
     * @param instructions the instructions for how each user is suppose to play the level for the specific game type
     * @param context Context to which the material will be used from
     */
    public void insertData(ArrayList<String> myData, String[] levelFiles, int level ,
                           String[] section, String instructions,Context context){
        for(int i = 0; i< levelFiles.length ; i++){
            String fileName = levelFiles[i];
            readFile(myData, fileName,context);
            for (String item : myData) learnDao.insert(new Learn(level, section[i], item, instructions));
            myData.clear();
        }

    }

    /**
     * this function is used to populate the database table for learning for all the levels from 1, 2, and 3
     * every array contains material for all the levels in a text file and corrisponding to each topic
     * @param contexts the context which will be used to store the material to
     * @return
     */
    @Override
    protected Void doInBackground(Context... contexts) {
        ArrayList<String> learningMatirial1 = new ArrayList<>();
        ArrayList<String> learningMatirial2 = new ArrayList<>();
        ArrayList<String> learningMatirial3 = new ArrayList<>();

        String instructions = "Read material before attempting game";

       String[] level1Files = {"numbers.txt" , "vowels.txt" , "consonents.txt" , "clicks.txt" , "days.txt" , "seasons.txt"};
        String[] section1 = {" Numbers", " Vowels ", " Consonants",  " Clicks" , " Day of the week", " Seasons"};

        String[] level2Files = {"colours.txt" , "compusPoints.txt" , "driving.txt" , "timeDuration.txt" , "HomeAppliences.txt" , "months.txt" , "money.txt"};
        String[] section2 = {"Colours" , " Campus Point" , " Driving" , " Time Duration" ,  " Home appliances" , " Months of the year", "Money"};

        String[] level3Files = {"DailyConversation.txt" , "xhosaBasicPhrase.txt" , "xhosaToEng.txt","emergency.txt"};
        String[] section3 = {" Daily conversation " , " Xhosa basic phrases", " Xhosa to English" ,"Emergency"};
        insertData(learningMatirial1,level1Files , 1,section1, instructions,contexts[0]);
        insertData(learningMatirial2,level2Files,2,section2,instructions,contexts[0]);
        insertData(learningMatirial3,level3Files,3,section3,instructions,contexts[0]);

        return null;


    }
}
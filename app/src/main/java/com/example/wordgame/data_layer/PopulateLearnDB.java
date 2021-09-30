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


class PopulateLearnDB extends AsyncTask<Void,Void,Void> {
    private final LearnDao learnDao ;
    private Context context;

    protected PopulateLearnDB(WordGameDB wordGameDB, Context context){
        learnDao= wordGameDB.learnDao();
        this.context = context;

    }



    private void readFile(ArrayList<String> myData, String fileName){

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

    public void insertData(ArrayList<String> myData, String[] levelFiles, int level , String[] section, String instructions){
        for(int i = 0; i< levelFiles.length ; i++){
            String fileName = levelFiles[i];
            readFile(myData, fileName);
            for (String item : myData) learnDao.insert(new Learn(level, section[i], item, instructions));
            myData.clear();
        }

    }

    @Override
    protected Void doInBackground(Void... voids) {
        ArrayList<String> learningMatirial1 = new ArrayList<>();
        ArrayList<String> learningMatirial2 = new ArrayList<>();
        ArrayList<String> learningMatirial3 = new ArrayList<>();

        String instructions = "Read material before attempting game";

        String[] level1Files = {"numbers.txt" , "vowels.txt" , "consonents.txt" , "clicks.txt" , "days.txt" , "seasons.txt"};
        String[] section1 = {" Numbers or Izibalo", " Vowels or izikhamiso ", " Consonants or Amaqabane",  " Clicks or Izandi" , " Day of the week or Intsuku zeveki", " seasons"};

        String[] level2Files = {"colours.txt" , "compusPoints.txt" , "driving.txt" , "timeDuration.txt" , "HomeAppliences.txt" , "months.txt" , "money.txt"};
        String[] section2 = {" Colours or imibala" , " Campus Point" , " Driving" , " Time Duration" ,  " Home appliances" , " months of the year Or Inyanga Zonyaka", " money"};

        String[] level3Files = {"DailyConversation.txt" , "xhosaBasicPhrases" , "xhosaToEng.txt"};
        String[] section3 = {" Daily conversation " , " Xhosa to English" , " xhosa basic phrases"};

        String[] content1 = {"0; unothi", "1; enye","2; zimbini"};
        String[] content2 = {"a; like a in hard ", "e; like e in red",
                "i; like ee in seen","o; like a in all",
                "u; like oo in moon"};
        String[] content3 = {"a; like a in hard ", "e; like e in red",
                "i; like ee in seen","o; like a in all",
                "u; like oo in moon"};
        String[] content4 = {"c; Place your tongue at the back of the teeth and suck in, like when expressing annoyance. ",
                "q; Place your tongue on the roof of the mouth and suck in, like imitating a clock's ticking.",
                "x; Place your tongue on your upper right jaw and pull it down, as if urging on a horse."};

        //for (String item : content1) learnDao.insert(new Learn(1, section[0], item, instructions));
        //for (String value : content2) learnDao.insert(new Learn(1, section[1], value, instructions));
        //for (String s : content3) learnDao.insert(new Learn(1, section[2], s, instructions));
        //for (String s : content4) learnDao.insert(new Learn(1, section[3], s, instructions));
        insertData(learningMatirial1,level1Files , 1,section1, instructions);
        insertData(learningMatirial2,level2Files,2,section2,instructions);
        insertData(learningMatirial3,level3Files,3,section3,instructions);

        return null;


    }
}
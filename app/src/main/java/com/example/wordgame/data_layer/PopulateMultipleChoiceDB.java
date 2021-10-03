package com.example.wordgame.data_layer;

import android.content.Context;
import android.os.AsyncTask;
import com.example.wordgame.model_layer.MultipleChoice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
public class PopulateMultipleChoiceDB extends AsyncTask<Context, Void, Void> {
    private final MultipleChoiceDao multipleChoiceDao ;

    protected PopulateMultipleChoiceDB(WordGameDB wordGameDB){ multipleChoiceDao=wordGameDB.multipleChoiceDao();

    }


    @Override
    protected Void doInBackground(Context... contexts) {
        List<String> Data  = new ArrayList<>();
        List<String> instructions  = new ArrayList<>();

        String data ="";
        try {
            data = getData(Data,instructions,contexts[0]);

        } catch (IOException e) {

            e.printStackTrace();
        }

        int totalmarks  = Integer.parseInt(data.trim());
        String instruction =  instructions.get(0).substring(instructions.get(0).indexOf(";")+1);


        int level =  Integer.parseInt(instructions.get(0).substring(0,instructions.get(0)
                .indexOf(";")).trim());

        int size = Data.size();
        for(int i =0; i< size;) {
            String line  = Data.get(i);
            String question = line.substring(0, line.indexOf(";"));
            String answer  = line.substring(line.indexOf(";")+1);
            multipleChoiceDao.insert(new MultipleChoice(level,question,Data.get(i+1),Data.get(i+2),
                    Data.get(i+3),Data.get(i+4),instruction,answer,totalmarks));
            i+=5;
        }
        return null;
    }

    private String getData(List<String> Data,List<String> instructions,Context context) throws IOException {


        String Marks = "";

        String lineData;
        try {

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(context.getResources().getAssets().open("MCQ.txt"),
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
                    Data.add(lineData);
                }
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();

        }


        return Marks;
    }



}


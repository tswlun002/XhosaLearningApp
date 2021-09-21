package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wordgame.data_layer.LearnDao;
import com.example.wordgame.data_layer.MatchingDB;
import com.example.wordgame.data_layer.MatchingDao;
import com.example.wordgame.model_layer.Learn;
import com.example.wordgame.model_layer.Matching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FactoryMatchingDB {
    private final MatchingDao matchingDao;
    private final LiveData<List<Matching>>  questions;
    public FactoryMatchingDB(Application application){
        MatchingDB matchingDB = MatchingDB.getInstanceMatchingDb(application);

        matchingDao = matchingDB.matchingDao();
        questions = matchingDao.getAll();

    }

    public LiveData<List<Matching>> getQuestions() {
        return questions;
    }

    public  void insert(Matching matching){
        new insertHandler(matchingDao).execute(matching);
    }

    public  void update(Matching matching){
        new UpdateHandler(matchingDao).execute(matching);
    }


    private  static class insertHandler extends AsyncTask<Matching,Void,Void>{
        private final MatchingDao matchingDao;

        private  insertHandler(MatchingDao matchingDao){
            this.matchingDao =matchingDao;
        }

        @Override
        protected Void doInBackground(Matching... matchings) {
            matchingDao.insert(matchings[0]);
            return null;
        }
    }
    private  static class UpdateHandler extends AsyncTask<Matching,Void,Void>{
        private final MatchingDao matchingDao;

        private  UpdateHandler(MatchingDao matchingDao){
            this.matchingDao=matchingDao;
        }

        @Override
        protected Void doInBackground(Matching... matchings) {
            matchingDao.update(matchings[0]);
            String filename ="days.txt";
            Scanner scanner;
           List<String> wordMatch = new ArrayList<>();
            try {
                scanner = new Scanner(new File(filename));
                while (scanner.hasNext()){
                    wordMatch.add(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}

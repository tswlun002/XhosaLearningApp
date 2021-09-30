package com.example.wordgame.data_layer;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.wordgame.model_layer.Learn;
import com.example.wordgame.model_layer.Matching;
import com.example.wordgame.model_layer.TrueFalseGame;

@Database(entities = {Learn.class, Matching.class, TrueFalseGame.class}, version = 2, exportSchema = true)
public abstract class WordGameDB extends RoomDatabase {
    public static WordGameDB wordGameDB;
    public abstract LearnDao learnDao();
    public abstract MatchingDao matchingDao();
    public abstract TrueFalseDao trueFalseDao();
     static  int i =0;
     static  Context context1;
    public static synchronized WordGameDB getInstanceLearnDb(Context context){
        context1 = context;

        wordGameDB = Room.databaseBuilder(context.getApplicationContext(),
                WordGameDB.class,"WordGameDatabase")
                .fallbackToDestructiveMigration()
                .addCallback(roomCallBack)
                .build();

        return wordGameDB;
    }


    private  static final RoomDatabase.Callback roomCallBack =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateLearnDB(wordGameDB).execute();
            new PopulateMatchingDB(wordGameDB,context1).execute();
            new  PopulateTrueFalseDB(wordGameDB,context1).execute();


        }

        /*@Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDb(wordGameDB).execute();;
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }*/
    };




}

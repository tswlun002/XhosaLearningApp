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
import com.example.wordgame.model_layer.MultipleChoice;
import com.example.wordgame.model_layer.TranslationGame;
import com.example.wordgame.model_layer.TrueFalseGame;

@Database(entities = {Learn.class, Matching.class, TrueFalseGame.class, MultipleChoice.class, TranslationGame.class}, version = 4, exportSchema = true)
public abstract class WordGameDB extends RoomDatabase {
    public static WordGameDB wordGameDB;
    public abstract LearnDao learnDao();
    public abstract MatchingDao matchingDao();
    public abstract TrueFalseDao trueFalseDao();
    public abstract MultipleChoiceDao multipleChoiceDao();
    public abstract TranslationDao translationDao();
    static Context context1;

    public static synchronized WordGameDB getInstanceWordGameDb(Context context){
        context1 = context;

        wordGameDB = Room.databaseBuilder(context.getApplicationContext(),
                WordGameDB.class,"WordGameDatabase")
                .fallbackToDestructiveMigration()
                .addCallback(roomCallBack)
                .build();

        return wordGameDB;
    }

    /**
     * Populate Database for WordGame project
     */
    private  static final RoomDatabase.Callback roomCallBack =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateLearnDB(wordGameDB).execute(context1);
            new PopulateMatchingDB(wordGameDB).execute(context1);
            new PopulateTrueFalseDB(wordGameDB).execute(context1);
            new PopulateMultipleChoiceDB(wordGameDB).execute(context1);
            new PopulateTranslationGameDB(wordGameDB).execute(context1);


        }

        /*@Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDb(wordGameDB).execute();;
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }*/
    };




}

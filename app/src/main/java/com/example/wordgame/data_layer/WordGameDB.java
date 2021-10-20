package com.example.wordgame.data_layer;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.wordgame.model_layer.Learn;
import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.Matching;
import com.example.wordgame.model_layer.MultipleChoice;
import com.example.wordgame.model_layer.ProgressReport;
import com.example.wordgame.model_layer.TranslationGame;
import com.example.wordgame.model_layer.TrueFalseGame;
import com.example.wordgame.model_layer.User;

import java.security.PublicKey;

/**
 * @Class create a database for all the application
 */
@Database(entities = {Learn.class, Matching.class, TrueFalseGame.class, MultipleChoice.class,
        TranslationGame.class,LevelResults.class, ProgressReport.class, User.class},
        version =10, exportSchema = true)
public abstract class WordGameDB extends RoomDatabase {
    /**
     * @serialField object to access the database with
     */
    public static WordGameDB wordGameDB;

    /**
     * an abstruct class for learnDao to ove ride cna create a database with
     * @return
     */
    public abstract LearnDao learnDao();
    /**
     * an abstruct class for matchingDao to ove ride cna create a database with
     * @return
     */
    public abstract MatchingDao matchingDao();
    /**
     * an abstruct class for trueFalseDao to ove ride cna create a database with
     * @return
     */
    public abstract TrueFalseDao trueFalseDao();
    /**
     * an abstruct class for multipleChoiceDao to ove ride cna create a database with
     * @return
     */
    public abstract MultipleChoiceDao multipleChoiceDao();
    /**
     * an abstruct class for translationDao to ove ride cna create a database with
     * @return
     */
    public abstract TranslationDao translationDao();
    /**
     * an abstruct class for levelResultsDao to ove ride cna create a database with
     * @return
     */
    public abstract LevelResultsDao levelResultsDao();
    /**
     * an abstruct class for progressReportDao to ove ride cna create a database with
     * @return
     */
    public abstract ProgressReportDao progressReportDao();
    /**
     * an abstruct class for userDao to ove ride cna create a database with
     * @return
     */
    public  abstract  UserDao userDao();
    static Context context1;

    /**
     * this constructor is used to build a database of the application
     * @param context
     * @return
     */
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
            new PopulateUser(wordGameDB).execute();
            new PopulateLearnDB(wordGameDB).execute(context1);
            new PopulateMatchingDB(wordGameDB).execute(context1);
            new PopulateTrueFalseDB(wordGameDB).execute(context1);
            new PopulateMultipleChoiceDB(wordGameDB).execute(context1);
            new PopulateTranslationGameDB(wordGameDB).execute(context1);
            new PopulateProgressDB(wordGameDB).execute();
        }
    };




}

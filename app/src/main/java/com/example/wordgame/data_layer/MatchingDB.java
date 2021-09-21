package com.example.wordgame.data_layer;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.wordgame.model_layer.Learn;
import com.example.wordgame.model_layer.Matching;

@Database(entities = {Matching.class}, version = 1, exportSchema = true)
public abstract class MatchingDB extends RoomDatabase {
    public static  MatchingDB matchingDB;
    public abstract MatchingDao matchingDao();
    static  int i =0;
    public static synchronized MatchingDB getInstanceMatchingDb(Context context){


        matchingDB = Room.databaseBuilder(context.getApplicationContext(),
                MatchingDB.class,"WordGameDatabase")
                .fallbackToDestructiveMigration()
                .addCallback(roomCallBack)
                .build();

        return matchingDB;
    }


    private  static final RoomDatabase.Callback roomCallBack =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDb(matchingDB).execute();;


        }

        /*@Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDb(learnDB).execute();;
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }*/
    };

    private  static class PopulateDb extends  AsyncTask<Void,Void,Void> {
        private final MatchingDao matchingDao ;

        private PopulateDb(MatchingDB matchingDB){

            matchingDao=matchingDB.matchingDao();

        }


        @Override
        protected Void doInBackground(Void... voids) {



           /* for (String item : content1) matchingDao.insert(new Learn(1, section[0], item, instructions));
            for (String value : content2) matchingDao.insert(new Learn(1, section[1], value, instructions));
            for (String s : content3) matchingDao.insert(new Learn(1, section[2], s, instructions));
            for (String s : content4) matchingDao.insert(new Learn(1, section[3], s, instructions));*/

            return null;
        }
    }


}

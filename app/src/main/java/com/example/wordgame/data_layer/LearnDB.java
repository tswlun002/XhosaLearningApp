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

@Database(entities = {Learn.class}, version = 1, exportSchema = true)
public abstract class LearnDB extends RoomDatabase {
    public static  LearnDB learnDB;
    public abstract LearnDao learnDao();
     static  int i =0;
    public static synchronized LearnDB getInstanceLearnDb(Context context){


        learnDB = Room.databaseBuilder(context.getApplicationContext(),
                LearnDB.class,"Learn")
                .fallbackToDestructiveMigration()
                .addCallback(roomCallBack)
                .build();

        return learnDB;
    }


    private  static final RoomDatabase.Callback roomCallBack =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDb(learnDB).execute();;


        }

        /*@Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDb(learnDB).execute();;
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }*/
    };

    private  static class PopulateDb extends  AsyncTask<Void,Void,Void> {
       private final LearnDao learnDao ;

       private PopulateDb(LearnDB learnDB){

           learnDao=learnDB.learnDao();

       }


        @Override
        protected Void doInBackground(Void... voids) {

            String instructions = "Read material before attempting game";
            String[] section = {
                    "Numbers Or Izibalo",
                    "Vowels",
                    "Consonants Or Amaqabane",
                    "Clicks Or Izandi"
            };

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

            for (String item : content1) learnDao.insert(new Learn(1, section[0], item, instructions));
            for (String value : content2) learnDao.insert(new Learn(1, section[1], value, instructions));
            for (String s : content3) learnDao.insert(new Learn(1, section[2], s, instructions));
            for (String s : content4) learnDao.insert(new Learn(1, section[3], s, instructions));

            return null;
        }
    }


}

package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wordgame.data_layer.WordGameDB;
import com.example.wordgame.data_layer.LearnDao;
import com.example.wordgame.model_layer.Learn;

import java.util.List;

/**
 * @Class  gets learning material for the user
 */
public class FactoryLearnDB {
    /**
     * @serialField object learnDao to access database
     * @serialField  allNotes to store notes for the user
     * @serialField allNotes for level 1 learning
     * @serialField  allnotes for level 2 learning
     * @serialField  allNotes for level 3 learning
     */
    private final LearnDao learnDao;
    private final LiveData<List<Learn>> allNotes;
    private final LiveData<List<Learn>> allNotesLevel1, allNotesLevel2,allNotesLevel3;
   // private final LiveData<List<Learn>> allNotesLeve2;


    /**
     * get level1 notes from the database
     * get all level 2 notes from the database
     *  get all level 3 notes from the database
     * @param application to prove access to the database
     */
    public FactoryLearnDB(Application application){
        WordGameDB wordGameDB = WordGameDB.getInstanceWordGameDb(application);

        learnDao = wordGameDB.learnDao();
        allNotes = learnDao.getAll();
        allNotesLevel1 = learnDao.level1();
        allNotesLevel2 = learnDao.level2();
        allNotesLevel3 = learnDao.level3();

    }

    /**
     * get all note from the database for every level
     * @return the notes for level 1
     */
    public LiveData<List<Learn>> getAllNotes() {
        return allNotes;
    }

    /**
     * get all the notes for level 1 from the database
     * @return the notes for level 2
     */
    public LiveData<List<Learn>> getAllNotesLevel1() {
        return allNotesLevel1;
    }

    /**
     * get all the notes for the database from level 2
     * @return the notes for level 2 from the database
     *
     */
    public LiveData<List<Learn>> getAllNotesLevel2() {
        return allNotesLevel2;
    }

    /**
     * gets all the notes for level 3 from the database
     * @return the notes from the database of the application
     */
    public LiveData<List<Learn>> getAllNotesLevel3() {
        return allNotesLevel3;
    }

    /**
     * inserts the content to the database of the application
     * @param learn inserts to all the columns on the database
     */
    public  void insert(Learn learn){
          new insertHandler(learnDao).execute(learn);
    }

    /**
     * updates all the information from learning
     * @param learn updates the information from the user
     */
    public  void update(Learn learn){
           new UpdateHandler(learnDao).execute(learn);
    }

    /**
     * extends threads for the database
     */
    private  static class insertHandler extends AsyncTask<Learn,Void,Void>{
        private final LearnDao learnDao;

        private  insertHandler(LearnDao learnDao){
            this.learnDao=learnDao;
        }

        /**]
         * insert into the database all the learning material
         * @param learns insert into the database of the application
         * @return
         */
        @Override
        protected Void doInBackground(Learn... learns) {
            learnDao.insert(learns[0]);
            return null;
        }
    }
    private  static class UpdateHandler extends AsyncTask<Learn,Void,Void>{
        private final LearnDao learnDao;

        private  UpdateHandler(LearnDao learnDao){
            this.learnDao=learnDao;
        }

        /**
         * update the information  from the database of the application
         * @param learns updates information into the database, from learn table
         * @return
         */
        @Override
        protected Void doInBackground(Learn... learns) {
            learnDao.update(learns[0]);
            return null;
        }
    }
}

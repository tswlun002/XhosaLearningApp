package com.example.wordgame.data_layer;
import android.os.AsyncTask;
import com.example.wordgame.model_layer.User;

/**
 * @Class create information for the user
 */
class PopulateUser extends AsyncTask<Void,Void,Void> {

    /**
     * @serialField to create and prove access to the database with
     */
    private final UserDao userDao;

    /**
     * constructor to create and update a table for the user on the database
     * @param wordGameDB used to access the user table to the database with
     */
    protected PopulateUser(WordGameDB wordGameDB){
        userDao =wordGameDB.userDao();

    }

    /**
     * this function is used to insert information to the user table on the database ( user ID and current level)
     * @param voids
     * @return
     */
    @Override
    protected Void doInBackground(Void... voids) {
        userDao.insert(new User(0,1));
        return null;
    }

}
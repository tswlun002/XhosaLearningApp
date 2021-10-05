package com.example.wordgame.data_layer;
import android.os.AsyncTask;
import com.example.wordgame.model_layer.User;

class PopulateUser extends AsyncTask<Void,Void,Void> {
    private final UserDao userDao;
    protected PopulateUser(WordGameDB wordGameDB){
        userDao =wordGameDB.userDao();

    }
    @Override
    protected Void doInBackground(Void... voids) {
        userDao.insert(new User(0,1));
        return null;
    }

}
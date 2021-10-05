package com.example.wordgame.respotory_layer;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wordgame.data_layer.UserDao;
import com.example.wordgame.data_layer.WordGameDB;
import com.example.wordgame.model_layer.User;

import java.util.List;

public class UserRespotory {
    private final UserDao userDao;
    private final LiveData<List<User>> user;
    public UserRespotory(Application application){
        WordGameDB userDB = WordGameDB.getInstanceWordGameDb(application);
        userDao = userDB.userDao();
        user=userDao.getAll();
    }

    public LiveData<List<User>> getUser() {
        return user;
    }

    public  void insert(User user){
        new UserRespotory.insertHandler(userDao).execute(user);
    }

    public  void update(User user){
        new UserRespotory.UpdateHandler(userDao).execute(user);
    }


    private  static class insertHandler extends AsyncTask<User,Void,Void> {
        private final UserDao userDao;

        private  insertHandler(UserDao user){
            this.userDao =user;
        }

        @Override
        protected Void doInBackground(User... user) {
            userDao.insert(user[0]);
            return null;
        }


    }
    private  static class UpdateHandler extends AsyncTask<User,Void,Void>{
        private final UserDao user1;

        private  UpdateHandler(UserDao user){
            this.user1 =user;
        }

        @Override
        protected Void doInBackground(User... user) {
            user1.update(user[0]);


            return null;
        }


    }
}

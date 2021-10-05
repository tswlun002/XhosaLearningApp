package com.example.wordgame.model_layer;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.wordgame.respotory_layer.UserRespotory;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private final UserRespotory userRespotory;
    private final LiveData<List<User>> allMaterial;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRespotory = new UserRespotory(application);
        allMaterial = userRespotory.getUser();
    }

    /**
     * get all material for matching game
     *
     * @return matching material
     */
    public LiveData<List<User>> getGameMaterial() {
        return allMaterial;
    }

    /**
     * insert data of matching
     *
     * @param user is a data being inserted
     */
    public void insert(User user) {
        userRespotory.insert(user);
    }

    /**
     * update data of matching game
     *
     * @param user is the data being updated
     */
    public void update(User user) {
        userRespotory.update(user);
    }
}
package com.matilfa.twentyquestions.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.matilfa.twentyquestions.data.users.User;
import com.matilfa.twentyquestions.data.users.UserDao;

import java.util.List;

public class UserRepository {
    private final Context context;
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Context context) {
        this.context = context;
        userDao = TwentyQuestionsDatabase.getInstance(context).userDao();
        allUsers = userDao.getAll();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void addNewUser(@NonNull String userName) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (userDao.getByName(userName) != null) {
                    throw new RuntimeException(String.format(
                            "The user '%s' already exists in database.%n", userName));
                }
                var user = new User();
                user.name = userName;

                userDao.insertUser(user);
                if (userDao.getByName(userName) == null) {
                    throw new RuntimeException(String.format(
                            "Failed to insert value '%s' into database.%n", userName));
                }
            }
        });

        thread.start();
    }

    public void addNewUser2(@NonNull User user) {
        TwentyQuestionsDatabase.databaseWriteExecutor.execute(() ->
                userDao.insertUser(user)
        );
    }

    public User getUserByName(String name) {
        return null;
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //todo check how to return value from thread...
////            user = userDao.getByName(name);
//            }
//        })

    }
}

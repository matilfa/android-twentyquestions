package com.matilfa.twentyquestions.data.users;

import android.content.Context;

import androidx.annotation.NonNull;

import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class UserRepository {
    private final Context context;
    private UserDao userDao;

    @Inject
    public UserRepository(@ApplicationContext Context context) {
        this.context = context;
        userDao = TwentyQuestionsDatabase.getInstance(context).userDao();
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

//    public void addNewUser(@NonNull String userName) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (userDao.getByName(userName) != null) {
//                    throw new RuntimeException(String.format(
//                            "The user '%s' already exists in database.%n", userName));
//                }
//                var user = new User();
//                user.name = userName;
//
//                userDao.insertUser(user);
//                if (userDao.getByName(userName) == null) {
//                    throw new RuntimeException(String.format(
//                            "Failed to insert value '%s' into database.%n", userName));
//                }
//            }
//        });
//
//        thread.start();
//    }

    public void addNewUser(@NonNull User user) {
                userDao.insertUser(user);
    }

    /**
     * Delete a user from database.
     * @param userToDelete
     * @return true if the an entity has been deleted from the database, false otherwise.
     */
    public boolean deleteUser(@NonNull User userToDelete) {
        if (userToDelete.name == null) {
            throw new IllegalArgumentException("The user to be deleted is missing a name value.");
        }

        if (userDao.getByName(userToDelete.name) == null) {
            throw new IllegalArgumentException("Cannot find user " + userToDelete.name +
                    ". Unable to delete.");
        }

        int rowsUpdated = userDao.deleteUser(userToDelete.name);
        return rowsUpdated > 0;
    }
//
//    public User getUserByName(String name) {
//        return null;
////        Thread thread = new Thread(new Runnable() {
////            @Override
////            public void run() {
////                //todo check how to return value from thread...
//////            user = userDao.getByName(name);
////            }
////        })
//
//    }
}

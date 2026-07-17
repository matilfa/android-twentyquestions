package com.matilfa.twentyquestions.data.users;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.matilfa.twentyquestions.data.sessions.Session;

import java.util.List;

/**
 * Data access object for data operations on {@code User} objects.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE userId = :id")
    User getById(int id);

    @Query("SELECT * FROM user WHERE name LIKE :name")
    User getByName(String name);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertUser(User user);

    @Query("DELETE FROM user WHERE name = :name")
    void deleteUser(String name);
}

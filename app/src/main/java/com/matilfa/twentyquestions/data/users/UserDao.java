package com.matilfa.twentyquestions.data.users;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.matilfa.twentyquestions.data.sessions.Session;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id = :id")
    User getById(int id);

    @Query("SELECT * FROM user WHERE name LIKE :name")
    User getByName(String name);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertUser(User user);

}

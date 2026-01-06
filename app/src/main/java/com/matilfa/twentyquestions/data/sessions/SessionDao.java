package com.matilfa.twentyquestions.data.sessions;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SessionDao {
    @Query("SELECT * FROM session")
    List<Session> getAll();

    @Query("SELECT * FROM session WHERE id = :id")
    Session getById(int id);

    @Query("SELECT * FROM session WHERE name LIKE :name")
    Session getByName(String name);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertSession(Session session);
}

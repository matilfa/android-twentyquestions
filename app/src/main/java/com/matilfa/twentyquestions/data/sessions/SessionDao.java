package com.matilfa.twentyquestions.data.sessions;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

/**
 * Data access object for data operations on {@code Session} objects.
 */
@Dao
public interface SessionDao {
    @Query("SELECT * FROM session")
    List<Session> getAll();

    @Query("SELECT * FROM session WHERE sessionId = :id")
    Session getById(int id);

    @Query("SELECT * FROM session WHERE name LIKE :name")
    Session getByName(String name);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    Long insertSession(Session session);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertAllUserCrossRefs(List<UserSessionCrossRef> crossRefs);

    @Transaction
    @Query("SELECT * FROM session")
    List<SessionWithUsers> getSessionsWithUsers();
}

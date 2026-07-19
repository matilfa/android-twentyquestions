package com.matilfa.twentyquestions.data.sessions;

import androidx.lifecycle.LiveData;
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
    LiveData<List<Session>> getAll();

    @Query("SELECT * FROM session WHERE sessionId = :id")
    LiveData<Session> getById(Long id);

    @Query("SELECT * FROM session WHERE name LIKE :name")
    LiveData<Session> getByName(String name);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    Long insertSession(Session session);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertAllUserCrossRefs(List<UserSessionCrossRef> crossRefs);

    @Transaction
    @Query("SELECT * FROM session")
    List<SessionWithUsers> getSessionsWithUsers();

    @Transaction
    @Query("SELECT * FROM session WHERE sessionId = :id")
    SessionWithAskedQuestions getSessionByIdWithAskedQuestions(Long id);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    Long insertQuestionSessionCrossRef(QuestionSessionCrossRef crossRef);
}

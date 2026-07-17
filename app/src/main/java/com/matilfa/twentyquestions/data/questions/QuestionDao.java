package com.matilfa.twentyquestions.data.questions;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Data access object for data operations on {@code Question} objects.
 */
@Dao
public interface QuestionDao {
    @Query("SELECT * FROM question")
    List<Question> getAll();

    @Query("SELECT * FROM question WHERE questionId = :id")
    Question getById(int id);

    /**
     * Get the total number of questions in the data source.
     * @return The total number of questions
     */
    @Query("SELECT COUNT(questionId) FROM question")
    int getQuestionCount();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertAll(List<Question> questions);


}

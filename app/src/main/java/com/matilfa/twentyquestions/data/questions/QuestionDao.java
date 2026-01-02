package com.matilfa.twentyquestions.data.questions;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM question")
    List<Question> getAll();

    @Query("SELECT * FROM question WHERE id = :id")
    Question getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Question> questions);
}

package com.matilfa.twentyquestions.data;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM question")
    List<Question> getAll();

    @Query("SELECT * FROM question WHERE id = :id")
    Question getById(int id);
}

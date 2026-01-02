package com.matilfa.twentyquestions.data.questions;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Question.class}, version = 1)
public abstract class QuestionDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();
}

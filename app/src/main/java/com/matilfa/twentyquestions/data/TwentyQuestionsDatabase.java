package com.matilfa.twentyquestions.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.matilfa.twentyquestions.data.questions.Question;
import com.matilfa.twentyquestions.data.questions.QuestionDao;

@Database(entities = {Question.class}, version = 1)
public abstract class TwentyQuestionsDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();
}

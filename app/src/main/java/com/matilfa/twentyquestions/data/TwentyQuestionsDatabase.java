package com.matilfa.twentyquestions.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.matilfa.twentyquestions.data.questions.Question;
import com.matilfa.twentyquestions.data.questions.QuestionDao;
import com.matilfa.twentyquestions.data.sessions.Session;
import com.matilfa.twentyquestions.data.sessions.SessionDao;
import com.matilfa.twentyquestions.data.users.User;
import com.matilfa.twentyquestions.data.users.UserDao;

@Database(entities = {Question.class, Session.class, User.class}, version = 1, exportSchema = false)
public abstract class TwentyQuestionsDatabase extends RoomDatabase {

    private static volatile TwentyQuestionsDatabase dbInstance;
    public static final String DATABASE_NAME = "twentyQuestions.db";

    public abstract QuestionDao questionDao();
    public abstract SessionDao sessionDao();
    public abstract UserDao userDao();

    public static TwentyQuestionsDatabase getInstance(final Context context) {
        if (dbInstance == null) {
            synchronized (TwentyQuestionsDatabase.class) {
                if (dbInstance == null) { //??
                    dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                            TwentyQuestionsDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return dbInstance;
    }
}

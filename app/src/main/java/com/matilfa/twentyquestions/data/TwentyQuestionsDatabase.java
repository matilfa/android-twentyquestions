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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Question.class, Session.class, User.class}, version = 1, exportSchema = false)
//todo: fix data migration
public abstract class TwentyQuestionsDatabase extends RoomDatabase {

    private static volatile TwentyQuestionsDatabase dbInstance;
    public static final String DATABASE_NAME = "twentyQuestions.db";
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

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


package com.matilfa.twentyquestions.di;

import android.content.Context;

import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;
import com.matilfa.twentyquestions.data.questions.QuestionDao;
import com.matilfa.twentyquestions.data.sessions.SessionDao;
import com.matilfa.twentyquestions.data.users.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Provides
    @Singleton
    public TwentyQuestionsDatabase provideDatabase(@ApplicationContext Context context) {
        // Hilt calls your existing code here to reuse your singleton instance safely
        return TwentyQuestionsDatabase.getInstance(context);
    }

    @Provides
    public SessionDao provideSessionDao(TwentyQuestionsDatabase db) {
        return db.sessionDao();
    }

    @Provides
    public QuestionDao provideQuestionDao(TwentyQuestionsDatabase db) {
        return db.questionDao();
    }

    @Provides
    public UserDao provideUserDao(TwentyQuestionsDatabase db) {
        return db.userDao();
    }
}

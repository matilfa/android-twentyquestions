package com.matilfa.twentyquestions.data.questions;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.lifecycle.LiveData;

import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;
import com.matilfa.twentyquestions.data.sessions.SessionDao;
import com.matilfa.twentyquestions.data.users.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class QuestionsRepository {
    private final Context context;
    private QuestionDao questionDao;
    private SessionDao sessionDao;
    private UserDao userDao;
    private LiveData<List<Question>> questions;


    @Inject
    public QuestionsRepository(@ApplicationContext Context context) {
        this.context = context;
    }

    public void initDatabase() {
        if (questionDao == null) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    var db = TwentyQuestionsDatabase.getInstance(context.getApplicationContext());

                    questionDao = db.questionDao();
                    sessionDao = db.sessionDao();
                    userDao = db.userDao();

                    if (questionDao.getAll().getValue().isEmpty()) {
                        populateQuestionsList();
                        questionDao.insertAll(questions.getValue());
                    }
                }
            });

            thread.start();
        }
    }

    private void populateQuestionsList() {
        AssetManager am = context.getAssets();
        try {
            try (InputStream is = am.open("questions-data.txt");//Todo fix hard-coded filename
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line = br.readLine();
                while (line != null) {
                    var question = new Question();

                    question.text = line;
                    questions.getValue().add(question);

                    line = br.readLine();

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public QuestionDao getQuestionRepoDao() {
        return questionDao;
    }

    public LiveData<List<Question>> getQuestions() {
        return questions;
    }

}

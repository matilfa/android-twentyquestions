package com.matilfa.twentyquestions.data.questions;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;
import com.matilfa.twentyquestions.data.sessions.SessionDao;
import com.matilfa.twentyquestions.data.users.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    private MutableLiveData<List<Question>> questions;


    @Inject
    public QuestionsRepository(@ApplicationContext Context context, QuestionDao questionDao, SessionDao sessionDao, UserDao userDao) {
        this.context = context;
        this.questionDao = questionDao;
        this.sessionDao = sessionDao;
        this.userDao = userDao;
    }

    public void initDatabase(){
        TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {
            if (questionDao == null) {
                System.out.println("null!!");
            }
            if (questionDao.getQuestionCount() == 0) {
                List<Question> questionsToAdd = extractQuestionsFromFile();
                questionDao.insertAll(questionsToAdd);

                questions.postValue(questionsToAdd);
            }
            else {
                questions.postValue(questionDao.getAll());
            }
        });
    }

//    public void initDb() {
//        if (questionDao == null) {
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                    var db = TwentyQuestionsDatabase.getInstance(context.getApplicationContext());
//
//                    questionDao = db.questionDao();
//                    sessionDao = db.sessionDao();
//                    userDao = db.userDao();
//
//                    if (!questionDao.getAll().isInitialized() || questionDao.getAll().getValue().isEmpty()) {
//                        questions = new MutableLiveData<List<Question>>();
//                        extractQuestionsFromFile();
//                        questionDao.insertAll(questions.getValue());
//                    }
//                }
//            });
//
//            thread.start();
//        }
//    }

    private List<Question> extractQuestionsFromFile() {
        AssetManager am = context.getAssets();
        List<Question> questionsToAdd = new ArrayList<>();
        int questionCount = 1;
        try {
            try (InputStream is = am.open("questions-data.txt");//Todo fix hard-coded filename
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line = br.readLine();
                while (line != null) {
                    var question = new Question();

                    question.text = line;
                    question.questionNumber = questionCount;
                    questionsToAdd.add(question);

                    line = br.readLine();
                    questionCount++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return questionsToAdd;
    }

    public QuestionDao getQuestionRepoDao() {
        return questionDao;
    }

    public LiveData<List<Question>> getQuestions() {
        if (questions.getValue() == null || questions.getValue().isEmpty()) {
            initDatabase();
        }
        return questions;
    }

}

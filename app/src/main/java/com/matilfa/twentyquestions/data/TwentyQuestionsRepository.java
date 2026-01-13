package com.matilfa.twentyquestions.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.TextView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.questions.Question;
import com.matilfa.twentyquestions.data.questions.QuestionDao;
import com.matilfa.twentyquestions.data.sessions.SessionDao;
import com.matilfa.twentyquestions.data.users.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TwentyQuestionsRepository {
    private final Context context;
    private QuestionDao questionDao;
    private SessionDao sessionDao;
    private UserDao userDao;
    private final List<Question> questions = new ArrayList<>();


    public TwentyQuestionsRepository(Context context) {
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

                    if (questionDao.getAll().isEmpty()) {
                        populateQuestionsList();
                        questionDao.insertAll(questions);
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
                    questions.add(question);

                    line = br.readLine();

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public QuestionDao getQuestionRepoDao(){
        return questionDao;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

}

package com.matilfa.twentyquestions.data.questions;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.TextView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;
import com.matilfa.twentyquestions.data.sessions.SessionDao;
import com.matilfa.twentyquestions.data.users.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TwentyQuestionsRepository {
    private final Context context;
    private TwentyQuestionsDatabase db;
    private QuestionDao questionDao;
    private SessionDao sessionDao;
    private UserDao userDao;
    private final List<Question> questions = new ArrayList<>();

    private final TextView questionText;

    public TwentyQuestionsRepository(Context context, TextView questionText) {
        this.context = context;
        this.questionText = questionText;
    }

    public void setup() {
        if (questionDao == null || questions.isEmpty()) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    db = TwentyQuestionsDatabase.getInstance(context.getApplicationContext());

                    questionDao = db.questionDao();
                    sessionDao = db.sessionDao();
                    userDao = db.userDao();

                    if (questions.isEmpty()) {
                        populateQuestionsList();
                        questionDao.insertAll(questions);
                    }
                }
            });

            thread.start();
        }
    }

    public void generateRandomQuestion() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int randomNo = ThreadLocalRandom
                        .current()
                        .nextInt(0, questions.size());

                var question = questionDao.getById(randomNo);

                questionText.post(() ->
                        questionText.setText(context.getResources().getString(
                                R.string.displayed_question,
                                question.id,
                                question.text)));
            }
        }).start();

    }

    private void populateQuestionsList() {
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open("questions-data.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            while (line != null) {
                var question = new Question();

                question.text = line;
                questions.add(question);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package com.matilfa.twentyquestions.data.questions;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.TextView;

import androidx.room.Room;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionRepository {
    private Context context;
    private TwentyQuestionsDatabase db;
    private QuestionDao questionDao;
    private List<Question> questions = new ArrayList<>();

    private TextView questionText;

    public QuestionRepository(Context context, TextView questionText) {
        this.context = context;
        this.questionText = questionText;
    }

    public void setup() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                populateQuestionsList();

                db = Room.databaseBuilder(context.getApplicationContext(),
                        TwentyQuestionsDatabase.class, "twentyQuestions.db").build();

                questionDao = db.questionDao();
                questionDao.insertAll(questions);
            }
        });

        thread.start();
//        thread.join(1000);

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

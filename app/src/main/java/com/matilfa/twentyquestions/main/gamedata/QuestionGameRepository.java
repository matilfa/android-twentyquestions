package com.matilfa.twentyquestions.main.gamedata;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;
import com.matilfa.twentyquestions.data.TwentyQuestionsRepository;
import com.matilfa.twentyquestions.data.questions.Question;
import com.matilfa.twentyquestions.data.questions.QuestionDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionGameRepository {
    private final Context context;
    private final TextView questionText;
    private final QuestionDao questionDao;

    public QuestionGameRepository(@NonNull Context context,
                                  @NonNull TextView questionText) {
        this.context = context;
        this.questionText = questionText;

        questionDao = TwentyQuestionsDatabase.getInstance(context).questionDao();

    }

    public void generateRandomQuestion() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int randomNo = ThreadLocalRandom
                        .current()
                        .nextInt(0, questionDao.getQuestionCount() + 1);

                var question = questionDao.getById(randomNo);

                questionText.post(() ->
                        questionText.setText(context.getResources().getString(
                                R.string.displayed_question,
                                question.id, //todo fix a field for question number, not use id
                                question.text)));
            }
        }).start();

    }
}

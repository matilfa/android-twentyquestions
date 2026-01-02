package com.matilfa.twentyquestions.main;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.Question;
import com.matilfa.twentyquestions.data.QuestionDao;
import com.matilfa.twentyquestions.data.QuestionDatabase;
import com.matilfa.twentyquestions.data.QuestionRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    private QuestionRepository questionRepository;
    private QuestionDatabase db;
    private QuestionDao questionDao;
    private List<Question> questions = new ArrayList<>();

    private List<String> allQuestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        try {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);


                questionRepository = new QuestionRepository(
                        this, findViewById(R.id.questionText));
                questionRepository.setup();

                Button nextButton = findViewById(R.id.nextQuestionButton);

                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        questionRepository.generateRandomQuestion();

                    }
                });


                return insets;
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void populateQuestionsList() {
        AssetManager am = this.getAssets();
        try {
            InputStream is = am.open("questions-data.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            while (line != null) {
                allQuestions.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String q :
                allQuestions) {
            var question = new Question();
            question.text = q;
            questions.add(question);
        }
    }

    private String generateRandomQuestion() {
        int randomNo = ThreadLocalRandom
                .current()
                .nextInt(0, allQuestions.size());

        return allQuestions.get(randomNo);
    }

}

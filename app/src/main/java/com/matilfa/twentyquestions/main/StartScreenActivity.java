package com.matilfa.twentyquestions.main;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;
import com.matilfa.twentyquestions.data.TwentyQuestionsRepository;
import com.matilfa.twentyquestions.session.viewmodel.SessionViewModel;

public class StartScreenActivity extends AppCompatActivity {
//    private SessionViewModel sessionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start_screen);

        var twentyQuestionsRepository = new TwentyQuestionsRepository(this);
        twentyQuestionsRepository.initDatabase();

//        sessionViewModel = new ViewModelProvider(this).get(SessionViewModel.class);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.start_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);



            return insets;
        });
    }
}
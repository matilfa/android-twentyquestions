package com.matilfa.twentyquestions.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.questions.TwentyQuestionsRepository;


public class MainGameFragment extends Fragment {
    private TwentyQuestionsRepository twentyQuestionsRepository;

    public MainGameFragment() {
        super((R.layout.fragment_main_game));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        twentyQuestionsRepository = new TwentyQuestionsRepository(
                getContext(), getActivity().findViewById(R.id.questionText));
        twentyQuestionsRepository.setup();

        Button nextButton = getActivity().findViewById(R.id.nextQuestionButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twentyQuestionsRepository.generateRandomQuestion();
            }
        });

    }
}
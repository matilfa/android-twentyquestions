package com.matilfa.twentyquestions.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.TwentyQuestionsRepository;
import com.matilfa.twentyquestions.main.gamedata.QuestionGameRepository;

/**
 * Fragment for the main part of the game where you get the random questions.
 */
public class MainGameFragment extends Fragment {
    private QuestionGameRepository questionGameRepository;

    public MainGameFragment() {
        super((R.layout.fragment_main_game));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        var questionGameRepository = new QuestionGameRepository(getActivity().getApplicationContext(),
                getActivity().findViewById(R.id.questionText));

        Button nextButton = getActivity().findViewById(R.id.nextQuestionButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionGameRepository.generateRandomQuestion();
            }
        });

    }
}
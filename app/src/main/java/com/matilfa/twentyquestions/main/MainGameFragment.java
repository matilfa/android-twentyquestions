package com.matilfa.twentyquestions.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.questions.Question;
import com.matilfa.twentyquestions.main.gamedata.MainGameViewModel;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Fragment for the main part of the game where you get the random questions.
 */
@AndroidEntryPoint
public class MainGameFragment extends Fragment {
    private MainGameViewModel viewModel;

    public MainGameFragment() {
        super((R.layout.fragment_main_game));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MainGameViewModel.class);
//        var questionGameRepository = new QuestionGameRepository(getActivity().getApplicationContext(),
//                getActivity().findViewById(R.id.questionText));
        Long sessionId = getArguments().getLong("sessionId");
        if (sessionId > 0) {
            viewModel.setActiveSession(sessionId);
        }

        Button nextButton = getActivity().findViewById(R.id.nextQuestionButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = getActivity().findViewById(R.id.questionText);
                Question question = viewModel.generateRandomQuestion();

                tv.setText(question.questionNumber + ". " + question.text); //Todo: Fix resource string
                viewModel.registerAskedQuestion(question);
            }
        });

    }
}
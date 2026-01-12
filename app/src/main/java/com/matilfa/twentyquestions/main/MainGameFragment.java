package com.matilfa.twentyquestions.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.questions.QuestionRepository;


public class MainGameFragment extends Fragment {
    private QuestionRepository questionRepository;

    public MainGameFragment() {
        super((R.layout.fragment_main_game));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        questionRepository = new QuestionRepository(
                getContext(), getActivity().findViewById(R.id.questionText));
        questionRepository.setup();

        Button nextButton = getActivity().findViewById(R.id.nextQuestionButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionRepository.generateRandomQuestion();
            }
        });

    }
}
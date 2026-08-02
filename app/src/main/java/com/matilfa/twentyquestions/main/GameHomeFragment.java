package com.matilfa.twentyquestions.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.questions.Question;
import com.matilfa.twentyquestions.main.gamedata.MainGameViewModel;


public class GameHomeFragment extends Fragment {
    private MainGameViewModel viewModel;

    public GameHomeFragment() {
        super(R.layout.fragment_game_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainGameFragment mainGameFragment = (MainGameFragment) requireParentFragment().requireParentFragment();
        viewModel = new ViewModelProvider(mainGameFragment).get(MainGameViewModel.class);


        Button nextButton = view.findViewById(R.id.nextQuestionButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = view.findViewById(R.id.questionText);
                Question question = viewModel.generateRandomQuestion();

                tv.setText(question.questionNumber + ". " + question.text); //Todo: Fix resource string
                viewModel.registerAskedQuestion(question);
            }
        });
    }
}
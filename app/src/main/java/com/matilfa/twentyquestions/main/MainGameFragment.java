package com.matilfa.twentyquestions.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
//
//        NavBackStackEntry navBackStackEntry = NavHostFragment.findNavController(this)
//                .getBackStackEntry(R.id.game_nav_graph);

        viewModel = new ViewModelProvider(this).get(MainGameViewModel.class);
        viewModel.getAllQuestions().observe(getViewLifecycleOwner(), allQuestions -> {
            Long sessionId = getArguments().getLong("sessionId");
            if (sessionId > 0) {
                viewModel.setActiveSession(sessionId);
            } else {
                viewModel.setupNonSessionMode();
            }

        });

//        Button nextButton = getActivity().findViewById(R.id.nextQuestionButton);
//
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView tv = getActivity().findViewById(R.id.questionText);
//                Question question = viewModel.generateRandomQuestion();
//
//                tv.setText(question.questionNumber + ". " + question.text); //Todo: Fix resource string
//                viewModel.registerAskedQuestion(question);
//            }
//        });

        NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_in_game);

        if (navHostFragment != null) {
            NavController innerNavController = navHostFragment.getNavController();

            BottomNavigationView bottomNavigation = view.findViewById(R.id.bottomNavigationMainGame);
            NavigationUI.setupWithNavController(bottomNavigation, innerNavController);
        }
        else {
            throw new RuntimeException("Could not find nested navhost fragment");
        }

    }
}
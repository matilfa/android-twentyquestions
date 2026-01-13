package com.matilfa.twentyquestions.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.View;
import android.widget.Button;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.TwentyQuestionsRepository;


public class StartScreenFragment extends Fragment {

    public StartScreenFragment() {
        super(R.layout.fragment_start_screen);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        Button quickStartButton = getActivity().findViewById(R.id.quickStartButton);
        quickStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_startScreenFragment_to_mainGameFragment);
            }
        });

        Button sessionModeButton = getActivity().findViewById(R.id.sessionModeButton);
        sessionModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_startScreenFragment_to_topSessionFragment2);
            }
        });
    }
}
package com.matilfa.twentyquestions.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.main.gamedata.MainGameViewModel;
import com.matilfa.twentyquestions.main.recyclerview.HistoryListAdapter;


public class GameHistoryFragment extends Fragment {
    private MainGameViewModel viewModel;

    public GameHistoryFragment() {
        super(R.layout.fragment_game_history);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainGameFragment mainGameFragment = (MainGameFragment) requireParentFragment().requireParentFragment();
        viewModel = new ViewModelProvider(mainGameFragment).get(MainGameViewModel.class);

        HistoryListAdapter historyListAdapter = new HistoryListAdapter();

        RecyclerView recyclerView = view.findViewById(R.id.game_members_recyclerView);
        recyclerView.setAdapter(historyListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getQuestionsAsked().observe(getViewLifecycleOwner(), prevQuestions -> {
            if (prevQuestions != null) {
                historyListAdapter.setAskedQuestions(prevQuestions);
            }
        });
    }
}
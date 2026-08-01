package com.matilfa.twentyquestions.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.main.gamedata.MainGameViewModel;


public class GameMembersFragment extends Fragment {
    private MainGameViewModel viewModel;

    public GameMembersFragment() {
        super(R.layout.fragment_game_members);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainGameFragment mainGameFragment = (MainGameFragment) requireParentFragment().requireParentFragment();
        viewModel = new ViewModelProvider(mainGameFragment).get(MainGameViewModel.class);

    }
}
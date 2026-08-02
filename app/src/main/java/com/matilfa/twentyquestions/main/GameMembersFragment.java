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
import com.matilfa.twentyquestions.main.recyclerview.MembersListAdapter;


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

        MembersListAdapter membersListAdapter = new MembersListAdapter();

        RecyclerView recyclerView = view.findViewById(R.id.game_members_recyclerView);
        recyclerView.setAdapter(membersListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        membersListAdapter.setUsersInSession(viewModel.getUsersInSession().getValue());

        viewModel.getUsersInSession().observe(getViewLifecycleOwner(), users -> {
            if (users != null) {
                membersListAdapter.setUsersInSession(users);
            }
        });
    }
}
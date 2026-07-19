package com.matilfa.twentyquestions.session;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.session.recyclerview.SessionListAdapter;
import com.matilfa.twentyquestions.session.viewmodel.LoadSessionViewModel;
import com.matilfa.twentyquestions.session.viewmodel.UserListViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoadSessionFragment extends Fragment {
    private LoadSessionViewModel viewModel;
    private SessionListAdapter sessionListAdapter;

    public LoadSessionFragment() {
        super(R.layout.fragment_load_session);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoadSessionViewModel.class);

        sessionListAdapter = new SessionListAdapter(session -> {
            Long sessionId = session.sessionId;
            var action = LoadSessionFragmentDirections.actionLoadSessionFragmentToMainGameFragment(sessionId);

            NavController navController = Navigation.findNavController(view);
            navController.navigate(action);
        });

        RecyclerView sessionRecyclerView = getActivity().findViewById(R.id.sessionlist_recyclerview);
        sessionRecyclerView.setAdapter(sessionListAdapter);
        sessionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getAllSessions().observe(getViewLifecycleOwner(), sessions -> {
            if (sessions != null) {
                sessionListAdapter.setSessions(sessions);
            }
        });
    }
}
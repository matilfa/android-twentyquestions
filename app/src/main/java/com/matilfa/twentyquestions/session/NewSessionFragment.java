package com.matilfa.twentyquestions.session;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.users.User;
import com.matilfa.twentyquestions.session.viewmodel.UserListViewModel;
import com.matilfa.twentyquestions.session.views.UserListAdapter;

import java.util.concurrent.ThreadLocalRandom;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NewSessionFragment extends Fragment {
    private UserListViewModel userListViewModel;

    private UserListAdapter adapter;

    public NewSessionFragment() {
        super(R.layout.fragment_new_session);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        RecyclerView recyclerView = getActivity().findViewById(R.id.userlist_recyclerview);
//        final UserListAdapter adapter = new UserListAdapter(new UserListAdapter.UserDiff());
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        userViewModel = new ViewModelProvider(requireActivity()).get(SessionViewModel.class);
//        sessionViewModel.getAllUsers().observe(getActivity(), users ->
//                adapter.submitList(users)
//        );

//        userListViewModel = new ViewModelProvider(this, ViewModelProvider.Factory)
        adapter = new UserListAdapter();

        RecyclerView recyclerView = getActivity().findViewById(R.id.userlist_recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userListViewModel = new ViewModelProvider(this).get(UserListViewModel.class);
        userListViewModel.getAllUsers().observe(getViewLifecycleOwner(), allUsers -> {
            if (allUsers != null) {
                adapter.setAllUsers(allUsers);
            }
        });

        Button createNewPlayerButton = getActivity().findViewById(R.id.createNewPlayerButton); //Todo try catch?


        createNewPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var dialogFragment = new CreateUserDialogFragment();
                dialogFragment.show(getParentFragmentManager(), null);

//                Navigation.findNavController(view).navigate(R.id.action_newSessionFragment_to_createUserDialogFragment);
            }
        });
    }



}
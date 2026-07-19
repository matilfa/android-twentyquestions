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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.session.viewmodel.UserListViewModel;
import com.matilfa.twentyquestions.session.recyclerview.UserListAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NewSessionFragment extends Fragment {
    private UserListViewModel userListViewModel;

    private UserListAdapter userListAdapter;
    private UserListAdapter selectedUserListAdapter;

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

        userListViewModel = new ViewModelProvider(this).get(UserListViewModel.class);

        //todo: Fix bad code below, (temporary solution)
        userListAdapter = new UserListAdapter(user -> {
            userListViewModel.addSelectedUser(user);
        });
        selectedUserListAdapter = new UserListAdapter(user -> {
            userListViewModel.removeSelectedUser(user);
            Toast.makeText(getContext(), "User " + user.name + " was removed!! :(", Toast.LENGTH_SHORT).show();
        });

        RecyclerView userListRecyclerView = getActivity().findViewById(R.id.userlist_recyclerview);
        userListRecyclerView.setAdapter(userListAdapter);
        userListAdapter.setHasAddButton(true);
        userListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerView selectedUserListRecyclerView = getActivity().findViewById(R.id.selected_users_list_recyclerview);
        selectedUserListRecyclerView.setAdapter(selectedUserListAdapter);
        selectedUserListAdapter.setHasAddButton(false);
        selectedUserListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        userListViewModel.getAllUsers().observe(getViewLifecycleOwner(), allUsers -> {
            if (allUsers != null) {
                userListAdapter.setUsers(allUsers);
            }
        });

        userListViewModel.getSelectedUsers().observe(getViewLifecycleOwner(), selectedUsers -> {
            if (selectedUsers != null) {
                selectedUserListAdapter.setUsers(selectedUsers);
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

        Button startButton = getActivity().findViewById((R.id.startNewSessionButton));
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = getActivity().findViewById(R.id.inputSessionName);
                String sessionName = editText.getText().toString();
                boolean success = userListViewModel.saveNewSession(sessionName);

                if (success) {
                    long sessionId = userListViewModel.getCreatedSession().sessionId;
                    var action = NewSessionFragmentDirections.actionStartNewSession(sessionId);

                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(action);
                }
                else {
                    Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show(); //todo: fix proper error handling
                }

            }
        });

    }


}
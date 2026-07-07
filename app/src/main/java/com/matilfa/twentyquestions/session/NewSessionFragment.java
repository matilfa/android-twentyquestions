package com.matilfa.twentyquestions.session;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.users.User;
import com.matilfa.twentyquestions.session.viewmodel.UserListViewModel;

import java.util.concurrent.ThreadLocalRandom;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NewSessionFragment extends Fragment {
    private UserListViewModel userListViewModel;

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
        userListViewModel.getAllUsers().observe(getViewLifecycleOwner(), allUsers -> {

            for (User user : allUsers) {
                displayUser(user);
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

    private void displayUser(User user) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                TextView textView = getActivity().findViewById(R.id.testUserListTv);
                var newText = textView.getText() + "\n" + user.name;

                textView.post(() ->
                        textView.setText(newText));
            }
        }).start();
    }

}
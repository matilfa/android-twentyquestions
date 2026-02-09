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

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.session.viewmodel.SessionViewModel;
import com.matilfa.twentyquestions.session.views.UserListAdapter;

public class NewSessionFragment extends Fragment {
    private SessionViewModel sessionViewModel;

    public NewSessionFragment() {
        super(R.layout.fragment_new_session);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getActivity().findViewById(R.id.userlist_recyclerview);
        final UserListAdapter adapter = new UserListAdapter(new UserListAdapter.UserDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        sessionViewModel = new ViewModelProvider(requireActivity()).get(SessionViewModel.class);
        sessionViewModel.getAllUsers().observe(getActivity(), users ->
                adapter.submitList(users)
        );


        Button createNewPlayerButton = getActivity().findViewById(R.id.createNewPlayerButton); //Todo try catch?


        createNewPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var dialogFragment = new CreateUserDialogFragment();
                dialogFragment.show(getParentFragmentManager(), null);
            }
        });
    }

}
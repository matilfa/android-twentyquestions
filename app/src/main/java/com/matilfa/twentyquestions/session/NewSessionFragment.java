package com.matilfa.twentyquestions.session;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.session.models.SessionViewModel;

public class NewSessionFragment extends Fragment {
    private SessionViewModel viewModel;
    private CreateUserDialogFragment dialogFragment;

    public NewSessionFragment() {
        super(R.layout.fragment_new_session);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_new_session, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SessionViewModel.class);

        Button createNewPlayerButton = getActivity().findViewById(R.id.createNewPlayerButton); //Todo try catch?

        createNewPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment = new CreateUserDialogFragment();
                dialogFragment.show(getParentFragmentManager(), null);
            }
        });
    }

}
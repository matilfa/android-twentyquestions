package com.matilfa.twentyquestions.session;

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


public class TopSessionFragment extends Fragment {

    private SessionViewModel viewModel;

    public TopSessionFragment() {
        super(R.layout.fragment_session_top);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_session_top, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SessionViewModel.class);

        Button loadSessionButton = view.findViewById(R.id.loadSessionButton);
        Button newSessionButton = view.findViewById(R.id.newSessionButton);

        loadSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setNextFragment(
//                        getParentFragmentManager().findFragmentByTag("loadSessionFrag")
                        new LoadSessionFragment()
                );
            }
        });

        newSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setNextFragment(
//                        getParentFragmentManager().findFragmentByTag("loadSessionFrag")
                        new NewSessionFragment()
                );
            }
        });

    }
}
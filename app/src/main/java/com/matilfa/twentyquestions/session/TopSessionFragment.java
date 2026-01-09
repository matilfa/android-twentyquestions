package com.matilfa.twentyquestions.session;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
//        viewModel = new ViewModelProvider(requireActivity()).get(SessionViewModel.class);

        NavHostFragment navHostFragment = (NavHostFragment) getParentFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_session);

        NavController navController = navHostFragment.getNavController();

                Button loadSessionButton = view.findViewById(R.id.loadSessionButton);
        Button newSessionButton = view.findViewById(R.id.newSessionButton);

        loadSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_topSessionFragment2_to_loadSessionFragment);

//                viewModel.setNextFragment(
////                        getParentFragmentManager().findFragmentByTag("loadSessionFrag")
//                        new LoadSessionFragment()
//                );
            }
        });

        newSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "hej", Toast.LENGTH_SHORT).show();
//                viewModel.setNextFragment(
////                        getParentFragmentManager().findFragmentByTag("loadSessionFrag")
//                        new NewSessionFragment()
//                );
            }
        });

    }
}
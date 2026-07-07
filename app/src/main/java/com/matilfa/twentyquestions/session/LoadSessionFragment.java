package com.matilfa.twentyquestions.session;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.session.viewmodel.UserListViewModel;


public class LoadSessionFragment extends Fragment {
    private UserListViewModel viewModel;

    public LoadSessionFragment() {
        super(R.layout.fragment_load_session);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        return inflater.inflate(R.layout.fragment_load_session, container, false);
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        viewModel = new ViewModelProvider(requireActivity()).get(SessionViewModel.class);
    }
}
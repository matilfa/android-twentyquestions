package com.matilfa.twentyquestions.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.main.gamedata.MainGameViewModel;
import com.matilfa.twentyquestions.settings.recyclerview.EntityListAdapter;
import com.matilfa.twentyquestions.settings.viewmodel.DataEditViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DataEditFragment extends Fragment {
    private DataEditViewModel viewModel;

    public DataEditFragment() {
        super(R.layout.fragment_data_edit);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(DataEditViewModel.class);
        viewModel.initEntityList(DataEditFragmentArgs.fromBundle(getArguments()).getSettingOption());

        EntityListAdapter entityListAdapter = new EntityListAdapter(entityToEdit -> {
            Toast.makeText(getActivity(), "Edited entity", Toast.LENGTH_SHORT).show();
        }, entityToDelete -> {
            Toast.makeText(getActivity(), "Deleted entity", Toast.LENGTH_SHORT).show();

        });

        RecyclerView entityListRecyclerView = view.findViewById(R.id.entitylist_recyclerview);
        entityListRecyclerView.setAdapter(entityListAdapter);
        entityListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getAllSessions().observe(getViewLifecycleOwner(), sessions -> {
            if (sessions != null) {
                entityListAdapter.setEntities(sessions);
            }
        });

        viewModel.getAllUsers().observe(getViewLifecycleOwner(), users -> {
            if (users != null) {
                entityListAdapter.setEntities(users);
            }
        });
    }
}
package com.matilfa.twentyquestions.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.settings.recyclerview.SettingsListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {
    private final List<SettingOption> settingOptions = new ArrayList<>(List.of(
            SettingOption.EDIT_SESSION,
            SettingOption.EDIT_USER));

    public SettingsFragment() {
        super(R.layout.fragment_settings);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SettingsListAdapter settingsListAdapter = new SettingsListAdapter(settingOption ->  {
            Toast.makeText(getActivity(), "Hej", Toast.LENGTH_SHORT).show();
        });

        RecyclerView settingsRecyclerView = view.findViewById(R.id.settings_recyclerview);
        settingsRecyclerView.setAdapter(settingsListAdapter);
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        settingsListAdapter.setSettingOptions(settingOptions);
    }
}
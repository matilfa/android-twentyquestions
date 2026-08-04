package com.matilfa.twentyquestions.settings.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.session.recyclerview.SessionListItemViewHolder;
import com.matilfa.twentyquestions.settings.SettingOption;

import java.util.ArrayList;
import java.util.List;

public class SettingsListAdapter extends RecyclerView.Adapter<SettingsItemViewHolder> {
    private OnSettingSelectedListener selectedListener;
    private List<SettingOption> settingOptions = new ArrayList<>();

    public SettingsListAdapter(OnSettingSelectedListener selectedListener) {
        this.selectedListener = selectedListener;
    }

    public void setSettingOptions(List<SettingOption> settingOptions) {
        this.settingOptions = settingOptions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SettingsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.settings_list_item, parent, false);

        return new SettingsItemViewHolder(view, selectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsItemViewHolder holder, int position) {
        holder.getSettingText().setText(settingOptions.get(position).toString());
        holder.setCurrentSetting(settingOptions.get(position));
    }

    @Override
    public int getItemCount() {
        return settingOptions.size();
    }
}

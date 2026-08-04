package com.matilfa.twentyquestions.settings.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.settings.SettingOption;

public class SettingsItemViewHolder extends RecyclerView.ViewHolder {
    private TextView settingText;
    private SettingOption currentSetting;
    public SettingsItemViewHolder(@NonNull View itemView, OnSettingSelectedListener onSettingSelectedListener) {
        super(itemView);
        settingText = itemView.findViewById(R.id.settingListItemText_tv);

        itemView.setOnClickListener(v -> {
            onSettingSelectedListener.onSettingSelected(currentSetting);
        });
    }

    public TextView getSettingText() {
        return settingText;
    }

    public SettingOption getCurrentSetting() {
        return currentSetting;
    }

    public void setCurrentSetting(SettingOption currentSetting) {
        this.currentSetting = currentSetting;
    }
}

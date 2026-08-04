package com.matilfa.twentyquestions.settings;

public enum SettingOption {
    EDIT_SESSION("Edit sessions"),
    EDIT_USER("Edit users");

    private final String prettyName;

    SettingOption(String prettyName) {
        this.prettyName = prettyName;
    }

    @Override
    public String toString() {
        return prettyName;
    }
}

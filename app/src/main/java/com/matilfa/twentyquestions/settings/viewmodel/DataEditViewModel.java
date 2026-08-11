package com.matilfa.twentyquestions.settings.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;
import com.matilfa.twentyquestions.data.sessions.Session;
import com.matilfa.twentyquestions.data.sessions.SessionRepository;
import com.matilfa.twentyquestions.data.users.User;
import com.matilfa.twentyquestions.data.users.UserRepository;
import com.matilfa.twentyquestions.settings.SettingOption;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DataEditViewModel extends ViewModel {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    private MutableLiveData<List<Session>> allSessions = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<User>> allUsers = new MutableLiveData<>(new ArrayList<>());

    @Inject
    public DataEditViewModel(@NonNull UserRepository userRepository, @NonNull SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    /**
     * Initializes the list of entities for the corresponding setting, decided by the {@code SettingOption}.
     * The {@code SettingOption} reflects which setting the user has selected, and the list of entities will
     * be populated with the corresponding entity type.
     * @param settingOptionForCurrentSettings
     */
    public void initEntityList(@NonNull SettingOption settingOptionForCurrentSettings) {
        switch (settingOptionForCurrentSettings) {
            case EDIT_USER -> TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {
                allUsers.postValue(userRepository.getAllUsers());
            });
            case EDIT_SESSION -> TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {
                allSessions.postValue(sessionRepository.getAllSessions());
            });
        }
    }

    public MutableLiveData<List<Session>> getAllSessions() {
        return allSessions;
    }

    public MutableLiveData<List<User>> getAllUsers() {
        return allUsers;
    }
}

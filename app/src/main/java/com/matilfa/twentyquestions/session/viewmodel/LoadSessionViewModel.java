package com.matilfa.twentyquestions.session.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.matilfa.twentyquestions.data.sessions.Session;
import com.matilfa.twentyquestions.data.sessions.SessionRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoadSessionViewModel extends ViewModel {
    private final SessionRepository sessionRepository;
    private LiveData<List<Session>> allSessions;

    @Inject
    public LoadSessionViewModel(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
        allSessions = sessionRepository.getAllSessions();
    }

    public LiveData<List<Session>> getAllSessions() {
        return allSessions;
    }
}

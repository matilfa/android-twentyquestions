package com.matilfa.twentyquestions.session.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;
import com.matilfa.twentyquestions.data.sessions.Session;
import com.matilfa.twentyquestions.data.sessions.SessionRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoadSessionViewModel extends ViewModel {
    private final SessionRepository sessionRepository;
    private MutableLiveData<List<Session>> allSessions = new MutableLiveData<>(new ArrayList<>());

    @Inject
    public LoadSessionViewModel(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
        TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {
            allSessions.postValue(sessionRepository.getAllSessions());
        });
    }

    public MutableLiveData<List<Session>> getAllSessions() {
        return allSessions;
    }
}

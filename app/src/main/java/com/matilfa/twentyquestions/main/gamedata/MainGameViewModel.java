package com.matilfa.twentyquestions.main.gamedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.matilfa.twentyquestions.data.questions.Question;
import com.matilfa.twentyquestions.data.questions.QuestionsRepository;
import com.matilfa.twentyquestions.data.sessions.Session;
import com.matilfa.twentyquestions.data.sessions.SessionRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainGameViewModel extends ViewModel {
    private LiveData<List<Question>> allQuestions;
    private MutableLiveData<List<Question>> questionsAsked;
    private final QuestionsRepository questionsRepository;
    private final SessionRepository sessionRepository;
    private Session activeSession;

    @Inject
    public MainGameViewModel(@NonNull QuestionsRepository questionsRepository, @NonNull SessionRepository sessionRepository, Session activeSession) {
        this.questionsRepository = questionsRepository;
        this.sessionRepository = sessionRepository;
        this.activeSession = activeSession;

        if (activeSession != null && activeSession.sessionId != null) {
            initSessionData();
        }
        else {
            allQuestions = questionsRepository.getQuestions();
            questionsAsked = new MutableLiveData<List<Question>>();
        }
    }


    public MainGameViewModel(QuestionsRepository questionsRepository, SessionRepository sessionRepository) {
        this(questionsRepository, sessionRepository, null);
    }

    private void initSessionData() {
        //Todo: get questions from db for session
    }


}

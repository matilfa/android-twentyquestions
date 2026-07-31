package com.matilfa.twentyquestions.main.gamedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;
import com.matilfa.twentyquestions.data.questions.Question;
import com.matilfa.twentyquestions.data.questions.QuestionsRepository;
import com.matilfa.twentyquestions.data.sessions.Session;
import com.matilfa.twentyquestions.data.sessions.SessionRepository;
import com.matilfa.twentyquestions.data.sessions.SessionWithAskedQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainGameViewModel extends ViewModel {
    private MutableLiveData<List<Question>> allQuestions;

    private MutableLiveData<List<Question>> sessionQuestions = new MutableLiveData<>();
    private MutableLiveData<List<Question>> questionsAsked = new MutableLiveData<>(new ArrayList<Question>());
    private final QuestionsRepository questionsRepository;
    private final SessionRepository sessionRepository;
    private Session activeSession;

    @Inject
    public MainGameViewModel(@NonNull QuestionsRepository questionsRepository, @NonNull SessionRepository sessionRepository) {
        this.questionsRepository = questionsRepository;
        this.sessionRepository = sessionRepository;
        questionsRepository.initDatabase();

//        questionsAsked = new MutableLiveData<>(new ArrayList<Question>());
        allQuestions = questionsRepository.getQuestions();

    }

    public MutableLiveData<List<Question>> getAllQuestions() {
        return allQuestions;
    }

    public void setActiveSession(Long sessionId) {
        TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {
            activeSession = sessionRepository.getSessionById(sessionId);
            initSessionData(sessionId);
        });
    }

    private void initSessionData(Long sessionId) {
        SessionWithAskedQuestions savedSession = sessionRepository
                .getSavedSessionWithAskedQuestions(sessionId);

        questionsAsked.postValue(savedSession.askedQuestions);
        var remainingQs = new ArrayList<>(allQuestions.getValue());
        remainingQs.removeAll(savedSession.askedQuestions);

        sessionQuestions.postValue(remainingQs);

    }

    public void setupNonSessionMode() {
        sessionQuestions.postValue(allQuestions.getValue());
    }


    public Question generateRandomQuestion() {
        int randomNo = ThreadLocalRandom
                .current()
                .nextInt(0, sessionQuestions.getValue().size() + 1);
        return sessionQuestions.getValue().get(randomNo);
    }

    public void registerAskedQuestion(Question question) {
        TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {

            if (activeSession != null) {
                var successfulInsert = sessionRepository
                        .registerQuestionAskedInSession(question.questionId, activeSession.sessionId);

                if (!successfulInsert) {
                    throw new RuntimeException("Something went wrong when registering asked question.");
                }
            }

            List<Question> askedQsUpdate = questionsAsked.getValue();
            askedQsUpdate.add(question);
            questionsAsked.postValue(askedQsUpdate);

            List<Question> remainingQs = sessionQuestions.getValue();
            remainingQs.remove(question);
            sessionQuestions.postValue(remainingQs);
        });
    }
}

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
    private MutableLiveData<List<Question>> questionsAsked;
    private final QuestionsRepository questionsRepository;
    private final SessionRepository sessionRepository;
    private MutableLiveData<Session> activeSession = new MutableLiveData<Session>();

    @Inject
    public MainGameViewModel(@NonNull QuestionsRepository questionsRepository, @NonNull SessionRepository sessionRepository) {
        this.questionsRepository = questionsRepository;
        this.sessionRepository = sessionRepository;
        questionsRepository.initDatabase();

        questionsAsked = new MutableLiveData<>(new ArrayList<Question>());
        allQuestions = questionsRepository.getQuestions();

//        questionGameRepository = new QuestionGameRepository()
        //        this.activeSession = ;

//        if (activeSession != null && activeSession.sessionId != null && activeSession.sessionId > 0) {
//            initSessionData();
//        }
    }

    public MutableLiveData<Session> getActiveSession() {
        return activeSession;
    }

    public void setActiveSession(Long sessionId) {
        var session = sessionRepository.getSessionById(sessionId);
        activeSession.setValue(session.getValue());
        initSessionData();
    }

    private void initSessionData() {
        SessionWithAskedQuestions savedSession = sessionRepository
                .getSavedSessionWithAskedQuestions(activeSession.getValue().sessionId);

        //todo Fix better
        questionsAsked.setValue(savedSession.askedQuestions);
        allQuestions.getValue().removeAll(savedSession.askedQuestions);

    }


    public Question generateRandomQuestion() {
        int randomNo = ThreadLocalRandom
                .current()
                .nextInt(0, allQuestions.getValue().size() + 1);
        return allQuestions.getValue().get(randomNo);
    }

    public void registerAskedQuestion(Question question) {
        TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {
            var successfulInsert = sessionRepository
                    .registerQuestionAskedInSession(question.questionId, activeSession.getValue().sessionId);

            if (!successfulInsert) {
                throw new RuntimeException("Something went wrong when registering asked question.");
            }
            List<Question> askedQsUpdate = questionsAsked.getValue();
            askedQsUpdate.add(question);
            questionsAsked.setValue(askedQsUpdate);

            List<Question> remainingQs = allQuestions.getValue();
            remainingQs.remove(question);
            allQuestions.setValue(remainingQs);
        });
    }
}

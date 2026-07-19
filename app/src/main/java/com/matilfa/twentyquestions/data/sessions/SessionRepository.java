package com.matilfa.twentyquestions.data.sessions;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Transaction;

import com.matilfa.twentyquestions.data.TwentyQuestionsDatabase;
import com.matilfa.twentyquestions.data.users.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class SessionRepository {
    private final Context context;
    private final SessionDao sessionDao;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Inject
    public SessionRepository(@ApplicationContext Context context, SessionDao sessionDao) {
        this.context = context;
        this.sessionDao = sessionDao;
    }


    public interface InsertCallback {
        void onInsertComplete(Session newSession);
    }

    public LiveData<List<Session>> getAllSessions() {
        return sessionDao.getAll();
    }

    public LiveData<Session> getSessionByName(@NonNull String sessionName) {
        return sessionDao.getByName(sessionName);
    }

    public LiveData<Session> getSessionById(Long sessionId) {
        return sessionDao.getById(sessionId);
    }

    /**
     * Inserts a new session into the database, and insert entries into the junction table for each
     * user in the list.
     * This is done within a transaction, which will rollback in case of some failure in the process.
     *
     * @param sessionName
     * @param usersInSession
     * @return
     */
    @Transaction
    public void createSession(String sessionName, List<User> usersInSession) {
        TwentyQuestionsDatabase.databaseWriteExecutor.execute(() -> {
            Session session = new Session();
            session.name = sessionName;
            session.sessionId = sessionDao.insertSession(session);

            List<UserSessionCrossRef> crossRefs = new ArrayList<>();
            for (User user :
                    usersInSession) {
                var cr = new UserSessionCrossRef();
                cr.sessionId = session.sessionId;
                cr.userId = user.userId;

                crossRefs.add(cr);
            }
            sessionDao.insertAllUserCrossRefs(crossRefs);

        });
    }

    /**
     * Get a specified session from database, containing a member list of all questions asked in that session.
     *
     * @param sessionId
     * @return
     */
    public SessionWithAskedQuestions getSavedSessionWithAskedQuestions(Long sessionId) {
        return sessionDao.getSessionByIdWithAskedQuestions(sessionId);
    }

    @Transaction
    public boolean registerQuestionAskedInSession(Long questionId, Long sessionId) {
        QuestionSessionCrossRef crossRef = new QuestionSessionCrossRef();
        crossRef.questionId = questionId;
        crossRef.sessionId = sessionId;

        Long rowsInserted = sessionDao.insertQuestionSessionCrossRef(crossRef);
        return rowsInserted > 0;
    }
}

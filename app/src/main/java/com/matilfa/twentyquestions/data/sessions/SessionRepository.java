package com.matilfa.twentyquestions.data.sessions;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Transaction;

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

    @Inject
    public SessionRepository(@ApplicationContext Context context, SessionDao sessionDao) {
        this.context = context;
        this.sessionDao = sessionDao;
    }

    public Session getSessionByName(@NonNull String sessionName) {
        return sessionDao.getByName(sessionName);
    }

    /**
     * Inserts a new session into the database, and insert entries into the junction table for each
     * user in the list.
     * This is done within a transaction, which will rollback in case of some failure in the process.
     * @param sessionName
     * @param usersInSession
     * @return
     */
    @Transaction
    public Session createSession(String sessionName, List<User> usersInSession) {
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

        return session;
    }
}

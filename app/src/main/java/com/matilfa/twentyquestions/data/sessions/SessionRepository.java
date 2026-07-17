package com.matilfa.twentyquestions.data.sessions;

import android.content.Context;

import androidx.annotation.NonNull;

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
}

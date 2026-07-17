package com.matilfa.twentyquestions.data.sessions;

import androidx.room.Entity;

@Entity(primaryKeys = {"userId", "sessionId"})
public class UserSessionCrossRef {
    public int userId;
    public int sessionId;
}

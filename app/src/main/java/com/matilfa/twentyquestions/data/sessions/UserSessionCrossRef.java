package com.matilfa.twentyquestions.data.sessions;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"userId", "sessionId"})
public class UserSessionCrossRef {
    @NonNull
    Long userId;
    @NonNull
    public Long sessionId;
}

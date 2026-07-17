package com.matilfa.twentyquestions.data.sessions;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.matilfa.twentyquestions.data.users.User;

import java.util.List;

public class SessionWithUsers {
    @Embedded
    public Session session;

    @Relation(
            parentColumn = "sessionId",
            entityColumn = "userId",
            associateBy = @Junction(UserSessionCrossRef.class)
    )
    public List<User> users;
}

package com.matilfa.twentyquestions.data.sessions;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"questionId", "sessionId"})
public class QuestionSessionCrossRef {
    @NonNull
    public Long questionId;
    @NonNull
    public Long sessionId;
}

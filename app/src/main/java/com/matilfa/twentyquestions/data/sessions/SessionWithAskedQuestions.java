package com.matilfa.twentyquestions.data.sessions;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.matilfa.twentyquestions.data.questions.Question;

import java.util.List;

public class SessionWithAskedQuestions {
    @Embedded
    public Session session;
    @Relation(
            parentColumn = "sessionId",
            entityColumn = "questionId",
            associateBy = @Junction(QuestionSessionCrossRef.class)
    )
    public List<Question> askedQuestions;
}

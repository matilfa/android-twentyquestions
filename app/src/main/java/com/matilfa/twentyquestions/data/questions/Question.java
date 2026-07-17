package com.matilfa.twentyquestions.data.questions;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity class for a question object.
 */
@Entity
public class Question {
    @PrimaryKey(autoGenerate = true)
    public Long questionId;

    @ColumnInfo(name = "text")
    public String text;

}

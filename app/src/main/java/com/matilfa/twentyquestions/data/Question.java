package com.matilfa.twentyquestions.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question {
    @PrimaryKey
    @ColumnInfo(name = "Question_id")
    public int id;

    @ColumnInfo(name = "Question_text")
    public String text;

}

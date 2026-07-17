package com.matilfa.twentyquestions.data.sessions;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity class for a session. To be used as a savepoint for users, so they can continue playing
 * at a later time.
 */
@Entity
public class Session {
    @PrimaryKey(autoGenerate = true)
    public Long sessionId;

    @ColumnInfo(name = "name")
    public String name;
}

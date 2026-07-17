package com.matilfa.twentyquestions.data.users;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * Entity class for a user. A user can be added to a {@code Session}.
 */
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int userId;

    @ColumnInfo(name = "name")
    public String name;
}

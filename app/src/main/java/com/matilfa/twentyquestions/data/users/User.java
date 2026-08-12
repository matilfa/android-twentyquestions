package com.matilfa.twentyquestions.data.users;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * Entity class for a user. A user can be added to a {@code Session}.
 */
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public Long userId;

    @ColumnInfo(name = "name")
    public String name;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }

    @Override
    public String toString() {
        return name;
    }
}

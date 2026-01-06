package com.matilfa.twentyquestions.data.sessions;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Session.class}, version = 1)
public abstract class SessionDatabase extends RoomDatabase {
    public abstract SessionDao sessionDao();
}

package com.example.projetoandroid.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projetoandroid.model.Local;
import com.example.projetoandroid.model.Photo;

@Database(entities = {Local.class, Photo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LocalDao localDao();
    public abstract PhotoDao photoDao();

    private static AppDatabase INSTANCE;
    public static synchronized AppDatabase getInstance(Context ctx) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    ctx.getApplicationContext(),
                    AppDatabase.class, "mytour-db"
            ).fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}

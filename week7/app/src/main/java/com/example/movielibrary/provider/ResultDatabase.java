package com.example.movielibrary.provider;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Result.class}, version = 1)
public abstract class ResultDatabase extends RoomDatabase {

    public static final String RESULT_DATABASE_NAME = "result_database";

    public abstract ResultDao resultDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile ResultDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ResultDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ResultDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ResultDatabase.class, RESULT_DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}


package com.example.todolist.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todolist.model.Notification;

/**
 * This is the notification database, it utilizes multi threading to handle database activities
 */

@Database(entities = {Notification.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase INSTANCE;
    public abstract  NoteDAO dao();

    public static synchronized NoteDatabase getInstance(Context context)
    {
        if(INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database").fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }
        return INSTANCE;
    }

    /**
     * Populates DB on start up, this method was not used.
     */
    private  static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDB(INSTANCE).execute();
        }
    };

    /** Logic for populating database on startup.
     *
     */
    private static class populateDB extends AsyncTask<Void,Void,Void>
    {
        private NoteDAO noteDAO;

        private populateDB(NoteDatabase db)
        {
           noteDAO = db.dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }


}

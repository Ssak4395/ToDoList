package com.example.todolist.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.todolist.model.Notification;

import java.util.List;

/**
 * A layer of abstraction for handling database tasks.
 */
public class NoteRepository {

    private NoteDAO noteDAO;
    private LiveData<List<Notification>> allNotes;

    /**
     *
     * @param context
     */
    public NoteRepository(Context context)
    {
        NoteDatabase database = NoteDatabase.getInstance(context);
        noteDAO = database.dao();
        allNotes = noteDAO.getAllNotifications();
    }

    /**
     * Inserts Item to database
     * @param notification
     */
    public void insert(Notification notification)
    {
        new InsertNoteAsyncTask(noteDAO).execute(notification);
    }
    /**
     * updates previous item in database
     * @param notification
     */
    public void update(Notification notification)
    { new UpdateNoteAsyncTask(noteDAO).execute(notification); }

    /**
     * Deletes item in database
     * @param notification
     */
    public void delete(Notification notification)
    {
        new DeleteNoteAsyncTask(noteDAO).execute(notification);
    }

    /**
     *
     * @return all notes in database
     */
    public LiveData<List<Notification>> getAllNotes() {
        return allNotes;
    }

    /**
     * Performs Asynchronous insertion task
     */
    private static class InsertNoteAsyncTask extends AsyncTask<Notification,Void,Void>{
        private NoteDAO noteDAO;

        private InsertNoteAsyncTask(NoteDAO noteDAO)
        {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Notification... notifications) {
           noteDAO.insert(notifications[0]);  // Inserts element in background.
           return null;
        }
    }

    /**
     * Performs asynchronous deletion task
     */
    private static class DeleteNoteAsyncTask extends AsyncTask<Notification,Void,Void>{
        private NoteDAO noteDAO;

        private DeleteNoteAsyncTask(NoteDAO noteDAO)
        {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Notification... notifications) {
            noteDAO.delete(notifications[0]);
            return null;
        }
    }
    /**
     * Performs asynchronous update task
     */
    private static class UpdateNoteAsyncTask extends AsyncTask<Notification,Void,Void>{
        private NoteDAO noteDAO;

        private UpdateNoteAsyncTask(NoteDAO noteDAO)
        {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Notification... notifications) {
            noteDAO.update(notifications[0]);  // Computes task in background to avoid load on main activity
            return null;
        }
    }
}

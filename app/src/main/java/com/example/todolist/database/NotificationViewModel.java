package com.example.todolist.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolist.model.Notification;

import java.util.List;


public class NotificationViewModel extends AndroidViewModel {

    private  NoteRepository repository;
    private LiveData<List<Notification>> allNotes;


    public NotificationViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    /**
     *  Insert items into database
     * @param notification
     */
    public void insert(Notification notification)
    {
        repository.insert(notification);
    }

    /**
     * Update item previously in database
     * @param notification
     */
    public void update(Notification notification)
    {
        repository.update(notification);
    }

    /**
     * Delete notification in database
     * @param notification
     */
    public  void delete(Notification notification)
    {
        repository.delete(notification);
    }

    /**
     * Return all notes in database
     * @return
     */
    public LiveData<List<Notification>> getAllNotes()
    {
        return allNotes;
    }


}

package com.example.todolist.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist.model.Notification;

import java.util.List;

/**
 * Note Database Object Interface, is responsible for adding a layer of abstraction over
 * the SQLite Database
 */
@Dao
public interface NoteDAO {

    /**
     * SQL statements for Insert notification
     * @param notification
     */
    @Insert
    void insert(Notification notification);
    /**
     * SQL statements for Update notification
     * @param notification
     */
    @Update
    void update(Notification notification);
    /**
     * SQL statements for Delete notification
     * @param notification
     */
    @Delete
    void delete(Notification notification);

    /**
     *
     * @return All Notes
     */
    @Query("SELECT * From notification_table order by date DESC")
    LiveData<List<Notification>> getAllNotifications();
}

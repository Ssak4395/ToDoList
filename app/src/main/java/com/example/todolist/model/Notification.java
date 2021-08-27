package com.example.todolist.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;


/**
 * JAVA POJO Object, for handling notification data
 */
@Entity(tableName = "notification_table")
public class Notification implements Serializable {

    /**
     * Primary Key
     * @return
     */
    public int getNid() {
        return nid;
    }

    /**
     * Sets Primary Key
     * @param nid
     */
    public void setNid(int nid) {
        this.nid = nid;
    }

    @PrimaryKey(autoGenerate = true)
    private int nid;

    @ColumnInfo(name = "notif_event")
    private String notifEvent;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "status")
    private String status;

    /**
     *
     * @return Status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets Status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns notification event string
     * @return
     */
    public String getNotifEvent() {
        return notifEvent;
    }

    /**
     * Sets notification event text
     * @param notifEvent
     */
    public void setNotifEvent(String notifEvent) {
        this.notifEvent = notifEvent;
    }

    /** Returns the date
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Set Date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Default Constructor
     * @param notifEvent
     * @param date
     * @param status
     */
    public Notification(String notifEvent, String date, String status) {
        this.notifEvent = notifEvent;
        this.date = date;
        this.status = status;
    }
}

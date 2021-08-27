package com.example.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todolist.R;
import com.example.todolist.model.Notification;

import java.util.ArrayList;

/**
 * This class is responsible for displaying the custom listview, it is essentially
 * the adapter to the display the layout that was made using the XML file.
 */
public class NotificationAdapter extends ArrayAdapter<Notification> {
    private Context mContext;
    int mResource;

    /**
     *
     * @param context
     * @param resource
     * @param objects
     */
    public NotificationAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Notification> objects) {
        super(context, resource, objects);

        this.mContext = context;

        this.mResource =resource;
    }

    /**
     *  Returns and displays the view
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String eventName = getItem(position).getNotifEvent();

        String eventDate = getItem(position).getDate(); // Assign the variables

        String eventStatus = getItem(position).getStatus(); // Assign the variables

        LayoutInflater layoutInflater = LayoutInflater.from(mContext); // Explode layout

        convertView = layoutInflater.inflate(mResource,parent,false);

        TextView tv1 = convertView.findViewById(R.id.textView1);  // Display text from user

        TextView tv2 = convertView.findViewById(R.id.textView2); // Display text from user

        TextView tv3 = convertView.findViewById(R.id.textView3); // Display text from user

        tv1.setText(eventName); // Display Event Name from User

        tv2.setText(eventStatus); // Display Event Status from user

        tv3.setText(eventDate); // Display event date from user

        notifyDataSetChanged();  // Notify that data has changed.

        return convertView; // Return updated view
    }


}


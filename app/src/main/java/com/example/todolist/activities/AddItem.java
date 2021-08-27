package com.example.todolist.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.database.NotificationViewModel;
import com.example.todolist.model.Notification;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is the Add Item Activity, it allows you to
 * Add a new item to the To-Do List
 */
public class AddItem extends AppCompatActivity {

    private NotificationViewModel notificationViewModel;
    private EditText editText;
    private Button save;
    private Button discard;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_item);

        notificationViewModel = ViewModelProviders.of(this).get(NotificationViewModel.class);


        editText = findViewById(R.id.addtext);

        save = findViewById(R.id.save2);

        discard = findViewById(R.id.discard);

        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent intent = new Intent(context, MainActivity.class);
                                AddItem.this.startActivity(intent);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                ;
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                if(editText.toString() == "" && editText.toString().length() == 0) {
                    builder.setMessage("Are you sure you want to go back").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }
                else
                    builder.setMessage("Discard Item").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEvent();

                Intent intent = new Intent(context,MainActivity.class);

                AddItem.this.startActivity(intent);
            }
        });

    }

    /**
     * Adds an item to the To-Do List, it essentially gets the user text
     * and adds it to the Notification POJO.
     */
    public void addEvent()
    {
        String addToString = editText.getText().toString();  // Gets the text

        if(editText.toString() != "") {  // performs a check to ensure text box isnt empty
            Date sysDate = new Date();

            SimpleDateFormat sysDateF = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"); //Date

            String dateofSystem = sysDateF.format(sysDate);

            Notification notification = new Notification(addToString, dateofSystem, "Created on");

            notificationViewModel.insert(notification);  // Insert notification to the database.

            MainActivity.adapter.notifyDataSetChanged();  // notifes adapter of the dataset change.
        }
    }


}
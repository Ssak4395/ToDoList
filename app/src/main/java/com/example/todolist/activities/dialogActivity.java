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
import android.widget.TextView;

import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.database.NotificationViewModel;
import com.example.todolist.model.Notification;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

/**
 * This is the dialog Activity, this is for editing a already present event
 */
public class dialogActivity extends AppCompatActivity {

    static Stack<Notification> toEdit;
    TextView textView;
    private NotificationViewModel notificationViewModel;
    private Button buttonSave;
    private Button discard;
    private Context context = this;


    /**
     * On start up preload all the elements and display the stage.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        textView = findViewById(R.id.editText);
        buttonSave = findViewById(R.id.save);
        discard = findViewById(R.id.discard);

        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE: // If the user presses yes
                                Intent intent = new Intent(context, MainActivity.class); // then return to main activity
                                dialogActivity.this.startActivity(intent);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE: // else do nothing and let user edit item
                                ;
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Discard current edit").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
        notificationViewModel = ViewModelProviders.of(this).get(NotificationViewModel.class);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(context,MainActivity.class);


           // When user presses save, run business logic to save infomation.
            @Override
            public void onClick(View view) {
                editText();
                dialogActivity.this.startActivity(intent);

            }
        });

    }

    /** Save users edits, and persist them to the database, this method updates the event and resorts it.
     *
     */
    public void editText() {

        Notification notification = (Notification) getIntent().getSerializableExtra("event");

        System.out.println(notification.getNotifEvent());

        notification.setNotifEvent(textView.getText().toString());

        Date sysDate = new Date();
        SimpleDateFormat sysDateF = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        String dateofSystem = sysDateF.format(sysDate);

        notification.setDate(dateofSystem);

        notification.setStatus("Edit Time");

        notificationViewModel.update(notification);

        MainActivity.adapter.notifyDataSetChanged();
    }



}
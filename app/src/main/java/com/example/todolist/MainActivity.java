package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.todolist.adapter.NotificationAdapter;
import com.example.todolist.database.NotificationViewModel;
import com.example.todolist.activities.AddItem;
import com.example.todolist.model.Notification;
import com.example.todolist.activities.dialogActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    private Button explodeActivity;
    public static NotificationAdapter adapter;
    private Context context = this;
    private NotificationViewModel notificationViewModel;
    private ListView mListView;
    ArrayList<Notification> row = new ArrayList();
    private static final String TAG = "MainActivity";


    /**
     * preload the activity and set the stage.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list);

        notificationViewModel = ViewModelProviders.of(this).get(NotificationViewModel.class); // Returns the view model


        notificationViewModel.getAllNotes().observe(this, new Observer<List<Notification>>() {

            @Override
            public void onChanged(List<Notification> notifications) {
                Toast.makeText(getApplicationContext(), "To edit an item simply long press", Toast.LENGTH_LONG);

                if (!notifications.isEmpty()) {
                    row.clear(); // if list view is full remove it, (edge case)
                }
                for (Notification notification : notifications) {
                    row.add(notification);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        adapter = new NotificationAdapter(this, R.layout.adapter_layout, row);
        mListView.setAdapter(adapter);
        explodeActivity = findViewById(R.id.explodeActivity);

        onItemLongClick();
        explodeActivity = findViewById(R.id.explodeActivity);
        explodeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddItemClick(view);
            }
        });
        onItemShortClick();
    }


    /**
     * Displays the add item Activity.
     * @param view
     */
    public void onAddItemClick(View view) {

        Intent intent = new Intent(this, AddItem.class);
        MainActivity.this.startActivity(intent);
    }


    /**
     * When the user long presses an item, launch display  a prompt, if yes remove the element from the view (row adapter)
     * and the database (NotifViewModel)
     */
    public void onItemLongClick() {
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                notificationViewModel.delete(row.get(i));
               row.remove(i);
               adapter.notifyDataSetChanged();
               break;
               case DialogInterface.BUTTON_NEGATIVE:
                   break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return true;
            }
        });
    }


    /**
     * Launch the edit activity, when a user short presses.
     */
    public void onItemShortClick()
    {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, dialogActivity.class);
                intent.putExtra("event",row.get(i));
                MainActivity.this.startActivity(intent);
            }
        });
    }
}





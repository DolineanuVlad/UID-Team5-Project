package com.uid.team5.project.auth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.adapters.AddExpensesAdapter;
import com.uid.team5.project.adapters.CreateGroupAdapter;
import com.uid.team5.project.models.Expense;
import com.uid.team5.project.models.User;
import com.uid.team5.project.shared.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupActivity extends AppCompatActivity {

    AppDataSingleton dataService;
    AlertDialog.Builder builder;
    AlertDialog popup;
    CreateGroupAdapter groupAdapter;
    ArrayList<User> usersToInvite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        dataService = AppDataSingleton.getInstance();

        ListView usersList = findViewById(R.id.create_group_list_view);

        usersToInvite = new ArrayList<>();

         groupAdapter = new CreateGroupAdapter(this,usersToInvite);
        usersList.setAdapter(groupAdapter);

        //hamburger menu
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_group);
        mToolbar.setTitle("Invite members to group");
        setSupportActionBar(mToolbar);

        FloatingActionButton addBtn = findViewById(R.id.floatingActionButton_add_to_group);


        //add popup
        builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.popup_add_user);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup = builder.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_group_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onUserAdded(View v) {
        TextView name = popup.findViewById(R.id.new_user_name);
        TextView email = popup.findViewById(R.id.new_user_email);
        //Spinner icon = popup.findViewById(R.id.new_user_avatar_spinner);

        if (name.equals("") || email.equals("")) {
            popup.dismiss();
            return;
        }

        usersToInvite.add(new User(email.getText().toString(), "" ,name.getText().toString()));
        groupAdapter.notifyDataSetChanged();

        name.setText(null);
        email.setText(null);

        popup.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.create_group_send_invites)
        {
            AlertDialog.Builder builder;

                builder = new AlertDialog.Builder(CreateGroupActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                 builder.setTitle("Hey")
                    .setMessage("Are you sure you want to invite all these users?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(CreateGroupActivity.this, MainActivity.class));
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onPopupCancel(View v) {
        popup.dismiss();
    }
}

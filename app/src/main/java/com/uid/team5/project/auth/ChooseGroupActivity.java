package com.uid.team5.project.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uid.team5.project.AppDataSingleton;
import com.uid.team5.project.R;
import com.uid.team5.project.shared.MainActivity;

public class ChooseGroupActivity extends AppCompatActivity {

    private AppDataSingleton dataService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_group);

        dataService = AppDataSingleton.getInstance();

        TextView link_signup = (TextView) findViewById(R.id.link_signup);
        Button btn_join_group =(Button)findViewById(R.id.btn_join_group);

        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseGroupActivity.this, CreateGroupActivity.class));
            }
        });
        btn_join_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseGroupActivity.this, MainActivity.class));
            }
        });
    }
}

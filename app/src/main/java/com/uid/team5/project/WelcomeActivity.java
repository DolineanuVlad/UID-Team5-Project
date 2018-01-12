package com.uid.team5.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.uid.team5.project.auth.LoginActivity;
import com.uid.team5.project.auth.RegisterActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void loginUser(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void registerUser(View view)
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        AppDataSingleton.loadFromFile(getApplicationContext());
        super.onStart();
    }
}

package com.uid.team5.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.uid.team5.project.auth.LoginActivity;
import com.uid.team5.project.auth.SignupActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void loginUser(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void registerUser(View view)
    {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}

package com.uid.team5.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.uid.team5.project.auth.LoginActivity;
import com.uid.team5.project.auth.RegisterActivity;
import com.uid.team5.project.shared.MainActivity;

import java.util.UUID;

public class WelcomeActivity extends AppCompatActivity {

    private AppDataSingleton dataService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDataSingleton.loadFromFile(getApplicationContext());
        dataService = AppDataSingleton.getInstance();
        UUID userId = dataService.getCurrentUserId();

        if(userId == null)
        {
            setContentView(R.layout.activity_welcome);
        }
        else{
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    public void loginUser(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void registerUser(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}

package com.example.S10174164_Week11Practical;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUserActivity extends AppCompatActivity {
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        dbHandler = new DbHandler(this, null, null, 1);
    }

    public void onCancel(View v) {
        Intent in = new Intent(CreateUserActivity.this, MainActivity.class);
        startActivity(in);
    }

    public void onCreate(View v) {
        EditText etUsername = findViewById(R.id.etNewUser);
        EditText etPassword = findViewById(R.id.etNewPassword);

        String txtUsername = etUsername.getText().toString();
        String txtPassword = etPassword.getText().toString();

        Pattern userPattern = Pattern.compile("^[a-zA-Z0-9]{6,12}$");
        Matcher userMatcher = userPattern.matcher(txtUsername);

        Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*+=])(?=.*[a-zA-Z]).{1,}$");
        Matcher passMatcher = passPattern.matcher(txtPassword);

        if (userMatcher.matches() && passMatcher.matches()) {
            SharedPreferences.Editor editor = getSharedPreferences("MY_GLOBAL_PREFS", MODE_PRIVATE).edit();
            editor.putString("Username", txtUsername);
            editor.putString("Password", txtPassword);
            editor.apply();
            Account a = new Account(txtUsername, txtPassword);
            dbHandler.addAccount(a);
            Toast tt = Toast.makeText(CreateUserActivity.this, "New User Created Successfully", Toast.LENGTH_LONG);
            tt.show();
        }
        else {
            Toast tt = Toast.makeText(CreateUserActivity.this, "Invalid User Creation. Please Try Again", Toast.LENGTH_LONG);
            tt.show();
        }

        Intent in = new Intent(CreateUserActivity.this, MainActivity.class);
        startActivity(in);
    }
}

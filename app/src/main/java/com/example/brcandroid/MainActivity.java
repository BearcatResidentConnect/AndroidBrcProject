package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText userNameEdt, passwordEdt;

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our edit text  and buttons.
        userNameEdt = findViewById(R.id.idEdtUserName);
        passwordEdt = findViewById(R.id.idEdtPassword);

    }

    private boolean CheckAllFields() {
        if (userNameEdt.length() == 0) {
            userNameEdt.setError("Username is required");
            return false;
        }

        if (passwordEdt.length() == 0) {
            passwordEdt.setError("Password is required");
            return false;
        } else if (passwordEdt.length() < 6) {
            passwordEdt.setError("Password must be minimum 6 characters");
            return false;
        }

        // after all validation return true.
        return true;
    }

    public void onClickLogin(View v){

        // store the returned value of the dedicated function which checks
        // whether the entered data is valid or if any fields are left blank.
        isAllFieldsChecked = CheckAllFields();

        if (isAllFieldsChecked) {
            Intent i = new Intent(MainActivity.this, UserHome.class);
            startActivity(i);
        }
    }

    public void onClickRegister(View v){

        //open user register page

        Intent userHomeIntent = new Intent(this, UserRegister.class);
        startActivity(userHomeIntent);
        finish();

    }
}
package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserRegister extends AppCompatActivity {

    private EditText userNameEdt, emailEdt, passwordEdt, cpasswordEdt;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        // initializing our edit text  and buttons.
        userNameEdt = findViewById(R.id.idREdtUserName);
        emailEdt = findViewById(R.id.idREdtEmail);
        passwordEdt = findViewById(R.id.idREdtPassword);
        cpasswordEdt = findViewById(R.id.idRConEdtPassword);
    }

    private boolean CheckAllFields() {
        if (userNameEdt.length() == 0) {
            userNameEdt.setError("Username is required");
            return false;
        }
        if (emailEdt.length() == 0) {
            emailEdt.setError("email is required");
            return false;
        }

        if (passwordEdt.length() == 0) {
            passwordEdt.setError("Password is required");
            return false;
        } else if (passwordEdt.length() < 6) {
            passwordEdt.setError("Password must be minimum 6 characters");
            return false;
        }

        if (cpasswordEdt.length() == 0) {
            cpasswordEdt.setError("Confirm Password is required");
            return false;
        } else if (cpasswordEdt.length() < 6) {
            cpasswordEdt.setError("Confirm Password must be minimum 6 characters");
            return false;
        }
        // after all validation return true.
        return true;
    }

    public void onClickRegister(View v){

        //Redirect to Register page
        isAllFieldsChecked = CheckAllFields();

        if (isAllFieldsChecked) {
            Intent j = new Intent(this, UserRegister.class);
            startActivity(j);
            finish();
        }

    }

    public void onClickLogin(View v){

        //Redirect to Login page
        Intent i = new Intent(UserRegister.this, MainActivity.class);
        startActivity(i);
        finish();

    }
}
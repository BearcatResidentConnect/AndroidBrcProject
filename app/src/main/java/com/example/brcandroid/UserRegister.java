package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
    }

    public void onClickLogin(View v){

        //Redirect to Login page

        Intent userLoginIntent = new Intent(this, MainActivity.class);
        startActivity(userLoginIntent);
        finish();


    }
}
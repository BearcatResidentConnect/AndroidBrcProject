package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLogin(View v){

        // open user home page

        Intent userHomeIntent = new Intent(this, UserHome.class);
        startActivity(userHomeIntent);
        finish();


    }

    public void onClickRegister(View v){

        //open user register page

        Intent userHomeIntent = new Intent(this, UserRegister.class);
        startActivity(userHomeIntent);
        finish();


    }
}
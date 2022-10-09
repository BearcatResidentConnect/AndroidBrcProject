package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
    }

    public void onClickProfileIcon(View v){

        //open user register page

        Intent userProfileIntent = new Intent(this, UserProfile.class);
        startActivity(userProfileIntent);
        //finish(); // allow back press


    }

    public void onClickApplications(View v){

        //open user register page

        Intent userProfileIntent = new Intent(this, UserApplications.class);
        startActivity(userProfileIntent);
        //finish(); // allow back press


    }

    public void onClickListRentals(View v){

        //open user register page

        Intent userProfileIntent = new Intent(this, ListRentals.class);
        startActivity(userProfileIntent);
        //finish(); // allow back press


    }
}
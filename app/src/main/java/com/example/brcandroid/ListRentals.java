package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListRentals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_list_rentals);
    }

    public void onClickRegisterRental(View v){

        //open user register page

        Intent userHomeIntent = new Intent(this, RentalRegister.class);
        startActivity(userHomeIntent);
        finish();

    }
}
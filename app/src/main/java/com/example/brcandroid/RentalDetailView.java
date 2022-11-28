package com.example.brcandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


import java.util.List;

public class RentalDetailView extends AppCompatActivity {

    private TextView propertyNameTV, propertyAddressTV, propertyEmailTV, PropertyDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rental_detail);
        Intent intent = getIntent();
        String fetchID =  intent.getStringExtra("eventID");
        String fetchName =  intent.getStringExtra("propertyName");
        String fetchLocation =  intent.getStringExtra("propertyAdd");
        String fetchEmail =  intent.getStringExtra("propertyEmail");

        propertyNameTV=findViewById(R.id.tvDName);
        propertyAddressTV=findViewById(R.id.tvDAddress);
        propertyEmailTV=findViewById(R.id.tvDEmail);
        PropertyDesc=findViewById(R.id.tvDDesc);

        propertyNameTV.setText(fetchName);
        propertyAddressTV.setText(fetchLocation);
        propertyEmailTV.setText(fetchEmail);
    }


}

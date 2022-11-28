package com.example.brcandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONObject;

import java.util.List;

public class RentalDetailView extends AppCompatActivity {

    private TextView propertyNameTV, propertyAddressTV, propertyEmailTV, PropertyDesc;
    private String fetchID, fetchLocation, fetchName, fetchEmail, username, useremail, firstname, lastname;

    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rental_detail);
        Intent intent = getIntent();
        fetchID =  intent.getStringExtra("eventID");
        fetchName =  intent.getStringExtra("propertyName");
        fetchLocation =  intent.getStringExtra("propertyAdd");
        fetchEmail =  intent.getStringExtra("propertyEmail");

        propertyNameTV=findViewById(R.id.tvDName);
        propertyAddressTV=findViewById(R.id.tvDAddress);
        propertyEmailTV=findViewById(R.id.tvDEmail);
        PropertyDesc=findViewById(R.id.tvDDesc);

        propertyNameTV.setText(fetchName);
        propertyAddressTV.setText(fetchLocation);
        propertyEmailTV.setText(fetchEmail);

        sharedpreferences = getSharedPreferences(
                MainActivity.MyPREFERENCES,
                Context.MODE_PRIVATE
        );

        username = sharedpreferences.getString("USERNAME", "username");
        useremail = sharedpreferences.getString("userEmail", "userEmail");
        firstname = sharedpreferences.getString("userFirstName", "userFirstName");
        lastname = sharedpreferences.getString("userLastName", "userLastName");

    }

    public void onClickSubmit(View v){

        try{

            BrcAPIInterface api = new BrcAPIInterface(RentalDetailView.this);
            JSONObject requestBody = new JSONObject();
            /*
              "subject": "string",
              "rental_name": "string",
              "rental_email": "string",
              "user_email": "string",
              "user_name": "string",
             */
            //
            requestBody.put("subject", "Looking Apartments for Rent");
            requestBody.put("rental_name", fetchName);
            requestBody.put("rental_email", fetchEmail);
            requestBody.put("user_email", useremail);
            requestBody.put("user_name", username);
            //
            api.brcPostAPI("/mail-service",requestBody, new BrcAPIResponse() {
                @Override
                public void onSuccess(JSONObject response) {

                    Toast.makeText(RentalDetailView.this, "Email request Sent", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Exception exception) {

                    Toast.makeText(RentalDetailView.this, "Email request Failed", Toast.LENGTH_SHORT).show();
                    Log.v("Exception: ", "Exception " + exception);


                }

            });

        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(RentalDetailView.this, "Unexpected error occurred", Toast.LENGTH_SHORT).show();
        }finally{

        }
        finish();

    }




}

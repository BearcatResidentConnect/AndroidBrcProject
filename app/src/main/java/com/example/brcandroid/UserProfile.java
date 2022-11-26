package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class UserProfile extends AppCompatActivity {

    private EditText emailtv, firstnametv, lastnametv;
    private TextView unametv;
    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_user_profile);
        sharedpreferences = getSharedPreferences(
                MainActivity.MyPREFERENCES,
                Context.MODE_PRIVATE
        );
        String username = sharedpreferences.getString("USERNAME", "username");
        String email = sharedpreferences.getString("userEmail", "userEmail");
        String firstname = sharedpreferences.getString("userFirstName", "userFirstName");
        String lastname = sharedpreferences.getString("userLastName", "userLastName");

        //init
        //findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        unametv = findViewById(R.id.pUserNameTV);
        emailtv = findViewById(R.id.pEmailTV);
        firstnametv = findViewById(R.id.pFirstNameTV);
        lastnametv = findViewById(R.id.pLastNameTV);
        try{

            unametv.setText(username);
            emailtv.setText(email);
            firstnametv.setText(firstname);
            lastnametv.setText(lastname);
        }catch (Exception e){
            Log.d("Error", "Error");
        }


    }

    private boolean CheckAllFields() {
        if (emailtv.length() == 0) {
            emailtv.setError("Email is required");
            return false;
        }

        if (firstnametv.length() == 0) {
            firstnametv.setError("First Name is required");
            return false;
        } else if (lastnametv.length() == 0) {
            lastnametv.setError("Last Name is Required");
            return false;
        }

        // after all validation return true.
        return true;
    }

    public void onClickUpdate(View v){


        boolean isAllFieldsChecked = CheckAllFields();

        if (!isAllFieldsChecked){
            return;
        }

        //findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);

        try{

            String username = unametv.getText().toString();
            String email = emailtv.getText().toString();
            String fname = firstnametv.getText().toString();
            String lname = lastnametv.getText().toString();

            BrcAPIInterface api = new BrcAPIInterface(UserProfile.this);
            JSONObject requestBody = new JSONObject();
            //requestBody.put("content-type", "application/json");
            /*
              "user_name" : "string",
              "first_name": "string",
              "last_name": "string",
              "email": "string",
             */
            requestBody.put("user_name", username);
            requestBody.put("first_name", fname);
            requestBody.put("last_name", lname);
            requestBody.put("email", email);
            api.brcPutAPI("/user",requestBody, new BrcAPIResponse() {
                @Override
                public void onSuccess(JSONObject response) {

                    Toast.makeText(UserProfile.this,"Profile Update Successful ", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    try{
                        editor.putString("userEmail", email);
                        editor.putString("userFirstName", fname);
                        editor.putString("userLastName", lname);
                    }catch (Exception e){
                        Log.v("Error", "Error");
                    }finally{
                        editor.commit();
                    }

                }

                @Override
                public void onFailure(Exception exception) {

                    Toast.makeText(UserProfile.this, "Profile Update Failed", Toast.LENGTH_SHORT).show();
                    Log.v("Exception: ", "Exception " + exception);
                    //findViewById(R.id.loadingPanel).setVisibility(View.GONE);

                }

            });

        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(UserProfile.this, "Unexpected error occurred", Toast.LENGTH_SHORT).show();
        }finally{
            //findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        }

    }

}
package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class UserRegister extends AppCompatActivity {

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
    }

    private boolean CheckAllFields() {

        EditText userNameET = findViewById(R.id.idREdtUserName);
        EditText userPasswordET = findViewById(R.id.idREdtPassword);
        EditText userEmailEt = findViewById(R.id.idREdtEmail);
        EditText userFNET = findViewById(R.id.idRFName);
        EditText userLNET = findViewById(R.id.idRLName);

        if (userNameET.length() == 0) {
            userNameET.setError("Username is required");
            return false;
        }
        if (userEmailEt.length() == 0) {
            userEmailEt.setError("email is required");
            return false;
        }

        if (userFNET.length() == 0) {
            userFNET.setError("First Name is required");
            return false;
        }
        if (userLNET.length() == 0) {
            userLNET.setError("Last Name is required");
            return false;
        }

        if (userPasswordET.length() == 0) {
            userPasswordET.setError("Password is required");
            return false;
        } else if (userPasswordET.length() < 6) {
            userPasswordET.setError("Password must be minimum 6 characters");
            return false;
        }

        // after all validation return true.
        return true;
    }

    public void onClickRegister(View v){

        try{
            isAllFieldsChecked = CheckAllFields();

            if (!isAllFieldsChecked){
                return;
            }
            //
            EditText userNameET = findViewById(R.id.idREdtUserName);
            EditText userPasswordET = findViewById(R.id.idREdtPassword);
            EditText userEmailEt = findViewById(R.id.idREdtEmail);
            EditText userFNET = findViewById(R.id.idRFName);
            EditText userLNET = findViewById(R.id.idRLName);

            String userName = userNameET.getText().toString();
            String password = userPasswordET.getText().toString();
            String email = userEmailEt.getText().toString();
            String first_name = userFNET.getText().toString();
            String last_name = userLNET.getText().toString();

            BrcAPIInterface api = new BrcAPIInterface(UserRegister.this);
            JSONObject requestBody = new JSONObject();
            // Request Body
            /*
              "user_name": "string",
              "password": "string",
              "first_name": "string",
              "last_name": "string",
              "email": "string",
             */
            requestBody.put("user_name", userName);
            requestBody.put("password", password);
            requestBody.put("first_name", first_name);
            requestBody.put("last_name", last_name);
            requestBody.put("email", email);
            //
            api.brcPostAPI("/user",requestBody, new BrcAPIResponse() {
                @Override
                public void onSuccess(JSONObject response) {

                    Toast.makeText(UserRegister.this,"User Account Created ", Toast.LENGTH_SHORT).show();

                    //
                    Intent i = new Intent(UserRegister.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }

                @Override
                public void onFailure(Exception exception) {

                    Toast.makeText(UserRegister.this, "User Account Creation Failed", Toast.LENGTH_SHORT).show();
                    Log.v("Exception: ", "Exception " + exception);


                }

            });

        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(UserRegister.this, "Unexpected error occurred", Toast.LENGTH_SHORT).show();
        }finally{

        }

    }

    public void onClickLogin(View v){

        //Redirect to Login page
        Intent i = new Intent(UserRegister.this, MainActivity.class);
        startActivity(i);
        finish();

    }
}
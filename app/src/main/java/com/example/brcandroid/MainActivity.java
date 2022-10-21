package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

       try{
           EditText userNameET = findViewById(R.id.idEdtUserName);
           EditText userPasswordET = findViewById(R.id.idEdtPassword);

           String userName = userNameET.getText().toString();
           String password = userPasswordET.getText().toString();

           BrcAPIInterface api = new BrcAPIInterface(MainActivity.this, "http://192.168.1.35:5000/api");
           JSONObject requestBody = new JSONObject();
           //requestBody.put("content-type", "application/json");
           requestBody.put("user_name", userName);
           requestBody.put("password", password);
            api.brcPostAPI("/users/auth",requestBody, new BrcAPIResponse() {
                @Override
                public void onSuccess(JSONObject response) {

                    Toast.makeText(MainActivity.this,"Log in Successful ", Toast.LENGTH_SHORT).show();
                    //
                    Intent i = new Intent(MainActivity.this, UserHome.class);
                    startActivity(i);

                }

                @Override
                public void onFailure(Exception exception) {

                    Toast.makeText(MainActivity.this, "Some Error", Toast.LENGTH_SHORT).show();
                    Log.v("Exception: ", "Exception " + exception);

                }

            });

       }catch (Exception e) {
           e.printStackTrace();
           Toast.makeText(MainActivity.this, "Unexpected error occurred", Toast.LENGTH_SHORT).show();
       }

    }

    public void onClickRegister(View v){

        //open user register page

        Intent userHomeIntent = new Intent(this, UserRegister.class);
        startActivity(userHomeIntent);
        finish();

    }
}
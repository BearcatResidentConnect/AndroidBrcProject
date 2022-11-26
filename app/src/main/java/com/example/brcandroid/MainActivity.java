package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences;

//import android.widget.TextView;
import android.widget.Toast;


//import com.android.volley.AuthFailureError;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String USERNAME = "USERNAME";
    public static final String userEmail = "userEmail";
    public static final String userFirstName = "userFirstName";
    public static final String userLastName = "userLastName";

    SharedPreferences sharedpreferences;

    //public static final String Email = "emailKey";
    private EditText userNameEdt, passwordEdt;

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        // initializing our edit text  and buttons.
        userNameEdt = findViewById(R.id.idEdtUserName);
        passwordEdt = findViewById(R.id.idEdtPassword);

        //
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

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

//        try{
//            BrcAPIInterface ap = new BrcAPIInterface(MainActivity.this);
//            ap.brcGetListAPI ("/rental-postings", new BrcAPIListResponse() {
//
//
//                @Override
//                public void onSuccess(JSONArray response) {
//
//                    Toast.makeText(MainActivity.this,"Log in Successful ", Toast.LENGTH_SHORT).show();
//
//                    Log.v("MSG " , response.toString());
//
//                }
//
//                @Override
//                public void onFailure(Exception exception) {
//
//                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
//                    Log.v("Exception: ", "Exception " + exception);
//
//
//                }
//
//            });
//        } catch(Exception e){
//
//        }

        // store the returned value of the dedicated function which checks
        // whether the entered data is valid or if any fields are left blank.

        isAllFieldsChecked = CheckAllFields();

        if (!isAllFieldsChecked){
            return;
        }

        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);

       try{
           EditText userNameET = findViewById(R.id.idEdtUserName);
           EditText userPasswordET = findViewById(R.id.idEdtPassword);

           String userName = userNameET.getText().toString();
           String password = userPasswordET.getText().toString();

           BrcAPIInterface api = new BrcAPIInterface(MainActivity.this);
           JSONObject requestBody = new JSONObject();
           //requestBody.put("content-type", "application/json");
           requestBody.put("user_name", userName);
           requestBody.put("password", password);
            api.brcPostAPI("/users/auth",requestBody, new BrcAPIResponse() {
                @Override
                public void onSuccess(JSONObject response) {

                    Toast.makeText(MainActivity.this,"Log in Successful ", Toast.LENGTH_SHORT).show();
                    // Save Session Data
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    try{
                        editor.putString(USERNAME, response.getString("username"));
                        editor.putString(userEmail, response.getString("email"));
                        editor.putString(userFirstName, response.getString("firstname"));
                        editor.putString(userLastName, response.getString("lastname"));
                    }catch (Exception e){
                        Log.v("Error", "Error");
                    }finally{
                        editor.commit();
                    }

                    //
                    Intent i = new Intent(MainActivity.this, UserHome.class);
                    startActivity(i);

                }

                @Override
                public void onFailure(Exception exception) {

                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    Log.v("Exception: ", "Exception " + exception);


                }

            });

       }catch (Exception e) {
           e.printStackTrace();
           Toast.makeText(MainActivity.this, "Unexpected error occurred", Toast.LENGTH_SHORT).show();
       }finally{
           findViewById(R.id.loadingPanel).setVisibility(View.GONE);
       }

    }

    public void onClickRegister(View v){

        //open user register page

        Intent userHomeIntent = new Intent(this, UserRegister.class);
        startActivity(userHomeIntent);
        finish();

    }
}
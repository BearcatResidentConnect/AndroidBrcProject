package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class RentalRegister extends AppCompatActivity {

    private EditText userNameEdt, emailEdt, passwordEdt, cpasswordEdt;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_register);
    }

}